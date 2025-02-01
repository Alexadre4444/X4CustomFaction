package io.tbbc.cf.common;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class IOHelper {
    private IOHelper() {
    }

    public static void writeInFile(File file, String content) {
        try {
            Files.writeString(file.toPath(), content);
        } catch (IOException e) {
            throw new InternalException("Failed to write in file: " + file.getAbsolutePath());
        }
    }

    public static void writeInFile(File file, byte[] content) {
        try {
            Files.write(file.toPath(), content);
        } catch (IOException e) {
            throw new InternalException("Failed to write in file: " + file.getAbsolutePath());
        }
    }

    public static File createAndGetFile(File file, Behavior behaviorIfExists) {
        return createAndGetFile(file, Type.FILE, behaviorIfExists);
    }

    public static File createAndGetFile(File file, Type type, Behavior behaviorIfExists) {
        if (!file.exists()) {
            if (!createFile(file, type)) {
                throw new InternalException("Failed to create: " + file.getAbsolutePath());
            }
        } else {
            switch (behaviorIfExists) {
                case THROW:
                    throw new InternalException("Folder already exists: " + file.getAbsolutePath());
                case DELETE:
                    try {
                        FileUtils.deleteDirectory(file);
                    } catch (IOException e) {
                        throw new InternalException("Failed to delete file: " + file.getAbsolutePath());
                    }
                    createAndGetFolder(file, Behavior.THROW);
                    break;
                case IGNORE:
                    break;
            }
        }
        return file;
    }

    public static File createAndGetFolder(File folder, Behavior behaviorIfExists) {
        return createAndGetFile(folder, Type.FOLDER, behaviorIfExists);
    }

    private static boolean createFile(File file, Type type) {
        return switch (type) {
            case FOLDER -> file.mkdir();
            case FILE -> {
                try {
                    yield file.createNewFile();
                } catch (IOException e) {
                    throw new InternalException("Failed to create file: " + file.getAbsolutePath());
                }
            }
        };
    }

    public static byte[] getResourceContent(String path) {
        try (InputStream is = IOHelper.class.getResourceAsStream(path)) {
            if (is == null) {
                throw new InternalException("Resource not found: " + path);
            }
            return is.readAllBytes();
        } catch (IOException e) {
            throw new InternalException("Error reading resource: " + path, e);
        }
    }

    public enum Behavior {
        IGNORE,
        THROW,
        DELETE
    }

    public enum Type {
        FOLDER,
        FILE
    }
}
