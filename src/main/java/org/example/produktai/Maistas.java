package org.example.produktai;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Maistas extends Produktas{

    private LocalDate galiojimoData;

    public Maistas(int id, String pavadinimas, double kaina, LocalDate galiojimoData) {
        super(id, pavadinimas, kaina);
        this.galiojimoData = galiojimoData;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        galiojimoData.format(formatter);
        return super.toString() + String.format(" ** galiojimo data: %s ", galiojimoData.toString());
    }
}
