<%--
  Created by IntelliJ IDEA.
  User: andrii
  Date: 02.04.19
  Time: 0:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Calendar of exhibitions</title>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/img/paint-board-and-brush.png" sizes="96x96">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
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
                    <a href="${pageContext.request.contextPath}/app/expositions" class="nav-link">Exhibitions</a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link">Contact us</a>
                </li>

            </ul>
        </div>
        <div class="mr-5 d-flex">
            <button type="button" class="btn btn-outline-success mx-2">Sing In</button>
            <button type="button" class="btn btn-success mx-2">Sing Up</button>
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

    <div class="container-fluid">
        <div class="container">
            <div class="row  justify-content-center">
                <div class="col-xs-12 col-sm-2 p-2" style="background: blue; height: 100%">
                    <div class="list-group">
                        <form method="post" id="checkbox_places">
                            <input type="text" hidden name="command" value="places"/>

                            <input type="checkbox" name="CheckBoxInputName" value="Value1" id="CheckBox1" />
                            <label class="list-group-item" for="CheckBox1">Caption for CheckBox1</label>

                            <input type="checkbox" name="CheckBoxInputName" value="Value2" id="CheckBox2" />
                            <label class="list-group-item" for="CheckBox2">Caption for CheckBox2</label>

                            <input type="checkbox" name="CheckBoxInputName" value="Value3" id="CheckBox3" />
                            <label class="list-group-item" for="CheckBox3">Caption for CheckBox3</label>

                            <input type="checkbox" name="CheckBoxInputName" value="Value4" id="CheckBox4" />
                            <label class="list-group-item" for="CheckBox4">Caption for CheckBox4</label>

                            <input class="btn btn-success" type="submit" name="OK" value="SUBMIT">
                            <input class="btn btn-danger" type="reset" value="RESET">
                        </form>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-8" style="border-left: 1px solid grey; border-right: 1px solid grey;" >


                    <div class="card my-1">
                        <div class="card-body">
                            <h5 class="card-title" style="text-align: left;"><a href="#">Card One</a></h5>
                            <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aspernatur illum enim nisi natus deleniti voluptate laboriosam esse debitis, sint officia, aperiam in dignissimos, quidem? Fuga voluptatum alias, accusamus officia dolorum.</p>
                            <form>
                                <input type="text" hidden name="buy" value="1"/>
                                <input type="submit" class="btn btn-secondary" value="BUY">
                            </form>
                        </div>
                    </div>
                    <div class="card my-1">
                        <div class="card-body">
                            <h5 class="card-title"><a href="#">Card One</a></h5>
                            <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quia ipsam porro, quam delectus deserunt minima perspiciatis! Delectus architecto, autem optio culpa, molestias dicta, ipsam laudantium voluptatibus temporibus facilis iste soluta.</p>
                            <form>
                                <input type="text" hidden name="buy" value="1"/>
                                <input type="submit" class="btn btn-secondary" value="BUY">
                            </form>
                        </div>
                    </div>
                    <div class="card my-1">
                        <div class="card-body">
                            <h5 class="card-title"><a href="#">Card One</a></h5>
                            <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nulla consequuntur, atque animi. Cum exercitationem aut aperiam animi harum dolores, reprehenderit cupiditate molestiae eos, incidunt aliquam, dicta nostrum impedit tempora minus!</p>
                            <form>
                                <input type="text" hidden name="buy" value="1"/>
                                <input type="submit" class="btn btn-secondary" value="BUY">
                            </form>
                        </div>
                    </div>
                    <div class="card my-1">
                        <div class="card-body">
                            <h5 class="card-title"><a href="#">Card One</a></h5>
                            <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Error laboriosam veritatis provident, aperiam id aliquam laudantium doloremque laborum repellendus ut, officia quasi commodi placeat voluptas architecto recusandae voluptatum unde maiores.</p>
                            <form>
                                <input type="text" hidden name="buy" value="1"/>
                                <input type="submit" class="btn btn-secondary" value="BUY">
                            </form>
                        </div>
                    </div>
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-center">
                            <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                            <li class="page-item active"><a class="page-link" href="#">1</a></li>
                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                            <li class="page-item"><a class="page-link" href="#">Next</a></li>
                        </ul>
                    </nav>
                </div>
                <div class="col-xs-12 col-sm-2" style="background: green; height: 100%">
                    <div class="list-group">

                        <input type="radio" name="RadioInputName" value="Value1" id="Radio1" />
                        <label class="list-group-item" for="Radio1">Caption for Radio1</label>

                        <input type="radio" name="RadioInputName" value="Value2" id="Radio2" />
                        <label class="list-group-item" for="Radio2">Caption for Radio2</label>

                        <input type="radio" name="RadioInputName" value="Value3" id="Radio3" />
                        <label class="list-group-item" for="Radio3">Caption for Radio3</label>

                    </div>
                </div>
            </div>
        </div>

    </div>
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
