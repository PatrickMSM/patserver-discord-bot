package tk.patsite.Patserverdiscordbot;

import javax.annotation.CheckReturnValue;
import java.io.*;
import java.util.Random;
import java.util.concurrent.CompletableFuture;


public final class OnTheFlyCompiler {
    private final static Random RAND = new Random();
    private final static String NEWLINE = System.getProperty("line.separator");

    private static String getCode(String input, String className) {
        return "public class " + className+ "{" + NEWLINE +
                "public static void main(String[] args) {" + NEWLINE +
                input + "}}";
    }

    private final File uncompiledFile, rootDir;
    private final String name;
    private File compiledFile;



    public OnTheFlyCompiler(final String name, final String contentNoClass) throws IOException {
        this.name = name;
        long tempId = RAND.nextLong();
        rootDir = new File(System.getProperty("java.io.tmpdir"), name + tempId);
        rootDir.mkdirs();
        uncompiledFile = new File(rootDir, name+".java");
        uncompiledFile.createNewFile();
        FileUtil.write(uncompiledFile, getCode(contentNoClass, name));
    }

    @CheckReturnValue
    public final CompletableFuture<Boolean> compile() throws IOException, InterruptedException {
        final CompletableFuture<Boolean> f = new CompletableFuture<>();
        Process compileProc = new ProcessBuilder("javac", uncompiledFile.getAbsolutePath()).start();
        compileProc.waitFor();

        if (compileProc.exitValue() == 0) {
            compiledFile = FileUtil.getFilesOtherThan(rootDir, uncompiledFile);
            f.complete(true);
        } else {
            p(FileUtil.convertToString(new BufferedReader(new InputStreamReader(compileProc.getErrorStream()))));
            f.complete(false);
        }
        return f;
    }

    private void p(String m) {
        System.out.println(m);
    }
    public final CompletableFuture<String> run() throws IOException, InterruptedException {
        final CompletableFuture<String> f = new CompletableFuture<>();
        if (compiledFile == null) {
            f.complete("");
            return f;
        }
        Process runProc = new ProcessBuilder("java", "-Xmx20M", "-Xms20M", compiledFile.getAbsolutePath().split("\\.")[0]).start();
        runProc.waitFor();
        String st = FileUtil.convertToString(new BufferedReader(new InputStreamReader(runProc.getInputStream())));
        f.complete(st);
        p(st);
        p("a");
        p(FileUtil.convertToString(new BufferedReader(new InputStreamReader(runProc.getErrorStream()))));
        return f;
    }
}




final class FileUtil {

    public static void write(final File file, final String data) {
        try (FileWriter fw = new FileWriter(file, false)) {
            fw.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String convertToString(BufferedReader read) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = read)
        {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null)
            {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

    @CheckReturnValue
    public static File getFilesOtherThan(File fold, File than) {
        for (File file : fold.listFiles()) {
            if (!file.equals(than)) {
                return file;
            }
        }
        return null;
    }
}
