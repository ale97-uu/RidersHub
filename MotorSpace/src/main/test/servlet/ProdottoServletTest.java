package servlet;

import Servlet.AcquistoServlet;
import Servlet.ProdottoServlet;
import model.Utente;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProdottoServletTest {

    @Test
    public void doGetSuccessTest() throws ServletException, IOException {
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class );
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class );
        HttpSession session = mock(HttpSession.class);
        when(httpServletRequest.getSession(true)).thenReturn(session);
        Utente utente = new Utente();
        when(httpServletRequest.getAttribute("utente")).thenReturn(utente);
        when(httpServletRequest.getParameter("id")).thenReturn("12");
        try{

            Mockito.verify(new ProdottoServlet()).doGet(httpServletRequest,httpServletResponse);
        }catch (ServletException e){
            throw new RuntimeException();
        }catch (IOException e){
            throw new RuntimeException();
        }
    }
}
