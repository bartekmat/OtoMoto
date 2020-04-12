<%@ page language="java" pageEncoding="UTF-8" contentType="text/html" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>
<jsp:include page="navbar.jsp"/>
<div class="container">
    <div class="row">
        <c:forEach items="${myAds}" var="ad">
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