package controllers.person;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Person;
import repositories.PersonRepository;
import utils.data_base.DbConnection;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/persons/sort")
public class PersonListSortedCharController extends HttpServlet {
    PersonRepository personRepository = PersonRepository.getInstance();
    public void init() {
        try {
            DbConnection.getInstance().getConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Person> persons = personRepository.NameFilterByChar();
        request.setAttribute("persons", persons);

        request.getRequestDispatcher("/WEB-INF/views/person/person_list_filter_name_char.jsp")
                .forward(request, response);
    }
}
