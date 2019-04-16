<%@ page import="cn.habitdiary.utils.CookieUtils" %><%--
  Created by IntelliJ IDEA.
  Date: 18-2-15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>login</title>
  <link rel="stylesheet" href="./css/login.css">
  <%
    String username="";
    Cookie[] cookies = request.getCookies();
    Cookie cookie = CookieUtils.findCookie(cookies, "username");
    if(cookie != null){
      username = cookie.getValue();
    }

    if(username==null || username.length() ==0){
      if(session.getAttribute("username")!=null){
         username = (String)session.getAttribute("username");
      }

    }
    if(username==null || username.length() ==0){
      if(request.getAttribute("username") != null){
         username = (String)request.getAttribute("username");
      }
   }

    String tourist = request.getParameter("tourist");
    String status = request.getParameter("status");
  %>
  <script type="text/javascript">
    var status = "<%=tourist%>";
    if(status == "true"){
        alert('login please.');
          return;
    }
    status = "<%=status%>";
    if(status == 'illegal'){
        alert('Access not Allowed.');
    }
  </script>

  <%
    String msg = "";
    if(request.getAttribute("successMsg") != null){
      msg = (String)request.getAttribute("successMsg"); 
    }else if(request.getAttribute("failMsg") != null){
        msg = (String)request.getAttribute("failMsg");
    }

  %>
  <script type="text/javascript">
    function  changeImage() {
        document.getElementById("code").src = "http://localhost:8080/kaptcha.jpg?" + new Date().getTime();
    }
  </script>
</head>
<body>
<div class="login">
  <div class="header">
    <h1>
      <a href="/login.jsp">login</a> <a href="/register.jsp">register</a>
    </h1>
  </div>
  <h1 style="font-size: medium;color: #FF0000;text-align: center"><%=msg%></h1>
  <form action="/LoginServlet" method="post">
    <table>
      <tr>
        <td class="td1">Username</td>
        <td><input type="text" class="input1" name="username" required="required" value=<%=username%> ></td>
      </tr>
      <tr>
        <td class="td1">Password</td>
        <td><input type="password" class="input1" name="password" required="required"></td>
      </tr>
      <tr>
        <td colspan="2">
          <span style="margin-left: 9px">Random code</span>
         <input type="text" class="input2" name="verification" required="required">
          <img id="code" class='kaptcha' style="margin-left: 8px;width: 78px;height: 32px" src="kaptcha.jpg"/>
          <a style="margin-left: 8px" href="javascript:void(0)"onclick="changeImage()">Refresh</a>
        </td>
      </tr>
      <tr>
        <td class="td1" colspan="2">
          <input style="margin-right: 10px" type="checkbox" name="remember" value="true" checked="checked"> Remember Username
          <span id="tourist"><a href="/LoginServlet">no login Access</a></span>
        </td>
      </tr>
      <tr>
        <td colspan="2">
          <div class="btn-red">
            <input type="submit" value="login" id="login-btn">
          </div>
        </td>
      </tr>
    </table>
  </form>
</div>
<style>
.kaptcha{
   vertical-align: inherit;
}
</style>
</body>
</html>
