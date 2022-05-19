
package com.myportfolio.pcblack.controllers;

import com.myportfolio.pcblack.models.Experience;
import com.myportfolio.pcblack.models.Person;
import com.myportfolio.pcblack.services.ExperienceService;
import com.myportfolio.pcblack.services.PersonService;
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
@RequestMapping("/api/experience")
public class ExperienceController {
    
    @Autowired
    PersonService personService;
    
    @Autowired
    ExperienceService experienceService;
    
    @GetMapping("/all")
    public ResponseEntity<List<Experience>> getAllExperience(){
        List<Experience> experienceList = experienceService.getAllExperience();
        return new ResponseEntity<>(experienceList, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Experience> getExperienceById(@PathVariable(value = "id") Long id){
        Experience experience = experienceService.getExperienceById(id);
        return new ResponseEntity<>(experience, HttpStatus.OK);
    }
    
    @GetMapping("/person/{person_id}")
    public ResponseEntity<List<Experience>> getAllExperienceByPersonId(@PathVariable(value = "person_id") Long personId){
        List<Experience> experienceList = new ArrayList();
        if(personService.getPersonById(personId) != null){
            experienceList = experienceService.getExperienceByPersonId(personId);
            return new ResponseEntity<>(experienceList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(experienceList, HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/person/{person_id}")
    public ResponseEntity<Experience> createExperience(@PathVariable(value = "person_id")Long personId, @RequestBody Experience experienceRequest){
        Person p = personService.findById(personId);
        experienceRequest.setPerson(p);
        Experience newExperience = experienceService.save(experienceRequest);
        return new ResponseEntity<>(newExperience, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Experience> updateComment(@PathVariable("id") Long id, @RequestBody Experience experienceRequest){
        Experience experience = experienceService.getExperienceById(id);
        experience.setPerson(experienceRequest.getPerson());
        experienceRequest.setId(experience.getId());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(experienceRequest, experience);
        
        return new ResponseEntity<>(experienceService.save(experience), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteExperience(@PathVariable("id") Long id){
        experienceService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
