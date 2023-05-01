package repositories;


import models.AddMaterials;
import models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddMaterialsRepository extends JpaRepository<AddMaterials, Long> {


}
