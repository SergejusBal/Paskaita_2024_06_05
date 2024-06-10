package org.example.produktai;

import java.time.LocalDate;
import java.util.Comparator;

public class ProduktuDatosKomperatorius<T extends Produktas> implements Comparator<T> {
    @Override
    public int compare(T produktas1, T produktas2) {
        LocalDate LocalDateOfProduktas1 = ((Maistas) produktas1).getGaliojimoData();
        LocalDate LocalDateOfProduktas2 = ((Maistas) produktas2).getGaliojimoData();

        if(LocalDateOfProduktas1.isBefore(LocalDateOfProduktas2)) return 1;
        else if (LocalDateOfProduktas1.isEqual(LocalDateOfProduktas2)) return 0;
        return -1;
    }
}
