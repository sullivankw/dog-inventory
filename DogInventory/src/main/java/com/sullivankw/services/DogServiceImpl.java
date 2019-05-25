package com.sullivankw.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.sullivankw.Action;
import com.sullivankw.DogUtils;
import com.sullivankw.daos.DogDao;
import com.sullivankw.models.Dog;

@Service
public class DogServiceImpl implements DogService{
	
	private static Logger LOGGER = LoggerFactory.getLogger(DogServiceImpl.class);
	
	@Autowired
	DogDao dogDao;

	@Override
	public Dog getOne(int id) {	
		return dogDao.getOne(id);
	}

	@Override
	public List<Dog> getAll() {
		return dogDao.getAll();
	}

	@Override
	public List<Dog> create(Dog dog) {
    	Assert.notNull(dog, "dog is required");
    	Assert.notNull(dog.getName(), "dog name is required");
    	Assert.notNull(dog.getOwnerName(), "owner name is required");
    	Assert.notNull(dog.getNotes(), "notes is required");
		dogDao.create(dog);
		return getAll();
		
	}
	
	@Override
	public String delete(int id) {
		Dog dogToDelete = getOne(id);
		if (dogToDelete == null) {
			return DogUtils.createFailedDisplayMessage(Action.DELETE);
		}
			dogDao.delete(id);
			return DogUtils.createSuccessDisplayMessage(Action.DELETE);		
	}

	@Override
	public String update(int oldDogId, Dog newDog) {
		Dog oldDog = getOne(oldDogId);
		if (oldDog == null) {
			return DogUtils.createFailedDisplayMessage(Action.UPDATE);
		}
		newDog.setId(oldDog.getId());	
		Dog updatedDog = dogDao.update(newDog);
		LOGGER.debug("update successful for {}", updatedDog.toString());
		return DogUtils.createSuccessDisplayMessage(Action.UPDATE);
		
	}

}
