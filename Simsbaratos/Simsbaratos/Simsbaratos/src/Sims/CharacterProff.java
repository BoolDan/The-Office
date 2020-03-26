package Sims;

import java.io.Serializable;

import Sims.Trait;

/**
 * CharacterClass is the super method for all of the different classes that can
 * be played in the game. Each different class will hold different starting
 * values for all the traits. The traits will be able to be modified once the
 * player levels up in game.
 * 
 * @author Matt Turner, Ross Bottorf, Zach Boe, Jonathan Perrine
 * 
 */
public abstract class CharacterProff implements Serializable {

	private static final long serialVersionUID = 1L;
	private int Charisma;
	private int Romance;
	private int Atractive;
	private int Intelligence;
	private int Luck;

	// private int tecniqueMod;

	/**
	 * CharacterClass constructor is an empty constructor.
	 */
	public CharacterProff() {

	}

	public int getMod(Trait stat) {
		if (stat == Trait.INTELLIGENCE)
			return Intelligence;
		else if (stat == Trait.LUCK)
			return Luck;
		else if (stat == Trait.ATRACTIVE)
			return Atractive;
		else if (stat == Trait.ROMANCE)
			return Romance;
		else if (stat == Trait.CHARISMA)
			;
			return Charisma;
		

	}

	/**
	 * setMod is a universal setter. It accepts a Trait enum, which it then uses
	 * to set the appropriate private variable representing the requested stat
	 * modifier. This cuts down on the number of methods required to set all
	 * instance variables.
	 * 
	 * @param int value The value to set the stat to.
	 * @param Trait
	 *            stat The stat to modify.
	 */
	public void setMod(int value, Trait stat) {
		if (stat == Trait.INTELLIGENCE)
			Intelligence = value;
		else if (stat == Trait.ROMANCE)
			Romance = value;
		else if (stat == Trait.ATRACTIVE)
			Atractive = value;
		else if (stat == Trait.CHARISMA)
			Charisma = value;
		else if (stat == Trait.LUCK)
			Luck = value;
	}

	/**
	 * This method overrides Object's toString() to display a simple text
	 * message of the player's class.
	 * 
	 * @return A String that represents the player's class.
	 */
	public abstract String toString();
}
