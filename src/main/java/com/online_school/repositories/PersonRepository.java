package com.online_school.repositories;


import com.online_school.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {


    @Query(value = " SELECT p.name, p.last_name, COUNT(cp.person_id) " +
            "FROM persons AS p  JOIN courses_persons AS  cp on p.id = cp.person_id where p.role = 'STUDENT'" +
            "GROUP BY p.name, p.last_name " +
            "ORDER BY p.name", nativeQuery = true)
    List<Object[]> findByCountCourse();


}

