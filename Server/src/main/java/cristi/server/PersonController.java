/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cristi.server;

import DAO.Person;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author crist
 */
@RestController
@RequestMapping("/persons")
public class PersonController {
    private List<String> persons = new ArrayList<>();
    public PersonController() {
        persons = Person.getPersons();
    }
    @GetMapping
    public List<String> getPersons() {
        persons = Person.getPersons();
        return persons;
    }
    @PostMapping
    public int createPerson(@RequestParam String name) {
        int id = 1 + persons.size();
        Person.createPerson(name);
        persons = Person.getPersons();
        return id;
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePerson(@PathVariable int id, @RequestParam String name) {
        if (!Person.existID(id)) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
        Person.updatePerson(id, name);
        return new ResponseEntity<>("Product updated successsfully", HttpStatus.OK);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        if (!Person.existID(id)) {
            return new ResponseEntity<>("Product not found", HttpStatus.GONE);
        }
        Person.deletePerson(id);
        return new ResponseEntity<>("Product removed", HttpStatus.OK);
    }
    

}
