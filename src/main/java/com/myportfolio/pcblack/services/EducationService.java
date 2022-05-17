package com.myportfolio.pcblack.services;

import com.myportfolio.pcblack.models.Education;
import com.myportfolio.pcblack.repositories.EducationRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Black
 */
@Service
public class EducationService {

    @Autowired
    EducationRepository educationRepository;

    public ArrayList<Education> getAllEducation() {
        return (ArrayList<Education>) educationRepository.findAll();
    }

    public Education save(Education education) {
        return educationRepository.save(education);
    }

    public Education getEducationById(Long id) {
        return educationRepository.findById(id).get();
    }

    public boolean deleteById(Long id) {
        try {
            educationRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public List<Education> getEducationByPersonId(Long personId){
        return educationRepository.findAllByPersonId(personId);
    }
    
    
}
