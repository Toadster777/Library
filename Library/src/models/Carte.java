package models;

public class Carte implements Comparable<Carte>{
    private final Autor autor;
    private final String titlu;
    private final String sectiune;
    private String Stare;


    public Carte(Autor autor, String titlu, String sectiune) {
        this.autor = autor;
        this.titlu = titlu;
        this.sectiune = sectiune;
        this.Stare = "Neimprumutata";
    }

    public Carte(String autor, String titlu, String sectiune) {
        this.autor = new Autor(autor);
        this.titlu = titlu;
        this.sectiune = sectiune;
        this.Stare = "Neimprumutata";
    }

    public Autor getAutor() {
        return autor;
    }

    public String getTitlu() {
        return titlu;
    }

    public String getStare() {
        return Stare;
    }

    public int compareTo(Carte c) {
        return (titlu.compareTo(c.titlu));
    }

    public String getSectiune() {
        return sectiune;
    }
}
