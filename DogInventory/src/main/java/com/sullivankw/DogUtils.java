package com.sullivankw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sullivankw.models.Dog;

public class DogUtils {
	
	public static String createSuccessDisplayMessage(Action action) {
		return action.name() + " was successful"; 		
	}
	
	public static String createFailedDisplayMessage(Action action) {
		return "unable to " + action.name() + " the id provided was not found";
		
	}
	
	public static List<Dog> convertArrayToArrayList(Dog[] dogs) {		
		return new ArrayList<Dog>(Arrays.asList(dogs));
	}
	
	public static int getRandomId() {
		double randomDouble = Math.random();
		randomDouble = randomDouble * 9000000 + 1;
		return (int) randomDouble;
	}

}
