package com.fakeghost.bbs.doc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "bookshelf", type = "post") @Getter @Setter @AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    private long id;
    private String title;
    private String author;
    private String content;
    private String createTime;
    private String category;

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}

