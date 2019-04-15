package cn.habitdiary.servlet;

import cn.habitdiary.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repeat_password = request.getParameter("repeat_password");
        String telephone = request.getParameter("telephone");
        String email = request.getParameter("email");

        boolean flag1 = false,flag2 = false;
        /*判断参数是否为空，防止用户对注册页面的html进行修改绕过前台校验造成空指针异常*/
        if(username == null || password == null || repeat_password == null
                || telephone == null || email == null){
            request.setAttribute("msg", "您未输入所有信息，请认真检查！");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        /*校验两次密码是否输入一致*/
        if(!password.equals(repeat_password)){
            request.setAttribute("msg", "您两次输入的密码不同，请重新填写！");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        /*进行后台格式校验，防止用户对注册页面的html进行修改绕过前台校验*/
        flag1 = telephone.matches("1[3578]\\d{9}");
        flag2 = email.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

        if(!flag1 || !flag2){
            request.setAttribute("msg", "您的手机或邮箱不符合格式要求，请仔细检查！");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        /*检查用户名是否存在*/
        List<User> userList = (List<User>) this.getServletContext().getAttribute("userList");
        for (User u : userList) {
            if (u.getUsername().equals(username)){
                request.setAttribute("msg", "用户名已经存在！");
                request.getRequestDispatcher("/register.jsp").forward(request, response);
                return;
            }
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setTelephone(telephone);
        user.setEmail(email);
        userList.add(user);//添加用户
        //把List集合存回ServletContext作用域
        this.getServletContext().setAttribute("userList", userList);
        //request域保存注册成功后直接显示在登录页面的用户名
        request.setAttribute("username", username);
        //successMsg保存成功提示
        request.setAttribute("successMsg", "注册成功，请登录！");
        //注册成功，跳转到登录页面
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request, response);
    }
}
