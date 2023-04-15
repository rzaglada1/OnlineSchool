package controllers.course;

import controllers.MainController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Course;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.CourseService;


import java.io.IOException;



@WebServlet(urlPatterns = "/courses/new")

public class CourseNewController extends HttpServlet {

    private final AnnotationConfigApplicationContext context = MainController.context;
    private final CourseService courseService = context.getBean("courseService", CourseService.class);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/course/course_new.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String name = request.getParameter("Name");
        courseService.saveCourse(new Course(name));
        response.sendRedirect("/");
    }
}
