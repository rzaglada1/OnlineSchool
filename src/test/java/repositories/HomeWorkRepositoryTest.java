package repositories;


class HomeWorkRepositoryTest {

//    static Homework homework0;
//    static Homework homework1;
//
//    @BeforeAll
//    static void createTestRepository() {
//        HomeWorkRepository homeWorkRepository = HomeWorkRepository.getInstance();
//
//        homework0 = Mockito.mock(Homework.class);
//        homework1 = Mockito.mock(Homework.class);
//
//        when(homework0.getLectureID()).thenReturn(Optional.of(0));
//        when(homework1.getLectureID()).thenReturn(Optional.of(1));
//        when((homework0.getName())).thenReturn("Name0");
//        when((homework1.getName())).thenReturn("Name1");
//
//        homeWorkRepository.getRepository().add(homework0);
//        homeWorkRepository.getRepository().add(homework1);
//    }
//
//    @Test
//    void TestGetById() {
//        Optional<Homework> actualList = HomeWorkRepository.getInstance().getById(0);
//        Optional<Homework> expectedList = Optional.of(homework0);
//
//        assertEquals(expectedList, actualList);
//    }
//
//    @Test
//    void sortedByName() {
//        List<Homework> actualList = HomeWorkRepository.getInstance().sortedByName();
//        List<Homework> expectedList = List.of(homework0,homework1);
//
//        assertEquals(expectedList, actualList);
//    }
//
//    @Test
//    void getHomeworkByLectureId() {
//        List<Homework> actualList = HomeWorkRepository.getInstance().getHomeworkByLectureId(0);
//        List<Homework> expectedList = List.of(homework0);
//
//        assertEquals(expectedList, actualList);
//    }
}