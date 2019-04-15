<%@ page import="cn.habitdiary.domain.Message" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: xiejunyu
  Date: 18-2-18
  Time: 下午11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>新建帖子</title>
    <link rel="stylesheet" href="./css/create.css">
</head>
<body>
<%
    if(session.getAttribute("username") == "游客"){
        response.sendRedirect(request.getContextPath() + "/login.jsp?tourist=true");
    }
%>
<%
    List<Message> list = (List<Message>) application.getAttribute("msgList");
    int id = list.size() + 1;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String createTime = sdf.format(new Date());
%>
<div class="create">
    <div class="header">
        <span>
            <a href="/BBS/main.jsp">返回</a>
        </span>
        <span style="margin-left: 120px">
            新建帖子
        </span>
    </div>

    <form action="/BBS/CreateServlet" method="post">
        <table>
            <tr>
                <td class="td1">ID</td>
                <td><input type="text" class="input1" name="ID" value=<%=id%> readonly></td>
            </tr>
            <tr>
                <td class="td1">创建人</td>
                <td><input type="text" class="input1" name="author" value="${sessionScope.username}" readonly></td>
            </tr>
            <tr>
                <td class="td1">创建时间</td>
                <td><input type="text" class="input1" name="createTime" value=<%=createTime%> readonly></td>
            </tr>
            <tr>
                <td class="td1">分类</td>
                <td>
                <select name="category">
                    <option value ="活动通知">活动通知</option>
                    <option value ="灌水专区">灌水专区</option>
                    <option value="职场信息">职场信息</option>
                    <option value="校园杂谈">校园杂谈</option>
                    <option value ="国际观察">国际观察</option>
                    <option value ="情感天地">情感天地</option>
                    <option value="创业家园">创业家园</option>
                    <option value="贴图专区">贴图专区</option>
                </select>
                </td>
            </tr>
            <tr>
                <td class="td1">标题</td>
                <td><input type="text" class="input1" required="required" name="title"></td>
            </tr>

            <tr>
                <td class="td1">内容</td>
                <td>
                    <textarea required="required" name="content" cols="30" rows="8" style="resize: none"></textarea>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <div class="btn-red">
                        <input type="submit" value="创建" id="reg-btn">
                    </div>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>