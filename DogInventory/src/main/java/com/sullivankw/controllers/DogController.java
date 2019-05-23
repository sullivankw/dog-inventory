package com.sullivankw.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sullivankw.services.DogService;

import models.Dog;

@RestController
@RequestMapping(value="/dogs")
public class DogController {
	
	@Autowired
	DogService dogService;

    @GetMapping(value="/{id}")
    public Dog getOne(@PathVariable int id) {
        return dogService.getOne(id);
    }

    @GetMapping
    public List<Dog> getAll() {
        return dogService.getAll();
    }

    @DeleteMapping(value="/{id}")
    public String delete(@PathVariable int id) {
    	return dogService.delete(id);
    }
    @PutMapping(value="/{id}")
    public String update(@PathVariable int id, @RequestBody Dog dog) {
    	return dogService.update(id, dog);
    }
    @PostMapping
    public List<Dog>add(@RequestBody Dog dog) {
    	dogService.create(dog);
    	return dogService.getAll();
    }

}
