package repositories;


import models.AddMaterials;
import models.Lecture;
import models.model_enum.Role;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LectureRepository implements Repository<Lecture>, Serializable {

    private static LectureRepository instance;
    private final List<Lecture> repository;

    private LectureRepository() {
        repository = new ArrayList<>();
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


    public void firstLectureMaxMaterials() throws NoSuchElementException {
        /*  1) Знайти лекцію, що була створена раніше за всіх
         *  2) Знайти у мапі по її Map<Integer(lectureId), List<AdditionalMaterial» лекції, які задовільняють першій умові
         *  3) Якщо їх декілька, обрати ті, що мають size List<AdditionalMaterial>.size()  - max(може бути одна або декілько)
         */

        List<AddMaterials> addMaterials = AddMaterialsRepository.getInstance().getRepository();

        // 1) Знайти лекцію, що була створена раніше за всіх

        // List LectureID with min created time
        LocalDateTime localDateTimeMin = repository.stream()
                .map(Lecture::getCreationDate)
                .min(LocalDateTime::compareTo).orElseThrow();

        List<Integer> lectureIDMinDate = repository.stream()
                .filter(el -> el.getCreationDate().equals(localDateTimeMin))
                .map(Lecture::getID)
                .toList();

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



}
