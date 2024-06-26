<%@ page import="java.util.List" %>
<%@ page import="model.Categoria" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype HTML>
<html lang="en">
<head>
    <title>MotorSpace - ${param.pageTitle}</title>

    <!-- meta -->
    <meta charset=utf-8>
    <meta name="description" content="BareCSS template file">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no"/>

    <!-- css -->

  <link href="css/bare.min.css" rel="stylesheet" type="text/css">
    <link href="css/header.css" rel="stylesheet" type="text/css">
    <script src="Ricerca.js"></script>
</head>
<body>
<nav><!-- use fx attribute for fixed positioning -->
    <label>

            <div class="logo">
                <a href="Home"><img src="img/logo.png"></a>
            </div>
        <ul>
        <!-- Caricamento delle varie categorie di prodotti all'interno dell'header della home-->
            <li>
                <form action="RicercaServlet" method="get" >
                    <input type="text" name="q" ricerca="ricerca-datalist" placeholder="Ricerca" onkeyup="ricerca(this.value)" value="<c:out value ="${param.q}"/>">
                    <datalist id="ricerca-datalist"></datalist>
                </form>
            </li>
           <li class="lista">
               <a>Vestiario</a>
                <menu>
                    <c:forEach items="${categorie1}" var="categorie1">
                        <menuitem>
                            <a href="Categoria?id=<c:out value="${categorie1.id}"/>"><c:out
                            value="${categorie1.nome}" /></a>
                        </menuitem>
                    </c:forEach>
                </menu>
           </li>
            <li>
                <a>Ricambi</a>
                <menu>
                    <c:forEach items="${categorie2}" var="categorie2">
                        <menuitem>
                            <a href="CategoriaParti?id=<c:out value="${categorie2.id}"/>">
                                <c:out value="${categorie2.nome}" /></a>
                        </menuitem>
                    </c:forEach>
                </menu>
            </li>
            <li>
                <a>Accessori Moto</a>
                <menu>
                    <c:forEach items="${categorie3}" var="categorie3">
                        <menuitem>
                            <a href="CategoriaAccessori?id=<c:out value="${categorie3.id}"/>"><c:out
                                    value="${categorie3.nome}" /></a>
                        </menuitem>
                    </c:forEach>
                </menu>
            </li>
        </li>
            <li>
                <a>Offerte</a>
            </li>

            <li>
                <c:choose>
                    <c:when test="${utente == null}">
                        <!-- In caso l'utente non sia loggato verrà data la possibilità di effettuare il login mediante un menù a tendina-->
                        <a> <img src="img/logoLogin_2_2.png"> </a>
                        <menu>
                            <menuitem>
                                <card>
                                    <form action="LoginServlet" method="post">
                                        <input type="text" name="username" value="Spaghettino"><br>
                                        <input type="password" name="password" value="Ciaone55"><br>
                                        <input type="submit" value="Login">
                                    </form>
                                </card>
                            </menuitem>
                            <menuitem><a href="RegistrazioneForm">Registrazione</a> </menuitem>

                            <menuitem><a href="HomeAmministratore?id=home">Accedi come amministratore</a> </menuitem>
                        </menu>
                    </c:when>


                    <c:otherwise>
                        <!-- In caso l'utente sia loggato avrà la possibilità di controllare il proprio profilo o la pagina dei propri ordini -->
                        <a> <img src="img/logoLogin_2_2.png"> </a>
                        <menu>
                            <menuitem><a href="profiloServlet">Profilo</a> </menuitem>
                            <menuitem><a href="VisualizzaOrdini">I miei ordini</a> </menuitem>
                            <menuitem>
                                <card>
                                    <form action="Logout">
                                        <input type="submit" value="Logout">
                                    </form>
                                </card>
                            </menuitem>
                        </menu>
                    </c:otherwise>
                </c:choose>

            </li>
            <li><a href="Carrello"><img src="img/carrello_rosso.png"></a></li>

        </ul>
    </label>
</nav>
