package controllers.lecture;

import controllers.MainController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Lecture;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.LectureService;


import java.io.IOException;
import java.util.List;

@WebServlet("/lectures/homeworks")
public class LectureListHomeworkController extends HttpServlet {

    private final AnnotationConfigApplicationContext context = MainController.context;
    private final LectureService lectureService = context.getBean("lectureService", LectureService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Lecture> lectures = lectureService.firstLectureMaxHomework();
        request.setAttribute("lectures", lectures);


        request.getRequestDispatcher("/WEB-INF/views/lecture/lecture_list_homeworks.jsp")
                .forward(request, response);
    }



}
