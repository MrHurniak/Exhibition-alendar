<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/signinup.css">
</head>
<body>
    <div class="container">
        <div class="col-md-6">
            <div id="logbox">
                <form id="signup" method="post" action="${pageContext.request.contextPath}/app/registration">
                    <h1>create an account</h1>
                    <input name="name" type="text" placeholder="What's your name?" autofocus="autofocus" required="required" class="input pass"/>
                    <input name="surname" type="text" placeholder="What's your surname?" autofocus="autofocus" required="required" class="input pass"/>
                    <input name="email" type="email" placeholder="Email address" class="input pass"/>
                    <input name="login" type="text" placeholder="Choose a login" required="required" class="input pass"/>
                    <input name="password" type="password" placeholder="Choose a password" required="required" class="input pass"/>
                    <input type="submit" value="Sign me up!" class="inputButton"/>
                    <div class="text-center">
                        already have an account? <a href="${pageContext.request.contextPath}/app/login" id="login_id">login</a>
                    </div>
                    <div class="text-center">
                        <a href="${pageContext.request.contextPath}/index.jsp">Main</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
        <%--<a href="${pageContext.request.contextPath}/index.jsp">Index</a>--%>
</body>
</html>