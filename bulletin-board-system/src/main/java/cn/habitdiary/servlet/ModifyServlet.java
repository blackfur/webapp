package cn.habitdiary.servlet;

import cn.habitdiary.domain.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ModifyServlet")
public class ModifyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String content = request.getParameter("textarea");
        List<Message> msgList = (List<Message>) request.getServletContext().getAttribute("msgList");
        for (Message msg : msgList) {
            if (msg.getId() == Long.valueOf(request.getParameter("id"))) {
                msg.setContent(content);
            }
        }
        request.getServletContext().setAttribute("msgList", msgList);
        response.sendRedirect(request.getContextPath() + "/view.jsp?status=1");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
