package io;

import models.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SingletonReader {
    private static SingletonReader instance = new SingletonReader();


    private SingletonReader() {
    }

    public static SingletonReader getInstance() {
        return instance;
    }

    public List<Carte> readCarte(String pathToCsv) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));
        List<Carte> books = new ArrayList<>();
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            if (data.length != 3)
                throw new FileFormatException("Fisierul trebuie sa aiba exact 3 cuvinte separate prin virgula");
            books.add(new Carte(data[0], data[1], data[2]));
        }
        csvReader.close();
        return books;
    }

    public List<Autor> readAutor(String pathToCsv) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));
        List<Autor> autors = new ArrayList<>();
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            if (data.length != 1)
                throw new FileFormatException("Fisierul trebuie sa aiba exact 1 cuvinte separate prin virgula");
            autors.add(new Autor(data[0]));
        }
        csvReader.close();
        return autors;
    }

    public List<Cititor> readCititor(String pathToCsv) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));
        List<Cititor> cititors = new ArrayList<>();
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            if (data.length != 2)
                throw new FileFormatException("Fisierul trebuie sa aiba exact 2 cuvinte separate prin virgula");
            cititors.add(new Cititor(data[0], Integer.parseInt(data[1])));
        }
        csvReader.close();
        return cititors;
    }

    public List<Sectiune> readSectiune(String pathToCsv) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));
        List<Sectiune> sectiune = new ArrayList<>();
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            if (data.length != 2)
                throw new FileFormatException("Fisierul trebuie sa aiba exact 2 cuvinte separate prin virgula");
            switch (data[0]) {
                case "Beletristica": {
                    sectiune.add(new SectiuneBeletristica(data[1]));
                }
                case "Educationala": {
                    sectiune.add(new SectiuneEducationala(data[1]));
                }
            }
        }
        csvReader.close();
        return sectiune;
    }
}
