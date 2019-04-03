<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark" style="justify-content: space-between;">
    <a class="navbar-brand " href="${pageContext.request.contextPath}/index.jsp">
        <img src="${pageContext.request.contextPath}/img/paint-board-and-brush.svg" class="mx-5" alt="Logo" style="width:40px;">
        Logo of project
    </a>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/index.jsp#map" class="nav-link">About us</a>
            </li>
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/app/exposition" class="nav-link">Exhibition</a>
            </li>
            <c:if test="${sessionScope['role'] eq 'ADMIN'}">
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/app/adminPage" class="nav-link">Admin</a>
            </li>
            </c:if>

        </ul>
    </div>
    <div class="mr-5 d-flex loginirovanie" style="height: 45px;">
        <c:if test="${not empty sessionScope['role'] }">
        <a href="${pageContext.request.contextPath}/app/logout"><button type="button" class="btn btn-success mx-2">Log Out</button></a>
        </c:if>
        <c:if test="${empty sessionScope['role']}">
        <a href="${pageContext.request.contextPath}/app/login"><button type="button" class="btn btn-outline-success mx-2">Sing In</button></a>
        <a href="${pageContext.request.contextPath}/app/registration"><button type="button" class="btn btn-success mx-2">Sing Up</button></a>
        </c:if>
        <div class="language mx-2" style="display: flex; flex-direction: column; justify-content: center; align-items: center">
                <div style="background-image: url('${pageContext.request.contextPath}/img/ua_icon.png');
                        background-size: cover; height: 17px; width: 23px;">
                    <a href="${pageContext.request.contextPath}/ua.lang" style="display: block; height: 100%"></a>
                </div>
                <div style="background-image: url('${pageContext.request.contextPath}/img/gb_icon.png');
                        background-size: cover; height: 17px; width: 23px; margin-top: 2.5px">
                    <a href="${pageContext.request.contextPath}/en.lang" style="display: block; height: 100%"></a>
                </div>
        </div>
    </div>
</nav>