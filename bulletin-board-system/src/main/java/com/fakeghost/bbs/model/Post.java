package com.fakeghost.bbs.model;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) long id;
    String title;
    String content;
    String author;
    //String createTime;
    String category;

	 public Post(String title, String content){
		 this.title = title;
		 this.content = content;
	 }

    @Override
    public String toString() {
            return "<tr>"+
            "<td class='td1' style='text-align: center'>"+id+"</td>"+
            "<td class='td1' style='text-align: center'>"+author+"</td>"+
            //"<td class='td1' style='text-align: center'>"+createTime+"</td>"+
            "<td class='td1' style='text-align: center'>"+category+"</td>"+
            "<td class='td1' style='text-align: center'><a href='/BBS/read.jsp?id="+id+"'>"+title+"</a></td>"+
            "</tr>";
    }
    public Map<String, Object> hashmap(){
       Map<String, Object> resp = new HashMap<String, Object>();
       resp.put("title", title);
       resp.put("id", id);
       resp.put("content", content);
       resp.put("author", author);
       //resp.put("createTime", createTime);
       resp.put("category", category);
       return resp;
    }
}
