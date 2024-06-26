<%@ page import="model.Prodotto" %>
<%@ page import="java.util.List" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="Header.jsp">
    <jsp:param name="pageTitle" value="Home"/>
</jsp:include>
<meta charset="UTF-8">
<section>
    <link href="${pageContext.request.contextPath}/css/animate.css" type="text/css" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <link rel="stylesheet" href="css/bare.min.css" type="text/css">
    <div class="slideshow-container">

        <!-- Full-width images with number and caption text -->
        <div class="mySlides">
            <div class="numbertext">1 / 3</div>
            <img src="img/offerte/Dainese_Kleidung_Banner.jpg" style="width:100%">
        </div>

        <div class="mySlides">
            <div class="numbertext">2 / 3</div>
            <img src="img/offerte/DaineseBanner4_m.jpg" style="width:100%">
        </div>

        <div class="mySlides">
            <div class="numbertext">3 / 3</div>
            <img src="img/offerte/img67.jpg" style="width:100%">
        </div>

        <!-- Next and previous buttons -->
        <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
        <a class="next" onclick="plusSlides(1)">&#10095;</a>
    </div>
    <br>

</section>
<section >
    <grid>
        <c:forEach items="${Prodotti}" var="prodotto">
            <div col="1/3">
                <h3>
                    <a href="Prodotto?id=<c:out value="${prodotto.id}"/>"><c:out value= "${prodotto.nome}" /></a>
                </h3>
                <a href="Prodotto?id=<c:out value="${prodotto.id}"/>"><img src=${prodotto.fotoCopertina}></a>
                <h4>Prezzo: <c:out value="${prodotto.prezzo}" /> &euro;</h4>
            </div>
        </c:forEach>
    </grid>
</section>
<script>
    var slideIndex = 0;
    showSlides();

    function showSlides() {
        var i;
        var slides = document.getElementsByClassName("mySlides");
        for (i = 0; i < slides.length; i++) {
            slides[i].style.display = "none";
        }
        slideIndex++;
        if (slideIndex > slides.length) {slideIndex = 1}
        slides[slideIndex-1].style.display = "block";
        setTimeout(showSlides, 4000); // Change image every 2 seconds
    }
</script>
<%@include file="../footer.html"%>