package com.sullivankw.services;

import java.util.List;

import com.sullivankw.models.Dog;

public interface DogService {
	
	String delete(int id);
	String update(int oldDogId, Dog newDog);
	Dog getOne(int id);
	List<Dog> getAll();
	void create(Dog dog);

}
