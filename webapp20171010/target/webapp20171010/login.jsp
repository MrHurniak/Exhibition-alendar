<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet"  href="${pageContext.request.contextPath}/css/signinup.css">
</head>
<body>

<div class="container">
    <div class="col-md-6">
        <div id="logbox">
            <form id="signup" method="post" action="/signup">
                <h1>account login</h1>
                <input name="user[email]" type="email" placeholder="enter your email" class="input pass"/>
                <input name="user[password]" type="password" placeholder="enter your password" required="required" class="input pass"/>
                <input type="submit" value="Sign me in!" class="inputButton"/>
                <div class="text-center">
                    <a href="signup.html" id="">create an account</a>
                </div>
            </form>
        </div>
    </div>
</div>
        <a href="${pageContext.request.contextPath}/app/logout">На головну</a>

</body>
</html>