package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CategoriaDAOTest {

    @Test
    void doRetrieveAllTest(){
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        Assertions.assertNotNull(categoriaDAO.doRetrieveAll());
    }

    @Test
    void doSaveTest(){

        CategoriaDAO categoriaDAO = Mockito.mock(CategoriaDAO.class);
        Categoria categoria= new Categoria();
       categoria.setNome("caschi");
       categoria.setDescrizione("bello");
        Mockito.verify(categoriaDAO);
    }

    @Test
    void doRetrieveById(){
        CategoriaDAO categoriaDAO = Mockito.mock(CategoriaDAO.class);
        Categoria categoria= new Categoria();
        categoria.setNome("caschi");
        categoria.setDescrizione("bello");
        Mockito.when(categoriaDAO.doRetrieveById(1)).thenReturn(categoria);
        Assertions.assertEquals(categoria,categoriaDAO.doRetrieveById(1));
    }
}
