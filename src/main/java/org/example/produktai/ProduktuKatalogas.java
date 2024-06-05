package org.example.produktai;

import java.util.ArrayList;
import java.util.List;

public class ProduktuKatalogas <T extends Produktas>{

    private List<T> produktai;

    public ProduktuKatalogas() {
        this.produktai = new ArrayList<>();
    }

    public boolean pridetiProdukta(T produktas){
        if (produktas == null) return false;
        if (gautiProdukta(produktas.getId()) != null) return false;
        return produktai.add(produktas);
    }

    public List<T> gautiProduktusPagalKaina(double minKaina, double maxKaina){
        List<T> produkaiTenkinantysKainas = new ArrayList<>();

        for(int i = 0; i < produktai.size(); i++){
            T produktas = produktai.get(i);
            if (produktas.getKaina() > minKaina && produktas.getKaina() < maxKaina)
                produkaiTenkinantysKainas.add(produktas);
        }

        return produkaiTenkinantysKainas;
    }

    public void spausdintiVisusProduktus(){
        for(T t : produktai){
            System.out.println(t);
        }
    }


    public boolean pasalintiProdukta(int id){
        return produktai.remove(gautiProdukta(id));
    }
    private T gautiProdukta(int id){
        for(int i = 0; i < produktai.size(); i++){
            if (produktai.get(i).getId() == id) return produktai.get(i);
        }
        return null;
    }






}
