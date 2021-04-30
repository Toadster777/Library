package servicii;

import models.Carte;
import models.Cititor;
import repository.BibliotecaRepo;

import java.util.List;

public class BibliotecaService {
    private static BibliotecaRepo repoInstance = BibliotecaRepo.getInstance();
    private LoggingService logger = LoggingService.getInstance();

    public boolean adaugaCarte(Carte carte) {
        logger.log("adaugaCarte");
        if (!repoInstance.isValidSection(carte.getSectiune())) return false;
        repoInstance.addCarte(carte);
        return true;
    }

    public Carte imprumutaCarte(Cititor cititor, String numeCarte) {
        logger.log("imprumutaCarte");
        Carte carte =  repoInstance.removeCarte(numeCarte);
        cititor.adaugaCarte(carte);
        return carte;
    }

    public void returneazaCarte(Cititor cititor, Carte carte) {
        logger.log("returneazaCarte");
        repoInstance.addCarte(carte);
        cititor.eliminaCarte(carte);
    }

    public List<Carte> fetchCarti() {
        logger.log("fetchCarti");
        return repoInstance.getCarti();
    }

    public List<Carte> fetchCartiBySectiune(String numSectiune) {
        logger.log("fetchCartiBySectiune");
        return repoInstance.getCartiBySectiune(numSectiune);
    }
}