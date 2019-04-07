<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Calendar of exhibitions</title>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/img/paint-board-and-brush.png" sizes="96x96">
    <jsp:include page="bootstrap.jsp"/>
    <%--<fmt:setLocale value = "ua_UA"/>--%>
    <fmt:setBundle var="link" basename="messages" scope="session" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/indexStyle.css">
</head>
    <body>
    <jsp:include page="header.jsp"/>
    <!--  Top Content -->
    <div class="container-fluid">
        <div class="container">
            <div class="row  justify-content-center">
                <div class="col-xs-12 col-sm-10 p-2" style="border-left: 1px solid grey; border-right: 1px solid grey;">
                    <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                        <div class="carousel-inner my-3" style="height: 300px; width: 100%">
                            <div class="carousel-item active">
                                <img class="d-block w-100"  src="${pageContext.request.contextPath}/img/expo1.jpg" alt="First slide">
                                <div class="carousel-caption d-none d-md-block  align-items-center">
                                    <h5>Перший слай</h5>
                                    <p>текст під слайдом</p>
                                </div>
                            </div>
                            <div class="carousel-item">
                                <img class="d-block w-100" src="${pageContext.request.contextPath}/img/expo2.jpg" alt="Second slide">
                                <div class="carousel-caption d-none d-md-block">
                                    <h5>Другий слайд</h5>
                                    <p>текст під слайдом</p>
                                </div>
                            </div>
                            <div class="carousel-item">
                                <img class="d-block w-100" src="${pageContext.request.contextPath}/img/expo3.jpg" alt="Third slide">
                                <div class="carousel-caption d-none d-md-block">
                                    <h5>Третій слайд</h5>
                                    <p>текс під слайдом</p>
                                </div>
                            </div>
                        </div>
                        <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                    <img src="${pageContext.request.contextPath}/img/mainImage.png" style="float: left;  height: 300px;" class="m-3" alt="">
                    <p style="text-align: left;">
                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eius quo soluta quidem aperiam quaerat inventore ducimus aspernatur repudiandae, iure. Nisi provident illo cum laudantium, reprehenderit sed facere! Quaerat quos minima fugit soluta est voluptas temporibus error alias saepe nobis, cupiditate animi, culpa iste iure non, nulla qui molestiae esse quam at delectus ad totam. Earum voluptate praesentium dignissimos, magni eaque consequuntur modi similique optio, fuga repellat quibusdam ea ipsum veritatis debitis. Ad commodi consequatur, quisquam. Illum labore architecto id quis vel ipsa magnam omnis, autem dolores assumenda voluptas. Error corporis recusandae libero, quasi labore vero eum maiores non perspiciatis officiis!lestiae debitis? Minima perferendis, at quam fuga quo deserunt qui ratione incidunt. Rem laboriosam consectetur et minus?Lorem ipsum dolor sit amet, consectetur adipisicing elit. Illo quam neque officiis, dolore. Veritatis tenetur perspiciatis delectus itaque autem fugit, eligendi porro, recusandae aliquid dolorum impedit illum veniam iusto, mollitia aut commodi dicta iste? Ratione nam, saepe, praesentium quae voluptates eligendi, iste sit voluptate vitae soluta consequatur excepturi suscipit ipsa obcaecati cum modi perspiciatis ab quaerat aliquid alias voluptatibus sequi illo? Officia corrupti odio alias! Et consequuntur id qui. Dignissimos debitis provident dolore modi voluptatibus aliquid animi odio sapiente, quae aspernatur magnam obcaecati deserunt unde pariatur quam quia at non vero perspiciatis optio atque ut alias ipsam aliquam error? Sequi!</p>
                    <h3 style="clear: left;">My Google Maps Demo</h3>
                    <!--The div element for the map -->
                    <div id="map">
                        <iframe src="https://www.google.com/maps/d/embed?mid=17wfchZA-bSnsVeZUzCVflCcYZyiAbVxr" width="640" height="480"></iframe>
                    </div>
                    <p style="text-align: center">+0384984916</p>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <jsp:include page="footer.jsp"/>




    </body>
</html>
