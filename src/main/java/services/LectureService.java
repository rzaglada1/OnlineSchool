package services;


import models.*;
import models.model_enum.Role;
import org.springframework.beans.factory.annotation.Autowired;
import repositories.HomeWorkRepository;
import repositories.LectureRepository;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class LectureService implements Service {


    private LectureRepository lectureRepository;

    private AddMaterialsService addMaterialsService;

    private HomeWorkRepository homeWorkRepository;

    public LectureService(){}


    @Autowired
    public void setLectureRepository(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    @Autowired
    public void setAddMaterialsService(AddMaterialsService addMaterialsService) {
        this.addMaterialsService = addMaterialsService;
    }

    @Autowired
    public void setHomeWorkRepository(HomeWorkRepository homeWorkRepository) {
        this.homeWorkRepository = homeWorkRepository;
    }

    public List<Lecture> getAllLecture() {
        return lectureRepository.getRepository();
    }

    public void printRepository() {
        getAllLecture().forEach(System.out::println);
    }

    public Optional<Lecture> getLectureById(long id) {
        return lectureRepository.getById(id);
    }

    public List<Lecture> sortedByName() {
        return lectureRepository.sortedByName();
    }


    public void printAfterBeforeDate(LocalDateTime startDateTime, LocalDateTime finishDateTime) {
        Predicate<Lecture> plAfter = element -> element.getCreationDate().isAfter(startDateTime);
        System.out.println('\n' + "Lecture after " + startDateTime);
        System.out.println("========================================");
        getAllLecture().stream().filter(plAfter).forEach(System.out::println);

        Predicate<Lecture> plBefore = element -> element.getCreationDate().isBefore(startDateTime);
        System.out.println('\n' + "Lecture before " + startDateTime);
        System.out.println("========================================");
        getAllLecture().stream().filter(plBefore).forEach(System.out::println);

        Predicate<Lecture> pb = element -> element.getCreationDate().isAfter(startDateTime)
                && element.getCreationDate().isBefore(finishDateTime);
        System.out.println('\n' + "Lecture between startDate : " + startDateTime + " - finishDate:  " + finishDateTime);
        System.out.println("========================================");
        getAllLecture().stream().filter(pb).forEach(System.out::println);

    }


    // List LectureID with min created time
    private List<Long> lectureMinDateCreate() {
        LocalDateTime localDateTimeMin = getAllLecture().stream()
                .map(Lecture::getCreationDate)
                .min(LocalDateTime::compareTo).orElseThrow();
        return getAllLecture().stream()
                .filter(el -> el.getCreationDate().equals(localDateTimeMin))
                .map(Lecture::getID)
                .toList();
    }


    public void firstLectureMaxMaterials() throws NoSuchElementException {
        /*  1) Знайти лекцію, що була створена раніше за всіх
         *  2) Знайти у мапі по її Map<Integer(lectureId), List<AdditionalMaterial» лекції, які задовільняють першій умові
         *  3) Якщо їх декілька, обрати ті, що мають size List<AdditionalMaterial>.size()  - max(може бути одна або декілько)
         */

        List<AddMaterials> addMaterials = addMaterialsService.getAllAddMaterials();

        // 1) Знайти лекцію, що була створена раніше за всіх
        List<Long> lectureIDMinDate = lectureMinDateCreate();

        System.out.println("---List lectureIDMinDate----");
        System.out.println(lectureIDMinDate);
        System.out.println("==========================\n");


        // 2) Знайти у мапі по її Map<Integer(lectureId), List<AdditionalMaterial» лекції, які задовільняють першій умові

        // Creating Map with key - LecturesID, Value - number of addMaterials
        Map<Long, List<AddMaterials>> mapAddMaterialsByLectureID = addMaterials.stream()
                .collect(Collectors.groupingBy(el -> el.getLecture().getID()));

        System.out.println("---Map  key LectureID  Value AddMaterials----");
        System.out.println(mapAddMaterialsByLectureID);
        System.out.println("==========================\n");


        // Search in Map LectureID  with min DateCreation
        Map<Long, List<AddMaterials>> filterMinTimeMap = mapAddMaterialsByLectureID.entrySet().stream()
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

        List<Long> lectureIDResult = filterMinTimeMap.entrySet().stream()
                .filter(e -> e.getValue().size() == maxCountAddMaterials)
                .map(Map.Entry::getKey)
                .toList();

        // Print the found lecture
        System.out.println("--------Work result  - Lecture/s ---------");
        getAllLecture().stream().filter(e -> lectureIDResult.contains(e.getID())).forEach(System.out::println);
    }

    public void printLectureGroupByTeacher() {
        getAllLecture().stream()
                .collect(Collectors.groupingBy(
                        teacher -> teacher.getPerson().filter(p -> p.getRole().equals(Role.TEACHER))))
                .forEach((k, v) -> System.out.println(k + " : " + v));
    }


    public List<Lecture> lectureAddMaterialSortedByDate() {
        List<Lecture> lectureList;
        lectureList = getAllLecture().stream()
                .filter(v -> v.getLectureDate().getYear() < 2023)
                .sorted(Comparator.comparing(Lecture::getLectureDate))
                .toList();
        lectureList.forEach(System.out::println);
        return lectureList;
    }

    public List<Lecture> firstLectureMaxHomework() throws NoSuchElementException {

        List<Homework> homeworks = homeWorkRepository.getRepository();

        // 1) Знайти лекцію, що була створена раніше за всіх
        List<Long> lectureIDMinDate = lectureMinDateCreate();

        // 2) Знайти у мапі по її Map<Integer(lectureId), List<AdditionalMaterial» лекції, які задовільняють першій умові

        // Creating Map with key - LecturesID, Value - number of homeworks
        Map<Long, List<Homework>> mapHomeworksByLectureID = homeworks.stream()
                .collect(Collectors.groupingBy(el -> el.getLecture().getID()));


        // Search in Map LectureID  with min DateCreation
        Map<Long, List<Homework>> filterMinTimeMap = mapHomeworksByLectureID.entrySet().stream()
                .filter(el -> lectureIDMinDate.contains(el.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // 3) Якщо їх декілька, обрати ті, що мають size List<Homework>.size()  - max(може бути одна або декілько)

        // search LectureID with max Homeworks

        Integer maxCountHomeworks = filterMinTimeMap.values().stream()
                .map(List::size)
                .max(Comparator.naturalOrder())
                .orElseThrow();

        List<Long> lectureIDResult = filterMinTimeMap.entrySet().stream()
                .filter(e -> e.getValue().size() == maxCountHomeworks)
                .map(Map.Entry::getKey)
                .toList();

        return getAllLecture().stream().filter(e -> lectureIDResult.contains(e.getID())).toList();

    }


    public void saveLecture(Lecture lecture) {
        lectureRepository.saveLectureToRepository(lecture);
    }

}
