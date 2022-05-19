package com.myportfolio.pcblack.services;

import com.myportfolio.pcblack.models.Project;
import com.myportfolio.pcblack.repositories.ProjectRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Black
 */
@Service
public class ProjectService {
    
    @Autowired
    ProjectRepository projectRepository;
    
    public ArrayList<Project> getAllProject(){
        return (ArrayList<Project>) projectRepository.findAll();
    }
    
    public Project save(Project project){
        return projectRepository.save(project);
    }
    
    public Project getProjectById(Long id){
        return projectRepository.findById(id).get();
    }
    
    public boolean deleteById(Long id){
        try{
            projectRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    
    public List<Project> getProjectByPersonId(Long personId){
        return projectRepository.findAllByPersonId(personId);
    }
}
