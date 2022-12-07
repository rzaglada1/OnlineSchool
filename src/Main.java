

import exceptions.EntityNotFoundException;
import exceptions.ValidationException;
import models.*;
import repositories.*;
import services.*;
import utils.*;


public class Main {
    public static void main(String[] args) {

        MenuUtils menuUtils = new MenuUtils();
        RegexUtil regexUtil = new RegexUtil();

        CourseService courseService = new CourseService();
        LectureService lectureService = new LectureService();
        PersonService personService = new PersonService();
        HomeworkService homeworkService = new HomeworkService();


        GenericsRepository<Course> courseGenericRepository = new GenericsRepository<>(new Course[]{new Course()});
        System.out.println("");

        GenericsRepository<Lecture> lectureGenericRepository = new GenericsRepository<>(new Lecture[]{new Lecture()});
        GenericsRepository<Person> personGenericRepository = new GenericsRepository<>(new Person[]{new Person()});
        GenericsRepository<Homework> homeworkGenericRepository = new GenericsRepository<>(new Homework[]{new Homework()});


        System.out.println("What is in the repository?");
        System.out.println("================================");
        // creating Course
        courseGenericRepository.add(courseService.create("Java course"));

        // printing repository objects
        for (Course course : courseGenericRepository.getArrayGenericRepository()) {
            System.out.println(course.toString());
        }

        // creating Lecture
        for (int i = 0; i < 5; i++) {
            lectureGenericRepository.add(lectureService.create("Lecture " + i));
        }

        // printing repository objects
        for (Lecture lecture : lectureGenericRepository.getArrayGenericRepository()) {
            System.out.println(lecture.toString());
        }


        System.out.println("");

        System.out.println("=============== Homework 16 =====================");

        SimpleIterator<Lecture> simpleIteratorLecture = lectureGenericRepository.simpleIterator();

        System.out.println("");
        System.out.println("Remove object Lecture from index 2");

        try {
            simpleIteratorLecture.next();
            simpleIteratorLecture.next();
            simpleIteratorLecture.next();
            simpleIteratorLecture.remove();

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array index out");
            System.out.println("");
        }


        System.out.println("");
        System.out.println("Printing objects from method findAll()");
        lectureGenericRepository.findAll();

//        System.out.println("");
//        System.out.println("Printing all remaining objects from simpleIterator ");
//        while (simpleIteratorLecture.hasNext()) {
//            System.out.println(simpleIteratorLecture.next() );
//        }
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

                    try {
                        Person personTeacher = personService.create(regexUtil.personAttribute(),
                                Role.TEACHER, courseGenericRepository.getArrayGenericRepository()[0]);
                        personGenericRepository.add(personTeacher);
                    } catch (ValidationException e) {
                        System.out.println("Something wrong, try again");
                    }

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
                    try {
                        Person personStudent = personService.create(regexUtil.personAttribute(),
                                Role.STUDENT, courseGenericRepository.getArrayGenericRepository()[0]);
                        personGenericRepository.add(personStudent);
                    } catch (ValidationException e) {
                        System.out.println("Something wrong, try again");
                    }

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
                    } catch (ArrayIndexOutOfBoundsException e) {
                        //e.printStackTrace();
                        System.out.println("Lecture not found");
                    }


                    // printing repository objects
                    for (Homework homeworkPrint : homeworkGenericRepository.getArrayGenericRepository()) {
                        if (homeworkPrint != null) {
                            System.out.println(homeworkPrint.toString());
                        }
                    }
                    break;

                case 8:
                    System.out.println("Selected   - \"8 - Get lecture by ID\" ");

                    System.out.print("Enter lecture ID ");
                    int inputID = menuUtils.inputDigit();
                    try {
                        System.out.println(lectureGenericRepository.getById(inputID).toString());
                    } catch (EntityNotFoundException e) {
                        System.out.println(e.getMessage());
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


