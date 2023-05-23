package com.online_school.controllers.course;

import com.online_school.models.Course;
import com.online_school.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public String courses(Model model) {
        List<Course> courses = courseService.getAllCourse();
        model.addAttribute("courses", courses);
        return "/course/course_list";
    }

    @GetMapping("/course_detail/{id}")
    public String courseDetail(Model model, @PathVariable long id) {
        Optional<Course> course = courseService.getCourseById(id);
        model.addAttribute("course", course.orElseThrow());
        return "/course/course_detail";
    }


    @GetMapping("/courses/new")
    public String courseNew() {
        return "/course/course_new";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/courses/new")
    public String courseSave(Course course) {
        System.out.println("111");
        courseService.saveCourse(course);
        return "redirect:/";
    }


}
