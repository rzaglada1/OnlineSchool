package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Course;
import repositories.CourseRepository;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/")
public class CourseController extends HttpServlet {

    private final CourseRepository courseRepository = CourseRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Course> courses = courseRepository.getRepository();
       // courses.forEach(System.out::println);
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("/WEB-INF/views/course/course_list.jsp")
                .forward(request, response);
    }
}
