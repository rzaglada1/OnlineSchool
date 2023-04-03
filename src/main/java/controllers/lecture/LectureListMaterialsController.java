package controllers.lecture;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Lecture;
import repositories.LectureRepository;
import utils.data_base.DbConnection;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/lectures/materials")
public class LectureListMaterialsController extends HttpServlet {

    LectureRepository lectureRepository = LectureRepository.getInstance();

    public void init() {
        try {
            DbConnection.getInstance().getConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Lecture> lectures = lectureRepository.lectureAddMaterialSortedByDate();
        request.setAttribute("lectures", lectures);

        request.getRequestDispatcher("/WEB-INF/views/lecture/lecture_list_materials.jsp")
                .forward(request, response);
    }
}

