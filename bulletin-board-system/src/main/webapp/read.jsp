<%@ page import="cn.habitdiary.domain.Message" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: xiejunyu
  Date: 18-2-19
  Time: 下午12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>帖子正文</title>
    <link rel="stylesheet" href="./css/read.css">
    <%
        String user = (String) session.getAttribute("username");
        String id = request.getParameter("id");
    %>

</head>
<body>
<div class="reg">

    <div class="header">
        <h1>
            <a href="/BBS/main.jsp" style="margin-left: 0">返回</a>
        </h1>
        <%
            List<Message> msgList = (List<Message>) application.getAttribute("msgList");
            for (Message msg : msgList) {
                if(msg.getId() == Long.valueOf(request.getParameter("id")) && msg.getAuthor().equals(session.getAttribute("username"))){%>


                    <form style="float:right" action="/BBS/DeleteServlet?id=<%=id%>" method="post" >
                            <input style="padding: 5px;float:inherit" type="submit" value="删除帖子">
                    </form>
                    <form style="float:right" action="/BBS/ModifyServlet?id=<%=id%>" method="post" id="form">
                           <input style="padding: 5px;float:inherit" type="submit"  value="提交修改">
                    </form>
                    <%break;
                }
            }%>

    </div>
    <div style="width: 100%;height: 430px">
    <%
        String author = null;
        for (Message message : msgList) {
            if (id.equals(String.valueOf(message.getId()))) {
                author = message.getAuthor();%>
                <textarea id="textarea" name="textarea" form="form" style="width: 100%;height: 100%;resize: none">
                    <%=message.getContent()%>
                </textarea>
            <%  break;
            }
    }%>
    </div>
    <script type="text/javascript">
        if("<%=user%>" != "<%=author%>")
        document.getElementById("textarea").readOnly = true;
    </script>

</div>
</body>
</html>
