package cn.habitdiary.servlet;

import com.fakeghost.bbs.model.Post;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/CreateServlet")
public class CreateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         long id = Long.valueOf(request.getParameter("ID"));
         String title = request.getParameter("title");
         String author = request.getParameter("author");
         String content = request.getParameter("content");
         String createTime = request.getParameter("createTime");
         String category = request.getParameter("category");
         ServletContext servletContext = this.getServletContext();
         List<Post> msgList = (List<Post>) servletContext.getAttribute("msgList");
         Post msg = new Post();
         msg.setId(id);
         msg.setTitle(title);
         msg.setAuthor(author);
         msg.setContent(content);
         msg.setCreateTime(createTime);
         msg.setCategory(category);
         msgList.add(msg);
         servletContext.setAttribute("msgList", msgList);
         response.sendRedirect(request.getContextPath() + "/view.jsp?status=3");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
    }
}
