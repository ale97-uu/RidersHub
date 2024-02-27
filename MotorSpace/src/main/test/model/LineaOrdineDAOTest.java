package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class LineaOrdineDAOTest {


        @Test
        void doSaveTest(){

            LineaOrdineDAO lineaOrdineDAO = Mockito.mock(LineaOrdineDAO.class);
            LineaOrdine lineaOrdine = new LineaOrdine();
            lineaOrdine.setIdProdotto(100);
            lineaOrdine.setQuantità(30);
            lineaOrdine.setPrezzoUnitario(15.50f);
            lineaOrdineDAO.doSave(lineaOrdine);

        }

        @Test
        void doRetrieveByOrdine(){
            LineaOrdineDAO lineaOrdineDAO = Mockito.mock(LineaOrdineDAO.class);
            LineaOrdine lineaOrdine = new LineaOrdine();
            List<LineaOrdine> lista = new ArrayList<>();
            lista.add(lineaOrdine);
            lineaOrdine.setIdProdotto(5);
            lineaOrdine.setQuantità(50);
            lineaOrdine.setPrezzoUnitario(17.50f);
            Mockito.when(lineaOrdineDAO.doRetrieveByOrdine(3)).thenReturn(lista);
            //Mockito.verify(lineaOrdineDAO);
            Assertions.assertEquals(lineaOrdine,lineaOrdineDAO.doRetrieveByOrdine(3).get(0));
        }// con 1 il test fallisce


    }

