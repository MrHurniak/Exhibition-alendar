<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>Expositions editor</h3>
<p>Create new exposition</p>
<form action="${pageContext.request.contextPath}/app/r/admin/expositions">
    <input type="text" hidden name="command" value="add"/>
    <div class="form-group">
        <label for="theme">Theme:</label>
        <input type="text" name="theme" class="form-control" id="theme" minlength="3" maxlength="150" required>
    </div>
    <div class="form-group">
        <label for="price">Price:</label>
        <input type="number" name="price" class="form-control" id="price" min="0" max="1000000000" required>
    </div>
    <div class="form-group">
        <label for="short">Short description:</label>
        <textarea class="form-control" name="short" maxlength="1000" rows="2" id="short" required></textarea>
    </div>
    <div class="form-group">
        <label for="full">Full description:</label>
        <textarea class="form-control" name="full" maxlength="1000" rows="3" id="full" ></textarea>
    </div>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <label class="input-group-text" for="inputGroupSelect01">Hall</label>
        </div>
        <select class="custom-select" name="hall_id" id="inputGroupSelect01" required>
            <c:forEach items="${halls}" var="hall">
                <option value="${hall.id}"><c:out value="${hall.name}"/></option>
            </c:forEach>
        </select>
    </div>
    <input class="btn btn-primary" type="submit" value="Submit" >
</form>
<hr>
<p>Update or delete</p>
<c:forEach var="expo" items="${requestScope['expositions']}">
    <hr>
    <form action="${pageContext.request.contextPath}/app/r/admin/expositions">
        <input type="text" hidden name="command" value="update"/>
        <input type="text" hidden name="expo_id" value="${expo.id}"/>
        <div class="form-group">
            <label for="theme${expo.id}">Theme:</label>
            <input type="text" name="theme" class="form-control" id="theme${expo.id}"
                   minlength="3" maxlength="150" value="${expo.theme}" required>
        </div>
        <div class="form-group">
            <label for="price${expo.id}">Price:</label>
            <input type="number" name="price" class="form-control" id="price${expo.id}"
                   min="0" max="1000000000" value="${expo.price}" required>
        </div>
        <div class="form-group">
            <label for="short${expo.id}">Short description:</label>
            <textarea class="form-control" name="short" maxlength="1000"
                      rows="2" id="short${expo.id}" required><c:out value="${expo.shortDescription}"/></textarea>
        </div>
        <div class="form-group">
            <label for="full${expo.id}">Full description:</label>
            <textarea class="form-control" name="full" maxlength="1000"
                      rows="3" id="full${expo.id}" ><c:out value="${expo.fullDescription}"/></textarea>
        </div>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <label class="input-group-text" for="inputGroupSelect${expo.id}">Hall</label>
            </div>
            <select class="custom-select" name="hall_id" id="inputGroupSelect${expo.id}" required>
                <c:forEach items="${halls}" var="hall">
                    <option <c:if test="${expo.hall.id eq hall.id}"> selected="selected" </c:if>
                            value="${hall.id}"><c:out value="${hall.name}"/></option>
                </c:forEach>
            </select>
        </div>
        <input class="btn btn-primary" type="submit" value="Submit" >
    </form>
    <form action="${pageContext.request.contextPath}/app/r/admin/expositions"
          onsubmit="return confirm('Do you really want to submit the form?');">
        <input type="text" hidden name="command" value="delete"/>
        <input type="text" hidden name="expo_id" value="${expo.id}"/>
        <input type="submit" class="btn btn-warning" value="Delete"/>
    </form>
</c:forEach>
