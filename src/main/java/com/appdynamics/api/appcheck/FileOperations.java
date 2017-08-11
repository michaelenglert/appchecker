package com.appdynamics.api.appcheck;

import java.io.*;

public class FileOperations {
    private static String fileName;
    public String readFile() throws IOException {
        StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(
                new FileReader(fileName));
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }
        reader.close();
        return fileData.toString();
    }

    public void writeFile(String content) throws IOException {
        FileWriter fstream = new FileWriter(fileName);
        BufferedWriter out = new BufferedWriter(fstream);
        out.write(content);
        out.close();
    }

    public Boolean fileExists(String fileName){
        this.fileName = fileName;
        File f = new File(fileName);
        return f.exists();
    }
}
