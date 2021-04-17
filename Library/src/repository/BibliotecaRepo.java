package repository;

import models.Carte;
import models.Sectiune;

import java.util.*;

public class BibliotecaRepo {
    private static final BibliotecaRepo bibliotecaRepoSingleton = new BibliotecaRepo();
    private final List<Carte> carti = new ArrayList<>();
    private Map<String, Sectiune> sectiuni = new HashMap<>();

    public static BibliotecaRepo getInstance() {
        return bibliotecaRepoSingleton;
    }
    private BibliotecaRepo() {

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
