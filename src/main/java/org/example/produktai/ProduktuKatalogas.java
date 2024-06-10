package org.example.produktai;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProduktuKatalogas <T extends Produktas>{

    private List<T> produktai;

    public ProduktuKatalogas() {
        this.produktai = new ArrayList<>();
    }

    public List<T> getProduktai() {
        return produktai;
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

    public T gautiProduktaPagalPavadinima(String pavadinimas){
        for(T t : produktai){
            if(t.getPavadinimas().equals(pavadinimas)) return t;
        }
        return null;
    }

    public void rusiuotiPagalKaina(){
        produktai.sort(new ProduktuKomparatorius<>());
    }

    public List<T> gautiProduktusArtiGaliojimoPabaigos(int dienos) {
        List<T> listWithDaysLeft = new ArrayList<>();
        List<T> currentProductList = new ArrayList<>();

        for(T p : produktai){
            try{
                Maistas maistas = (Maistas) p;
                currentProductList.add(p);
            }catch (ClassCastException e){
                continue;
            }
        }

        currentProductList.sort(new ProduktuDatosKomperatorius<>());
        LocalDate dateBefore = LocalDate.now();
        dateBefore = dateBefore.plusDays(dienos);

        boolean theIsMoreProducts = true;

        for(int i = currentProductList.size()-1; i >= 0 ; i-- ){
            Produktas produktas = currentProductList.get(i);
            LocalDate produkDate = ((Maistas) produktas).getGaliojimoData();
            if (produkDate.isBefore(dateBefore)){
                listWithDaysLeft.add((T)produktas);
            } else if (produkDate.isAfter(dateBefore)) {
                break;
            }

        }

        return listWithDaysLeft;
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
