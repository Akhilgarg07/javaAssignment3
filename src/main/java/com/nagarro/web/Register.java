package com.nagarro.web;
import javax.servlet.annotation.WebServlet;
import com.nagarro.entity.UsersEntity;
import com.nagarro.util.HibernateUtil;
import org.hibernate.Session;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("check1");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UsersEntity user = new UsersEntity();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(password);
        Session session = HibernateUtil.sessionFactory.openSession();
        session.beginTransaction();
        try{
            session.save(user);
            session.getTransaction().commit();
        }catch (Exception e){
            response.getWriter().println("User could not be saved");
            session.getTransaction().rollback();
            session.close();
            return;
        }
        session.close();
        response.getWriter().print("Registered Successfully");
        response.addCookie(new Cookie("username",username));
        response.addCookie(new Cookie("password",password));
        response.sendRedirect("login");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.sendRedirect("register.jsp");
    }
}
