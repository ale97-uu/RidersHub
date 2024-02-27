
package model;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Date;

public class OrdineDAOTest{

        @Test
        void doSaveTest(){

            OrdineDAO ordineDAO = Mockito.mock(OrdineDAO.class);
            Ordine ordine = new Ordine();
            ordine.setCliente("Mario");
            ordine.setDataOrdine(ordine.dataOrdine);
            ordine.setDataConsegna(Date.valueOf("2024-05-05"));
            ordine.setDataSpedizione(Date.valueOf("2024-05-03"));
            ordineDAO.doSave(ordine);
            Mockito.verify(ordineDAO);
        }
        @Test
        void doRetrieveById(){
            OrdineDAO ordineDAO = Mockito.mock(OrdineDAO.class);
            Ordine ordine = new Ordine();
            ordine.setCliente("Mario");
            ordine.setDataOrdine(Date.valueOf("2024-01-05"));
            ordine.setDataConsegna(Date.valueOf("2024-03-05"));
            ordine.setDataSpedizione(Date.valueOf("2024-05-05"));
            Mockito.when(ordineDAO.doRetrieveById(1)).thenReturn(ordine);
            Assertions.assertEquals(ordine,ordineDAO.doRetrieveById(1));
        }


    }



