<%--
  Created by IntelliJ IDEA.
  User: xiejunyu
  Date: 18-2-18
  Time: 下午7:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主界面</title>
    <link href="./css/main.css" rel="stylesheet">
</head>
<body>
        <%
             String  user = (String) session.getAttribute("username");
        %>
        <div align="right" style="margin:10px 60px 10px 10px">
            <img src="./img/default.jpg" width="120px" height="120px"/>
            <h2 align="right">欢迎你 <%=user%></h2>
            <a href="/BBS/login.jsp"  style="font-size: large">退出</a>
        </div>
        <table>
            <tr>
                <td><h3 style="margin-left: 70px">主界面</h3></td>
            </tr>
            <tr>
                <td>****<a href="/BBS/view.jsp">查看帖子</a>****</td>
            </tr>
            <tr>
                <td>****<a href="/BBS/search.jsp">查询帖子</a>****</td>
            </tr>
            <tr>
                <td>****<a href="/BBS/create.jsp">新建帖子</a>****</td>
            </tr>
        </table>
</body>
</html>
