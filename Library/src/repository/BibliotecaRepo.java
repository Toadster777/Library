package repository;

import io.FileFormatException;
import io.SingletonReader;
import models.Autor;
import models.Carte;
import models.Cititor;
import models.Sectiune;
import java.sql.*;

import java.io.IOException;
import java.util.*;

public class BibliotecaRepo {
    private static final BibliotecaRepo bibliotecaRepoSingleton = new BibliotecaRepo();
    private final List<Carte> carti = new ArrayList<>();
    private final List<Autor> autori = new ArrayList<>();
    private final List<Cititor> cititori = new ArrayList<>();
    private Map<String, Sectiune> sectiuni = new HashMap<>();
    private SingletonReader singletonReader = SingletonReader.getInstance();

    public static BibliotecaRepo getInstance() {
        return bibliotecaRepoSingleton;
    }
    private BibliotecaRepo() {

    }
    private static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_library", "root", "toor");
        } catch (SQLException e) {
            throw new RuntimeException("Nu s-a putut realiza conectarea la baza de date.");
        }
    }

    public void addAutor(Autor autor) {
        //  autori.add(autor);
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into autor(Nume) values(?)");
            preparedStatement.setString(1, autor.getName());
            preparedStatement.executeUpdate();
            System.out.println(" Autor Adaugat Cu Success");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remAutor(String nume){
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE from carte WHERE nume = ?;");
            preparedStatement.setString(1, nume);
            preparedStatement.executeUpdate();
            System.out.println("Autor sters cu succes");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   /* public void addAutors(Collection<Autor> autors) {
        autors.forEach(this::addAutor);
    }

    public boolean addAutorsFromCsv(String path) {
        try {
            addAutors(singletonReader.readAutor(path));
        } catch (IOException | FileFormatException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }




    public void addCarti(Collection<Carte> carti) {
        this.carti.addAll(carti);
    }

    public boolean addCartiFromCsv(String path) {
        try {
            addCarti(singletonReader.readCarte(path));
        } catch (IOException | FileFormatException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public void addCititori(Collection<Cititor> cititori) {
        this.cititori.addAll(cititori);
    }

    public boolean addCititoriFromCsv(String path) {
        try {
            addCititori(singletonReader.readCititor(path));
        } catch (IOException | FileFormatException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean addSectiuniFromCsv(String path) {
        try {
            List<Sectiune> sectiuni = singletonReader.readSectiune(path);
            sectiuni.forEach(sectiune -> this.sectiuni.put(sectiune.getNumeSectiune(), sectiune));
        } catch (IOException | FileFormatException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

 public Carte removeCarte(String titlu) {
        for (Carte carte : carti) {
            if (carte.getTitlu().equals(titlu)) {
                carti.remove(carte);
                sectiuni.get(carte.getSectiune()).removeCarte(carte);
                return carte;
            }
        }
        return null;
    }



    */

    public void addCarte(Carte carte) {
       // carti.add(carte); //inlocuieste cu adaugare cu jdbc in baza de date
        //Collections.sort(carti);
       // sectiuni.get(carte.getSectiune()).adaugaCarte(carte);
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into carte(titlu,autor,sectiune,stare) values(?,?,?,?)");
            preparedStatement.setString(1, carte.getTitlu());
            preparedStatement.setString(2, carte.getAutor().getName());
            preparedStatement.setString(3, carte.getSectiune());
            preparedStatement.setString(4, carte.getStare());
            preparedStatement.executeUpdate();
            System.out.println(" Carte Adaugata Cu Success");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void remCarte(String titlu){
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE from carte WHERE titlu = ?;");
            preparedStatement.setString(1, titlu);
            System.out.println("Carte Stearsa Cu Succes");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void impCarte(String titlu){
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE carte set Stare = ? WHERE titlu = ?;");
            preparedStatement.setString(1, "Imprumutata");
            preparedStatement.setString(2, titlu);
            System.out.println("Carte Imprumutata Cu Succes");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void addCititor(Cititor cititor) {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into cititor(Nume, varsta) values(?,?)");
            preparedStatement.setString(1, cititor.getNume());
            preparedStatement.setInt(2, cititor.getVarsta());
            preparedStatement.executeUpdate();
            System.out.println("Cititor Adaugat Cu Succes");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void remCititor(String nume) {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE from cititor WHERE Nume like ?;");
            preparedStatement.setString(1, nume);
            System.out.println("Cititor Sters Cu Succes");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Carte removeCarte(String titlu) {
        for (Carte carte : carti) {
            if (carte.getTitlu().equals(titlu)) {
                carti.remove(carte);
                sectiuni.get(carte.getSectiune()).removeCarte(carte);
                return carte;
            }
        }
        return null;
    }

    public List<Carte> getCarti() {
        return new ArrayList<>(carti);
    }

    public boolean isValidSection(String numeSectie) {
        return sectiuni.containsKey(numeSectie);
    }

    public List<Carte> getCartiBySectiune(String numeSectiune) {
        return sectiuni.get(numeSectiune).getCartiDinSectiune();
    }

}