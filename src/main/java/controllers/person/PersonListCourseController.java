package controllers.person;

import controllers.MainController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Person;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.PersonService;
import utils.data_base.DbConnection;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/persons/courses")
public class PersonListCourseController extends HttpServlet {
    ClassPathXmlApplicationContext context = MainController.context;
    PersonService personService = context.getBean("personService", PersonService.class);

    public void init() {
        try {
            DbConnection.getInstance().getConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       Map<Person, Integer> personIntegerMap = personService.sortedByCourses();

        request.setAttribute("personIntegerMap", personIntegerMap);

        request.getRequestDispatcher("/WEB-INF/views/person/person_list_courses.jsp")
                .forward(request, response);
    }

}
