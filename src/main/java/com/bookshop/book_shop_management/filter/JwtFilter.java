package com.bookshop.book_shop_management.filter;

import com.bookshop.book_shop_management.service.impl.AdminDetailsService;
import com.bookshop.book_shop_management.service.impl.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final ApplicationContext context;

    public JwtFilter(JwtService jwtService, ApplicationContext context) {
        this.jwtService = jwtService;
        this.context = context;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String userName = null;
        if (authHeader != null && authHeader.startsWith("Bearer")) {
            token = authHeader.substring(7);
            userName = jwtService.extractUserName(token);
        }
        //  Check the Security Context Holder Are hold Authentication Or Not
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // load User Details For its Add The Security Context To This User Are Authenticated User
            UserDetails userDetails =context.getBean(AdminDetailsService.class).loadUserByUsername(userName);
            if(jwtService.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken authenticationToken =new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
