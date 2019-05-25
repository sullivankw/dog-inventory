package com.sullivankw.daos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sullivankw.DogUtils;
import com.sullivankw.models.Dog;

@Component
public class DogDaoImpl implements DogDao {
	
	private static Logger LOGGER = LoggerFactory.getLogger(DogDaoImpl.class);
			
	@Autowired
	private File file;
	
	private BufferedWriter bufferedWriter;
	
	@Override
	public List<Dog> getAll() {
		List<Dog> dogList = new ArrayList<Dog>();
		BufferedReader bufferedReader;
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			Dog[] dogs = new Gson().fromJson(bufferedReader, Dog[].class);
			if (dogs != null) {
				dogList = DogUtils.convertArrayToArrayList(dogs);
			}
		} catch (FileNotFoundException e) {
			LOGGER.error("unable to retrieve dog list. ()", e.getCause());
		}
		return dogList;
	}
	
	@Override
	public Dog getOne(int id) {
		for (Dog dog: getAll()) {
			if (dog.getId() == id) {
				return dog;
			}
		}
		return null;
	}

	@Override
	public Dog update(Dog newDog) {
		try {
		delete(newDog.getId());
		create(newDog);
		} catch (Exception e) {
			LOGGER.error("unable to update dog with id () ", newDog.getId());
		}
		return newDog;
	}

	@Override
	public void delete(int id) {
		List<Dog> dogs = getAll();
		dogs.removeIf(b -> b.getId() == id);
		try {
			addAll(new GsonBuilder().create(),dogs);
		} catch (IOException e) {
			LOGGER.error("unable to reload list. ()", e.getCause());
		}
		
	}

	@Override
	public void create(Dog dog) {
		if (dog.getId() == 0) {
		dog.setId(DogUtils.getRandomId());
		}
		Gson gson = new GsonBuilder().create();
		String dogAsJson = gson.toJson(dog);
		List<Dog> dogs = getAll();
		BufferedWriter bufferedWriter = null;
			try {	
				if (CollectionUtils.isEmpty(dogs)) {
					bufferedWriter = getBufferedWriter();
					bufferedWriter.write('[');
					bufferedWriter.write(dogAsJson);
					bufferedWriter.write(']');
					bufferedWriter.flush();
					bufferedWriter.close();
				} else {
					dogs.add(dog);
					addAll(gson, dogs);
				}
			} catch (IOException e) {
				LOGGER.error("error creating entry or reloading list {}", e.getCause());
			}
				
	}

	private void addAll(Gson gson, List<Dog> dogs) throws IOException {
		BufferedWriter bufferedWriter = getBufferedWriter();
		writeFirstEntry(bufferedWriter, gson.toJson(dogs.get(0)));
		for (int i =1; i < dogs.size(); i ++) {
			bufferedWriter.write(',');
			bufferedWriter.write(gson.toJson(dogs.get(i)));
		}
		bufferedWriter.write(']');
		bufferedWriter.flush();
		bufferedWriter.close();
		
	}
	
	private void writeFirstEntry(BufferedWriter bufferedWriter,String dogAsJson) throws IOException {	
		bufferedWriter.write('[');
		bufferedWriter.write(dogAsJson);
		bufferedWriter.flush();
	}
	
	private BufferedWriter getBufferedWriter() throws IOException {	
		if (bufferedWriter == null) {
		return new BufferedWriter(new FileWriter(file, false));
		} else {
		return bufferedWriter;	
		}
	}

}
