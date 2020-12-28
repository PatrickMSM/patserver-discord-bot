package tk.patsite.Patserverdiscordbot.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringJoiner;

public final class FileUtil {

    public static String readFile(File file) {
        StringJoiner contentJoiner = new StringJoiner("\n");

        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null)
            {
                contentJoiner.add(sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentJoiner.toString();
    }

    public static void writeFile(File file, String data) {
        try (FileWriter fw = new FileWriter(file, false)) {
            fw.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
