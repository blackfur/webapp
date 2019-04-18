package com.fakeghost.bbs.model;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper{
   List<User> users(int userid);
   User user(@Param("nickname") String nickname, @Param("password") String password);
   void register(@Param("id")int id,@Param("nickname")String nickname, @Param("password")String password);
}
