package org.example.produktai;

public class BuitineTechnika extends Produktas{

    private int garantija;
    private String energijosKlase;

    public BuitineTechnika(int id, String pavadinimas, double kaina, int garantija, String energijosKlase) {
        super(id, pavadinimas, kaina);
        this.garantija = garantija;
        this.energijosKlase = energijosKlase;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" ** garantija: %d metai ** energijos Klasė: %s ", garantija, energijosKlase);
    }
}
