
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
<c:when test="${amministratore == null}">
    <jsp:include page="Header.jsp">
        <jsp:param name="pageTitle" value="Home"/>
    </jsp:include>
</c:when>
<c:otherwise>
    <jsp:include page="HeaderAmministratore.jsp">
        <jsp:param name="pageTitle" value="Home"/>
    </jsp:include>
    </c:otherwise>
    </c:choose>




<head>
    <link href="${pageContext.request.contextPath}/css/animate.css" type="text/css" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css" type="text/css">
</head>

<div col="1/3">
    <div class="slideshow-container">

        <!-- Full-width images with number and caption text -->
        <div class="mySlides">
            <div class="numbertext">1 / 3</div>
            <img src=${prodotto.getListaFoto().get(1)} style="width:100%">
        </div>

        <div class="mySlides">
            <div class="numbertext">2 / 3</div>
            <img src=${prodotto.getListaFoto().get(2)} style="width:100%">
        </div>

        <div class="mySlides">
            <div class="numbertext">3 / 3</div>
            <img src=${prodotto.getListaFoto().get(3)} style="width:100%">
        </div>

        <!-- Next and previous buttons -->
        <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
        <a class="next" onclick="plusSlides(1)">&#10095;</a>
    </div>
</div>
<div col="1/3">
    <h1>
        <a href="a">${prodotto.nome}</a>
    </h1>
    ${prodotto.descrizione}
</div>
<div col="3/3">
    <c:if test="${amministratore != null}">
        <form action="AdminProdotto" method="post">
            <input type="hidden" name="id" value="${prodotto.id}">
            <input type="submit" value="modifica">
            <input type="submit" name="rimuovi" value="Rimuovi">
        </form>
    </c:if>
    <p>Marca: ${prodotto.marca}
        </p>
<h4>Prezzo: ${prodotto.prezzo} &euro; </h4>
<form action="Carrello" method="post">
        <label>Quantità:</label>
        <select name="addNum">
            <c:forEach begin="1" end="30" varStatus="loop">
                <option value="${loop.index}">${loop.index}</option>
            </c:forEach>
        </select>
        <input type="hidden" name="prodId" value="${prodotto.id}">
        <input type="submit" value="Aggiungi al carrello">
    </form>
</div>
<script>
    let slideIndex = 1;
    showSlides(slideIndex);

    // Next/previous controls
    function plusSlides(n) {
        showSlides(slideIndex += n);
    }

    // Thumbnail image controls
    function currentSlide(n) {
        showSlides(slideIndex = n);
    }

    function showSlides(n) {
        let i;
        let slides = document.getElementsByClassName("mySlides");
        let dots = document.getElementsByClassName("dot");
        if (n > slides.length) {slideIndex = 1}
        if (n < 1) {slideIndex = slides.length}
        for (i = 0; i < slides.length; i++) {
            slides[i].style.display = "none";
        }
        slides[slideIndex-1].style.display = "block";
    }
</script>
<%@include file="../footer.html"%>


