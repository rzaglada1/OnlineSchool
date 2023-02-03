package utils;

import backup.ServiceBackupFile;
import exceptions.EntityNotFoundException;
import models.*;
import models.model_enum.ResourceType;
import exceptions.ValidationException;
import models.model_enum.Role;
import repositories.*;
import server_client.MyClient;
import server_client.MyServer;
import services.*;
import student_exam.exam.Exam;
import student_exam.repo.StudentRepo;
import student_exam.student.Student;
import utils.log.Log;
import utils.log.LogLevel;
import utils.log.LogProperty;
import utils.log.LogToFile;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MenuUtils {

    String nameLog = "Log OnlineSchool";
    CourseRepository courseRepository = CourseRepository.getInstance();

    private final String STR_ENTER_FORMAT_DATE = "dd-MM-yyyy HH:mm";

    public int checkCorrect() {

        final int MENU_ITEM_START = 0;
        final int MENU_ITEM_FINISH = 18;

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
            System.out.println("11 - Change log level");
            System.out.println("12 - Students exam");
            System.out.println("13 - Start server");
            System.out.println("14 - Start client");
            System.out.println("15 - Creating backup");
            System.out.println("16 - Print backup");
            System.out.println("17 - Print list with Lambda by lectureID");
            System.out.println("18 - Print list with Lambda by DateTime");


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

    public int itemLogLevel() {
        final int MENU_LOG_LEVEL_ITEM_START = 0;
        final int MENU_LOG_LEVEL_ITEM_FINISH = 4;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println('\n' + "Select log level:");

            System.out.println("0 - OFF");
            System.out.println("1 - DEBUG");
            System.out.println("2 - INFO");
            System.out.println("3 - WARNING");
            System.out.println("4 - ERROR");


            try {
                String item = scanner.next();
                if (Integer.parseInt(item) >= MENU_LOG_LEVEL_ITEM_START && Integer.parseInt(item) <= MENU_LOG_LEVEL_ITEM_FINISH) {
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
                              PersonRepository personRepository, HomeWorkRepository homeWorkRepository) {
        System.out.println('\n' + "What is in the repository?");
        System.out.println("================================");

        // creating Course
        Course javaCourse = new CourseService().create("Java course");
        Course cPlusCourse = new CourseService().create("C++ course");
        Course pythonCourse = new CourseService().create("Python course");
        courseRepository.getRepository().add(javaCourse);
        courseRepository.getRepository().add(cPlusCourse);
        courseRepository.getRepository().add(pythonCourse);

        // creating Person
        personRepository.getRepository().add(new PersonService().create(new String[]{"Олена", "Романенко"
                , "+380989584545", "Dasdasd@sdf.sdf"}, Role.STUDENT, javaCourse));
        personRepository.getRepository().add(new PersonService().create(new String[]{"Олена", "Водерацький"
                , "+380989584545", "Dasdasd@sdf.sdf"}, Role.TEACHER, javaCourse));
        personRepository.getRepository().add(new PersonService().create(new String[]{"Олена", "Ломачевський"
                , "+380989584545", "Dasdasd@sdf.sdf"}, Role.STUDENT, javaCourse));
        personRepository.getRepository().add(new PersonService().create(new String[]{"Олена", "Андрієнко"
                , "+380989584545", "Dasdasd@sdf.sdf"}, Role.STUDENT, javaCourse));
        personRepository.getRepository().add(new PersonService().create(new String[]{"Олена", "Командний"
                , "+380989584545", "Dasdasd@sdf.sdf"}, Role.TEACHER, javaCourse));
        personRepository.getRepository().add(new PersonService().create(new String[]{"Олена", "Солітер"
                , "+380989584545", "Dasdasd@sdf.sdf"}, Role.STUDENT, javaCourse));

        // creating Lecture
        Lecture lecture;
        for (int i = 0; i < 5; i++) {
            lectureRepository.getRepository().add(new LectureService().create("Lecture " + i, javaCourse, LocalDateTime.now()) );
        }
        lectureRepository.getRepository().add( lecture = new LectureService().create("Lecture6 ", pythonCourse, LocalDateTime.now()));

        // creating AddMaterials
        addMaterialsRepository.getRepository().add(new AddMaterialsService()
                .create("Video", ResourceType.VIDEO, lecture));
        addMaterialsRepository.getRepository().add(new AddMaterialsService()
                .create("Text URL", ResourceType.URL, lecture));
        addMaterialsRepository.getRepository().add(new AddMaterialsService()
                .create("Text book", ResourceType.BOOK, lecture));

        try {
            addMaterialsRepository.getRepository().add(new AddMaterialsService()
                    .create("Text book", ResourceType.BOOK, lectureRepository.getById(3)));
            addMaterialsRepository.getRepository().add(new AddMaterialsService()
                    .create("Text book", ResourceType.BOOK, lectureRepository.getById(2)));
            addMaterialsRepository.getRepository().add(new AddMaterialsService()
                    .create("Text book", ResourceType.BOOK, lectureRepository.getById(1)));
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }


        // creating Homework
        homeWorkRepository.getRepository().add(new HomeworkService().create("homeworkLecture",lecture));

        // printing repository objects
        courseRepository.printRepository();
        lectureRepository.printRepository();
        addMaterialsRepository.printRepository();
        personRepository.printRepository();
        homeWorkRepository.printRepository();
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
        String nameLecture = inputString();
        System.out.println("Enter Course ID for lecture");
        int inputCourseID = inputDigit();
        try {
            Course course = courseRepository.getById(inputCourseID);
            LocalDateTime lectureDate = enterDate(STR_ENTER_FORMAT_DATE);
            lectureRepository.getRepository().add(new LectureService().create(nameLecture, course, lectureDate));
        } catch (EntityNotFoundException e) {
            Log.warning(nameLog, "Something wrong", e.getStackTrace());
        }

        // printing repository objects
        lectureRepository.printRepository();
    }

    public void case3(PersonRepository personRepository) {
        Log.info(nameLog, "Selected   - \"3 - Creating teacher");

        System.out.println("Enter ID course for teacher");
        int inputCourseID = inputDigit();

        try {
            Course course = courseRepository.getById(inputCourseID);
            Person personTeacher = new PersonService().create(new RegexUtil().personAttribute(),
                    Role.TEACHER, course);
            personRepository.getRepository().add(personTeacher);

        } catch (EntityNotFoundException e) {
            Log.warning(nameLog, "Course not found", e.getStackTrace());
        } catch (ValidationException e) {
            Log.warning(nameLog, "Something wrong, try again", e.getStackTrace());
        }

        personRepository.printRepositoryTeacher();
    }

    public void case4(PersonRepository personRepository) {
        Log.info(nameLog, "Selected   - \"4 - Creating student");
        System.out.println("Enter ID course for student");
        int inputCourseID = inputDigit();

        try {
            Course course = courseRepository.getById(inputCourseID);
            Person personStudent = new PersonService().create(new RegexUtil().personAttribute(),
                    Role.STUDENT, course);
            personRepository.getRepository().add(personStudent);
        } catch (EntityNotFoundException e) {
        Log.warning(nameLog, "Course not found", e.getStackTrace());
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
        System.out.println("Enter lecture ID for homework");
        int lectureID = inputDigit();
        Lecture lecture;
        Course course;
        try {
            lectureRepository.getById(lectureID);
            lecture = lectureRepository.getById(lectureID);
            course = lecture.getCourse();
            homeworkRepository.getRepository().add(new HomeworkService().create(nameHomework, lecture));
        } catch (EntityNotFoundException e) {
            Log.warning(nameLog, "Something wrong", e.getStackTrace());
        }

        // printing repository objects
        homeworkRepository.printRepository();
    }

    public void case6(AddMaterialsRepository addMaterialsRepository, LectureRepository lectureRepository) {
        Log.info(nameLog, "Selected   - \"6 - Creating addMaterials\" ");

        System.out.println("Enter lecture ID for add materials");

         int inputID = inputDigit();
         try {
             Lecture lecture = lectureRepository.getById(inputID);
             System.out.println("Enter name addMaterials");
             AddMaterials addMaterials = new AddMaterialsService().create(inputString(),resourceType(),lecture );
             addMaterialsRepository.getRepository().add(addMaterials);
         } catch (EntityNotFoundException e) {
             Log.warning(nameLog, "Lecture id " + inputID + " - not found", e.getStackTrace());
             return;
         } catch (ValidationException e) {
             throw new RuntimeException(e);
         }

        // printing repository objects
        addMaterialsRepository.printRepository();
    }

    public void case7(HomeWorkRepository homeworkRepository
            , AddMaterialsRepository addMaterialsRepository, LectureRepository lectureRepository) {
        Log.info(nameLog, "Selected   - \"7 - Get homework and add task by ID lecture\" ");

        System.out.print("Enter lecture ID ");
        int inputLectureID = inputDigit();
        try {
            Lecture lecture = lectureRepository.getById(inputLectureID);
            // filter && printing lecture by ID
            System.out.println('\n' + "=============Lecture Id = " + inputLectureID + "=============");
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
                    Course course = lecture.getCourse();
                    homeworkRepository.getRepository().add(new HomeworkService().create(inputString(), lecture));
                }
                case 2 -> {
                    System.out.println("Enter name addMaterials");
                    try {
                        addMaterialsRepository.getRepository()
                                .add(new AddMaterialsService().create(inputString(),
                                        resourceType(), lecture ));
                    } catch (ValidationException e) {
                        e.printStackTrace();
                    }
                }
                case 3 -> homeworkRepository.getRepository().removeIf(obj -> obj.getLectureID() == inputLectureID);
                case 4 -> addMaterialsRepository.getRepository().removeIf(obj -> obj.getLectureID() == inputLectureID);
                default -> {
                    throw new ValidationException("id object not found");
                }
            }

        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            Log.warning(nameLog, "Called default ", e.getStackTrace());
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
        LogToFile.getInstance().loadFromLogFile();
    }

    public void case11() {
        // =========For example===========
        System.out.println('\n' + "Log_level = " + LogToFile.getInstance().loadFromServiceFile(LogProperty.LOG_LEVEL).name()
                + ". What log types are being printed now: ");
        System.out.println("==============================");
        Log.info(nameLog, "Selected   - \"11 - Change log level\" ");
        Log.debug(nameLog, "debug");
        Log.info(nameLog, "info");
        Log.warning(nameLog, "warning", null);
        Log.error(nameLog, "error", null);
        //================================

        switch (itemLogLevel()) {
            case 1 -> LogToFile.getInstance().saveToServiceFile(LogProperty.LOG_LEVEL, LogLevel.DEBUG);
            case 2 -> LogToFile.getInstance().saveToServiceFile(LogProperty.LOG_LEVEL, LogLevel.INFO);
            case 3 -> LogToFile.getInstance().saveToServiceFile(LogProperty.LOG_LEVEL, LogLevel.WARNING);
            case 4 -> LogToFile.getInstance().saveToServiceFile(LogProperty.LOG_LEVEL, LogLevel.ERROR);
            default -> LogToFile.getInstance().saveToServiceFile(LogProperty.LOG_LEVEL, LogLevel.OFF);
        }
    }

    public void case12() {
        Log.info(nameLog, "Selected   - \"12 - Students exam\" ");

        int examTask;
        int examTime;
        int students = 10;
        Set<Integer> examTaskSet = new HashSet<>();
        StudentRepo.getInstance().getRepoStudent().clear();

        for (int i = 1; i < students + 1; i++) {
            examTime = (int) (Math.random() * 7 + 8);
            examTask = (int) (Math.random() * students + 1);
            while (!examTaskSet.add(examTask)) {
                examTask = (int) (Math.random() * students + 1);
            }
            StudentRepo.getInstance().getRepoStudent()
                    .add(new Student("StudentName" + i, examTask, examTime));
            System.out.println("Student " + i + " got the task number " + examTask);
        }

        System.out.println("=======================================" + '\n');

        ExecutorService executorService = Executors.newFixedThreadPool(students);

                for (Student student : StudentRepo.getInstance().getRepoStudent()) {
                    executorService.execute(student);
        }

        examStart();
        StudentRepo.getInstance().printRepo();
        executorService.shutdownNow();
        // delay to have time to print the main menu
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Log.warning(nameLog, "Thread.sleep(1)  error", e.getStackTrace());
        }

    }

    public void case13() {
        Log.info(nameLog, "Selected   - \"13 - Start server\" ");

        new Thread(new MyServer()).start();

    }

    public void case14() {
        Log.info(nameLog, "Selected   - \"14 - Start client\" ");

        new MyClient().startMyClient();
    }

    public void case15() {
        Log.info(nameLog, "Selected   - \"15 - Creating backup\" ");

        ServiceBackupFile sb = new ServiceBackupFile();
        CourseRepository courseRepository = CourseRepository.getInstance();
        courseRepository.printRepository();

        System.out.println("Enter ID course for backup");
        int inputCourseID = inputDigit();
        try {
            courseRepository.getById(inputCourseID);
            sb.createBackup(
                    AddMaterialsRepository.getInstance().getRepository()
                    , HomeWorkRepository.getInstance().getRepository()
                    , LectureRepository.getInstance().getRepository()
                    , PersonRepository.getInstance().getRepository()
                    , inputCourseID, ServiceBackupFile.NAME_FILE_LECTURE);
        } catch (EntityNotFoundException e) {
            Log.warning(nameLog, "Something wrong", e.getStackTrace());
        }
    }

    public void case16() {
        Log.info(nameLog, "Selected   - \"16 - Print backup\" ");


        ServiceBackupFile sr = new ServiceBackupFile();
        System.out.println("Enter ID course for printingBackup");
        int inputCourseID = inputDigit();
        try {
            courseRepository.getById(inputCourseID);
            sr.printBackup(inputCourseID, ServiceBackupFile.NAME_FILE_LECTURE);
        } catch (EntityNotFoundException e) {
            Log.warning(nameLog, "Something wrong", e.getStackTrace());
        }
    }
    public void case17(AddMaterialsRepository addMaterialsRepository) {
        Log.info(nameLog, "Selected   - \"17 - Print list Add materials with Lambda by lectureID\" ");
        System.out.println("Enter ID lecture  for printing Add materials");
        try {
            addMaterialsRepository.printAddMaterialsByLectureId(inputDigit());
        } catch (EntityNotFoundException e) {
            Log.warning(nameLog, "id Lecture object not found", e.getStackTrace());
        }
    }

    public void case18(LectureRepository lectureRepository) {
        Log.info(nameLog, "Selected   - \"18 - Print list with Lambda by DateTime\" ");

        String formatDate = "MM-dd-yyy HH:mm";
        System.out.println("Start dateTime");
        LocalDateTime startDateTime = enterDate(formatDate);
        System.out.println("Finish dateTime (should be First dataTime "
                + formatDateMenu(startDateTime, formatDate,Locale.US) + ")");
        LocalDateTime finishDateTime;
        while ((finishDateTime = enterDate("MM-dd-yyy HH:mm")).isBefore(startDateTime) ) {
            System.out.println("First dateTime > Second dateTime, try again");
        }
        lectureRepository.printAfterBeforeDate(startDateTime, finishDateTime);
    }



    private void examStart() {
        StudentRepo.getInstance().getBestStudent().clear();
        Thread exam = new Thread(new Exam(12));
        exam.start();
        try {
            exam.join();
        } catch (InterruptedException e) {
            Log.warning(nameLog, "exam.join() error ", e.getStackTrace());
        }
    }

    private LocalDateTime enterDate (String enterFormatData) {
        String dateString;
        DateTimeFormatter df = DateTimeFormatter.ofPattern(enterFormatData);
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Enter date and time  in format : " + LocalDateTime.now().format(df));

        while (true) {
            dateString = inputScanner.nextLine();
            try {
                LocalDateTime inputDate = LocalDateTime.parse(dateString, df);
                return inputDate;
            } catch (DateTimeParseException dtpe) {
                System.out.println("Invalid date: " + dateString + ". Re-enter again...");
            }
        }
    }

    public String formatDateMenu(LocalDateTime dateTime, String strFormat, Locale locale) {
        String formatDate = "--";
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern(strFormat, locale);
            formatDate = dateTime.format(df);
        } catch (NullPointerException e) {
            Log.warning("On-line school", "convert date format error", e.getStackTrace());
        }

        return formatDate;
    }


}
