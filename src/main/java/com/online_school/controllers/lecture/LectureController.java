package com.online_school.controllers.lecture;

import com.online_school.models.Lecture;
import com.online_school.services.LectureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class LectureController {
    private final LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @GetMapping("/lectures/homeworks")
    public String lectureHomework(Model model) {
        List<Lecture> lectures = lectureService.firstLectureMaxHomework();
        model.addAttribute("lectures", lectures);
        return "/lecture/lecture_list_homeworks";
    }


}
