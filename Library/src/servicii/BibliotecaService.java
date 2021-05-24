package servicii;

import models.Autor;
import models.Carte;
import models.Cititor;
import models.Sectiune;
import repository.BibliotecaRepo;

import java.util.List;

public class BibliotecaService {
    private static BibliotecaRepo repoInstance = BibliotecaRepo.getInstance();
    private LoggingService logger = LoggingService.getInstance();

    public void adaugaCarte(Carte carte) {
        logger.log("adaugaCarte");
        repoInstance.addCarte(carte);
    }

    public void stergeCarte(String titlu){
        logger.log("stergeCarte");
        repoInstance.remCarte(titlu);
    }


    public void adaugaAutor(Autor autor) {
        logger.log("adaugaAutor");
        repoInstance.addAutor(autor);
    }
    public void stergeAutor(String nume){
        logger.log("adaugaAutor");
        repoInstance.remAutor(nume);
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