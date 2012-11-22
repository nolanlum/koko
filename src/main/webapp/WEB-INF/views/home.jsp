<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
    <head>
        <title>Home</title>
    </head>
    <body>
        <h1>Hello ${uid}! Let's call you ${name}.</h1>
        Wanted to <a href="j_spring_security_logout">logout</a>? How about <a href="j_spring_cas_security_logout">CAS logout</a>?
    </body>
</html>