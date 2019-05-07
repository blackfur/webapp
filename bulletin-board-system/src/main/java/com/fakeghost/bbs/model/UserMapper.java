package com.fakeghost.bbs.model;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import org.apache.ibatis.session.RowBounds;

public interface UserMapper{
   List<User> users(int userid);
   List<User> users(RowBounds bounds);
   User user(@Param("nickname") String nickname, @Param("password") String password);
   void register(@Param("id")int id,@Param("nickname")String nickname, @Param("password")String password);
}
