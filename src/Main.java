

import models.model_enum.ResourceType;
import models.model_enum.Role;
import exceptions.EntityNotFoundException;
import exceptions.ValidationException;
import models.*;
import repositories.*;
import services.*;
import utils.*;

import java.util.Comparator;

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

        ListRepository<Course> courseRepository = new ListRepository<>();
        ListRepository<Lecture> lectureRepository = new ListRepository<>();
        ListRepository<Person> personRepository = new ListRepository<>();

        MapRepository<Integer, Homework> homeworkRepository = new MapRepository<>();
        MapRepository<Integer, AddMaterials> addMaterialsRepository = new MapRepository<>();


        System.out.println('\n' + "What is in the repository?");
        System.out.println("================================");
        // creating Course
        courseRepository.getRepository().add(courseService.create("Java course"));
        courseRepository.getRepository().add(courseService.create("C++ course"));
        courseRepository.getRepository().add(courseService.create("C# course"));
        courseRepository.getRepository().add(courseService.create("Python course"));

        personRepository.getRepository().add(personService.create(new String[]{"Олена", "Романенко", "+380989584545", "Dasdasd@sdf.sdf"}
                , Role.STUDENT));
        personRepository.getRepository().add(personService.create(new String[]{"Олена", "Водерацький", "+380989584545", "Dasdasd@sdf.sdf"}
                , Role.TEACHER));
        personRepository.getRepository().add(personService.create(new String[]{"Олена", "Ломачевський", "+380989584545", "Dasdasd@sdf.sdf"}
                , Role.STUDENT));
        personRepository.getRepository().add(personService.create(new String[]{"Олена", "Андрієнко", "+380989584545", "Dasdasd@sdf.sdf"}
                , Role.STUDENT));
        personRepository.getRepository().add(personService.create(new String[]{"Олена", "Командний", "+380989584545", "Dasdasd@sdf.sdf"}
                , Role.TEACHER));
        personRepository.getRepository().add(personService.create(new String[]{"Олена", "Солітер", "+380989584545", "Dasdasd@sdf.sdf"}
                , Role.STUDENT));

        addMaterialsRepository.getRepository().put(1, addMaterialsService.create("Video", ResourceType.VIDEO, 1));
        addMaterialsRepository.getRepository().put(3, addMaterialsService.create("Text URL", ResourceType.URL, 3));
        addMaterialsRepository.getRepository().put(2, addMaterialsService.create("Text book", ResourceType.BOOK, 2));


        // printing repository objects
        addMaterialsRepository.getRepository().entrySet().forEach(System.out::println);

        // printing repository objects
        personRepository.getRepository().forEach(System.out::println);

        // printing repository objects
        courseRepository.getRepository().forEach(System.out::println);

        // creating Lecture
        for (int i = 0; i < 5; i++) {
            lectureRepository.getRepository().add(lectureService.create("Lecture " + i));
        }

        // printing repository objects
        lectureRepository.getRepository().forEach(System.out::println);

        System.out.println('\n' + "=============== Homework 19 =====================");

        System.out.println('\n' + "====================================");


        while (true) {

            int category = menuUtils.checkCorrect();

            switch (category) {
                case 1:
                    System.out.println("Selected   - \"1 - Objects course\" ");
                    // printing repository objects
                    courseRepository.getRepository().forEach(System.out::println);
                    break;

                case 2:
                    System.out.println("Selected   - \"2 - Objects lecture\" ");
                    // printing repository objects
                    lectureRepository.getRepository().forEach(System.out::println);
                    break;

                case 3:
                    System.out.println("Selected   - \"3 - Creating course\" ");
                    System.out.println("Enter name Course");
                    courseRepository.getRepository().add(courseService.create(menuUtils.inputString()));
                    // printing repository objects
                    courseRepository.getRepository().forEach(System.out::println);
                    break;

                case 4:
                    System.out.println("Selected   - \"4 - Creating lecture\" ");
                    System.out.println("Enter name Lecture");
                    String nameLecture = menuUtils.inputString();
                    lectureRepository.getRepository().add(lectureService.create(nameLecture));
                    // printing repository objects
                    lectureRepository.getRepository().forEach(System.out::println);
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

                    personRepository.getRepository().stream()
                            .filter(person -> person.getRole() == Role.TEACHER)
                            .forEach(System.out::println);
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

                    personRepository.getRepository().stream()
                            .filter(person -> person.getRole() == Role.STUDENT)
                            .forEach(System.out::println);
                    break;

                case 7:
                    System.out.println("Selected   - \"7 - Creating Homework\" ");

                    System.out.println("Enter name Homework");
                    String nameHomework = menuUtils.inputString();
                    System.out.print("Enter lecture ID ");
                    int lectureID = menuUtils.inputDigit();
                    try {
                        lectureRepository.getById(lectureID);
                        homeworkRepository.getRepository().put(lectureID, homeworkService.create(nameHomework, lectureID));
                    } catch (EntityNotFoundException e) {
                        System.out.println(e.getMessage());
                    }

                    // printing repository objects
                    homeworkRepository.getRepository().entrySet().forEach(System.out::println);
                    break;

                case 8:
                    System.out.println("Selected   - \"8 - Creating addMaterials\" ");

                    System.out.print("Enter lecture ID ");
                    inputID = menuUtils.inputDigit();

                    try {
                        lectureRepository.getById(inputID);
                    } catch (EntityNotFoundException e) {
                        System.out.println("Lecture id " + inputID + " - not found");
                        break;
                    }

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
                    addMaterials.setLectureId(inputID);
                    addMaterialsRepository.getRepository().put(inputID, addMaterials);

                    // printing repository objects
                    addMaterialsRepository.getRepository().entrySet().forEach(System.out::println);
                    System.out.println("================================");
                    System.out.println("Total " + addMaterialsRepository.getRepository().size() + " AddMaterials objects");
                    break;

                case 9:
                    System.out.println("Selected   - \"9 - Get homework and add task by ID lecture\" ");

                    System.out.print("Enter lecture ID ");
                    int inputLectureID = menuUtils.inputDigit();
                    try {
                        lectureRepository.getById(inputLectureID);
                    } catch (EntityNotFoundException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                    // filter && printing lecture by ID
                    System.out.println('\n' + "=============Lecture Id = " + inputLectureID + "=============");
                    lectureRepository.getRepository().stream()
                            .filter(repo -> repo.getID() == inputLectureID)
                            .forEach(System.out::println);
                    // filter && printing homework by lecture ID
                    System.out.println('\n' + "==================Homework ==============");
                    homeworkRepository.getRepository().entrySet().stream()
                            .filter(repo -> repo.getKey() == inputLectureID)
                            .forEach(System.out::println);

                    // filter && printing add materials by lecture ID
                    System.out.println('\n' + "==================Add materials =========");
                    addMaterialsRepository.getRepository().entrySet().stream()
                            .filter(repo -> repo.getKey() == inputLectureID)
                            .forEach(System.out::println);

                    int resultSelect = menuUtils.addRemoveHomework();

                    switch (resultSelect) {
                        case 1:
                            System.out.println("Enter name Homework");
                            homeworkRepository.getRepository()
                                    .put(inputLectureID, homeworkService.create(menuUtils.inputString(), inputLectureID));
                            break;
                        case 2:
                            System.out.println("Enter name addMaterials");
                            try {
                                addMaterialsRepository.getRepository()
                                        .put(inputLectureID, addMaterialsService.create(menuUtils.inputString(),
                                                menuUtils.resourceType(), inputLectureID));
                            } catch (ValidationException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 3:
                            homeworkRepository.getRepository().remove(inputLectureID);
                            break;
                        case 4:
                            addMaterialsRepository.getRepository().remove(inputLectureID);
                            break;
                        default:
                            break;
                    }


                    break;

                case 10:
                    System.out.println("Selected   - \"10 - Sort Course by name\" ");

                    courseRepository.getRepository().stream()
                            .sorted(Comparator.comparing(repo -> repo.getName()))
                            .forEach(System.out::println);

                    System.out.println("================================");
                    System.out.println("Total " + courseRepository.getRepository().size() + " Course objects");
                    break;

                case 11:
                    System.out.println("Selected   - \"11 - Sort Teacher and Student by last name\" ");

                    personRepository.getRepository().stream()
                            .sorted(Comparator.comparing(repo -> repo.getLastName()))
                            .forEach(System.out::println);

                    System.out.println("================================");
                    System.out.println("Total " + personRepository.getRepository().size() + " Person objects");

                    break;

                default:
                    System.out.println("Try again...");
                    break;
            }
            if (category == 0) {
                System.out.println("Exiting program ...");
                break;
            }
        }
    }

}


