package cn.habitdiary.domain;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class LoginCache { //使用单例模式保证用户缓存只创建一个
    private static LoginCache ourInstance = new LoginCache();

    //建立用户名和SessionId之间的映射,SessionId是一个会话的唯一标识
    private Map<String, String> loginUserSession = new HashMap<String,String>();
    //建立SessionId和Session之间的映射
    private Map<String,HttpSession> loginSession = new HashMap<String,HttpSession>();

    public static LoginCache getInstance() {
        return ourInstance;
    }

    private LoginCache() {

    }

    public String getSessionIdByUsername(String username){
        return loginUserSession.get(username);
    }

    public HttpSession getSessionBySessionId(String sessionId){
        return loginSession.get(sessionId);
    }
    public void setSessionIdByUsername(String username,String sessionId){
        loginUserSession.put(username,sessionId);
    }

    public void setSessionBySessionId(String sessionId,HttpSession session){
        loginSession.put(sessionId,session);
    }
}