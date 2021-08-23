package handler;

import java.nio.file.Path;

public class FileHelper {

    public static boolean IsNotTemporaryFile(Path file) {
        String filename = file.getFileName().toString();
        return !filename.contains("~$");
    }
}
