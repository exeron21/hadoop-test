<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <title>Title</title>
  </head>
  <body>
  <form action="/doReg" method="post">
    username:<input type="text" name="username" value=""> <br/>
    password:<input type="password" name="password" value=""><br/>

    <input type="submit" value="reg" />
  </form>
  </body>
</html>
