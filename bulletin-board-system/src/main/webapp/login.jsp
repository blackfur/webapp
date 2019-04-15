<%@ page import="cn.habitdiary.utils.CookieUtils" %><%--
  Created by IntelliJ IDEA.
  User: xiejunyu
  Date: 18-2-15
  Time: 上午11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>登录页面</title>
  <link rel="stylesheet" href="./css/login.css">
  <%
    String username="";
    // 获得从客户端携带过来的所有的Cookie
    Cookie[] cookies = request.getCookies();
    // 从Cookie的数组中查找指定名称的Cookie
    Cookie cookie = CookieUtils.findCookie(cookies, "username");
    if(cookie != null){
      username = cookie.getValue();//先使用cookie记住的用户名
    }

    if(session.getAttribute("username")!=null){ //如果session里有用户名,说明当前有登录用户,则覆盖cookie记住的用户名
      username = (String)session.getAttribute("username");
    }

    if(request.getAttribute("username") != null){//如果request里有用户,说明用户刚注册,直接显示用户名
      username = (String)request.getAttribute("username");
    }

    String tourist = request.getParameter("tourist");
    String status = request.getParameter("status");
  %>
  <script type="text/javascript">
    var status = "<%=tourist%>";
    if(status == "true"){
        alert('您还未登录,没有权限进行该操作,请先登录！');
    }
    status = "<%=status%>";
    if(status == 'illegal'){
        alert('请您登录或者选择游客模式！');
    }
  </script>

  <%
    String msg = "";
    if(request.getAttribute("successMsg") != null){
      msg = (String)request.getAttribute("successMsg"); //用于显示注册成功的提示
    }
    if(request.getAttribute("failMsg") != null){ //用于显示登录失败的信息
        msg = (String)request.getAttribute("failMsg");
    }

  %>
  <script type="text/javascript">
      /**
       * 更换验证码函数
       */
    function  changeImage() {
        document.getElementById("code").src = "http://localhost:8080/BBS/kaptcha.jpg";
    }
  </script>
</head>
<body>
<div class="login">
  <div class="header">
    <h1>
      <a href="/BBS/login.jsp">登录</a> <a href="/BBS/register.jsp">注册</a>
    </h1>
  </div>
  <h1 style="font-size: medium;color: #FF0000;text-align: center"><%=msg%></h1>
  <form action="/BBS/LoginServlet" method="post">
    <table>
      <tr>
        <td class="td1">用户名</td>
        <td><input type="text" class="input1" name="username" required="required" value=<%=username%> ></td>
      </tr>
      <tr>
        <td class="td1">密&nbsp;&nbsp;&nbsp;&nbsp;码</td>
        <td><input type="password" class="input1" name="password" required="required"></td>
      </tr>
      <tr>
        <td colspan="2">
          <span style="margin-left: 9px">验证码</span>
          <span><input type="text" class="input2" name="verification" required="required"></span>
          <span style="margin-left: 15px"><img id="code" style="width: 50px;height: 25px" src="http://localhost:8080/BBS/kaptcha.jpg"/></span>
          <span style="margin-left: 15px"><a href="javascript:void(0)"onclick="changeImage()">换一张</a></span>
        </td>
      </tr>
      <tr>
        <td class="td1" colspan="2">
          <input style="margin-right: 10px" type="checkbox" name="remember" value="true" checked="checked"> 记住用户名
          <span id="tourist"><a href="/BBS/LoginServlet">游客入口</a></span>
        </td>
      </tr>
      <tr>
        <td colspan="2">
          <div class="btn-red">
            <input type="submit" value="登录" id="login-btn">
          </div>
        </td>
      </tr>
    </table>
  </form>
</div>
</body>
</html>