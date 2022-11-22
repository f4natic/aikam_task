package com.example.file;

import java.io.File;

public class FileService {

    private File input;
    private File output;

    public FileService(String inputPath, String outputPath) {
        input = new File(inputPath);
        output = new File(outputPath);
    }

    public String getFileContent(){
        return null;
    }

}
