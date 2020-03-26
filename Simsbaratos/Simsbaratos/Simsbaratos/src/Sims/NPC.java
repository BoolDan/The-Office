package Sims;

import java.util.Random;

public class NPC extends PersonType{

	Random r1;
	
	NPC (int Atracc, int Charis, int Roman, int Intelli, int Luck ){
		Charisma = Charis;
		Atracctive = Atracc;
		Romance = Roman;
		Intelligence = Intelli;
		Luck = Luck;
		
		/*Charaff = r1.nextInt(30)/100;
		Atracaff = r1.nextInt(30)/100;
		Romancaff = r1.nextInt(30)/100;
		Intelliaff = r1.nextInt(30)/100;*/
	}
	


}
