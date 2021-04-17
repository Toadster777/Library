package servicii;

import models.Carte;
import models.Cititor;
import repository.BibliotecaRepo;

import java.util.List;

public class BibliotecaService {
    private static BibliotecaRepo repoInstance = BibliotecaRepo.getInstance();

    public boolean adaugaCarte(Carte carte) {
        if (!repoInstance.isValidSection(carte.getSectiune())) return false;
        repoInstance.addCarte(carte);
        return true;
    }

    public Carte imprumutaCarte(Cititor cititor, String numeCarte) {
        Carte carte =  repoInstance.removeCarte(numeCarte);
        cititor.adaugaCarte(carte);
        return carte;
    }

    public void returneazaCarte(Cititor cititor, Carte carte) {
        repoInstance.addCarte(carte);
        cititor.eliminaCarte(carte);
    }

    public List<Carte> fetchCarti() {
        return repoInstance.getCarti();
    }

    public List<Carte> fetchCartiBySectiune(String numSectiune) {
        return repoInstance.getCartiBySectiune(numSectiune);
    }
}
