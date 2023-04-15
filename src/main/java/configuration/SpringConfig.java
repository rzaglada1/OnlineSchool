package configuration;

import controllers.course.CourseListController;
import models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import repositories.*;
import services.*;
import utils.MenuUtils;
import utils.RegexUtil;



@Configuration
@PropertySource("classpath:application.properties")
public class SpringConfig {


    private Environment environment;

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DriverManagerDataSource dataSource () {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(environment.getRequiredProperty("DRIVER"));
        dataSource.setUrl(environment.getRequiredProperty("URL"));
        dataSource.setUsername(environment.getRequiredProperty("USER"));
        dataSource.setPassword(environment.getRequiredProperty("PASSWORD"));

        return dataSource;
    }

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
