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
import java.util.List;


@WebServlet(urlPatterns = "/courses")
public class CourseListController extends HttpServlet {


    private final AnnotationConfigApplicationContext context = MainController.context;
    private final CourseService courseService = context.getBean("courseService", CourseService.class);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Course> courses = courseService.getAllCourse();
        request.setAttribute("courses", courses);

        request.getRequestDispatcher("/WEB-INF/views/course/course_list.jsp")
                .forward(request, response);
    }
}
