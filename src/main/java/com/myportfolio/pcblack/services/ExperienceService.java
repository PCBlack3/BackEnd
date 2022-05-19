package com.myportfolio.pcblack.services;

import com.myportfolio.pcblack.models.Experience;
import com.myportfolio.pcblack.repositories.ExperienceRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Black
 */
@Service
public class ExperienceService {
    
    @Autowired
    ExperienceRepository experienceRepository;
    
    public ArrayList<Experience> getAllExperience(){
        return (ArrayList<Experience>) experienceRepository.findAll();
    }
    
    public Experience save(Experience experience){
        return experienceRepository.save(experience);
    }
    
    public Experience getExperienceById(Long id){
        return experienceRepository.findById(id).get();
    }
    
    public boolean deleteById(Long id){
        try{
            experienceRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    
    public List<Experience> getExperienceByPersonId(Long id){
        return experienceRepository.findAllByPersonId(id);
    }
}
