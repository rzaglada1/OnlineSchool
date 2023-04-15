package utils;

import backup.ServiceBackupFile;
import models.*;
import models.model_enum.ResourceType;
import exceptions.ValidationException;
import models.model_enum.Role;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MenuUtils {

    String nameLog = "Log OnlineSchool";

    private AddMaterialsService addMaterialsService;
    private CourseService courseService;
    private LectureService lectureService;
    private PersonService personService;
    private HomeworkService homeworkService;

    // ===================for create repository data========

    private HomeWorkRepository homeWorkRepository;

    @Autowired
    public void setHomeWorkRepository(HomeWorkRepository homeWorkRepository) {
        this.homeWorkRepository = homeWorkRepository;
    }

    //========================================================

    private RegexUtil regexUtil;

    @Autowired
    public void setAddMaterialsService(AddMaterialsService addMaterialsService) {
        this.addMaterialsService = addMaterialsService;
    }

    @Autowired
    public void setRegexUtil(RegexUtil regexUtil) {
        this.regexUtil = regexUtil;
    }

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }


    @Autowired
    public void setLectureService(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @Autowired
    public void setHomeworkService(HomeworkService homeworkService) {
        this.homeworkService = homeworkService;
    }

    private final static String userDir = System.getProperty("user.home");

    public final static String NAME_FILE_BACKUP = userDir + "/serviceOnlineSchool/Backup.";
    public static final String STR_PATH_DIRECTORY = userDir + "/serviceOnlineSchool/";

    {
        Path path = Path.of(STR_PATH_DIRECTORY);
        if (!Files.isDirectory(path)) {
            createdDir(path);
        }

    }

    public static final String STR_NAME_LOG = "log.txt";
    public final static String STR_NAME_SERVICE = "Service.log";
    public final static String STR_NAME_BLACK_LIST = "BlackList.txt";
    public final static String STR_NAME_FILe_EMAIL = "emails.txt";


    private final String STR_ENTER_FORMAT_DATE = "dd-MM-yyyy HH:mm";

    public int checkCorrect() {

        final int MENU_ITEM_START = 0;
        final int MENU_ITEM_FINISH = 26;

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
            System.out.println("19 - Print list teachers with name < char");
            System.out.println("20 - Print log file with filter");
            System.out.println("21 - Print first Lecture with max AddMaterials");
            System.out.println("22 - Print line count in logs with Level = INFO. Start from  middle the file.");
            System.out.println("23 - Print Lecture grouped by Teacher");
            System.out.println("24 - Print Add Materials grouped by Lecture");
            System.out.println("25 - Print Map key - Email, value - Name+LastName");
            System.out.println("26 - Save to file email students sorted by");


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

    public LogLevel itemLogLevel() {
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
                LogLevel logLevel;
                String item = scanner.next();
                if (Integer.parseInt(item) >= MENU_LOG_LEVEL_ITEM_START && Integer.parseInt(item) <= MENU_LOG_LEVEL_ITEM_FINISH) {
                    switch (Integer.parseInt(item)) {
                        case 1 -> logLevel = LogLevel.DEBUG;
                        case 2 -> logLevel = LogLevel.INFO;
                        case 3 -> logLevel = LogLevel.WARNING;
                        case 4 -> logLevel = LogLevel.ERROR;
                        default -> logLevel = LogLevel.OFF;
                    }
                    return logLevel;
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

    public void createObjects() {
        System.out.println('\n' + "What is in the repository?");
        System.out.println("================================");

        // printing repository objects
        courseService.printRepository();
        lectureService.printRepository();
        addMaterialsService.printRepository();
        personService.printRepository();
        homeworkService.printRepository();
    }

    public void case1() {
        Log.info(nameLog, "Selected   - \"1 - Creating course\" ");
        System.out.println("Enter name Course");

        courseService.saveCourse(new Course(inputString()));

        // printing repository objects
        courseService.printRepository();
    }

    public void case2() {
        Log.info(nameLog, "Selected   - \"2 - Creating lecture\" ");
        System.out.println("Enter name Lecture");
        String nameLecture = inputString();
        System.out.println("Enter Course ID for lecture");
        int inputCourseID = inputDigit();
        try {
            Course course = courseService.getCourseById(inputCourseID).orElseThrow(NoSuchElementException::new);
            LocalDateTime lectureDate = enterDate(STR_ENTER_FORMAT_DATE);
            lectureService.getAllLecture().add(new Lecture(
                    nameLecture
                    , course
                    , lectureDate,
                    personService.getByIdPerson(1, Role.TEACHER).orElseThrow()
            ));
        } catch (NoSuchElementException e) {
            Log.warning(nameLog, "Something wrong", e.getStackTrace());
        }

        // printing repository objects
        lectureService.printRepository();
    }

    public void case3() {
        Log.info(nameLog, "Selected   - \"3 - Creating teacher");

        System.out.println("Enter ID lecture for teacher");
        int inputLectureID = inputDigit();

        try {
            Lecture lecture = lectureService.getLectureById(inputLectureID).orElseThrow(NoSuchElementException::new);

            Person personTeacher = new Person(regexUtil.personAttribute(),
                    Role.TEACHER, lecture);
            personService.getAllPerson().add(personTeacher);

        } catch (NoSuchElementException e) {
            Log.warning(nameLog, "Course not found", e.getStackTrace());
        } catch (ValidationException e) {
            Log.warning(nameLog, "Something wrong, try again", e.getStackTrace());
        }

        personService.printRepositoryTeacher();
    }

    public void case4() {
        Log.info(nameLog, "Selected   - \"4 - Creating student");
        System.out.println("Enter ID course for student");
        int inputCourseID = inputDigit();

        try {
            Course course = courseService.getCourseById(inputCourseID).orElseThrow(NoSuchElementException::new);
            Person personStudent = new Person(regexUtil.personAttribute(),
                    Role.STUDENT, course);
            personService.getAllPerson().add(personStudent);
        } catch (NoSuchElementException e) {
            Log.warning(nameLog, "Course not found", e.getStackTrace());
        } catch (ValidationException e) {
            Log.warning(nameLog, "Something wrong, try again", e.getStackTrace());
        }
        // printing repository objects
        personService.printRepositoryStudent();
    }

    public void case5() {
        Log.info(nameLog, "Selected   - \"5 - Creating Homework\" ");

        System.out.println("Enter name Homework");
        String nameHomework = inputString();
        System.out.println("Enter lecture ID for homework");
        int lectureID = inputDigit();
        Lecture lecture;

        try {
            lectureService.getLectureById(lectureID);
            lecture = lectureService.getLectureById(lectureID).orElseThrow(NoSuchElementException::new);
            homeWorkRepository.getRepository().add(new Homework(nameHomework, lecture));
        } catch (NoSuchElementException e) {
            Log.warning(nameLog, "Something wrong", e.getStackTrace());
        }

        // printing repository objects
        homeworkService.printRepository();
    }

    public void case6() {
        Log.info(nameLog, "Selected   - \"6 - Creating addMaterials\" ");

        System.out.println("Enter lecture ID for add materials");

        int inputID = inputDigit();
        try {
            Lecture lecture = lectureService.getLectureById(inputID).orElseThrow(NoSuchElementException::new);
            System.out.println("Enter name addMaterials");
            AddMaterials addMaterials = new AddMaterials(inputString(), resourceType(), lecture);
            addMaterialsService.getAllAddMaterials().add(addMaterials);
        } catch (NoSuchElementException e) {
            Log.warning(nameLog, "Lecture id " + inputID + " - not found", e.getStackTrace());
            return;
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }

        // printing repository objects
        addMaterialsService.printRepository();
    }

    public void case7() {
        Log.info(nameLog, "Selected   - \"7 - Get homework and add task by ID lecture\" ");

        System.out.print("Enter lecture ID ");
        int inputLectureID = inputDigit();
        try {
            Lecture lecture = lectureService.getLectureById(inputLectureID).orElseThrow(NoSuchElementException::new);
            // filter && printing lecture by ID
            System.out.println('\n' + "=============Lecture Id = " + inputLectureID + "=============");
            System.out.println(lectureService.getLectureById(inputLectureID));

            // filter && printing homework by lecture ID
            System.out.println('\n' + "==================Homework ==============");
            System.out.println(homeworkService.getHomeworkByLectureId(inputLectureID));

            // filter && printing add materials by lecture ID
            System.out.println('\n' + "==================Add materials =========");
            System.out.println(addMaterialsService.getAddMaterialsByLectureId(inputLectureID));

            switch (addRemoveHomework()) {
                case 1 -> {
                    System.out.println("Enter name Homework");
                    homeWorkRepository.getRepository().add(new Homework(inputString(), lecture));
                }
                case 2 -> {
                    System.out.println("Enter name addMaterials");
                    try {
                        addMaterialsService.getAllAddMaterials()
                                .add(new AddMaterials(inputString(),
                                        resourceType(), lecture));
                    } catch (ValidationException e) {
                        e.printStackTrace();
                    }
                }
                case 3 -> homeWorkRepository.getRepository()
                        .removeIf(obj -> obj.getLectureID().orElseThrow(NoSuchElementException::new) == inputLectureID);
                case 4 -> addMaterialsService.getAllAddMaterials()
                        .removeIf(obj -> obj.getLectureID().orElseThrow(NoSuchElementException::new) == inputLectureID);
                default -> {
                    System.out.println("exit...");
                }
            }
        } catch (NoSuchElementException e) {
            Log.warning(nameLog, "EntityNotFoundException1", e.getStackTrace());
        }
    }

    public void case8() {
        Log.info(nameLog, "Selected   - \"8 - Sort Course by name\" ");
        courseService.sortedCourseByName().forEach(System.out::println);
    }

    public void case9() {
        Log.info(nameLog, "Selected   - \"9 - Sort Teacher and Student by last name\" ");
        personService.sortedByName().forEach(System.out::println);
    }

    public void case10() {
        Log.info(nameLog, "Selected   - \"10 - Print log file\" ");
        LogToFile.getInstance().loadFromLogFile();
    }

    public void case11() {
        // =========For example===========
        Path pathServiceFile = Path.of(MenuUtils.STR_PATH_DIRECTORY, STR_NAME_SERVICE);
        System.out.println('\n' + "Log_level = " + LogToFile.getInstance()
                .loadFromServiceFile(LogProperty.LOG_LEVEL, pathServiceFile).name()
                + ". What log types are being printed now: ");
        System.out.println("==============================");
        Log.info(nameLog, "Selected   - \"11 - Change log level\" ");
        Log.debug(nameLog, "debug (for example in MenuUtils)");
        Log.info(nameLog, "info (for example in MenuUtils)");
        Log.warning(nameLog, "warning (for example in MenuUtils)", null);
        Log.error(nameLog, "error (for example in MenuUtils)", null);
        //================================

        LogToFile.getInstance().saveToServiceFile(LogProperty.LOG_LEVEL, itemLogLevel(), pathServiceFile);
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
        courseService.printRepository();

        System.out.println("Enter ID course for backup");
        int inputCourseID = inputDigit();
        try {
            courseService.getCourseById(inputCourseID).orElseThrow(NoSuchElementException::new);
            sb.createBackup(
                    addMaterialsService.getAllAddMaterials()
                    , homeworkService.getAllHomework()
                    , lectureService.getAllLecture()
                    , personService.getAllPerson()
                    , inputCourseID, MenuUtils.NAME_FILE_BACKUP);
        } catch (NoSuchElementException e) {
            Log.warning(nameLog, "NoSuchElementException", e.getStackTrace());
        }
    }

    public void case16() {
        Log.info(nameLog, "Selected   - \"16 - Print backup\" ");


        ServiceBackupFile sr = new ServiceBackupFile();
        System.out.println("Enter ID course for printingBackup");
        int inputCourseID = inputDigit();
        try {
            courseService.getCourseById(inputCourseID).orElseThrow(NoSuchElementException::new);
            sr.printBackup(inputCourseID, MenuUtils.NAME_FILE_BACKUP);
        } catch (NoSuchElementException e) {
            Log.warning(nameLog, "Something wrong", e.getStackTrace());
        }
    }

    public void case17() {
        Log.info(nameLog, "Selected   - \"17 - Print list Add materials with Lambda by lectureID\" ");
        System.out.println("Enter ID lecture  for printing Add materials");
        addMaterialsService.printAddMaterialsByLectureId(inputDigit());
    }

    public void case18() {
        Log.info(nameLog, "Selected   - \"18 - Print list with Lambda by DateTime\" ");

        String formatDate = "MM-dd-yyy HH:mm";
        System.out.println("Start dateTime");
        LocalDateTime startDateTime = enterDate(formatDate);
        System.out.println("Finish dateTime (should be First dataTime "
                + formatDateMenu(startDateTime, formatDate, Locale.US) + ")");
        LocalDateTime finishDateTime;
        while ((finishDateTime = enterDate("MM-dd-yyy HH:mm")).isBefore(startDateTime)) {
            System.out.println("First dateTime > Second dateTime, try again");
        }
        lectureService.printAfterBeforeDate(startDateTime, finishDateTime);
    }

    public void case19() {
        Log.info(nameLog, "Selected   - \"19 - Print list teachers with name < char \" ");
        char charFilter = 'Ж';
        personService.printNameFilter(charFilter);
    }

    public void case20() {
        Log.info(nameLog, "20 - Print log file with filter\" ");
        LogToFile.getInstance().loadFromLogFileFilter(itemLogLevel());
    }

    public void case21() {
        Log.info(nameLog, "21 - Print first Lecture with max AddMaterials\" ");
        try {
            lectureService.firstLectureMaxMaterials();
        } catch (NoSuchElementException e) {
            Log.warning(nameLog, "There is no lecture that satisfies the conditions", e.getStackTrace());
        }
    }

    public void case22() {
        Log.info(nameLog, "22 - Print line count in logs with Level = INFO. Start from  middle the file\" ");

        LogToFile.getInstance().loadFromLogFileFilterByMiddle(LogLevel.INFO);
    }

    public void case23() {
        Log.info(nameLog, "23 - Print Lecture grouped by Teacher\" ");
        lectureService.printLectureGroupByTeacher();
    }

    public void case24() {
        Log.info(nameLog, "24 - Print Add Materials grouped by Lecture\" ");
        addMaterialsService.printAddMaterialsGroupByLecture();
    }

    public void case25() {
        Log.info(nameLog, "25 - Print Map key - Email, value - Name+LastName\" ");
        try {
            personService.printMap();
        } catch (IllegalStateException e) {
            Log.error(nameLog, "IllegalStateException in Menu Utils. Case 25", e.getStackTrace());
        }

    }

    public void case26() {
        Log.info(nameLog, "26 - Save to file email students sorted by\" ");
        try {
            personService.emailToFile();
        } catch (IOException e) {
            Log.warning(nameLog, "Warning in Menu Utils Case 26", e.getStackTrace());
        }
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

    private LocalDateTime enterDate(String enterFormatData) {
        String dateString;
        DateTimeFormatter df = DateTimeFormatter.ofPattern(enterFormatData);
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Enter date and time  in format : " + LocalDateTime.now().format(df));

        while (true) {
            dateString = inputScanner.nextLine();
            try {
                return LocalDateTime.parse(dateString, df);
            } catch (DateTimeParseException e) {
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

    private void createdDir(Path path) {
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            Log.warning(nameLog, "Error create directory in WatchDir.class", e.getStackTrace());
        }
    }

}
