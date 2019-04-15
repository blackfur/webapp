<%@ page import="cn.habitdiary.domain.Message" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: xiejunyu
  Date: 18-2-18
  Time: 下午11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>查看帖子</title>
    <link rel="stylesheet" href="./css/view.css">
    <%
        String status = request.getParameter("status");
    %>
    <script type="text/javascript">
        var st = <%=status%>;
        if(st == 1) {
            alert('修改成功!');
        }else if(st == 2){
            alert('删除成功！');
        }else if(st == 3){
            alert('创建成功！');
        }
    </script>
</head>
<body>
<div class="reg">
    <div class="header">
        <h1>
            <a href="/BBS/main.jsp" style="margin-left: 0">返回</a>
        </h1>
        <h1 style="margin-left: 200px">
            查看帖子
        </h1>
    </div>
        <table>
            <tr>
                <td class="td1" style="text-align: center">ID</td>
                <td class="td1" style="text-align: center">创建人</td>
                <td class="td1" style="text-align: center">创建时间</td>
                <td class="td1" style="text-align: center">分类</td>
                <td class="td1" style="text-align: center">标题</td>
            </tr>
            <%
                List<Message> msgList = (List<Message>) application.getAttribute("msgList");
                for(Message msg:msgList){ %>
                    <tr>
                    <td class="td1" style="text-align: center"><%=msg.getId()%></td>
                    <td class="td1" style="text-align: center"><%=msg.getAuthor()%></td>
                    <td class="td1" style="text-align: center"><%=msg.getCreateTime()%></td>
                    <td class="td1" style="text-align: center"><%=msg.getCategory()%></td>
                    <td class="td1" style="text-align: center"><a href="/BBS/read.jsp?id=<%=msg.getId()%>"><%=msg.getTitle()%></a></td>
                    </tr>
            <%}%>

        </table>
</div>
</body>
</html>