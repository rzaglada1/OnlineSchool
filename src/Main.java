

import ModelEnum.ResourceType;
import ModelEnum.Role;
import exceptions.EntityNotFoundException;
import exceptions.ValidationException;
import models.*;
import repositories.*;
import services.*;
import utils.*;


public class Main {
    public static void main(String[] args) {
        int inputID;

        MenuUtils menuUtils = new MenuUtils();
        RegexUtil regexUtil = new RegexUtil();

        CourseService courseService = new CourseService();
        LectureService lectureService = new LectureService();
        PersonService personService = new PersonService();
        HomeworkService homeworkService = new HomeworkService();
        AddMaterialsService addMaterialsService = new AddMaterialsService();

        System.out.println("");

        ListRepository<Course> courseRepository = new ListRepository<>();
        ListRepository<Lecture> lectureRepository = new ListRepository<>();
        ListRepository<Person> personRepository = new ListRepository<>();
        ListRepository<Homework> homeworkRepository = new ListRepository<>();
        ListRepository<AddMaterials> addMaterialsRepository = new ListRepository<>();


        System.out.println("What is in the repository?");
        System.out.println("================================");
        // creating Course
        courseRepository.getRepository().add(courseService.create("Java course"));

        // printing repository objects
        for (Course course : courseRepository.getRepository()) {
            System.out.println(course.toString());
        }

        // creating Lecture
        for (int i = 0; i < 5; i++) {
            lectureRepository.getRepository().add(lectureService.create("Lecture " + i));
        }

        // printing repository objects
        for (Lecture lecture : lectureRepository.getRepository()) {
            System.out.println(lecture.toString());
        }


        System.out.println("");



        System.out.println("=============== Homework 17 =====================");

        System.out.println("");
        System.out.println("====================================");


        while (true) {

            int category = menuUtils.checkCorrect();

            switch (category) {
                case 1:
                    System.out.println("Selected   - \"1 - Objects course\" ");
                    System.out.println("");
                    // printing repository objects
                    for (Course course : courseRepository.getRepository()) {
                        System.out.println(course.toString());
                    }
                    System.out.println("================================");
                    System.out.println("Total " + courseRepository.getRepository().size() + " Course objects");
                    break;

                case 2:
                    System.out.println("Selected   - \"2 - Objects lecture\" ");
                    System.out.println("");
                    // printing repository objects
                    for (Lecture lecture : lectureRepository.getRepository()) {
                        System.out.println(lecture.toString());
                    }

                    System.out.println("================================");
                    System.out.println("Total " + lectureRepository.getRepository().size() + " Lecture objects");
                    break;

                case 3:
                    System.out.println("Selected   - \"3 - Creating course\" ");
                    System.out.println("Enter name Course");
                    courseRepository.getRepository().add(courseService.create(menuUtils.inputString()));
                    // printing repository objects
                    for (Course course : courseRepository.getRepository()) {
                        System.out.println(course.toString());
                    }
                    System.out.println("================================");
                    System.out.println("Total " + courseRepository.getRepository().size() + " Course objects");
                    break;

                case 4:
                    System.out.println("Selected   - \"4 - Creating lecture\" ");
                    System.out.println("Enter name Lecture");
                    String nameLecture = menuUtils.inputString();
                    lectureRepository.getRepository().add(lectureService.create(nameLecture));
                    // printing repository objects
                    for (Lecture lecture : lectureRepository.getRepository()) {
                        System.out.println(lecture.toString());
                    }
                    System.out.println("================================");
                    System.out.println("Total " + lectureRepository.getRepository().size() + " Lecture objects");
                    break;

                case 5:
                    System.out.println("Selected   - \"5 - Creating teacher");

                    try {
                        Person personTeacher = personService.create(regexUtil.personAttribute(),
                                Role.TEACHER);
                        personRepository.getRepository().add(personTeacher);
                    } catch (ValidationException e) {
                        System.out.println("Something wrong, try again");
                    }

                    System.out.println("================================");
                    System.out.println("Total " + personRepository.getRepository().size() + " Person objects");

                    // printing repository objects
                    for (Person teacher : personRepository.getRepository()) {
                        if (teacher != null && teacher.getRole() == Role.TEACHER) {
                            System.out.println(teacher.toString());
                        }
                    }
                    break;

                case 6:
                    System.out.println("Selected   - \"6 - Creating student\" ");
                    try {
                        Person personStudent = personService.create(regexUtil.personAttribute(),
                                Role.STUDENT);
                        personRepository.getRepository().add(personStudent);
                    } catch (ValidationException e) {
                        System.out.println("Something wrong, try again");
                    }

                    System.out.println("================================");
                    System.out.println("Total " + personRepository.getRepository().size() + " Person objects");

                    // printing repository objects
                    for (Person student : personRepository.getRepository()) {
                        if (student != null && student.getRole() == Role.STUDENT) {
                            System.out.println(student.toString());
                        }
                    }
                    break;

                case 7:
                    System.out.println("Selected   - \"7 - Creating Homework\" ");

                    System.out.println("Enter name Homework");
                    homeworkRepository.getRepository().add(homeworkService.create(menuUtils.inputString()));
                    System.out.println("================================");
                    System.out.println("Total " + homeworkRepository.getRepository().size() + " Homework(s) object(s)");


                    // printing repository objects
                    for (Homework homeworkPrint : homeworkRepository.getRepository()) {
                        if (homeworkPrint != null) {
                            System.out.println(homeworkPrint.toString());
                        }
                    }
                    break;

                case 8:
                    System.out.println("Selected   - \"8 - Creating addMaterials\" ");

                    System.out.println("Enter name addMaterials");
                    String nameAddMaterials = menuUtils.inputString();
                    AddMaterials addMaterials;
                    try {
                        ResourceType resourceType = menuUtils.resourceType();
                        addMaterials = addMaterialsService.create(nameAddMaterials,
                                resourceType);
                    } catch (ValidationException e) {
                        e.printStackTrace();
                        addMaterials = addMaterialsService.create(nameAddMaterials);
                    }


                    System.out.print("Enter lecture ID ");
                    inputID = menuUtils.inputDigit();

                    try {
                        lectureRepository.getById(inputID);
                        addMaterials.setLectureId(inputID);
                    } catch (EntityNotFoundException e) {
                        System.out.println("Lecture id " + inputID + " - not found");
                    }

                    addMaterialsRepository.getRepository().add(addMaterials);

                    // printing repository objects
                    for (AddMaterials addMaterials1 : addMaterialsRepository.getRepository()) {
                        System.out.println(addMaterials1.toString());
                    }
                    System.out.println("================================");
                    System.out.println("Total " + addMaterialsRepository.getRepository().size() + " AddMaterials objects");
                    break;

                case 9:
                    System.out.println("Selected   - \"9 - Get lecture by ID\" ");

                    System.out.print("Enter lecture ID ");
                    inputID = menuUtils.inputDigit();
                    try {
                        System.out.println(lectureRepository.getById(inputID).toString());
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


