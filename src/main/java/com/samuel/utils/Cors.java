package com.samuel.utils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.io.IOException;

@WebFilter(asyncSupported = true, urlPatterns = { "/*" })
public class Cors implements javax.servlet.Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException, java.io.IOException {
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Headers",  "X-Requested-With,Origin,Content-Type, Accept, Authorization");
        ((HttpServletResponse) response).addHeader("Access-Control-Max-Age", "86400");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Credentials", "true");

        chain.doFilter(request, response);
    }
}
