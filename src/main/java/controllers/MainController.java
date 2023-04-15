package controllers;

import configuration.SpringConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.io.IOException;


@WebServlet(urlPatterns = "/")
public class MainController extends HttpServlet {


    public static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);




    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/views/index.jsp")
                .forward(request, response);
    }


}
