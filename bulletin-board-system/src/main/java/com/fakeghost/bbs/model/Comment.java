package com.fakeghost.bbs.model;

import com.fasterxml.jackson.databind.ObjectMapper;
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

@Entity(name = "Comments")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) long id;
    String ip;
    String content;
    long timestamp;
    long belongto;

    @Override
    public String toString() {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.writeValueAsString(this);
    }
}

