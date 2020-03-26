package Sims;

import Sims.Trait;

public class PersonType {
	protected int Charisma;
	protected int Romance;
	protected int Atracctive;
	protected int Intelligence;
	protected int Luck;
	
	/**
	 * CharacterRace constructor will not take in any parameters. The 
	 * default values for all the traits will be hard coded in.
	 */
	public PersonType() {
		
	}
	
	/**
	 * getMod is a universal getter. It accepts a Trait enum, which it then uses
	 * to get the appropriate private variable representing the requested stat
	 * modifier requested. This cuts down on the number of methods required to
	 * get all instance variables.
	 * 
	 * @return int The value of the requested stat modifier.
	 */
	public int getMod(Trait stat) {
		return 0;

	}
}