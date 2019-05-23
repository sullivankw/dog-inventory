package com.sullivankw.daos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sullivankw.models.Dog;

@Component
public class DogDaoInMemoryImpl implements DogDao{
	
	private static List<Dog> dogs;
	
	   static { 
			dogs = new ArrayList<Dog>();
			
			Dog dog = new Dog();
			dog.setId(1);
			dog.setName("Sparky");
			dog.setOwnerName("John Smith");
			List<String> notes = new ArrayList<String>();
			notes.add("Good boy");
			notes.add("Fast");
			dog.setNotes(notes);
			dogs.add(dog);
			
			Dog dog2 = new Dog();
			dog2.setId(2);
			dog2.setName("Sparky2");
			dog2.setOwnerName("John Smith2");
			List<String> notes2 = new ArrayList<String>();
			notes2.add("Good boy2");
			notes2.add("Fast2");
			dog2.setNotes(notes2);
			dogs.add(dog2);
			
			Dog dog3 = new Dog();
			dog.setId(3);
			dog.setName("Sparky3");
			dog.setOwnerName("John Smith3");
			List<String> notes3 = new ArrayList<String>();
			notes3.add("Good boy3");
			notes3.add("Fast3");
			dog3.setNotes(notes3);
			dogs.add(dog3);
			
	    } 
		
	@Override
	public void delete(int id) {
		
		dogs.removeIf(b -> b.getId() == id);
		
	}
	
	@Override
	public Dog getOne(int id) {
		for (Dog dog : dogs) {
			if (dog.getId() == id) {
				return dog;
			}
		}	
		return null;
	}

	@Override
	public List<Dog> getAll() {
		return dogs;
	}

	@Override
	public Dog update(Dog oldDog, Dog newDog) {
		dogs.removeIf(d -> d.getId() == oldDog.getId());
		newDog.setId(oldDog.getId());
		dogs.add(newDog);
		return newDog;
		
	}

	@Override
	public void create(Dog dog) {
		double value = Math.random();
		dog.setId(dogs.size() + 1);
		dogs.add(dog);
		
	}

}
