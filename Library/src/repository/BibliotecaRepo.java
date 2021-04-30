package repository;

import io.FileFormatException;
import io.SingletonReader;
import models.Autor;
import models.Carte;
import models.Cititor;
import models.Sectiune;

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

    public void addAutor(Autor autor) {
        autori.add(autor);
    }

    public void addAutors(Collection<Autor> autors) {
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




    public void addCarte(Carte carte) {
        carti.add(carte);
        Collections.sort(carti);
        sectiuni.get(carte.getSectiune()).adaugaCarte(carte);
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