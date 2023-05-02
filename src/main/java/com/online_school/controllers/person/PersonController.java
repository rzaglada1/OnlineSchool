package com.online_school.controllers.person;

import com.online_school.models.Person;
import com.online_school.services.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/persons/students")
    public String personList(Model model) {
        List<Person> persons = personService.sortedStudentByLastName();
        model.addAttribute("persons", persons);
        return "/person/person_list";
    }


    @GetMapping("/persons/courses")
    public String personListCourse(Model model) {
        Map<Person, Integer> personIntegerMap = personService.sortedByCourses();
        model.addAttribute("personIntegerMap", personIntegerMap);
        return "/person/person_list_courses";
    }

    @GetMapping("/persons/sort")
    public String personSort(Model model) {
        List<Person> persons = personService.NameFilterByChar();
        model.addAttribute("persons", persons);
        return "/person/person_list_filter_name_char";
    }


}
