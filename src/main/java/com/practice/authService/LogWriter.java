package com.practice.authService;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class LogWriter {

    public void writeToFile(String fileName, String content){
        try{
            String currentDir = System.getProperty("user.dir");
            File file = new File(currentDir + File.separator + fileName);
            if (!file.exists()){
                file.createNewFile();
                System.out.println("File created");
            }
            try(FileWriter writer = new FileWriter(file, true)){
                writer.write(content + "\n");

            }catch (IOException e1){
                System.out.println("Cant create writer: " + e1.getMessage());
            }
        }catch (IOException e2){
            System.out.println("Cant create file: " + e2.getMessage());
        }
    }

}
