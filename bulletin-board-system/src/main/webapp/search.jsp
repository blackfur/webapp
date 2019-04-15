<%--
  Created by IntelliJ IDEA.
  User: xiejunyu
  Date: 18-2-18
  Time: 下午11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询帖子</title>
    <link href="./css/search.css" rel="stylesheet">
</head>
<body>
    <div>
        <a class="return" href="/BBS/main.jsp">返回</a>
        <h2 style="text-align: center">查询帖子</h2>
    </div>
    <div class="searchdiv">
        <input type="text" class="search" id="input"/>
        <input type="button" value="按ID查询" class="button" onclick="funA()" />
        <input type="button" value="按作者查询" class="button" onclick="funB()"/>
        <input type="button" value="按题目查询" class="button" onclick="funC()"/>
        <input type="button" value="按分类查询" class="button" onclick="funD()"/>
    </div>
    <div class="tb">
    <table>
        <tr>
            <td class="td1" style="text-align: center">ID</td>
            <td class="td1" style="text-align: center">创建人</td>
            <td class="td1" style="text-align: center">创建时间</td>
            <td class="td1" style="text-align: center">分类</td>
            <td class="td1" style="text-align: center">标题</td>
        </tr>
    </table>
        <table align="center" id="show">

        </table>
    </div>
    <script type="text/javascript">

        function funA(){
            var input = document.getElementById("input").value;
            //1.创建一个 xmlhttpRequest对象
            var xmlHttpRequest = new XMLHttpRequest();
            //2.规定请求的类型、URL 以及是否异步处理请求。
            xmlHttpRequest.open("GET","/BBS/AjaxServlet?flag=A&input="+input,true);
            //3.将请求发送到服务器。
            xmlHttpRequest.send();
            //4、接收服务器端的响应(readyState=4表示请求已完成且响应已就绪    status=200表示请求响应一切正常)
            xmlHttpRequest.onreadystatechange = function () {
                if(xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200){
                    //responseText：表示的是服务器返回给ajax的数据
                    document.getElementById("show").innerHTML = xmlHttpRequest.responseText;
                }
            }


        }
        function funB(){
            var input = document.getElementById("input").value;
            var xmlHttpRequest = new XMLHttpRequest();
            xmlHttpRequest.open("GET","/BBS/AjaxServlet?flag=B&input=" + input,true);
            xmlHttpRequest.send();
            xmlHttpRequest.onreadystatechange = function () {
                if(xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200){
                    document.getElementById("show").innerHTML = xmlHttpRequest.responseText;
                }
            }


        }
        function funC(){
            var input = document.getElementById("input").value;
            var xmlHttpRequest = new XMLHttpRequest();
            xmlHttpRequest.open("GET","/BBS/AjaxServlet?flag=C&input="+input,true);
            xmlHttpRequest.send();
            xmlHttpRequest.onreadystatechange = function () {
                if(xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200){
                    document.getElementById("show").innerHTML = xmlHttpRequest.responseText;
                }
            }


        }
        function funD(){
            var input = document.getElementById("input").value;
            var xmlHttpRequest = new XMLHttpRequest();
            xmlHttpRequest.open("GET","/BBS/AjaxServlet?flag=D&input="+input,true);
            xmlHttpRequest.send();
            xmlHttpRequest.onreadystatechange = function () {
                if(xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200){
                    document.getElementById("show").innerHTML = xmlHttpRequest.responseText;
                }
            }


        }

    </script>
</body>
</html>
