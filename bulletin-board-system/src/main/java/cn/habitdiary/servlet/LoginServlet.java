package cn.habitdiary.servlet;

import cn.habitdiary.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@SuppressWarnings("serial")
@WebServlet("/LoginServlet")
//The handler bean id and the servlet name are exactly same.
//@WebServlet(urlPatterns = { "/LoginServlet" }, name = "loginHandler")
public class LoginServlet extends HttpServlet {
    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String kaptchaReceived = request.getParameter("verification");
        String kaptchaExpected = (String)request.getSession().getAttribute("kcode");
        if (kaptchaReceived == null || !kaptchaReceived.equalsIgnoreCase(kaptchaExpected)){
            request.setAttribute("failMsg","kaptcha code fault, Why not try again.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
        @SuppressWarnings("unchecked")
        List<User> list = (List<User>)this.getServletContext().getAttribute("userList");
        for (User user : list) {
            if(username.equals(user.getUsername()) && password.equals(user.getPassword())){
                String remember = request.getParameter("remember");
                if(remember.equals("true")){
                    Cookie cookie = new Cookie("username",username);
                    cookie.setPath("/"); 
                    cookie.setMaxAge(60 * 60 * 24); 
                    response.addCookie(cookie); 
                }
                request.getSession().setAttribute("username", username);
                response.sendRedirect(request.getContextPath() + "/main.jsp");
                return;
            }
        }
        request.setAttribute("failMsg","wrong Username or password.");
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("username", "No login Access");
        request.getRequestDispatcher("/main.jsp").forward(request, response);
    }
}
