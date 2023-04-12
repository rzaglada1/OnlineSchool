package controllers.course;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Course;
import services.CourseService;
import utils.data_base.DbConnection;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/courses")
public class CourseListController extends HttpServlet {

    private final CourseService courseService;

    public CourseListController(CourseService courseService) {
        this.courseService = courseService;
    }

    public void init() {
        try {
            DbConnection.getInstance().getConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Course> courses = courseService.getAllCourse();
        request.setAttribute("courses", courses);

        request.getRequestDispatcher("/WEB-INF/views/course/course_list.jsp")
                .forward(request, response);
    }
}
