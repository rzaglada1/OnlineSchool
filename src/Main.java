

import models.*;
import repositories.*;
import services.*;
import utils.*;


public class Main {
    public static void main(String[] args) {

        //  Homework N12 .
        System.out.println("");
        System.out.println("========= Homework N13  ================");
        System.out.println("");

        MenuUtils menuUtils = new MenuUtils();
        RegexUtil regexUtil = new RegexUtil();

        CourseService courseService = new CourseService();
        //CourseRepository courseRepository = new CourseRepository();

        LectureService lectureService = new LectureService();
        //LectureRepository lectureRepository = new LectureRepository();

        PersonService personService = new PersonService();
        //PersonRepository personRepository = new PersonRepository();


        GenericsRepository<Course> courseGenericRepository = new GenericsRepository<>(new Course[]{new Course()});
        GenericsRepository<Lecture> lectureGenericRepository = new GenericsRepository<>(new Lecture[]{new Lecture()});
        GenericsRepository<Person> personGenericRepository = new GenericsRepository<>(new Person[]{new Person()});

        // creating Course
        courseGenericRepository.add(courseService.createCurse("Java course"));

        // printing repository objects
        for (Course course : courseGenericRepository.getArrayGenericRepository()) {
            System.out.println(course.toString());
        }

        System.out.println("");
        System.out.println("===========adding 3 objects and remove  index 2=========================");

        courseGenericRepository.add(new Course("Test1"));
        courseGenericRepository.add(new Course("Test2"));
        courseGenericRepository.add(new Course("Test3"));
        courseGenericRepository.remove(2);
        for (Course course : courseGenericRepository.getArrayGenericRepository()) {
            System.out.println(course.toString());
        }
        System.out.println("");
        System.out.println("===============adding object in index 1=====================");
        courseGenericRepository.add(new Course("Test4"), 1);
        for (Course course : courseGenericRepository.getArrayGenericRepository()) {
            System.out.println(course.toString());
        }
        System.out.println("");
        System.out.println("=============Is empty, size, get index 2, get index 4 =======================");
        try {
            System.out.println("Get index 2  " + courseGenericRepository.get(2).toString());
            System.out.println("Get index 2  " + courseGenericRepository.get(4).toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index out of bounds");
        }

        System.out.println("is empty " + courseGenericRepository.isEmpty());
        System.out.println("Size " + courseGenericRepository.size());


        System.out.println("");
        System.out.println("====================================");


        while (true) {

            int category = menuUtils.checkCorrect();

            switch (category) {
                case 1:
                    System.out.println("Selected   - \"1 - Objects course\" ");
                    System.out.println("");
                    // printing repository objects
                    //courseService.printObjectsRepository(courseRepository);
                    for (Course course : courseGenericRepository.getArrayGenericRepository()) {
                        System.out.println(course.toString());
                    }
                    System.out.println("================================");
                    System.out.println(Course.getCreateCount() + " Course objects created since beginning");
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
                    System.out.println(Lecture.getCreateCount() + " Lecture objects created since beginning");
                    System.out.println("Total " + lectureGenericRepository.objectsTotal() + " Lecture objects");
                    break;

                case 3:
                    System.out.println("Selected   - \"3 - Creating course\" ");
                    System.out.println("Enter name Course");
                    courseGenericRepository.add(courseService.createCurse(menuUtils.inputString()));
                    System.out.println("================================");
                    System.out.println(Course.getCreateCount() + " Course objects created since beginning");
                    System.out.println("Total " + courseGenericRepository.objectsTotal() + " Course objects");
                    break;

                case 4:
                    System.out.println("Selected   - \"4 - Creating lecture\" ");
                    System.out.println("Enter name Lecture");
                    String nameLecture = menuUtils.inputString();
                    System.out.println("Enter description Lecture");
                    String descriptionLecture = menuUtils.inputString();
                    lectureGenericRepository.add(lectureService.createLecture(nameLecture, descriptionLecture));
                    System.out.println("================================");
                    System.out.println(Lecture.getCreateCount() + " Lecture objects created since beginning");
                    System.out.println("Total " + lectureGenericRepository.objectsTotal() + " Lecture objects");
                    break;

                case 5:
                    System.out.println("Selected   - \"5 - Creating teacher");

                    Person personTeacher = personService.createPerson(regexUtil.personAttribute(),
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
                    Person personStudent = personService.createPerson(regexUtil.personAttribute(),
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

            }
            if (category == 0) {
                System.out.println("Exiting program ...");
                break;
            }
        }
    }


}


