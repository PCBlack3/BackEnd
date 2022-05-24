package com.myportfolio.pcblack.controllers;

import com.myportfolio.pcblack.models.Person;
import com.myportfolio.pcblack.services.PersonService;
import java.util.ArrayList;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author Black
 */
@CrossOrigin(origins = {"http://localhost:4200" , "https://pcblack-porfolio.web.app"})
@RestController
@RequestMapping("/api/person")
public class PersonController {
    
    @Autowired
    PersonService personService;
    
    @GetMapping("/all")
    public ArrayList<Person> getAllPersons(){
        return personService.getAllPersons();
    }
    
    @PostMapping()
    public Person savePerson(@RequestBody Person person){
        return personService.savePerson(person);
    }
    
    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable("id") Long id, @RequestBody Person personToChange){
        
        Person p = personService.findById(id);
        personToChange.setId(p.getId());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(personToChange, p);
        
        return personService.savePerson(p);
    }
    
    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable("id") Long id){
        return personService.getPersonById(id);
    }
    
    @GetMapping("/query")
    public ArrayList<Person> getPersonByApellido(@RequestParam("apellido") String apellido){
        return personService.getPersonByApellido(apellido);
    }
    
    @DeleteMapping("/{id}")
    public String removePerson(@PathVariable("id") Long id){
        if(personService.removePerson(id)){
            return "La persona id: " + id + ", se eliminó correctamente";
        } else {
            return "La persona no existe o no pudo ser eliminada";
        }
    }
    
}
