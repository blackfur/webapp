<%--
  Created by IntelliJ IDEA.
  User: xiejunyu
  Date: 18-2-18
  Time: 下午5:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>注册页面</title>
    <link rel="stylesheet" href="./css/reg.css">
</head>
<body>
<div class="reg">
    <div class="header">
        <h1>
            <a href="/BBS/login.jsp">登录</a> <a href="/BBS/register.jsp">注册</a>
        </h1>
    </div>

    <%
        String msg = "";  //用于显示注册失败的原因
        if(request.getAttribute("msg")!=null){
            msg = (String)request.getAttribute("msg");
        }
    %>
    <h1 style="color: #FF0000;text-align:center;font-size: medium"><%=msg%></h1>
    <form action="/BBS/RegisterServlet" method="post">
        <table>
            <tr>
                <td class="td1">用户名</td>
                <td><input type="text" class="input1" name="username" required="required" placeholder="请输入用户名"></td>
            </tr>
            <tr>
                <td class="td1">密码</td>
                <td><input type="password" class="input1" name="password" required="required" placeholder="请输入密码"></td>
            </tr>
            <tr>
                <td class="td1">确认密码</td>
                <td><input type="password" class="input1" name="repeat_password" required="required" placeholder="请再次输入密码"></td>
            </tr>
            <tr>
                <td class="td1">手机</td>
                <td><input type="text" class="input1" name="telephone" required="required" pattern= "1[3578]\d{9}" placeholder="请输入11位合法手机号码"></td>
            </tr>
            <tr>
            <td class="td1">邮箱</td>
            <td><input type="text" class="input1" name="email" required="required" pattern="\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*" placeholder="请输入合法邮箱地址"></td>
            </tr>

            <tr>
                <td colspan="2">
                    <div class="btn-red">
                        <input type="submit" value="注册" id="reg-btn">
                    </div>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>