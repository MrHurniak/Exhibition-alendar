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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <jsp:include page="../../bootstrap.jsp"/>
</head>
<body>
    <jsp:include page="../../header.jsp"/>

    <div class="container-fluid">
        <div class="container">
            <div class="row  justify-content-center">
                <div class="col-xs-12 col-sm-2 p-2">
                    <div style="margin-top: 25px;">
                        <form method="post" action="#">
                            <input type="text" hidden name="command" value="places"/>
                            <div style="display: flex; height: 35px;">
                            <input type="checkbox" name="CheckBoxInputName" value="Value1" id="CheckBox1" />
                            <label style="line-height: 16px; margin-left:5px;" for="CheckBox1">CheckBox1</label>
                            </div>
                            <input type="text" hidden name="command" value="places"/>
                            <div style="display: flex; height: 35px;">
                                <input type="checkbox" name="CheckBoxInputName" value="Value1" id="CheckBox2" />
                                <label style="line-height: 16px; margin-left:5px;" for="CheckBox2">CheckBox4</label>
                            </div>
                            <input type="text" hidden name="command" value="places"/>
                            <div style="display: flex; height: 35px;">
                                <input type="checkbox" name="CheckBoxInputName" value="Value1" id="CheckBox3" />
                                <label style="line-height: 16px; margin-left:5px;" for="CheckBox3">CheckBox3</label>
                            </div>
                            <input type="text" hidden name="command" value="places"/>
                            <div style="display: flex; height: 35px;">
                                <input type="checkbox" name="CheckBoxInputName" value="Value1" id="CheckBox4" />
                                <label style="line-height: 16px; margin-left:5px;" for="CheckBox4">CheckBox4</label>
                            </div>

                            <input class="btn btn-success" type="submit" name="OK" value="SUBMIT">
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
                <div class="col-xs-12 col-sm-2">
                    <div style="margin-top: 25px;">
                        <input type="radio" name="RadioInputName" value="Value1" id="Radio1" />
                        <label for="Radio1">Caption for Radio1</label>

                        <input type="radio" name="RadioInputName" value="Value2" id="Radio2" />
                        <label for="Radio2">Caption for Radio2</label>

                        <input type="radio" name="RadioInputName" value="Value3" id="Radio3" />
                        <label for="Radio3">Caption for Radio3</label>

                    </div>
                </div>
            </div>
        </div>

    </div>
    <jsp:include page="../../footer.jsp"/>
</body>
</html>
