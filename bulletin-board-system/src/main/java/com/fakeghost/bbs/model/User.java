package com.fakeghost.bbs.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class User{
   int id;
   public String nickname;
   String password;

}
