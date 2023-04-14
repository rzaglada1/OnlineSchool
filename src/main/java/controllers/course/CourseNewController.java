package controllers.course;

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


@WebServlet(urlPatterns = "/courses/new")

public class CourseNewController extends HttpServlet {

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
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
        request.getRequestDispatcher("/WEB-INF/views/course/course_new.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String name = request.getParameter("Name" );
        courseService.saveCourse(new Course(name));
        response.sendRedirect("/");
    }
}