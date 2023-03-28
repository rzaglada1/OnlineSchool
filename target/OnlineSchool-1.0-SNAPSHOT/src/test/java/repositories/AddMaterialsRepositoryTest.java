package repositories;

import models.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class AddMaterialsRepositoryTest {

    static AddMaterials addMaterials0;
    static AddMaterials addMaterials1;

    @BeforeAll
    static void createTestRepository() {
        AddMaterialsRepository addMaterialsRepository = AddMaterialsRepository.getInstance();

        addMaterials0 = Mockito.mock(AddMaterials.class);
        addMaterials1 = Mockito.mock(AddMaterials.class);

        when(addMaterials0.getLectureID()).thenReturn(Optional.of(0));
        when(addMaterials1.getLectureID()).thenReturn(Optional.of(1));
        when(addMaterials0.getName()).thenReturn("Name0");
        when(addMaterials1.getName()).thenReturn("Name1");

        addMaterialsRepository.getRepository().add(addMaterials0);
        addMaterialsRepository.getRepository().add(addMaterials1);
    }

    @Test
    void testGetAddMaterialsByLectureId () {
        List<AddMaterials> actualList = AddMaterialsRepository.getInstance().getAddMaterialsByLectureId(1);
        List<AddMaterials> expectedList = List.of(addMaterials1);

        assertEquals(expectedList, actualList);
    }

    @Test
    void testGetById () {
        Optional<AddMaterials> actualList = AddMaterialsRepository.getInstance().getById(0);
        Optional<AddMaterials> expectedList = Optional.of(addMaterials0);

        assertEquals(expectedList, actualList);
    }

    @Test
    void testSortedByName () {
        List<AddMaterials> actualList = AddMaterialsRepository.getInstance().sortedByName();
        List<AddMaterials> expectedList = List.of(addMaterials0,addMaterials1);

        assertEquals(expectedList, actualList);
    }





}