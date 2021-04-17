package models;

import java.util.ArrayList;
import java.util.List;

public abstract class Sectiune {
    private List<Carte> cartiDinSectiune = new ArrayList<>();
    private final String numeSectiune;

    public Sectiune(String numeSectiune) {
        this.numeSectiune = numeSectiune;
    }

    public List<Carte> getCartiDinSectiune() {
        return cartiDinSectiune;
    }

    public void setCartiDinSectiune(List<Carte> cartiDinSectiune) {
        this.cartiDinSectiune = cartiDinSectiune;
    }

    public String getNumeSectiune() {
        return numeSectiune;
    }

    public void adaugaCarte(Carte carte) {
        cartiDinSectiune.add(carte);
    }

    public boolean removeCarte(Carte carte) {
        return cartiDinSectiune.remove(carte);
    }

    protected String getType() {
        return "Sectiune generica";
    }
}
