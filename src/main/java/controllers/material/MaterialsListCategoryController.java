package controllers.material;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.model_enum.ResourceType;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.AddMaterialsService;
import utils.data_base.DbConnection;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/materials/category")
public class MaterialsListCategoryController extends HttpServlet {

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    AddMaterialsService addMaterialsService = context.getBean("addMaterialsService", AddMaterialsService.class);

    public void init() {
        try {
            DbConnection.getInstance().getConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<ResourceType,Long> mapMaterials = addMaterialsService.countCategory();
        request.setAttribute("mapMaterials", mapMaterials);


        request.getRequestDispatcher("/WEB-INF/views/materials/material_list_category.jsp")
                .forward(request, response);
    }

}
