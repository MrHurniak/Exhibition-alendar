<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: andrii
  Date: 02.04.19
  Time: 0:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Panel</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/abminStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <fmt:setBundle var="link" basename="messages" scope="session"/>
</head>
<body>
<div class="container-fluid px-0">
    <div class="row collapse show no-gutters d-flex h-100 position-relative">
        <div class="col-3 p-0 h-100 w-sidebar navbar-collapse collapse d-none d-md-flex sidebar">
            <!-- fixed sidebar -->
            <div class="navbar-dark bg-dark text-white position-fixed h-100 align-self-start w-sidebar myclass">
                <div>
                    <h6 class="px-3 pt-3">
                        <a href="${pageContext.request.contextPath}/index.jsp">
                        <img src="${pageContext.request.contextPath}/img/paint-board-and-brush.svg" class="mx-5" alt="Logo" style="width:40px;">
                        </a>
                        <fmt:message key="admin.page" bundle="${link}"/>
                        <a data-toggle="collapse" class="px-1 d-inline d-md-none text-white" href="#" data-target=".collapse">
                            <i class="fa fa-bars"></i>
                        </a>
                    </h6>
                    <ul class="nav flex-column flex-nowrap text-truncate">
                        <li class="nav-item">
                            <a class="nav-link active" href="#">Active</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Link</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Link</a>
                        </li>
                    </ul>
                </div>
                <div class="logout">
                    <a href="${pageContext}/app/logout">
                        <fmt:message key="header.log.out" bundle="${link}"/>
                    </a>
                    <a href="${pageContext.request.contextPath}/index.jsp">
                        <fmt:message key="page.return" bundle="${link}"/>
                    </a>
                </div>
            </div>
        </div>
        <div class="col p-3">
            <h3>Lorem.</h3>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Porro, vel, odio. Sunt vel quo suscipit corporis repellat error asperiores labore maiores, quisquam voluptatem quos molestiae maxime debitis nobis consectetur animi esse itaque, voluptatibus alias aliquam amet illum hic omnis nulla! Cumque officiis molestias enim dolore repellendus! Ab officiis voluptate nobis, quasi aperiam ratione temporibus ducimus accusamus saepe doloribus similique assumenda tempore numquam praesentium. Dicta ad optio iusto vitae facilis voluptates fugiat quia maiores incidunt deleniti, illo provident aspernatur commodi dignissimos repellat cum sunt error ratione recusandae itaque laborum debitis necessitatibus sed voluptatibus! Velit molestias odio hic rem, doloremque nam? Eveniet.</p>

            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus minima labore delectus officiis? Quisquam velit, provident recusandae reiciendis, laborum, ut neque beatae nesciunt quia officia quibusdam. Aut illum sint, voluptate quaerat magnam similique sequi officia. Et, rem, aspernatur. Labore veritatis blanditiis dolore aliquid, odio tenetur expedita id beatae ipsam sequi culpa ex vitae temporibus at. Totam, laborum facere modi quas dolore perspiciatis ipsa vel temporibus neque distinctio officia obcaecati dolorem quisquam! Eveniet exercitationem error, dicta mollitia, ex dolorem ratione perspiciatis blanditiis aspernatur amet eaque. Beatae facere at eum odio sit reiciendis quibusdam veritatis voluptatibus, nisi excepturi atque blanditiis ea modi voluptate aliquam illo neque aut magni quod placeat necessitatibus nulla repellendus. Reiciendis dicta rem, saepe. Ullam enim, velit eos quaerat fuga quis ad minus perferendis cupiditate rem. Quis laboriosam quasi minima quibusdam ratione possimus, reiciendis vel. Ratione sed laboriosam sint ipsa saepe nesciunt suscipit dolore veniam a natus, molestiae tenetur, quae vel, provident odit voluptatibus modi commodi! Asperiores, dolorem! Iste ad veritatis, tempore voluptates, molestias fugit nobis aliquam sint qui natus exercitationem voluptatum provident aperiam debitis obcaecati quasi a sequi facilis. Doloribus saepe distinctio aut autem tenetur itaque natus, doloremque totam labore, sunt velit dolorum, dolores ipsa libero facere quas.</p>
        </div>
    </div>
</div>

</body>
</html>
