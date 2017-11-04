package com.example.config;

import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by asheesh.dwivedi on 1/21/2016.
 */

class CorsFilter implements Filter {


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {


        if (req instanceof HttpServletRequest && res instanceof HttpServletResponse) {

            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) res;


                // Access-Control-Allow-Origin
                String origin = request.getHeader("Origin");

                response.setHeader("Access-Control-Allow-Origin", origin);

                response.setHeader("Vary", "Origin");

                // Access-Control-Max-Age
                response.setHeader("Access-Control-Max-Age", "3600");

                // Access-Control-Allow-Credentials
                response.setHeader("Access-Control-Allow-Credentials", "true");

                // Access-Control-Allow-Methods
                response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");

                // Access-Control-Allow-Headers
                response.setHeader("Access-Control-Allow-Headers",
                        "Origin, X-Requested-With, Content-Type, Accept, ");
            if (request.getMethod()!="OPTIONS") {
                chain.doFilter(req, res);
            }
        }


    }

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public  void destroy() {}

}