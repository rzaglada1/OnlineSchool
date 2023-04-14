package controllers.course;

import controllers.MainController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Course;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.CourseService;
import utils.data_base.DbConnection;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet(value = "/course_detail")
public class CourseDetailController extends HttpServlet {

    ClassPathXmlApplicationContext context = MainController.context;
    CourseService courseService = context.getBean("courseService", CourseService.class);

    public void init() {
        try {
            DbConnection.getInstance().getConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("Id"));
        Optional<Course> course = courseService.getCourseById(id);

        course.ifPresent(value -> request.setAttribute("course", value));

        request.getRequestDispatcher("/WEB-INF/views/course/course_detail.jsp")
                .forward(request, response);
    }

}
