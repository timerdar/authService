package com.practice.authService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogController {

    private final String fileName;

    public LogController(String fileName){
        this.fileName = fileName;
    }
    public void writeToFile(String content){
        try{
            String currentDir = System.getProperty("user.dir");
            File file = new File(currentDir + File.separator + fileName);
            if (!file.exists()){
                file.createNewFile();
                System.out.println("File created");
            }
            try(FileWriter writer = new FileWriter(file, true)){
                writer.write(content + "\n");

            }catch (IOException e){
                System.out.println("Cant create writer: " + e.getMessage());
            }
        }catch (IOException e){
            System.out.println("Cant create file: " + e.getMessage());
        }
    }

    public String readRandomLine() throws IOException{
        List<String> lines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                lines.add(line);
                line = bufferedReader.readLine();
            }
        }
        if (lines.size() == 0){
            return null;
        }
        int randLineNum = (int) (Math.random() * (lines.size()));
        return lines.get(randLineNum);
    }

}
