<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Calendar of exhibitions</title>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/img/paint-board-and-brush.png" sizes="96x96">
    <link rel="stylesheet"  href="${pageContext.request.contextPath}/css/signinup.css">
</head>
<body>
    <div class="container">
        <div class="col-md-6">
            <div id="logbox">
                <form id="signup" method="post" action="${pageContext.request.contextPath}/app/login">
                    <h1>account login</h1>
                    <h3 style="color: red"> <c:out value="${sessionScope['message']}"/></h3>
                    <input name="login" type="text" placeholder="enter your login" class="input pass"/>
                    <input name="password" type="password" placeholder="enter your password" required="required" class="input pass"/>
                    <input type="submit" value="Sign me in!" class="inputButton"/>
                    <div class="text-center">
                        <a href="${pageContext.request.contextPath}/app/registration" id="">create an account</a>
                    </div>
                    <div class="text-center">
                        <a href="${pageContext.request.contextPath}/index.jsp">Main</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
            <%--<a href="${pageContext.request.contextPath}/index.jsp">На головну</a>--%>

    </body>
</html>