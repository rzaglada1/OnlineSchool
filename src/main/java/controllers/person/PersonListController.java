package controllers.person;

import controllers.MainController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.PersonService;


import java.io.IOException;
import java.util.List;

@WebServlet("/persons/students")
public class PersonListController extends HttpServlet {

    private final AnnotationConfigApplicationContext context = MainController.context;
    private final PersonService personService = context.getBean("personService", PersonService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Person> persons = personService.sortedStudentByLastName();
        request.setAttribute("persons", persons);

        request.getRequestDispatcher("/WEB-INF/views/person/person_list.jsp")
                .forward(request, response);
    }
}
