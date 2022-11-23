package com.example.file;

import java.io.*;

public class FileService {

    private File input;
    private File output;

    public FileService(String inputPath, String outputPath) {
        input = new File(inputPath);
        output = new File(outputPath);
    }

    public String getFileContent() throws IOException {
        if(!input.exists()) {
            throw new IOException("Input file not found");
        }

        BufferedReader reader = new BufferedReader(new FileReader(input));

        StringBuilder sb = new StringBuilder();
        String str = null;

        while((str = reader.readLine())!=null) {
            sb.append(str);
        }
        reader.close();
        return sb.toString();
    }

    public void writeFile(String json) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(output));
        writer.write(json);
        writer.close();
    }
}
