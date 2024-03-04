<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<body>
<jsp:include page="Header.jsp">
    <jsp:param name="pageTitle" value="Acquisto"/>
</jsp:include>
<section>
    <form action="Pagamento" method="post">
        <br>
        <label>Nome</label>
        <input type="text" name="nome" value="${utente.nome}">
        <label>Cognome</label>
        <input type="text" name ="cognome" value="${utente.cognome}">
        <label>Email</label>
        <input type="text" name="email" value="${utente.email}">
        <label>Provincia</label>
        <select name="provincia">
            <option value="SA">SA</option></select>
        <label>Città</label>
        <input type="text" name="città" placeholder="Inserire la città">
        <label>Cap</label>
        <input type="text" name="cap" placeholder="Inserire il cap">
        <label>Indirizzo</label>
        <input type="text" name="indirizzo" placeholder="Inserire l'indirizzo">
        <label>Numero Carta</label>
        <input type="text" name="Numero" placeholder="Inserire numero carta">
        <label>CVV</label>
        <input type="text" name ="cvv" placeholder= "CVV">
        <label>Data di scadenza carta</label>
        <input type="text" name="dataScadenza" placeholder= "GG/MM/AAAA">
        <label>Nome intestatario carta</label>
        <input type="text" name="nome" placeholder="Nome e cognome intestatario carta">
        <label></label>
        <c:choose>
            <c:when test="${utente != null}">
        <input type="submit" value="Avanti">
            </c:when>
            <c:otherwise>
                <label> Effettua l'accesso per completare l'acquisto</label>
            </c:otherwise>
        </c:choose>
    </form>
</section>
</body>
<script>
    var borderOk = '2px solid #080';
    var borderNo = '2px solid #f00';
    var usernameOk = false;
    var nomeOk = false;
    var cognomeOK = false;
    var passwordOk = false;
    var emailOk = false;
    var cartaOk = false;

    function validaUsername(){
        var input = document.forms['Pagamento']['username'];
        if (input.value.length >= 6 && input.value.match(/^[0-9a-zA-Z]+$/)){
            var xmlHttpReq = new XMLHttpRequest();
            xmlHttpReq.onreadystatechange = function(){
                if(this.readyState == 4 && this.status == 200 && this.responseText =='<ok/>'){
                    usernameOk = true;
                    input.style.border = borderOk;
                }else{
                    input.style.border = borderNo;
                    usernameOk = false;
                }
                cambiaStatoRegistrami()
            }
            xmlHttpReq.open("GET","VerificaUsername?username="+encodeURIComponent(input.value),true);
            xmlHttpReq.send();
        }else{

        }
    }

    function validaNome(){
        var input = document.forms['Pagamento']['nome'];
        if(input.value.trim().length > 0 && input.value().match(/^[a-zA-Z\u00C0-\u00ff]+$/)) {
            input.style.border = borderOk;
            nomeOk = true;
        }else {
            input.style.border = borderNo;
            nomeOk = false;
        }
        cambiaStatoRegistrami();
    }

    function validaCognome(){
        var input = document.forms['Pagamento']['cognome'];
        if(input.value.trim().length > 0 && input.value().match(/^[a-zA-Z\u00C0-\u00ff]+$/)) {
            input.style.border = borderOk;
            nomeOk = true;
        }else {
            input.style.border = borderNo;
            nomeOk = false;
        }
        cambiaStatoRegistrami();
    }

    function validaEmail(){
        var input = document.forms['Pagamento']['email'];
        if(input.value.match(/^\w+([\.-]?w+)'@\w+([\.-]?\w+)*(\.\w+)+$/)){
            input.style.border = borderOk;
            emailOk = true;
        }else {
            input.style.border = borderNo;
            emailOk = false;
        }
        cambiaStatoRegistrami();
    }

    function validaCarta(){
        var input = document.forms['Pagamento']['carta'];
        if(input.value.match(/^\w+([\.-]?w+)'@\w+([\.-]?\w+)*(\.\w+)+$/)){
            input.style.border = borderOk;
            cartaOk = true;
        }else {
            input.style.border = borderNo;
            emailOk = false;
        }
        cambiaStatoRegistrami();
    }

    function cambiaStatoRegistrami(){
        if(usernameOk && nomeOk && cognomeOK && passwordOk && emailOk){
            document.getElementById('registrami').disabled = false;
            document.getElementById('registramimessaggio').innerHTML ='';
        }else{
            document.getElementById('registrami').disabled = true;
            document.getElementById('registramimessaggio').innerHTML = 'Verifica che tutti i campi siano in verde.';
        }
    }

</script>
<%@include file="../footer.html"%>