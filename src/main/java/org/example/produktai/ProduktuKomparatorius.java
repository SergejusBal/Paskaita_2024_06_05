package org.example.produktai;

import java.util.Comparator;

public class ProduktuKomparatorius <T extends Produktas> implements Comparator<T> {

    @Override
    public int compare(T produktas1, T produktas2) {
        if(produktas1.getKaina() > produktas2.getKaina()) return 1;
        else if (produktas1.getKaina() == produktas2.getKaina()) return produktas1.getPavadinimas().compareTo(produktas2.getPavadinimas());
        return -1;
    }
}
