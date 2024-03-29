
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="utf-8"%>

<!DOCTYPE html>
<jsp:include page="Header.jsp">

    <jsp:param name="pageTitle" value="${categoria.nome}"/>
</jsp:include>

<section>
    <c:if test="${!Amministratore}">
    <h1>${categoria.nome}</h1>
    </c:if>
    <c:if test="${Amministratore != null}">
        <form action="AdminCategoria" method="post">
            <h1>${categoria.nome}
                <input type="hidden" name="id" value="${categoria.id}">
                <input type="submit" value="Modifica">
                <input type="submit" name="rimuovi" value="Rimuovi">
            </h1>
        </form>
    </c:if>
    <p>${categoria.descrizione}</p>
    <p text="r" fs="s">Ordine:
        <c:forEach items="${ord.values()}" var="o">
        <c:choose>
            <c:when test="${o == 'DEFAULT'}">
                <c:set var="desc" value="default" />
            </c:when>
            <c:when test="${o == 'PREZZO_ASC'}">
                <c:set var="desc" value="prezzo (crescente)" />
            </c:when>
            <c:when test="${o == 'PREZZO_DESC'}">
                <c:set var="desc" value="prezzo (decrescente)" />
            </c:when>
        </c:choose>
        <c:choose>
            <c:when test="${o == ord}">
                <i>${desc}</i>
            </c:when>
            <c:otherwise>
                <a href="?id=${param.id}&perpag=${perpag}&ord=${o}">${desc}</a>
            </c:otherwise>
        </c:choose>
        </c:forEach>
    </p>
    <grid>
        <c:forEach items="${prodotti}" var="prodotto">
            <div col="1/3">
                <a href="Prodotto?id=${prodotto.id}"><img src="img/prodotto${prodotto.id}.jpg"></a>
            </div>
            <div col="2/3">
                <h3>
                    <a href="Prodotto?id=${prodotto.id}">${prodotto.nome}</a>
                </h3>
                <p>${prodotto.descrizione}</p>
                <h4>Prezzo:${prodotto.prezzo}€</h4>
            </div>
        </c:forEach>
        <c:if test="${empty prodotti}">
            <div col="1">Nessun prodotto nella categoria.</div>
        </c:if>

        <div col="1/1" txt = "c" style="background-color:#E8E8E8">
            <a <c:if test="${pag > 1}">href="?id=${param.id}&pag =${pag - 1}"</c:if>>&larr; precedente</a>
            &emsp;
            <c:forEach begin="1" end="${npag}" varStatus="loop">
                <c:choose>
                    <c:when test="${loop.index == pag}">
                        <b>${loop.index}</b>
                    </c:when>
                    <c:otherwise>
                        <a href="?id=${param.id}&pag=${loop.index}">${loop.index}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            &emsp;
            <a <c:if test="${pag < npag}">href="?id=${param.id}&pag=${pag + 1}" </c:if>>successiva &rarr; </a>
        </div>
    </grid>
</section>
<%@include file="../footer.html"%>