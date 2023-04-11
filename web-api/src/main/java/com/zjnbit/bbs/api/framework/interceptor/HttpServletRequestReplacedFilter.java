package com.zjnbit.bbs.api.framework.interceptor;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author 陈俊羽
 * @emp chenjunyu1 211100011
 * @date 2023/4/11 16:22
 * @Description
 **/
@WebFilter(urlPatterns = "/*", filterName = "requestReplaced")
public class HttpServletRequestReplacedFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        ServletRequest requestWrapper = null;
        if (request instanceof HttpServletRequest) {
            requestWrapper = new MyHttpServletRequestWrapper((HttpServletRequest) request);
        }
        if (requestWrapper == null) {
            chain.doFilter(request, response);
        } else {
            chain.doFilter(requestWrapper, response);
        }
    }


    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }
}
