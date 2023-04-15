package controllers.material;

import controllers.MainController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.model_enum.ResourceType;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.AddMaterialsService;
import java.io.IOException;
import java.util.Map;

@WebServlet("/materials/category")
public class MaterialsListCategoryController extends HttpServlet {

    private final AnnotationConfigApplicationContext context = MainController.context;
    private final AddMaterialsService addMaterialsService = context.getBean("addMaterialsService", AddMaterialsService.class);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<ResourceType,Long> mapMaterials = addMaterialsService.countCategory();
        request.setAttribute("mapMaterials", mapMaterials);


        request.getRequestDispatcher("/WEB-INF/views/materials/material_list_category.jsp")
                .forward(request, response);
    }



}
