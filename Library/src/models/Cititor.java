package models;

import java.util.ArrayList;
import java.util.List;

public class Cititor {
    private List<Carte> cartiImprumutate = new ArrayList<>();
    private final String nume;
    private int varsta;

    public Cititor(String nume, int varsta) {
        this.nume = nume;
        this.varsta = varsta;
    }

    public List<Carte> getCartiImprumutate() {
        return cartiImprumutate;
    }

    public String getNume() {
        return nume;
    }

    public int getNumarCartiImprumutate() {
        return cartiImprumutate.size();
    }

    public void adaugaCarte(Carte carte) {
        cartiImprumutate.add(carte);
    }

    public void eliminaCarte(Carte carte) {
        cartiImprumutate.remove(carte);
    }

    @Override
    public String toString() {
        return "Cititor{" +
                "cartiImprumutate=" + cartiImprumutate +
                ", nume='" + nume + '\'' +
                '}';
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }
}
