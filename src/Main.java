

import models.*;
import repositories.*;
import services.*;
import utils.*;


public class Main {
    public static void main(String[] args) {

        //  Homework N12 .
        System.out.println("");
        System.out.println("========= Homework N12  ================");
        System.out.println("");

        MenuUtils menuUtils = new MenuUtils();

        CourseService courseService = new CourseService();
        CourseRepository courseRepository = new CourseRepository();

        LectureService lectureService = new LectureService();
        LectureRepository lectureRepository = new LectureRepository();

        PersonService personService = new PersonService();
        PersonRepository personRepository = new PersonRepository();

        HomeworkService homeworkService = new HomeworkService();
        HomeworkRepository homeworkRepository = new HomeworkRepository();

        AddTaskService addTaskService = new AddTaskService();
        AddTaskRepository addTaskRepository = new AddTaskRepository();


        // creating Course
        courseRepository.add(courseService.createCurse("Java course"));

        // creating three Lectures
        int idCourse = courseRepository.getAll()[0].getID();
        System.out.println("idCourse  " + idCourse);

        for (int i = 0; i < 3; i++) {
            lectureRepository.add(lectureService.createLecture("Lecture " + (i + 1), courseRepository.getAll()[0]));
        }

        System.out.println("Created " + Lecture.getCreateCount() + " lecture objects ");

        // printing repository objects
        lectureService.printObjectsRepository(lectureRepository);

        //===============================


        //================================


        while (true) {

            int category = menuUtils.checkCorrect();

            switch (category) {
                case 1:
                    System.out.println("Selected   - \"1 - Objects course\" ");
                    System.out.println("");
                    // printing repository objects
                    courseService.printObjectsRepository(courseRepository);
                    System.out.println("================================");
                    System.out.println(Course.getCreateCount() + " Course objects created since beginning");
                    System.out.println("Total " + courseRepository.objectsTotal() + " Course objects");
                    break;

                case 2:
                    System.out.println("Selected   - \"2 - Objects lecture\" ");
                    System.out.println("");
                    // printing repository objects
                    for (Model lecture : lectureRepository.getAll()) {
                        if (lecture != null) {
                            System.out.println(lecture);
                        }
                    }

                    System.out.println("================================");
                    System.out.println(Lecture.getCreateCount() + " Lecture objects created since beginning");
                    System.out.println("Total " + lectureRepository.objectsTotal() + " Lecture objects");
                    break;

                case 3:
                    System.out.println("Selected   - \"3 - Creating course\" ");
                    System.out.println("Enter name Course");
                    courseRepository.add(courseService.createCurse(menuUtils.inputString()));
                    System.out.println("================================");
                    System.out.println(Course.getCreateCount() + " Course objects created since beginning");
                    System.out.println("Total " + courseRepository.objectsTotal() + " Course objects");
                    break;

                case 4:
                    System.out.println("Selected   - \"4 - Creating lecture\" ");
                    System.out.println("Enter name Lecture");
                    String nameLecture = menuUtils.inputString();
                    System.out.println("Enter description Lecture");
                    String descriptionLecture = menuUtils.inputString();
                    lectureRepository.add(lectureService.createLecture(nameLecture, descriptionLecture));
                    System.out.println("================================");
                    System.out.println(Lecture.getCreateCount() + " Lecture objects created since beginning");
                    System.out.println("Total " + lectureRepository.objectsTotal() + " Lecture objects");
                    break;

                case 5:
                    System.out.println("Selected   - \"5 - Creating teacher and adding in lecture");
                    System.out.print("Enter lecture ID: ");
                    int enterLectureID = menuUtils.inputDigit();
                    if (lectureRepository.getById(enterLectureID) == null) {
                        break;
                    }
                    Person personTeacher = personService.createPerson(menuUtils.personAttribute(),
                            Role.TEACHER, courseRepository.getAll()[0]);
                    personRepository.add(personTeacher);

                    System.out.println("================================");
                    System.out.println("Total " + personRepository.objectsTotalTeacher() + " Teacher objects");

                    // set Teacher in Lecture
                    lectureRepository.setTeacher(lectureRepository.getById(enterLectureID), personTeacher);

                    // printing repository objects
                    for (Model teacher : personRepository.getAll()) {
                        if (teacher != null && ((Person) teacher).getRole() == Role.TEACHER) {
                            System.out.println(teacher.toString());
                        }
                    }
                    break;

                case 6:
                    System.out.println("Selected   - \"6 - Creating student\" ");
                    Person personStudent = personService.createPerson(menuUtils.personAttribute(),
                            Role.STUDENT, courseRepository.getAll()[0]);
                    personRepository.add(personStudent);
                    System.out.println("================================");
                    System.out.println("Total " + personRepository.objectsTotalStudent() + " Student objects");

                    // printing repository objects
                    for (Model teacher : personRepository.getAll()) {
                        if (teacher != null && ((Person) teacher).getRole() == Role.STUDENT) {
                            System.out.println(teacher.toString());
                        }
                    }
                    break;

                case 7:
                    System.out.println("Selected   - \"7 - Open Lecture by ID\" ");
                    System.out.print("Enter ID: ");
                    int enterID = menuUtils.inputDigit();
                    if (lectureRepository.getById(enterID) != null) {
                        System.out.println(lectureRepository.getById(enterID).toString());
                    }
                    break;
                case 8:
                    System.out.println("Selected   - \"8 - Delete Lecture by ID\" ");
                    System.out.println("Enter lecture ID");
                    lectureRepository.deleteById(menuUtils.inputDigit());
                    break;


            }
            if (category == 0) {
                System.out.println("Exiting program ...");
                break;
            }
        }
    }


}


