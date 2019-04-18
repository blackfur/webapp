package cn.habitdiary.servlet;

import com.fakeghost.bbs.model.Post;
import cn.habitdiary.domain.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;


public class InitServlet extends HttpServlet {
    /**
     * init User list and Posts list
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        List<User> list1 = new ArrayList<User>();
        List<Post> list2 = new ArrayList<Post>();
        ServletContext servletContext = this.getServletContext();
        servletContext.setAttribute("userList", list1);
        servletContext.setAttribute("msgList", list2);
    }
}
