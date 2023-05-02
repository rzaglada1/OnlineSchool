package com.online_school.repositories;


import com.online_school.models.AddMaterials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddMaterialsRepository extends JpaRepository<AddMaterials, Long> {


}
