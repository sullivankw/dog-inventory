package com.sullivankw;

public class DogUtils {
	
	public static String createSuccessDisplayMessage(Action action) {
		return action.name() + " was successful"; 		
	}
	
	public static String createFailedDisplayMessage(Action action) {
		return "unable to " + action.name() + " the id provided was not found";
		
	}

}
