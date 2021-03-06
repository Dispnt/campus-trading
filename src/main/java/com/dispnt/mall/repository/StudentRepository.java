package com.dispnt.mall.repository;

import com.dispnt.mall.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;



public interface StudentRepository extends JpaRepository<Student, Integer> {

    boolean existsByIdAndNameAndPhoneNumber(int id, String name,String phoneNumber);

}
