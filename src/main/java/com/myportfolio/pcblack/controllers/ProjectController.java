
package com.myportfolio.pcblack.controllers;

import com.myportfolio.pcblack.models.Person;
import com.myportfolio.pcblack.models.Project;
import com.myportfolio.pcblack.services.PersonService;
import com.myportfolio.pcblack.services.ProjectService;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Black
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/project")
public class ProjectController {
    
    @Autowired
    PersonService personService;
    
    @Autowired
    ProjectService projectService;
    
    @GetMapping("/all")
    public ResponseEntity<List<Project>> getAllProject(){
        List<Project> projectList = projectService.getAllProject();
        return new ResponseEntity<>(projectList, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable(value = "id") Long id){
        Project project = projectService.getProjectById(id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }
    
    @GetMapping("/person/{id}")
    public ResponseEntity<List<Project>> getAllProjectByPersonId(@PathVariable(value = "personId") Long personId){
        List<Project> projectList = new ArrayList<>();
        if(personService.getPersonById(personId) != null){
            projectList = projectService.getProjectByPersonId(personId);
            return new ResponseEntity<>(projectList, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(projectList, HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/person/{person_id}")
    public ResponseEntity<Project> createProject(@PathVariable(value = "person_id") Long personId, @RequestBody Project projectRequest){
        Person p = personService.findById(personId);
        projectRequest.setPerson(p);
        Project newProject = projectService.save(projectRequest);
        return new ResponseEntity<>(newProject, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateComment(@PathVariable("id") Long id, @RequestBody Project projectRequest){
        Project project = projectService.getProjectById(id);
        project.setPerson(projectRequest.getPerson());
        projectRequest.setId(project.getId());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(projectRequest, project);
        
        return new ResponseEntity<>(projectService.save(project), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProject(@PathVariable("id") Long id){
        projectService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
