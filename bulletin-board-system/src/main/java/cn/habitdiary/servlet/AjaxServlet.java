package cn.habitdiary.servlet;

import cn.habitdiary.domain.Message;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AjaxServlet")
public class AjaxServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = this.getServletContext();
        List<Message> msgList = (List<Message>) servletContext.getAttribute("msgList");
        //1.获取Ajax传递过来的参数信息
        String flag = request.getParameter("flag");
        String input = request.getParameter("input");
        //2.需要返回的数据信息
        StringBuilder data = new StringBuilder();
        boolean mark = false;
        if(flag.equals("A")){
            for (Message msg : msgList) {
                if(String.valueOf(msg.getId()).equals(input)){
                    mark = true;
                    data.append(msg.toString());
                }
            }

        }else if(flag.equals("B")){
            for (Message msg : msgList) {
                if(msg.getAuthor().equals(input)){
                    mark = true;
                    data.append(msg.toString());
                }
            }
        }else if(flag.equals("C")){
            for (Message msg : msgList) {
                if(msg.getTitle().equals(input)){
                    mark = true;
                    data.append(msg.toString());
                }
            }

        }else{
            for (Message msg : msgList) {
                if(msg.getCategory().equals(input)){
                    mark = true;
                    data.append(msg.toString());
                }
            }
        }
        //3.将数据信息写到输出流返回给Ajax
        if(!mark){
          data.append("<tr><td  colspan='5' style='text-align:center;padding-left:125px'>未查询到结果,请检查查询关键字是否正确！</td></tr>");
        }
        response.getOutputStream().write(data.toString().getBytes());
    }
}
