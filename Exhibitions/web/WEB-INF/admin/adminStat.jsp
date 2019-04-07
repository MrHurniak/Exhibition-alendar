<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>Statistics</h3>
<div class="container-fluid">
    <div class="container">
        <div class="row  justify-content-center">
            <div class="col-md-6 col-sm-12 px-5">
                <ul class="list-group">
                    <li class="list-group-item active">Users online</li>
                    <c:forEach var="user" items="${applicationScope['loggedUsers']}">
                        <li class="list-group-item">
                            <c:out value="${user}"/>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="col-md-6 col-sm-12">
                <h4>smth else</h4>
            </div>
        </div>
    </div>
</div>