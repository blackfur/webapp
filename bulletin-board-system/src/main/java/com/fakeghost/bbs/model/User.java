package com.fakeghost.bbs.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Users")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
@Column(nullable = true)
   String nickname;
   String username;
@Column(nullable = true)
   String password;
@Column(nullable = true)
   String role;
@Column(nullable = true)
   boolean enabled;
   public User(String name, String psw){
      nickname = username= name;
      password = psw;
   }

}
