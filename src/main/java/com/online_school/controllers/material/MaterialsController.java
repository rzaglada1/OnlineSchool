package com.online_school.controllers.material;

import com.online_school.models.model_enum.ResourceType;
import com.online_school.services.AddMaterialsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;


@Controller
public class MaterialsController {

    private final AddMaterialsService addMaterialsService;

    public MaterialsController(AddMaterialsService addMaterialsService) {
        this.addMaterialsService = addMaterialsService;
    }

    @GetMapping("/materials/category")
    public String materials(Model model) {
        Map<ResourceType, Long> mapMaterials = addMaterialsService.countCategory();
        model.addAttribute("mapMaterials", mapMaterials);
        return "/materials/material_list_category";
    }

}
