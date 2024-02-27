package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UtenteDAOTest {

        @Test
        void doRetrieveAllTest(){
            UtenteDAO utenteDAO= new UtenteDAO();
            Assertions.assertNotNull(utenteDAO.doRetrieveAll(1,2));
        }

        @Test
        void doSaveTest(){

            UtenteDAO utenteDAO = Mockito.mock(UtenteDAO.class);
            Utente utente = new Utente();
            utente.setUsername("Spaghettini0");
            utente.setEmail("admin345@gmail.com");
            utente.setPassword("dhguijbnfjew123");
            utente.setNome("Andrea");
            utente.setCognome("orlando");
            utenteDAO.doSave(utente);
            Mockito.verify(utenteDAO);
        }
        @Test
        void doRetrieveByUsernamePassword(){
            UtenteDAO utenteDAO = Mockito.mock(UtenteDAO.class);
            Utente utente = new Utente();
            utente.setUsername("Spaghettini");
            utente.setEmail("admin345@gmail.com");
            utente.setPassword("CIAO21");
            utente.setNome("Andrea");
            utente.setCognome("orlando");
            Mockito.when(utenteDAO.doRetrieveByUsernamePassword("Spaghettini","CIAO21")).thenReturn(utente);
            Assertions.assertEquals(utente,utenteDAO.doRetrieveByUsernamePassword("Spaghettini","CIAO21"));
        }

}

