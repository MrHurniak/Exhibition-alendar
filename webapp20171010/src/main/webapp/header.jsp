<nav class="navbar navbar-expand-sm bg-dark navbar-dark" style="justify-content: space-between;">
    <a class="navbar-brand " href="${pageContext.request.contextPath}/index.jsp">
        <img src="${pageContext.request.contextPath}/img/paint-board-and-brush.svg" class="mx-5" alt="Logo" style="width:40px;">
        Logo of project
    </a>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/index.jsp" class="nav-link">About us</a>
            </li>
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/app/exposition" class="nav-link">Exhibitions</a>
            </li>
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/app/adminPage" class="nav-link">Admin</a>
            </li>

        </ul>
    </div>
    <div class="mr-5 d-flex loginirovanie" style="height: 45px;">
        <a href="${pageContext.request.contextPath}/app/login"><button type="button" class="btn btn-outline-success mx-2">Sing In</button></a>
        <a href="${pageContext.request.contextPath}/app/registration"><button type="button" class="btn btn-success mx-2">Sing Up</button></a>
        <form class="language mx-2" method="get">
            <input type="text" hidden name="command" value="chooseLang"/>
            <input id="ua" type="submit" value="UA" name="lang">
            <label for="ua">
                <img src="${pageContext.request.contextPath}/img/ua_icon.png" >
            </label>
            <input id="en" type="submit" value="EN" name="lang">
            <label for="en" title="мова англійська">
                <img src="${pageContext.request.contextPath}/img/gb_icon.png">
            </label>
        </form>
    </div>
</nav>