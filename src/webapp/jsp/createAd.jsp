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
        <form method="post" action="createAd">
            <h2>Fill in the data:</h2>
            <div class="form-group">
                <label for="company">Company:</label>
                <input type="text"  id="company" class="form-control" placeholder="Enter company" name="company">
            </div>
            <div class="form-group">
                <label for="model">Model:</label>
                <input type="text" id="model" class="form-control" placeholder="Enter model" name="model">
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
                    <option>2014</option>
                    <option>2013</option>
                    <option>2012</option>
                    <option>2011</option>
                    <option>2010</option>
                    <option>2009</option>
                    <option>2008</option>
                    <option>2007</option>
                    <option>2006</option>
                    <option>2005</option>
                    <option>2004</option>
                    <option>2003</option>
                    <option>2002</option>
                    <option>2001</option>
                    <option>2000</option>
                </select>
            </div>

            <div class="form-group">
                <label for="price">Price:</label>
                <input type="text" class="form-control" id="price" placeholder="Enter price" name="price">
            </div>

            <input type="submit" value="POST AD"/>
        </form>
    </div>
</div>
</body>
</html>

