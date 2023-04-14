package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import utils.data_base.DbConnection;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/")
public class MainController extends HttpServlet {


    public static ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext("applicationContext.xml");

    public void init() {
        try {
            DbConnection.getInstance().getConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/views/index.jsp")
                .forward(request, response);
    }


}
