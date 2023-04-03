package repositories;


import models.AddMaterials;
import models.Homework;
import models.Lecture;
import models.model_enum.Role;
import services.LectureService;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LectureRepository implements Repository<Lecture>, Serializable {

    {
        CourseRepository courseRepository = CourseRepository.getInstance();
        PersonRepository personRepository = PersonRepository.getInstance();

        repository = new ArrayList<>();

        try {
            for (int i = 0; i < 5; i++) {
                //Thread.sleep(1);
                getRepository().add(new LectureService()
                        .create(
                                "Lecture " + i
                                , courseRepository.getById(1).orElseThrow()
                                , LocalDateTime.of(2022, 4, 8-i, 19,30 )
                                , personRepository.getByIdPerson(1,Role.TEACHER).orElseThrow()
                        ));
            }
            for (int i = 0; i < 3; i++) {
                getRepository().add(new LectureService()
                        .create(
                                "LectureSecond " + i
                                , courseRepository.getById(1).orElseThrow()
                                , LocalDateTime.now()
                                , personRepository.getByIdPerson(4,Role.TEACHER).orElseThrow()
                        ));
            }

        }  catch (NoSuchElementException e) {
            e.getStackTrace();
        }

    }
    private static LectureRepository instance;
    private final List<Lecture> repository;

    private LectureRepository() {

    }

    public static LectureRepository getInstance() {
        if (instance == null) {
            instance = new LectureRepository();
        }
        return instance;
    }

    @Override
    public List<Lecture> getRepository() {
        return repository;
    }

    @Override
    public void printRepository() {
        repository.forEach(System.out::println);
    }

    @Override
    public Optional<Lecture> getById(Integer id) {
        return repository.stream().filter(element -> element.getID().equals(id)).findAny();
    }

    @Override
    public List<Lecture> sortedByName() {
        return repository.stream()
                .sorted(Comparator.comparing(Lecture::getName))
                .collect(Collectors.toList());
    }

    public void printAfterBeforeDate(LocalDateTime startDateTime, LocalDateTime finishDateTime) {
        Predicate<Lecture> plAfter = element -> element.getCreationDate().isAfter(startDateTime);
        System.out.println('\n' + "Lecture after " + startDateTime);
        System.out.println("========================================");
        repository.stream().filter(plAfter).forEach(System.out::println);

        Predicate<Lecture> plBefore = element -> element.getCreationDate().isBefore(startDateTime);
        System.out.println('\n' + "Lecture before " + startDateTime);
        System.out.println("========================================");
        repository.stream().filter(plBefore).forEach(System.out::println);

        Predicate<Lecture> pb = element -> element.getCreationDate().isAfter(startDateTime)
                && element.getCreationDate().isBefore(finishDateTime);
        System.out.println('\n' + "Lecture between startDate : " + startDateTime + " - finishDate:  " + finishDateTime);
        System.out.println("========================================");
        repository.stream().filter(pb).forEach(System.out::println);

    }


    // List LectureID with min created time
    private List<Integer> lectureMinDateCreate () {
        LocalDateTime localDateTimeMin = repository.stream()
                .map(Lecture::getCreationDate)
                .min(LocalDateTime::compareTo).orElseThrow();
        return repository.stream()
                .filter(el -> el.getCreationDate().equals(localDateTimeMin))
                .map(Lecture::getID)
                .toList();
    }



    public void firstLectureMaxMaterials() throws NoSuchElementException {
        /*  1) Знайти лекцію, що була створена раніше за всіх
         *  2) Знайти у мапі по її Map<Integer(lectureId), List<AdditionalMaterial» лекції, які задовільняють першій умові
         *  3) Якщо їх декілька, обрати ті, що мають size List<AdditionalMaterial>.size()  - max(може бути одна або декілько)
         */

        List<AddMaterials> addMaterials = AddMaterialsRepository.getInstance().getRepository();

        // 1) Знайти лекцію, що була створена раніше за всіх
        List<Integer> lectureIDMinDate  = lectureMinDateCreate();

         System.out.println("---List lectureIDMinDate----");
        System.out.println(lectureIDMinDate);
        System.out.println("==========================\n");


        // 2) Знайти у мапі по її Map<Integer(lectureId), List<AdditionalMaterial» лекції, які задовільняють першій умові

        // Creating Map with key - LecturesID, Value - number of addMaterials
        Map<Integer, List<AddMaterials>> mapAddMaterialsByLectureID = addMaterials.stream()
                .collect(Collectors.groupingBy(el -> el.getLecture().getID()));

        System.out.println("---Map  key LectureID  Value AddMaterials----");
        System.out.println(mapAddMaterialsByLectureID);
        System.out.println("==========================\n");


        // Search in Map LectureID  with min DateCreation
        Map<Integer, List<AddMaterials>> filterMinTimeMap = mapAddMaterialsByLectureID.entrySet().stream()
                .filter(el -> lectureIDMinDate.contains(el.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println("---Filter Map by min Date.  Key LectureID  Value AddMaterials----");
        filterMinTimeMap.forEach((k, v) -> System.out.println(k + " : " + v));
        System.out.println("==========================\n");


        // 3) Якщо їх декілька, обрати ті, що мають size List<AdditionalMaterial>.size()  - max(може бути одна або декілько)

        // search LectureID with max AddMaterials
        Integer maxCountAddMaterials = filterMinTimeMap.values().stream()
                .map(List::size)
                .max(Comparator.naturalOrder())
                .orElseThrow();

        List<Integer> lectureIDResult = filterMinTimeMap.entrySet().stream()
                .filter(e -> e.getValue().size() == maxCountAddMaterials)
                .map(Map.Entry::getKey)
                .toList();

        // Print the found lecture
        System.out.println("--------Work result  - Lecture/s ---------");
        repository.stream().filter(e -> lectureIDResult.contains(e.getID())).forEach(System.out::println);
    }

    public void printLectureGroupByTeacher() {
        repository.stream()
                .collect(Collectors.groupingBy(
                        teacher -> teacher.getPerson().filter(p -> p.getRole().equals(Role.TEACHER))))
                .forEach((k, v) -> System.out.println(k + " : " + v));
    }


    public List<Lecture> lectureAddMaterialSortedByDate () {
        List<Lecture> lectureList;
        lectureList = repository.stream()
                .filter(v-> v.getLectureDate().getYear() < 2023)
                .sorted(Comparator.comparing(Lecture::getLectureDate))
                .toList();
                return lectureList;
    }

    public List<Lecture> firstLectureMaxHomework() throws NoSuchElementException {

        List<Homework> homeworks = HomeWorkRepository.getInstance().getRepository();

        // 1) Знайти лекцію, що була створена раніше за всіх
        List<Integer> lectureIDMinDate = lectureMinDateCreate();

        // 2) Знайти у мапі по її Map<Integer(lectureId), List<AdditionalMaterial» лекції, які задовільняють першій умові

        // Creating Map with key - LecturesID, Value - number of homeworks
        Map<Integer, List<Homework>> mapHomeworksByLectureID = homeworks.stream()
                .collect(Collectors.groupingBy(el -> el.getLecture().getID()));


        // Search in Map LectureID  with min DateCreation
        Map<Integer, List<Homework>> filterMinTimeMap = mapHomeworksByLectureID.entrySet().stream()
                .filter(el -> lectureIDMinDate.contains(el.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // 3) Якщо їх декілька, обрати ті, що мають size List<Homework>.size()  - max(може бути одна або декілько)

        // search LectureID with max Homeworks

        Integer maxCountHomeworks = filterMinTimeMap.values().stream()
                .map(List::size)
                .max(Comparator.naturalOrder())
                .orElseThrow();

        List<Integer> lectureIDResult = filterMinTimeMap.entrySet().stream()
                .filter(e -> e.getValue().size() == maxCountHomeworks)
                .map(Map.Entry::getKey)
                .toList();

        return repository.stream().filter(e -> lectureIDResult.contains(e.getID())).toList();

    }

}
