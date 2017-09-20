package com.winning.global.dispatcher;

import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wcx on 2017/8/2.
 */
public class SpringDispatcherServlet extends DispatcherServlet {

    @Override
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(request.getServletPath().matches(".*\\.weworkService$")) {
            // 扩展名为weworkService为微信相关调用免登陆，这边要做特别check
            String userAgent = request.getHeader("user-agent");
//            if(userAgent == null || !userAgent.matches(".*MicroMessenger.*")) { // todo for test
//                response.sendRedirect("/");
//            }
            super.doDispatch(request, response);
        } else {
            super.doDispatch(request, response);
        }
    }

}
