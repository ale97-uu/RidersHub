package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ProdottoDAOTest {

    @Test
    void doRetrieveAllTest(){
        ProdottoDAO prodottoDAO = new ProdottoDAO();
        Assertions.assertNotNull(prodottoDAO.doRetrieveAll(0,10));
    }

    @Test
    void doSaveTest(){

        ProdottoDAO prodottoDAO = Mockito.mock(ProdottoDAO.class);
        Prodotto prodotto = new Prodotto();
        prodotto.setNome("casco");
        prodotto.setCategoria("caschi");
        prodotto.setDescrizione("bello");
        prodotto.setMarca("agv");
        prodotto.setPrezzo(20);
        prodottoDAO.doSave(prodotto,1);
        Mockito.verify(prodottoDAO);
    }
    @Test
    void doRetrieveById(){
        ProdottoDAO prodottoDAO = Mockito.mock(ProdottoDAO.class);
        Prodotto prodotto = new Prodotto();
        prodotto.setNome("casco");
        prodotto.setCategoria("caschi");
        prodotto.setDescrizione("bello");
        prodotto.setMarca("agv");
        prodotto.setPrezzo(20);
        Mockito.when(prodottoDAO.doRetrieveById(1)).thenReturn(prodotto);
        Assertions.assertEquals(prodotto,prodottoDAO.doRetrieveById(1));
    }


}
