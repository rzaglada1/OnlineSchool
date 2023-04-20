package configuration;

import models.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import repositories.*;
import services.*;
import utils.MenuUtils;
import utils.RegexUtil;



@Configuration
public class SpringConfig {

    @Bean
    public CourseRepository courseRepository () {
        return new CourseRepository();
    }
    @Bean
    public CourseService courseService () {
        return new CourseService();
    }

    @Bean
    public LectureRepository lectureRepository () {
        return new LectureRepository();
    }
    @Bean
    public LectureService lectureService () {
        return new LectureService();
    }

    @Bean
    public PersonRepository personRepository () {
        return new PersonRepository();
    }
    @Bean
    public PersonService personService () {
        return new PersonService();
    }


    @Bean
    public AddMaterialsRepository addMaterialsRepository () {
        return new AddMaterialsRepository();
    }
    @Bean
    public AddMaterialsService addMaterialsService () {
        return new AddMaterialsService();
    }

    @Bean
    public HomeWorkRepository homeWorkRepository () {
        return new HomeWorkRepository();
    }
    @Bean
    public HomeworkService homeworkService () {
        return new HomeworkService();
    }

    //=================================================

    @Bean
    public MenuUtils  menuUtils(){
        return new MenuUtils();
    }

    @Bean
    public RegexUtil regexUtil(){
        return new RegexUtil();
    }

    @Bean
    public Person person(){
        return new Person();
    }

    @Bean
    public Lecture lecture(){
        return new Lecture();
    }

    @Bean
    public AddMaterials addMaterials(){
        return new AddMaterials();
    }


}
