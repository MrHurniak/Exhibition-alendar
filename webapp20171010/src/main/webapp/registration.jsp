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
                <form id="signup" method="post" action="/signup">
                    <h1>create an account</h1>
                    <input name="user[name]" type="text" placeholder="What's your username?" pattern="^[\w]{3,16}$" autofocus="autofocus" required="required" class="input pass"/>
                    <input name="user[password]" type="password" placeholder="Choose a password" required="required" class="input pass"/>
                    <input name="user[password2]" type="password" placeholder="Confirm password" required="required" class="input pass"/>
                    <input name="user[email]" type="email" placeholder="Email address" class="input pass"/>
                    <input type="submit" value="Sign me up!" class="inputButton"/>
                    <div class="text-center">
                        already have an account? <a href="${pageContext.request.contextPath}/app/login" id="login_id">login</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
        <%--<a href="${pageContext.request.contextPath}/index.jsp">Index</a>--%>
</body>
</html>