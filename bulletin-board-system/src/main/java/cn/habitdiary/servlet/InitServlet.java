package cn.habitdiary.servlet;

import cn.habitdiary.domain.Message;
import cn.habitdiary.domain.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class InitServlet extends HttpServlet {
    /**
     * 用于初始化用户列表和帖子列表的Servlet
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
         /*在ServletContext域里创建List集合分别保存用户和帖子实例*/
        List<User> list1 = new ArrayList<User>();
        List<Message> list2 = new ArrayList<Message>();
        ServletContext servletContext = this.getServletContext();
        servletContext.setAttribute("userList", list1);
        servletContext.setAttribute("msgList", list2);

        /*在XML文件中配置<load-on-startup>2</load-on-startup>
        使得Servlet在服务器启动的时候就创建这个Servlet实例并完成初始化*/

    }
}
