import org.example.produktai.BuitineTechnika;
import org.example.produktai.Maistas;
import org.example.produktai.Produktas;
import org.example.produktai.ProduktuKatalogas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class test_ProduktuKatalogas {

    private ProduktuKatalogas<Produktas> produktuKatalogas;

    @BeforeEach
    public void paruoštiObjektus() {
        produktuKatalogas = new ProduktuKatalogas<>();

    }

    @Test
    public void pridetiProdukta_pridetiTikraProdukta_returnTrue() {

        //Arrange
        Maistas maistas = new Maistas(1,"Ledai",1.59, LocalDate.parse("06-07-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        BuitineTechnika buitineTechnika = new BuitineTechnika(2,"Ledai",1.59, 3, "A klasė");
        boolean expected1 = true;
        boolean expected2 = true;

        //Act
        boolean actual1 = produktuKatalogas.pridetiProdukta(maistas);
        boolean actual2 = produktuKatalogas.pridetiProdukta(buitineTechnika);

        //Assert
        assertEquals(expected1,actual1);
        assertEquals(expected2,actual2);
    }
    @Test
    public void pridetiProdukta_pridetiTikraProduktaSuTokiupaciuId_returnFalse() {

        //Arrange
        Maistas maistas = new Maistas(1,"Ledai",1.59, LocalDate.parse("06-07-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        BuitineTechnika buitineTechnika = new BuitineTechnika(1,"Šaldytuvas",999.99, 3, "A klasė");
        produktuKatalogas.pridetiProdukta(maistas);
        boolean expected = false;

        //Act
        boolean actual = produktuKatalogas.pridetiProdukta(buitineTechnika);

        //Assert
        assertEquals(expected,actual);

    }
    @Test
    public void pridetiProdukta_pridetiNullVerte_returnFalse() {

        //Arrange
        boolean expected = false;

        //Act
        boolean actual = produktuKatalogas.pridetiProdukta(null);

        //Assert
        assertEquals(expected,actual);

    }

    @Test
    public void pasalintiProdukta_pasalintiRealuProdukta_returnTrue() {

         //Arrange
         Maistas maistas = new Maistas(1,"Ledai",1.59, LocalDate.parse("06-07-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
         produktuKatalogas.pridetiProdukta(maistas);
         boolean expected = true;
         //Act
         boolean actual = produktuKatalogas.pasalintiProdukta(1);
         //Assert
         assertEquals(expected,actual);

    }

    @Test
    public void pasalintiProdukta_pasalintiNeegzistuojantiProdukta_returnFalse() {

        //Arrange
        Maistas maistas = new Maistas(1,"Ledai",1.59, LocalDate.parse("06-07-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        produktuKatalogas.pridetiProdukta(maistas);
        boolean expected = false;
        //Act
        boolean actual = produktuKatalogas.pasalintiProdukta(2);
        //Assert
        assertEquals(expected,actual);
    }

    @Test
    public void pasalintiProdukta_patikrintiArKitiProduktaiNeissitrina_returnTrue() {

        //Arrange
        Maistas maistas = new Maistas(1,"Ledai",1.59, LocalDate.parse("06-07-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        BuitineTechnika buitineTechnika = new BuitineTechnika(2,"Šaldytuvas",999.99, 3, "A klasė");
        produktuKatalogas.pridetiProdukta(maistas);
        produktuKatalogas.pridetiProdukta(buitineTechnika);
        boolean expected = true;
        //Act
        produktuKatalogas.pasalintiProdukta(1);
        boolean actual = produktuKatalogas.pasalintiProdukta(2);

        //Assert
        assertEquals(expected,actual);
    }

    @Test
    public void gautiProduktusPagalKaina_patikrintiTarpinesKainas_returnList() {

        //Arrange
        Maistas maistas = new Maistas(1,"Ledai",1.59, LocalDate.parse("06-07-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        Maistas maistas2 = new Maistas(2,"Duona",4.59, LocalDate.parse("06-12-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        Maistas maistas3 = new Maistas(3,"Eco Bananai",3.29, LocalDate.parse("06-07-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        BuitineTechnika buitineTechnika = new BuitineTechnika(4,"Šaldytuvas",999.99, 3, "A klasė");
        BuitineTechnika buitineTechnika2 = new BuitineTechnika(5,"Šildytuvas",11.99, 3, "B klasė");
        BuitineTechnika buitineTechnika3 = new BuitineTechnika(6,"Fenas",19.99, 3, "C klasė");
        produktuKatalogas.pridetiProdukta(maistas);
        produktuKatalogas.pridetiProdukta(maistas2);
        produktuKatalogas.pridetiProdukta(maistas3);
        produktuKatalogas.pridetiProdukta(buitineTechnika);
        produktuKatalogas.pridetiProdukta(buitineTechnika2);
        produktuKatalogas.pridetiProdukta(buitineTechnika3);
        int expected = 4;

        //Act
        int actual = produktuKatalogas.gautiProduktusPagalKaina(2,100).size();

        //Assert
        assertEquals(expected,actual);
    }

    @Test
    public void gautiProduktusPagalKaina_patikrintiTuscioListoTarpinesKainas_returnList() {

        //Arrange
        int expected = 0;

        //Act
        int actual = produktuKatalogas.gautiProduktusPagalKaina(2,100).size();

        //Assert
        assertEquals(expected,actual);
    }


    @Test
    public void spausdintiVisusProduktus_patikrintiSuKeliaisProduktais_returnVoid() {

        //Arrange
        Maistas maistas = new Maistas(1,"Ledai",1.59, LocalDate.parse("06-07-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        BuitineTechnika buitineTechnika = new BuitineTechnika(2,"Šaldytuvas",999.99, 3, "A klasė");
        produktuKatalogas.pridetiProdukta(maistas);
        produktuKatalogas.pridetiProdukta(buitineTechnika);

        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        String expected = """
                Produkto id: 1 ** pavadinimas: Ledai ** kaina 1.59 ** galiojimo data: 2024-07-06 \r
                Produkto id: 2 ** pavadinimas: Šaldytuvas ** kaina 999.99 ** garantija: 3 metai ** energijos Klasė: A klasė \r
                """;

        //Act
        produktuKatalogas.spausdintiVisusProduktus();
        String actual = outputStreamCaptor.toString();

        //Assert
        assertEquals(expected,actual);
    }

    @Test
    public void spausdintiVisusProduktus_patikrintiSuNuliuProdoktu_returnVoid() {

        //Arrange
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        String expected = "";

        //Act
        produktuKatalogas.spausdintiVisusProduktus();
        String actual = outputStreamCaptor.toString();

        //Assert
        assertEquals(expected,actual);
    }

    @Test
    public void rusiuotiPagalKaina_patikrintiRusiavima_returnVoid() {

        //Arrange
        Maistas maistas = new Maistas(1,"Ledai",1.59, LocalDate.parse("06-07-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        Maistas maistas2 = new Maistas(2,"FDuona",0.59, LocalDate.parse("06-12-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        Maistas maistas3 = new Maistas(3,"Eco Bananai",0.59, LocalDate.parse("06-07-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        BuitineTechnika buitineTechnika = new BuitineTechnika(4,"Šaldytuvas",999.99, 3, "A klasė");
        BuitineTechnika buitineTechnika2 = new BuitineTechnika(5,"Šildytuvas",11.99, 3, "B klasė");
        BuitineTechnika buitineTechnika3 = new BuitineTechnika(6,"Fenas",19.99, 3, "C klasė");
        produktuKatalogas.pridetiProdukta(maistas);
        produktuKatalogas.pridetiProdukta(maistas2);
        produktuKatalogas.pridetiProdukta(maistas3);
        produktuKatalogas.pridetiProdukta(buitineTechnika);
        produktuKatalogas.pridetiProdukta(buitineTechnika2);
        produktuKatalogas.pridetiProdukta(buitineTechnika3);

        Produktas expected = maistas3;

        //Act
        produktuKatalogas.rusiuotiPagalKaina();
        Produktas actual = produktuKatalogas.getProduktai().getFirst();

        //Assert
        assertEquals(expected,actual);
    }







}
