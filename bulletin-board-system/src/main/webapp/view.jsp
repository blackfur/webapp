<%--
  Created by IntelliJ IDEA.
  Date: 18-2-18
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript" src="jquery-3.4.0.js"></script>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="./css/view.css">
    <%
        String status = request.getParameter("status");
    %>
    <script type="text/javascript">
        var st = <%=status%>;
        if(st == 1) {
            alert('Updated');
        }else if(st == 2){
            alert('deleted');
        }else if(st == 3){
            alert('Created.');
        }
    </script>
    <link rel="stylesheet" type="text/css" href="DataTables-1.10.18/css/jquery.dataTables.css"/>
    <script type="text/javascript" src="DataTables-1.10.18/js/jquery.dataTables.js"></script>
</head>
<body>
<div class="reg">
    <div class="header">
        <h1>
            <a href="/main.jsp" style="margin-left: 0">return</a>
        </h1>
        <h1 style="margin-left: 200px">
           look around.
        </h1>
    </div>
    <table id="poststbl" class="display">
        <thead>
           <tr>
              <th>id</th>
              <th>title</th>
              <th>author</th>
              <th>time</th>
              <th>category</th>
              <th>content</th>
           </tr>
        </thead>
        <tbody> </tbody>
    </table>
</div>

    <script type="text/javascript" src="util.js"></script>
    <script type="text/javascript" src="view.js"></script>
</body>
</html>
