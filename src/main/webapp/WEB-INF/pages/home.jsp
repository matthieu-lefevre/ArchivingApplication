<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

    <head>
        <title>${title}</title>
    </head>

    <body>
        <h1>Archiving Application</h1>
        <br/>
        <h3>Main items</h3>
        <c:forEach var="mainItem" items="${main}">
            <p>${mainItem}</p>
        </c:forEach>

        <br/><br/>
        <form action="http://localhost:8080/archive/documents" method="POST">
            <textarea rows="7" cols="30"></textarea>
            <input type="submit" value="Submit" />
        </form>

    </body>

</html>