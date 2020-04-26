<%@ page language="java" pageEncoding="UTF-8" contentType="text/html" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>
<jsp:include page="navbar.jsp"/>


<div class="container">
    <div class="thumbnail" style="padding: 20px">
        <div style="color: #df4930">
            <c:if test="${not empty requestScope.companyNameError}" >
                ${requestScope.companyNameError}
            </c:if>
        </div>
        <div>
            <form method="post" action="editUser">
                <h2>Fill in the data:</h2>
                <div class="form-group">
                    <label for="name">Name:</label>
                    <input type="text"  id="name" class="form-control" placeholder="Enter new name" name="name">
                </div>
                <div class="form-group">
                    <label for="surname">Surname:</label>
                    <input type="text" id="surname" class="form-control" placeholder="Enter new surname" name="surname">
                </div>
                <input hidden type="text" id="email" name="email" value="${editedUsersEmail}">
                <input type="submit" value="SAVE CHANGES"/>
            </form>
        </div>
    </div>
</div>
</body>
</html>

