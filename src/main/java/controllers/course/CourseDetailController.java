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
import java.util.Optional;

@WebServlet(value = "/course_detail")
public class CourseDetailController extends HttpServlet {

    private final AnnotationConfigApplicationContext context = MainController.context;
    private final CourseService courseService = context.getBean("courseService", CourseService.class);



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("Id"));
        Optional<Course> course = courseService.getCourseById(id);

        course.ifPresent(value -> request.setAttribute("course", value));

        request.getRequestDispatcher("/WEB-INF/views/course/course_detail.jsp")
                .forward(request, response);
    }

}
