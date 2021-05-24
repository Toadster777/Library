package main;


import models.Autor;
import models.Carte;
import models.Cititor;
import models.Sectiune;
import servicii.BibliotecaService;
import servicii.CititorService;

public class Main {

    public static void main(String[] args) {
        Carte carte = new Carte("Mihail Sadoveanu", "Baltagul", "Literatura Romana");
        BibliotecaService BS = new BibliotecaService();
        BS.imprumutaCarte("Baltagul");

        Autor autor = new Autor("Mihail Sadoveanu");
       // BS.adaugaAutor(autor);

        CititorService CS = new CititorService();
      //  Cititor cititor = new Cititor("Toader Andrei", 21);
      //  CS.AdaugaCititor(cititor);
       // CS.StergeCititor("Toader Andrei");
   }
}