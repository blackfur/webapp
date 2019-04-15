package cn.habitdiary.utils;

import javax.servlet.http.Cookie;

/**
 * Cookie查找的工具类
 *
 */
public class CookieUtils {

    public static Cookie findCookie(Cookie[] cookies, String name){
        if (cookies == null) {
            //说明客户端没有携带cookie
            return null;
        } else {
            //说明客户端携带了cookie
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
            return null;
        }
    }
}