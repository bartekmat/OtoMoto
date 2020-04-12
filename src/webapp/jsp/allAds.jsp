<%@ page language="java" pageEncoding="UTF-8" contentType="text/html" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>
<jsp:include page="navbar.jsp"/>


<div class="container">
    <div class="thumbnail" style="padding: 20px">
<%--        filters--%>
        <form method="get" action="/all">
            <div style="color: #df4930">
                <c:if test="${not empty requestScope.filterError}" >
                    ${requestScope.filterError}<br/>
                </c:if>
                <c:if test="${not empty requestScope.priceParamError}" >
                    ${requestScope.priceParamError}<br/>
                </c:if>
                <c:if test="${not empty requestScope.mileageParamError}" >
                    ${requestScope.mileageParamError}<br/>
                </c:if>
                <c:if test="${not empty requestScope.yearParamError}" >
                    ${requestScope.yearParamError}<br/>
                </c:if><br/>
            </div>
            <div class="filterColumn">
                <span>Producer : </span>
                    <select class="filterInput" name="company">
                        <option>any</option>
                        <c:forEach items="${companies}" var="company">
                            <option>${company}</option>
                        </c:forEach>
                    </select><br/><br/>
                <span>Show sorted : </span><select class="filterInput" name="sort">
                    <option>---</option>
                    <option>newest</option>
                    <option>cheapest</option>
                    <option>lowest mileage</option>
                    <option>recently added</option>
                </select><br/><br/>
            </div>
            <div class="filterColumn">
                <span>Min price : </span><input class="filterInput" type="text" name="priceMin" maxlength="8"/><br/><br/>
                <span>Max price : </span><input class="filterInput" type="text" name="priceMax" maxlength="8"/><br/><br/>
            </div>
            <div class="filterColumn">
                <span>Min mileage : </span><input class="filterInput" type="text" name="mileageMin" maxlength="7"/><br/><br/>
                <span>Max mileage : </span><input class="filterInput" type="text" name="mileageMax" maxlength="7"/><br/><br/>
            </div>
            <div class="filterColumn">
                <span>Min year : </span><input class="filterInput" type="text" name="yearMin" maxlength="4"/><br/><br/>
                <span>Max year : </span><input class="filterInput" type="text" name="yearMax" maxlength="4"/><br/>
            </div>
            <br style="clear: left"/>

            <input type="submit" value="FILTER">
        </form>
    </div>
    <div class="row">
<c:forEach items="${ads}" var="ad">
        <div class="col-sm-6 col-md-4" style="float: left">
            <div class="thumbnail">
                <img src="https://imageonthefly.autodatadirect.com/images/?USER=eDealer&PW=edealer872&IMG=USC80HOC011A021001.jpg&width=440&height=262" alt="Card image cap">
                <div class="caption">
                    <h3>${ad.car.company} ${ad.car.model}</h3>
                        <p class="card-text">
                            Mileage:  ${ad.car.mileage}
                        </p>
                        <p class="card-text">
                            Year of production:  ${ad.car.year}
                        </p><br/>
                        <p>
                            <a href="#" class="btn btn-primary" role="button"style="font-weight: bold; font-size: larger">${ad.price}$</a>
                            <span style="margin-left: 50px;">posted by: ${ad.user.name} ${ad.user.surname}</span>
                        </p>
                </div>
            </div>
        </div>
</c:forEach>
    </div>
</div>
</body>
</html>