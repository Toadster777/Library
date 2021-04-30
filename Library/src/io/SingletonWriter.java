package io;

import java.io.FileWriter;
import java.io.IOException;

public class SingletonWriter {
    private static SingletonWriter instance = new SingletonWriter();


    private SingletonWriter() {
    }

    public static SingletonWriter getInstance() {
        return instance;
    }

    public void writeToFile(String data, String path)  {
        try (FileWriter fileWriter = new FileWriter(path)){
            fileWriter.write(data);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
