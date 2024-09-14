package com.practice3.practice3.service;

import com.practice3.practice3.Exceptionhadle.ResourceNotFoundException;
import com.practice3.practice3.dto.Persondto;
import com.practice3.practice3.entity.Personentity;
import com.practice3.practice3.repository.Personrepository;
import lombok.ToString;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
@ToString
@Service
public class PService {
    //inject modelmapper and Repository not entity entity is just for conversion
    ModelMapper modelMapper;
    Personrepository repository;

    PService(ModelMapper modelMapper, Personrepository repository) {
        this.modelMapper=modelMapper;
        this.repository=repository;
    }

    public Optional<Persondto> getbyid(Long id) {
        //just sent response
        return repository.findById(id)
                .map(entity->modelMapper.map(entity,Persondto.class));
    }

    public List<Persondto> getall() {//the method must return list
        //same here use stream
        //store it in a entity list first;
        List<Personentity> entity= repository.findAll();
        return entity
                .stream()
                .map(entity1-> modelMapper.map(entity1,Persondto.class))
                .collect(Collectors.toList());
    }

    public Persondto createperson(Persondto dto) {

        Personentity getentity=modelMapper.map(dto,Personentity.class);//conver dto to entity
        //now save it in repository
        Personentity savedentity=repository.save(getentity);
        //now conver savedentity to dto and return
        return modelMapper.map(savedentity,Persondto.class);
    }

    public Persondto updateperson(Persondto dto, Long id) {
        //find and get it
        //set through dto
        //save it in repo conver and return
        if(!repository.existsById(id)) throw new ResourceNotFoundException("Id not found"+id);
        Personentity getentity=repository.findById(id).get();//herewe have to get
        Personentity personentity=modelMapper.map(dto,Personentity.class);
        personentity.setId(id);//change
        //convert saved dto to entityy again
        Personentity savedentity=repository.save(personentity);
        return modelMapper.map(savedentity,Persondto.class);
    }

    public Persondto updatefields(Map<String, Object> update, Long id) {

       Personentity getentity=repository.findById(id).get();

        update.forEach((field,value)->{
            //Entityclass.clas and its field
            Field fieldtobeupdated= ReflectionUtils.findRequiredField(Personentity.class,field);
            fieldtobeupdated.setAccessible(true);
            ReflectionUtils.setField(fieldtobeupdated, getentity , value);
        });
        return modelMapper.map(getentity,Persondto.class);

    }

    public boolean deletebyid(Long id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    //to get by id just use Optional method
}
