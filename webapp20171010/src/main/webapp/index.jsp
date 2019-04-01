<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Calendar of exhibitions</title>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/img/paint-board-and-brush.png" sizes="96x96">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/indexStyle.css">
</head>
    <body>
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark" style="justify-content: space-between;">
        <a class="navbar-brand " href="#">
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
                    <a href="#" class="nav-link">Contact us</a>
                </li>

            </ul>
        </div>
        <div class="mr-5 d-flex loginirovanie">
            <a href="${pageContext.request.contextPath}/app/login"><button type="button" class="btn btn-outline-success mx-2">Sing In</button></a>
            <a href="${pageContext.request.contextPath}/app/registration"><button type="button" class="btn btn-success mx-2">Sing Up</button></a>
            <form class="language mx-2" method="get">
                <input type="text" hidden name="command" value="chooseLang"/>
                <input id="ua" type="submit" value="UA" name="lang">
                <label for="ua">
                    <img src="img/ua_icon.png" >
                </label>
                <input id="en" type="submit" value="EN" name="lang">
                <label for="en" title="мова англійська">
                    <img src="img/gb_icon.png">
                </label>
            </form>
        </div>
    </nav>
    <!--  Top Content -->
    <div class="container-fluid">
        <div class="container">
            <div class="row  justify-content-center">
                <div class="col-xs-12 col-sm-10 p-2" style="border-left: 1px solid grey; border-right: 1px solid grey;">
                    <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                        <div class="carousel-inner my-3">
                            <div class="carousel-item active">
                                <img class="d-block w-100"  src="${pageContext.request.contextPath}/img/dobro.jpg" alt="First slide">
                            </div>
                            <div class="carousel-item">
                                <img class="d-block w-100" src="${pageContext.request.contextPath}/img/dobro.jpg" alt="Second slide">
                            </div>
                            <div class="carousel-item">
                                <img class="d-block w-100" src="${pageContext.request.contextPath}/img/dobro.jpg" alt="Third slide">
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
                    <img src="${pageContext.request.contextPath}/img/cartinca.jpg" style="float: left;  height: 300px;" class="m-3" alt="">
                    <p style="text-align: left;">
                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eius quo soluta quidem aperiam quaerat inventore ducimus aspernatur repudiandae, iure. Nisi provident illo cum laudantium, reprehenderit sed facere! Quaerat quos minima fugit soluta est voluptas temporibus error alias saepe nobis, cupiditate animi, culpa iste iure non, nulla qui molestiae esse quam at delectus ad totam. Earum voluptate praesentium dignissimos, magni eaque consequuntur modi similique optio, fuga repellat quibusdam ea ipsum veritatis debitis. Ad commodi consequatur, quisquam. Illum labore architecto id quis vel ipsa magnam omnis, autem dolores assumenda voluptas. Error corporis recusandae libero, quasi labore vero eum maiores non perspiciatis officiis!lestiae debitis? Minima perferendis, at quam fuga quo deserunt qui ratione incidunt. Rem laboriosam consectetur et minus?Lorem ipsum dolor sit amet, consectetur adipisicing elit. Illo quam neque officiis, dolore. Veritatis tenetur perspiciatis delectus itaque autem fugit, eligendi porro, recusandae aliquid dolorum impedit illum veniam iusto, mollitia aut commodi dicta iste? Ratione nam, saepe, praesentium quae voluptates eligendi, iste sit voluptate vitae soluta consequatur excepturi suscipit ipsa obcaecati cum modi perspiciatis ab quaerat aliquid alias voluptatibus sequi illo? Officia corrupti odio alias! Et consequuntur id qui. Dignissimos debitis provident dolore modi voluptatibus aliquid animi odio sapiente, quae aspernatur magnam obcaecati deserunt unde pariatur quam quia at non vero perspiciatis optio atque ut alias ipsam aliquam error? Sequi!</p>
                    <h3 style="clear: left;">My Google Maps Demo</h3>
                    <!--The div element for the map -->
                    <div id="map">
                        <iframe src="https://www.google.com/maps/d/embed?mid=17wfchZA-bSnsVeZUzCVflCcYZyiAbVxr" width="640" height="480"></iframe>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer class="footer">
        <div class='footer--social social'>
            <a href='#' class='social--link'>
                <i class='fab fa-facebook-f'></i>
            </a>
            <a href='#' class='social--link'>
                <i class='fab fa-github'></i>
            </a>
            <a href='#' class='social--link'>
                <i class='fab fa-twitter'></i>
            </a>
        </div>
        <div class='footer--copyright'>
            <p>Copyright © 2019 by Andrii. All Rights Reserved.</p>
        </div>
    </footer>




    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>
