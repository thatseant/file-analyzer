package model;

import java.io.File;

public interface FileLoader {
    FileData loadFile(File file);
}