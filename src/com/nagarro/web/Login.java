package com.nagarro.web;

import com.nagarro.entity.UsersEntity;
import com.nagarro.util.HibernateUtil;
import javax.servlet.annotation.WebServlet;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class Login {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Session session = HibernateUtil.sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from UsersEntity U where U.username=:username");
        query.setParameter("username",username);
        UsersEntity user = (UsersEntity) query.uniqueResult();
        session.getTransaction();
        session.close();

        if(user==null){
            response.getWriter().print("No users found");
        }else if(user.getPassword().equals(password)){
            response.getWriter().print("Logging in");
            response.addCookie(new Cookie("username",username));
            response.addCookie(new Cookie("password",password));
        }
        else{
            response.getWriter().print("Invalid password");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("Welcome");
    }

}

