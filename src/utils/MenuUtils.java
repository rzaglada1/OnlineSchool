package utils;

import exceptions.EntityNotFoundException;
import models.AddMaterials;
import models.Person;
import models.model_enum.ResourceType;
import exceptions.ValidationException;
import models.model_enum.Role;
import repositories.*;
import services.*;
import student_exam.exam.Exam;
import student_exam.repo.StudentRepo;
import student_exam.student.Student;
import utils.log.Log;
import utils.log.LogToFile;

import java.util.*;
import java.util.concurrent.Phaser;

public class MenuUtils {

    String nameLog = "Log OnlineSchool";

    public int checkCorrect() {

        final int MENU_ITEM_START = 0;
        final int MENU_ITEM_FINISH = 11;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println('\n' + "Select category:");
            System.out.println("0 - exit program");
            System.out.println("1 - Creating course");
            System.out.println("2 - Creating lecture");
            System.out.println("3 - Creating teacher");
            System.out.println("4 - Creating student");
            System.out.println("5 - Creating homework");
            System.out.println("6 - Creating addMaterials");
            System.out.println("7 - Get homework and add task by ID lecture");
            System.out.println("8 - Sort Course by name");
            System.out.println("9 - Sort Teacher and Student by last name");
            System.out.println("10 - Print log file");
            System.out.println("11 - Students exam");


            try {
                String item = scanner.next();
                if (Integer.parseInt(item) >= MENU_ITEM_START && Integer.parseInt(item) <= MENU_ITEM_FINISH) {
                    return Integer.parseInt(item);
                } else throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.out.println("Something went wrong ... Try again. ");
            }
        }
    }

    public String inputString() {
        return new Scanner(System.in).next();
    }

    public int inputDigit() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Please enter digit ");
                scanner.next();
            }
        }
    }

    public ResourceType resourceType() throws ValidationException {
        switch (resourceTypeMenu()) {
            case 1:
                return ResourceType.URL;

            case 2:
                return ResourceType.VIDEO;

            case 3:
                return ResourceType.BOOK;
            default:
                System.out.println("Try again...");
                throw new ValidationException("id object not found");
        }
    }


    public int resourceTypeMenu() {
        final int MENU_RESOURCE_ITEM_START = 1;
        final int MENU_RESOURCE_ITEM_FINISH = 3;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println('\n' + "Select resource type:");

            System.out.println("1 - URL");
            System.out.println("2 - VIDEO");
            System.out.println("3 - BOOK");

            try {
                String item = scanner.next();
                if (Integer.parseInt(item) >= MENU_RESOURCE_ITEM_START && Integer.parseInt(item) <= MENU_RESOURCE_ITEM_FINISH) {
                    return Integer.parseInt(item);
                } else throw new NumberFormatException();
            } catch (NumberFormatException e) {

                System.out.println("Something went wrong ... Try again. ");
            }
        }
    }

    public int addRemoveHomework() {
        final int MENU_SORTING_ITEM_START = 0;
        final int MENU_SORTING_ITEM_FINISH = 4;
        Scanner scanner = new Scanner(System.in);
        while (true) {

            System.out.println('\n' + "Add or remove: ");
            System.out.println("0 - Exit");
            System.out.println("1 - Add homework");
            System.out.println("2 - Add addMaterials");
            System.out.println("3 - Remove homework");
            System.out.println("4 - Remove addMaterials");

            try {
                String item = scanner.next();
                if (Integer.parseInt(item) >= MENU_SORTING_ITEM_START && Integer.parseInt(item) <= MENU_SORTING_ITEM_FINISH) {
                    return Integer.parseInt(item);
                } else throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.out.println("Something went wrong ... Try again. ");
            }
        }
    }

    public void createObjects(AddMaterialsRepository addMaterialsRepository, CourseRepository courseRepository,
                              LectureRepository lectureRepository,
                              PersonRepository personRepository) {
        System.out.println('\n' + "What is in the repository?");
        System.out.println("================================");

        // creating Course
        courseRepository.getRepository().add(new CourseService().create("Java course"));
        courseRepository.getRepository().add(new CourseService().create("C++ course"));
        courseRepository.getRepository().add(new CourseService().create("C# course"));
        courseRepository.getRepository().add(new CourseService().create("Python course"));

        personRepository.getRepository().add(new PersonService().create(new String[]{"Олена", "Романенко"
                , "+380989584545", "Dasdasd@sdf.sdf"}, Role.STUDENT));
        personRepository.getRepository().add(new PersonService().create(new String[]{"Олена", "Водерацький"
                , "+380989584545", "Dasdasd@sdf.sdf"}, Role.TEACHER));
        personRepository.getRepository().add(new PersonService().create(new String[]{"Олена", "Ломачевський"
                , "+380989584545", "Dasdasd@sdf.sdf"}, Role.STUDENT));
        personRepository.getRepository().add(new PersonService().create(new String[]{"Олена", "Андрієнко"
                , "+380989584545", "Dasdasd@sdf.sdf"}, Role.STUDENT));
        personRepository.getRepository().add(new PersonService().create(new String[]{"Олена", "Командний"
                , "+380989584545", "Dasdasd@sdf.sdf"}, Role.TEACHER));
        personRepository.getRepository().add(new PersonService().create(new String[]{"Олена", "Солітер"
                , "+380989584545", "Dasdasd@sdf.sdf"}, Role.STUDENT));

        addMaterialsRepository.getRepository().add(new AddMaterialsService().create("Video", ResourceType.VIDEO, 1));
        addMaterialsRepository.getRepository().add(new AddMaterialsService().create("Text URL", ResourceType.URL, 3));
        addMaterialsRepository.getRepository().add(new AddMaterialsService().create("Text book", ResourceType.BOOK, 2));

        // creating Lecture
        for (int i = 0; i < 5; i++) {
            lectureRepository.getRepository().add(new LectureService().create("Lecture " + i));
        }
        // printing repository objects
        courseRepository.printRepository();
        lectureRepository.printRepository();
        addMaterialsRepository.printRepository();
        personRepository.printRepository();
    }

    public void case1(CourseRepository courseRepository) {
        Log.info(nameLog, "Selected   - \"1 - Creating course\" ");
        System.out.println("Enter name Course");
        courseRepository.getRepository().add(new CourseService().create(inputString()));
        // printing repository objects
        courseRepository.printRepository();
    }

    public void case2(LectureRepository lectureRepository) {
        Log.info(nameLog, "Selected   - \"2 - Creating lecture\" ");
        System.out.println("Enter name Lecture");
        lectureRepository.getRepository().add(new LectureService().create(inputString()));
        // printing repository objects
        lectureRepository.printRepository();
    }

    public void case3(PersonRepository personRepository) {
        Log.info(nameLog, "Selected   - \"3 - Creating teacher");
        try {
            Person personTeacher = new PersonService().create(new RegexUtil().personAttribute(),
                    Role.TEACHER);
            personRepository.getRepository().add(personTeacher);
        } catch (ValidationException e) {
            Log.warning(nameLog, "Something wrong, try again", e.getStackTrace());
        }

        personRepository.printRepositoryTeacher();
    }

    public void case4(PersonRepository personRepository) {
        Log.info(nameLog, "Selected   - \"4 - Creating student");
        try {
            Person personStudent = new PersonService().create(new RegexUtil().personAttribute(),
                    Role.STUDENT);
            personRepository.getRepository().add(personStudent);
        } catch (ValidationException e) {
            Log.warning(nameLog, "Something wrong, try again", e.getStackTrace());
        }
        // printing repository objects
        personRepository.printRepositoryStudent();
    }

    public void case5(HomeWorkRepository homeworkRepository, LectureRepository lectureRepository) {
        Log.info(nameLog, "Selected   - \"5 - Creating Homework\" ");

        System.out.println("Enter name Homework");
        String nameHomework = inputString();
        System.out.print("Enter lecture ID ");
        int lectureID = inputDigit();
        try {
            lectureRepository.getById(lectureID);
            homeworkRepository.getRepository().add(new HomeworkService().create(nameHomework, lectureID));
        } catch (EntityNotFoundException e) {
            Log.warning(nameLog, "Something wrong", e.getStackTrace());
        }
        // printing repository objects
        homeworkRepository.printRepository();
    }

    public void case6(AddMaterialsRepository addMaterialsRepository, LectureRepository lectureRepository) {
        Log.info(nameLog, "Selected   - \"6 - Creating addMaterials\" ");

        System.out.print("Enter lecture ID ");
        int inputID = inputDigit();

        try {
            lectureRepository.getById(inputID);
        } catch (EntityNotFoundException e) {
            Log.warning(nameLog, "Lecture id " + inputID + " - not found", e.getStackTrace());
            return;
        }

        System.out.println("Enter name addMaterials");

        AddMaterials addMaterials = new AddMaterialsService().create(inputString());
        try {
            addMaterials.setResourceType(resourceType());
        } catch (ValidationException e) {
            Log.warning(nameLog, "Something wrong ", e.getStackTrace());
        }
        addMaterials.setLectureID(inputID);
        addMaterialsRepository.getRepository().add(addMaterials);

        // printing repository objects
        addMaterialsRepository.printRepository();
    }

    public void case7(HomeWorkRepository homeworkRepository
            , AddMaterialsRepository addMaterialsRepository, LectureRepository lectureRepository) {
        Log.info(nameLog, "Selected   - \"7 - Get homework and add task by ID lecture\" ");

        System.out.print("Enter lecture ID ");
        int inputLectureID = inputDigit();
        try {
            lectureRepository.getById(inputLectureID);
        } catch (EntityNotFoundException e) {
            Log.warning(nameLog, "Something wrong", e.getStackTrace());
            return;
        }

        // filter && printing lecture by ID
        System.out.println('\n' + "=============Lecture Id = " + inputLectureID + "=============");
        try {
            System.out.println(lectureRepository.getById(inputLectureID));

            // filter && printing homework by lecture ID
            System.out.println('\n' + "==================Homework ==============");
            System.out.println(homeworkRepository.getHomeworkByLectureId(inputLectureID));

            // filter && printing add materials by lecture ID
            System.out.println('\n' + "==================Add materials =========");
            System.out.println(addMaterialsRepository.getAddMaterialsByLectureId(inputLectureID));

            switch (addRemoveHomework()) {
                case 1 -> {
                    System.out.println("Enter name Homework");
                    homeworkRepository.getRepository()
                            .add(new HomeworkService().create(inputString(), inputLectureID));
                }
                case 2 -> {
                    System.out.println("Enter name addMaterials");
                    try {
                        addMaterialsRepository.getRepository()
                                .add(new AddMaterialsService().create(inputString(),
                                        resourceType(), inputLectureID));
                    } catch (ValidationException e) {
                        e.printStackTrace();
                    }
                }
                case 3 -> homeworkRepository.getRepository().removeIf(obj -> obj.getLectureID() == inputLectureID);
                case 4 -> addMaterialsRepository.getRepository().removeIf(obj -> obj.getLectureID() == inputLectureID);
                default -> {
                }
            }

        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void case8(CourseRepository courseRepository) {
        Log.info(nameLog, "Selected   - \"8 - Sort Course by name\" ");
        courseRepository.sortedByName().forEach(System.out::println);
    }

    public void case9(PersonRepository personRepository) {
        Log.info(nameLog, "Selected   - \"9 - Sort Teacher and Student by last name\" ");
        personRepository.sortedByName().forEach(System.out::println);
    }

    public void case10() {
        Log.info(nameLog, "Selected   - \"10 - Print log file\" ");
        LogToFile.getInstance().loadFromFile();
    }

    public void case11() {
        Log.info(nameLog, "Selected   - \"11 - Students exam\" ");

        int examTask;
        int examTime;
        int students = 10;
        Set<Integer> examTaskSet = new HashSet<>();
        StudentRepo.getInstance().getRepoStudent().clear();
        Phaser phaser = new Phaser();
        phaser.register();

        for (int i = 1; i < students + 1; i++) {
            examTime = (int) (Math.random() * 7 + 8);
            examTask = (int) (Math.random() * students + 1);
            while (!examTaskSet.add(examTask)) {
                examTask = (int) (Math.random() * students + 1);
            }
            StudentRepo.getInstance().getRepoStudent()
                    .add(new Student("StudentName" + i, examTask, examTime, phaser));
            System.out.println("Student " + i + " got the task number " + examTask);
        }

        System.out.println("=======================================" + '\n');
        ThreadGroup threadGroupStudents = new ThreadGroup("Students");

        for (Student student : StudentRepo.getInstance().getRepoStudent()) {
            new Thread(threadGroupStudents, student, student.getName()).start();
        }
        examStart(phaser);
        StudentRepo.getInstance().printRepo();

        Thread[] grp =  new  Thread[threadGroupStudents.activeCount()];
        threadGroupStudents.enumerate(grp);
        for (Thread element: grp) {
            System.out.println( "Time is up: "  + element.getName() );
        }}

    private void examStart(Phaser phaser) {
        StudentRepo.getInstance().getBestStudent().clear();
        phaser.arriveAndAwaitAdvance();
        Thread exam = new Thread(new Exam(12));
        exam.start();
        try {
            exam.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
