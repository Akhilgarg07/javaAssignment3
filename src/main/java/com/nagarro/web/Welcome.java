package main.java.com.nagarro.web;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.ServletException;

@WebServlet("/welcome")
public class Welcome extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for(Cookie cookie: cookies){
                if(cookie.getName().equals("username")){
                    response.sendRedirect("ImageUtility");
                }
            }
        }
        response.sendRedirect("login.jsp");
    }
}
