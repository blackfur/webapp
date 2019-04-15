package cn.habitdiary.domain;

import java.util.Date;

public class Message {
    private long id;
    private String title;
    private String author;
    private String content;
    private String createTime;
    private String category;

    @Override
    public String toString() {
            return "<tr>"+
            "<td class='td1' style='text-align: center'>"+id+"</td>"+
            "<td class='td1' style='text-align: center'>"+author+"</td>"+
            "<td class='td1' style='text-align: center'>"+createTime+"</td>"+
            "<td class='td1' style='text-align: center'>"+category+"</td>"+
            "<td class='td1' style='text-align: center'><a href='/BBS/read.jsp?id="+id+"'>"+title+"</a></td>"+
            "</tr>";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
