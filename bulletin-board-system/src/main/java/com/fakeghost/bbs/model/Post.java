package com.fakeghost.bbs.model;

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
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
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
}
