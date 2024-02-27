package Servlet;

import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/VisualizzaOrdine")
public class VisualizzaOrdineServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        HttpSession session= request.getSession();

        Utente utente = (Utente) session.getAttribute("utente");
        if(utente == null)
            throw new MyServletException("Nessun utente loggato");

        int id = Integer.parseInt(request.getParameter("id"));
        Ordine ordine = new OrdineDAO().doRetrieveById(id);
        if(ordine == null){
        throw new MyServletException("Ordine non valido");
        }
        List<LineaOrdine> lineaOrdine = new LineaOrdineDAO().doRetrieveByOrdine(id);
        Map<String, Prodotto> prodotti = new HashMap<>();
        List<ProdottiOrdine> prodottiOrdine=new ArrayList<>();
        Prodotto p;
        ProdottiOrdine po = new ProdottiOrdine();
        for(LineaOrdine ln : lineaOrdine){
            p = new ProdottoDAO().doRetrieveById(ln.getIdProdotto());
            po.setId("" + p.getId());
            po.setProdotto(p);
            prodottiOrdine.add(po);
        }
        session.setAttribute("ordine", ordine);
        session.setAttribute("id", id);
        session.setAttribute("prodottiOrdine",prodottiOrdine);
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("WEB-INF/jsp/VisualizzaOrdine.jsp");
        requestDispatcher.forward(request,response);
    }
    public class ProdottiOrdine{
        String id;
        Prodotto prodotto;
        private void setId(String id){
            this.id=id;
        }
        private void setProdotto(Prodotto prodotto){
            this.prodotto=prodotto;
        }
    }
}
