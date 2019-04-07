<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>Halls redactor</h3>
<p>Create new</p>
<form action="${pageContext.request.contextPath}/app/r/admin/halls">
    <input type="text" hidden name="command" value="add"/>
    <div class="form-group">
        <label for="name">Name:</label>
        <input type="text" name="name" class="form-control" id="name" minlength="3" maxlength="150" required>
    </div>
    <div class="form-group">
        <label for="info">Information</label>
        <textarea class="form-control" name="information" maxlength="1000" rows="5" id="info"></textarea>
    </div>
    <input class="btn btn-primary" type="submit" value="Submit" >
</form>
<hr>
<p>Update or delete</p>
<c:forEach var="hall" items="${requestScope['halls']}">
    <hr>
    <form action="${pageContext.request.contextPath}/app/r/admin/halls">
        <input type="text" hidden name="command" value="update"/>
        <input type="text" hidden name="id" value="${hall.id}"/>
        <div class="form-group">
            <label for="nameUp">Name:</label>
            <input type="text" name="name" class="form-control" id="nameUp" value="${hall.name}" required>
        </div>
        <div class="form-group">
            <label for="infoUp">Information:</label>
            <textarea class="form-control" name="information" rows="5" id="infoUp" required ><c:out value="${hall.information}"/></textarea>
        </div>
        <input class="btn btn-primary" type="submit" value="Update">
    </form>
    <form action="${pageContext.request.contextPath}/app/r/admin/halls"
          onsubmit="return confirm('Do you really want to submit the form?');">
        <input type="text" hidden name="command" value="delete"/>
        <input type="text" hidden name="id" value="${hall.id}"/>
        <input type="submit" class="btn btn-warning" value="Delete"/>
    </form>
</c:forEach>
