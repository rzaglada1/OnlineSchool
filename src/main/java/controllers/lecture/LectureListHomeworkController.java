package controllers.lecture;

import controllers.MainController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Lecture;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.LectureService;
import utils.data_base.DbConnection;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/lectures/homeworks")
public class LectureListHomeworkController extends HttpServlet {

    ClassPathXmlApplicationContext context = MainController.context;
    LectureService lectureService = context.getBean("lectureService", LectureService.class);

    public void init() {
        try {
            DbConnection.getInstance().getConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Lecture> lectures = lectureService.firstLectureMaxHomework();
        request.setAttribute("lectures", lectures);


        request.getRequestDispatcher("/WEB-INF/views/lecture/lecture_list_homeworks.jsp")
                .forward(request, response);
    }



}
