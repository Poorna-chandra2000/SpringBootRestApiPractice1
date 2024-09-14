package com.practice3.practice3.controller;

import com.practice3.practice3.Exceptionhadle.ResourceNotFoundException;
import com.practice3.practice3.dto.Persondto;
import com.practice3.practice3.service.PService;
import jakarta.validation.Valid;
import org.modelmapper.internal.bytebuddy.asm.Advice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@RestController
@CrossOrigin(origins = "*")  // Allow all origins, or specify your frontend URL for security
@RequestMapping("/person1")
public class Personcontroller {
    //inject service
    PService pservice;
    Personcontroller(PService pservice){
        this.pservice=pservice;
    }

    //we use ResponseEntity method or class to send response to servicelayer for everything
    //type DtOclass
    @GetMapping(path = "/{id}")
    ResponseEntity<Persondto> getbyid(@PathVariable(name="id") Long id){
        //just do the mapping while sending request
        Optional<Persondto> dto=pservice.getbyid(id);//follow this method to store
        return dto
                .map(dto1->ResponseEntity.ok(dto1))
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: "+id));
    }

    @GetMapping//this must be list of list as list will be returned
    ResponseEntity<List<Persondto>> getall(){
        return ResponseEntity.ok(pservice.getall());
    }

    @PostMapping
    //from here we send (RequestBody)dto object and id
    ResponseEntity<Persondto> createperson(@Valid @RequestBody Persondto dto){
        //first save it in a new dto variable and sent reponse
        Persondto pdto=pservice.createperson(dto);
        //now send the response by creating newobject of reponse every single time

        return new ResponseEntity<>(pdto, HttpStatus.CREATED);
    }

    @PutMapping(path="/{id}")
    ResponseEntity<Persondto> updateperson(@RequestBody Persondto dto,@PathVariable Long id){
        //here just return id and object
        return ResponseEntity.ok(pservice.updateperson(dto,id));
    }

    @PatchMapping(path="/{id}")
    //now this take Map<String,Object> update,id
    ResponseEntity<Persondto> updatefields(@RequestBody Map<String,Object>update,@PathVariable Long id){
        return ResponseEntity.ok(pservice.updatefields(update,id));
    }

    //no need of object here just id is enough
    //create boolean type method
    @DeleteMapping(path="/{id}")
    ResponseEntity<Boolean> delbyid(@PathVariable Long id){
        boolean deleted=pservice.deletebyid(id);
        if(deleted){
            return ResponseEntity.ok(true);
        }
        //else id not found just delete
        return ResponseEntity.notFound().build();
    }

}
