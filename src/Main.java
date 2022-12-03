

import models.*;
import repositories.*;
import services.*;
import utils.*;


public class Main {
    public static void main(String[] args) {

        //  Homework N12 .
        System.out.println("");
        System.out.println("========= Homework N14  ================");
        System.out.println("");

        MenuUtils menuUtils = new MenuUtils();
        RegexUtil regexUtil = new RegexUtil();

        CourseService courseService = new CourseService();
        LectureService lectureService = new LectureService();
        PersonService personService = new PersonService();
        HomeworkService homeworkService = new HomeworkService();


        GenericsRepository<Course> courseGenericRepository = new GenericsRepository<>(new Course[]{new Course()});
        GenericsRepository<Lecture> lectureGenericRepository = new GenericsRepository<>(new Lecture[]{new Lecture()});
        GenericsRepository<Person> personGenericRepository = new GenericsRepository<>(new Person[]{new Person()});
        GenericsRepository<Homework> homeworkGenericRepository = new GenericsRepository<>(new Homework[]{new Homework()});

        // creating Course
        courseGenericRepository.add(courseService.create("Java course"));

        // printing repository objects
        for (Course course : courseGenericRepository.getArrayGenericRepository()) {
            System.out.println(course.toString());
        }

        // creating Lecture
        lectureGenericRepository.add(lectureService.create("Lecture 1"));

        // printing repository objects
        for (Lecture lecture : lectureGenericRepository.getArrayGenericRepository()) {
            System.out.println(lecture.toString());
        }


        System.out.println("");
        System.out.println("====================================");


        while (true) {

            int category = menuUtils.checkCorrect();

            switch (category) {
                case 1:
                    System.out.println("Selected   - \"1 - Objects course\" ");
                    System.out.println("");
                    // printing repository objects
                    for (Course course : courseGenericRepository.getArrayGenericRepository()) {
                        System.out.println(course.toString());
                    }
                    System.out.println("================================");
                    System.out.println("Total " + courseGenericRepository.objectsTotal() + " Course objects");
                    break;

                case 2:
                    System.out.println("Selected   - \"2 - Objects lecture\" ");
                    System.out.println("");
                    // printing repository objects
                    for (Lecture lecture : lectureGenericRepository.getArrayGenericRepository()) {
                        System.out.println(lecture.toString());
                    }

                    System.out.println("================================");
                    System.out.println("Total " + lectureGenericRepository.objectsTotal() + " Lecture objects");
                    break;

                case 3:
                    System.out.println("Selected   - \"3 - Creating course\" ");
                    System.out.println("Enter name Course");
                    courseGenericRepository.add(courseService.create(menuUtils.inputString()));
                    // printing repository objects
                    for (Course course : courseGenericRepository.getArrayGenericRepository()) {
                        System.out.println(course.toString());
                    }
                    System.out.println("================================");
                    System.out.println("Total " + courseGenericRepository.objectsTotal() + " Course objects");
                    break;

                case 4:
                    System.out.println("Selected   - \"4 - Creating lecture\" ");
                    System.out.println("Enter name Lecture");
                    String nameLecture = menuUtils.inputString();
                    lectureGenericRepository.add(lectureService.create(nameLecture));
                    // printing repository objects
                    for (Lecture lecture : lectureGenericRepository.getArrayGenericRepository()) {
                        System.out.println(lecture.toString());
                    }
                    System.out.println("================================");
                    System.out.println("Total " + lectureGenericRepository.objectsTotal() + " Lecture objects");
                    break;

                case 5:
                    System.out.println("Selected   - \"5 - Creating teacher");

                    Person personTeacher = personService.create(regexUtil.personAttribute(),
                            Role.TEACHER, courseGenericRepository.getArrayGenericRepository()[0]);

                    personGenericRepository.add(personTeacher);

                    System.out.println("================================");
                    System.out.println("Total " + personGenericRepository.objectsTotal() + " Person objects");

                    // printing repository objects
                    for (Person teacher : personGenericRepository.getArrayGenericRepository()) {
                        if (teacher != null && teacher.getRole() == Role.TEACHER) {
                            System.out.println(teacher.toString());
                        }
                    }
                    break;

                case 6:
                    System.out.println("Selected   - \"6 - Creating student\" ");
                    Person personStudent = personService.create(regexUtil.personAttribute(),
                            Role.STUDENT, courseGenericRepository.getArrayGenericRepository()[0]);
                    personGenericRepository.add(personStudent);
                    System.out.println("================================");
                    System.out.println("Total " + personGenericRepository.objectsTotal() + " Person objects");

                    // printing repository objects
                    for (Person student : personGenericRepository.getArrayGenericRepository()) {
                        if (student != null && student.getRole() == Role.STUDENT) {
                            System.out.println(student.toString());
                        }
                    }
                    break;

                case 7:
                    System.out.println("Selected   - \"7 - Creating Homework\" ");

                    System.out.println("Enter name Homework");
                    homeworkGenericRepository.add(homeworkService.create(menuUtils.inputString()));
                    System.out.println("================================");
                    System.out.println("Total " + homeworkGenericRepository.objectsTotal() + " Homework(s) object(s)");

                    // adding  array homework in lecture
                    try {
                        lectureGenericRepository.get(0).setHomework(homeworkGenericRepository.getArrayGenericRepository());
                    } catch (Exception e) {
                        System.out.println("Lecture not found");
                    }


                    // printing repository objects
                    for (Homework homeworkPrint : homeworkGenericRepository.getArrayGenericRepository()) {
                        if (homeworkPrint != null) {
                            System.out.println(homeworkPrint.toString());
                        }
                    }
                    break;

            }
            if (category == 0) {
                System.out.println("Exiting program ...");
                break;
            }
        }
    }


}


