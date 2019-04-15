package cn.habitdiary.servlet;

import cn.habitdiary.domain.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Message> msgList = (List<Message>) request.getServletContext().getAttribute("msgList");
        for (Message msg : msgList) {
            if (msg.getId() == Long.valueOf(request.getParameter("id"))) {
                msgList.remove(msg);
                break;
            }
        }
        request.getServletContext().setAttribute("msgList", msgList);
        response.sendRedirect(request.getContextPath() + "/view.jsp?status=2");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
    }
}
