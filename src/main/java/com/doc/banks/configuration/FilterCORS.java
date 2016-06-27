package com.doc.banks.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class FilterCORS extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    response.addHeader("Access-Control-Allow-Origin", "*");

    // CORS "pre-flight" request
    response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");

    response.addHeader("Access-Control-Allow-Headers",
        "x-requested-with, Authorization, client_user, content-type, accept, authenticate");
    response.addHeader("Access-Control-Max-Age", "0");

    filterChain.doFilter(request, response);
  }

}
