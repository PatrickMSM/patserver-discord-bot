package tk.patsite.Patserverdiscordbot;

import javax.annotation.CheckReturnValue;
import java.io.*;
import java.util.Random;
import java.util.concurrent.CompletableFuture;


public final class OnTheFlyCompiler {
    private final static Random RAND = new Random();


    private final File uncompiledFile, rootDir;
    private File compiledFile;

    public OnTheFlyCompiler(final String name, final String content) throws IOException {
        long tempId = RAND.nextLong();
        rootDir = new File(System.getProperty("java.io.tmpdir"), name + tempId);
        rootDir.mkdirs();
        uncompiledFile = new File(rootDir, "code.java");
        uncompiledFile.createNewFile();
        FileUtil.write(uncompiledFile, content);
    }

    @CheckReturnValue
    public final CompletableFuture<Boolean> compile() throws IOException, InterruptedException {
        final CompletableFuture<Boolean> f = new CompletableFuture<>();
        Process compileProc = new ProcessBuilder("javac", uncompiledFile.getAbsolutePath()).start();
        compileProc.waitFor();

        if (compileProc.exitValue() == 0) {
            compiledFile = new File(rootDir, "code.class");
            f.complete(true);
        } else {
            f.complete(false);
        }
        return f;
    }

    public final CompletableFuture<String> run() throws IOException {
        final CompletableFuture<String> f = new CompletableFuture<>();
        if (compiledFile == null) {
            f.complete("");
            return f;
        }


        Process runProc = new ProcessBuilder("javac", compiledFile.getAbsolutePath()).start();
        runProc.onExit().thenAccept(process -> f.complete(FileUtil.convertToString(new BufferedReader(new InputStreamReader(runProc.getInputStream())))));


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
}
