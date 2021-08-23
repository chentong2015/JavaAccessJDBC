package logging;

import handler.DateTimeHelper;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Logger {

    private final static String LOGGER_FILE;

    static {
        LOGGER_FILE = getBaseFolder() + "/" + getLoggerFileName();
    }

    private static String getBaseFolder() {
        Path folder = FileSystems.getDefault().getPath("Output");
        try {
            if (!Files.exists(folder)) Files.createDirectory(folder);
        } catch (IOException exception) {
            System.out.println("Cannot create Logger Folder");
        }
        return folder.toAbsolutePath().toString();
    }

    private static String getLoggerFileName() {
        String currentTime = DateTimeHelper.getCurrentDateTime();
        return "Planif_Output " + currentTime + ".txt";
    }

    // Single Thread, do not need to set synchronized method
    public static void log(String message) {
        try {
            Path path = Path.of(LOGGER_FILE);
            if (!Files.exists(path)) Files.createFile(path);
            Files.writeString(path, message + "\n", StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Can not create Logger file");
        }
    }
}
