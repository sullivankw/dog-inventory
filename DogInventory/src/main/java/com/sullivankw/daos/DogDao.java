package com.sullivankw.daos;

import java.util.List;

import com.sullivankw.models.Dog;

public interface DogDao {
	
	Dog getOne(int id);
	List<Dog> getAll();
	Dog update(Dog newDog);
	void delete(int id);
	void create(Dog dog);

}
