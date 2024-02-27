package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AmministratoreDAOTest {

    @Test
       void doRetrieveAllTest(){
          AmministratoreDAO amministratoreDAO = new AmministratoreDAO();
            Assertions.assertNotNull(amministratoreDAO.doRetrieveAll(0,10));
       }

    @Test
    void doSaveTest(){

        AmministratoreDAO amministratoreDAO = Mockito.mock(AmministratoreDAO.class);
        Amministratore  amministratore= new Amministratore();
        amministratore.setUsername("admin");
        amministratore.setEmail("admin123@gmail.com");
        amministratore.setPassword("admin");
        amministratore.setNome("Alfredo");
        amministratore.setCognome("Russo");
        amministratore.setCodice("444");
        Mockito.verify(amministratoreDAO);
    }

    @Test
    void doRetrieveByUsername(){
        AmministratoreDAO amministratoreDAO = Mockito.mock(AmministratoreDAO.class);
        Amministratore  amministratore= new Amministratore();
        amministratore.setUsername("admin");
        amministratore.setEmail("admin123@gmail.com");
        amministratore.setPassword("admin");
        amministratore.setNome("Alfredo");
        amministratore.setCognome("Russo");
        amministratore.setCodice("444");
        Mockito.when(amministratoreDAO.doRetrieveByUsername("admin")).thenReturn(amministratore);//verifichiamo errore perchè l'username non è corretto
        Assertions.assertEquals(amministratore,amministratoreDAO.doRetrieveByUsername("admin"));
    }
}

