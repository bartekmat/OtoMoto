<%@ page language="java" pageEncoding="UTF-8" contentType="text/html" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>
<jsp:include page="navbar.jsp"/>


<div class="container">
    <c:if test="${not empty requestScope.loginExists}" >
        Login : ${requestScope.loginExists} already exists in system
    </c:if>
    <form method="post" action="/createAd">
        <h2>Fill in the data:</h2>
        <div class="form-group">
            <label for="company">Company:</label>
            <input type="text"  id="company" class="form-control" placeholder="Enter company" name="company">
        </div>
        <div class="form-group">
            <label for="surname">Model:</label>
            <input type="text" id="surname" class="form-control" placeholder="Enter surname" name="model">
        </div>
        <div class="form-group">
            <label for="mileage">Mileage:</label>
            <input type="text" class="form-control" id="mileage" placeholder="Enter mileage" name="mileage">
        </div>
        <div class="form-group">
            <label for="year">Year of production:</label>
            <select class="form-control" id="year" name="year">
                <option>2019</option>
                <option>2018</option>
                <option>2017</option>
                <option>2016</option>
                <option>2015</option>
            </select>
        </div>

        <div class="form-group">
            <label for="price">Price:</label>
            <input type="text" class="form-control" id="price" placeholder="Enter price" name="price">
        </div>

        <input type="submit" value="POST AD"/>
    </form>
</div>
</body>
</html>

