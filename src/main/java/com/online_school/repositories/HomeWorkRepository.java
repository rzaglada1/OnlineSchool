package com.online_school.repositories;


import com.online_school.models.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeWorkRepository extends JpaRepository<Homework, Long> {


}
