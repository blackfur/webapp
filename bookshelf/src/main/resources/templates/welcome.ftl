<html>
<head>
    <title>spring boot freemarker example</title>
</head>
<body>
<h1>Here is list of employee using spring boot freemarker</h1>
<table>
<#list list as todo>
    <tr>
        <td>${todo.id}</td>
        <td>${todo.title}</td>
        <td>${todo.completed?c}</td>
    </tr>
</#list>
</table>
</body>
</html>
