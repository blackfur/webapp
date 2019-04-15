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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    /**
     * 用户登录调用doPost方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String kaptchaReceived = request.getParameter("verification");
        //从session中取出servlet生成的验证码值
        String kaptchaExpected = (String)request.getSession().getAttribute("kcode");
        //校验验证码是否正确
        if (kaptchaReceived == null || !kaptchaReceived.equalsIgnoreCase(kaptchaExpected)){
            request.setAttribute("failMsg","验证码错误，请重新登录！");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
        //从ServletContext域中获取保存用户信息的集合
        List<User> list = (List<User>)this.getServletContext().getAttribute("userList");
        //遍历用户列表
        for (User user : list) {
            //检验用户名和密码是否匹配
            if(username.equals(user.getUsername()) && password.equals(user.getPassword())){
                String remember = request.getParameter("remember");
                //判断复选框是否勾选
                if(remember.equals("true")){
                    //创建Cookie,完成记住用户名的功能
                    Cookie cookie = new Cookie("username",username);
                    cookie.setPath("/BBS"); //设置Cookie的有效路径,在BBS的子目录下都会携带Cookie
                    cookie.setMaxAge(60 * 60 * 24); //秒为单位,保存24小时
                    response.addCookie(cookie); //将Cookie回写到浏览器
                }
                //登录成功,把用户信息保存在session域中
                request.getSession().setAttribute("username", username);
                response.sendRedirect(request.getContextPath() + "/main.jsp");
                return;
            }
        }
        //登录失败，保存并返回失败信息
        request.setAttribute("failMsg","用户名或密码错误，请重新登录！");
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    /**
     * 通过游客入口访问调用doGet方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("username", "游客");
        request.getRequestDispatcher("/main.jsp").forward(request, response);
    }
}
