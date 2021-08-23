package handler;

import exception.MyAccessException;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DirectoryHelper {

    public static List<String> getAbsoluteFilePaths(String subFolder) throws MyAccessException {
        List<String> paths = new ArrayList<>();
        for (Path file : getFiles(subFolder)) {
            if (FileHelper.IsNotTemporaryFile(file)) {
                paths.add(file.toAbsolutePath().toString());
            }
        }
        return paths;
    }

    public static DirectoryStream<Path> getFiles(String subFolder) throws MyAccessException {
        try {
            Path path = FileSystems.getDefault().getPath(subFolder);
            return Files.newDirectoryStream(path, "*.xlsx");
        } catch (IOException exception) {
            throw new MyAccessException("Can not load files in the directory: " + subFolder);
        }
    }
}
