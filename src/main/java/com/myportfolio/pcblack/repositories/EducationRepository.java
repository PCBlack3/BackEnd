/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myportfolio.pcblack.repositories;

import com.myportfolio.pcblack.models.Education;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Black
 */
public interface EducationRepository extends JpaRepository<Education, Long> {
    
        public List<Education> findAllByPersonId(Long personId);
}
