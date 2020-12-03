package main.java.com.nagarro.web;

import javax.servlet.annotation.WebServlet;
import org.hibernate.Session;
import org.hibernate.query.Query;

import main.java.com.nagarro.entity.UsersEntity;
import main.java.com.nagarro.util.HibernateUtil;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Login")
public class Login extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("check2");
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("./login.jsp");
    }

}

