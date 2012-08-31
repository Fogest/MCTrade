package me.fogest.mctrade;

import java.util.Random;

public class Verify {

	public static int createUserVerification (String player){
		Random gen = new Random();
		int num = gen.nextInt(6000);
		int UserId = DatabaseManager.getUserId(player);
		DatabaseManager.setMcCode(num, UserId);
		return num;
	}
	
}
