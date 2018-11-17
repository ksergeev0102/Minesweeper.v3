package lab3.log;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Filelogger implements lab3.log.Logger {
    public static void file_Logger(String message) {
        try {
            File file = new File("Logs.txt");
            if(!file.exists()) {
                file.createNewFile();
            }
            PrintWriter pw = new PrintWriter(file);
            pw.println(message);
            pw.close();
        } catch (IOException e) {
            System.out.println("Файл не был создан. : " + e);
        }
    }
    @Override
    public void printTrace(String message) {
        file_Logger("Trace: " + message);
    }
    @Override
    public void printError(String message) {
        file_Logger("Error: " + message);
    }
    @Override
    public void printInfo(String message) {
        file_Logger("Info: " + message);
    }
    @Override
    public void printWarn(String message) {
        file_Logger("Warn: " + message);
    }
    @Override
    public void printDebug(String message) {
        file_Logger("Debug: " + message);
    }
    @Override
    public void printFatal(String message) {
        file_Logger("Fatal: " + message);
    }
}
