package cn.habitdiary.listener;

import cn.habitdiary.domain.LoginCache;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;


public class LoginSessionListener implements HttpSessionAttributeListener {
    private final static String LOGIN_USER = "username";

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        String attrName = se.getName();
        if(attrName.equals(LOGIN_USER)){
            String attrVal = (String) se.getValue();
            HttpSession session = se.getSession();
            String sessionId = session.getId();

            String sessionId2 = LoginCache.getInstance().getSessionIdByUsername(attrVal);
            if(sessionId2 != null){//如果sessionId2 != null，说明该用户已经登录，踢除之前登录的用户会话
                HttpSession session2 = LoginCache.getInstance().getSessionBySessionId(sessionId2);
                session2.invalidate(); //使原先的会话失效
            }
            //把当前用户会话加入缓存
            LoginCache.getInstance().setSessionIdByUsername(attrVal, sessionId);
            LoginCache.getInstance().setSessionBySessionId(sessionId, session);
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

}
