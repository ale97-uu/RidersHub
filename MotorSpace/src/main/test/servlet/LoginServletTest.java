package servlet;

import Servlet.LoginServlet;
import Servlet.MyServletException;
import model.Utente;
import model.UtenteDAO;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import javax.faces.application.Application;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class LoginServletTest {

        private HttpServletRequest request = Mockito.mock(HttpServletRequest.class);


        private HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        private HttpSession session = Mockito.mock(HttpSession.class);

        private ServletConfig servletConfig = Mockito.mock(ServletConfig.class);

        private ServletContext servletContext = Mockito.mock(ServletContext.class);

        private RequestDispatcher requestDispatcher = Mockito.mock(RequestDispatcher.class);
        @InjectMocks
        private LoginServlet servlet = new LoginServlet();

        @BeforeEach
        void setUp() throws ServletException {
            MockitoAnnotations.openMocks(this);
            when(request.getSession()).thenReturn(session);
            when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
            when(servletConfig.getServletContext()).thenReturn(servletContext);
            servlet.init(servletConfig);
        }

        @Test
        void doPostSuccess() throws ServletException, IOException {
            when(request.getParameter("username")).thenReturn("Spaghettino");
            when(request.getParameter("password")).thenReturn("Ciaone55");
            servlet.doPost(request, response);
            verify(request, times(2)).getParameter(anyString());
        }

        @Test
        void doPostEmailFail() throws ServletException, IOException {
            when(request.getParameter("emailLog")).thenReturn("a@a.i");
            when(request.getParameter("passwordLog")).thenReturn("Ciaoprova1@");
            Assertions.assertThrows(MyServletException.class,()-> servlet.doPost(request, response));
        }

        @Test
        void doPostPasswordFail() throws ServletException, IOException {
            when(request.getParameter("emailLog")).thenReturn("testing@testing.it");
            when(request.getParameter("passwordLog")).thenReturn("Pas1@");
            Assertions.assertThrows(MyServletException.class,()-> servlet.doPost(request, response));
        }
    }

