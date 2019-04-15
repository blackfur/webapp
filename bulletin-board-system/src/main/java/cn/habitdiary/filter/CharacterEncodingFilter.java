package cn.habitdiary.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter {
    private FilterConfig config;

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding(config.getInitParameter("charset"));
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

    public FilterConfig getConfig() {
        return config;
    }

    public void setConfig(FilterConfig config) {
        this.config = config;
    }


}
