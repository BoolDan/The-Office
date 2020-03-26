package Sims;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Timer;

import Rooms.World;
import Sims.PersonType;
import Sims.CharacterProff;
import Rooms.DatabaseObject;
import Rooms.Mobile;
import Rooms.Movable;
import Rooms.Room;
import Sims.Trait;

/**
 * Players are an object in the world, which acts as an avatar for those
 * connected to the MUD, and stores their character. It extends DatabaseObject
 * and implements the Movable interface.
 * 
 * @author Matt Turner, Ross Bottorf, Zach Boe, Jonathan Perrine
 * 
 */
public class Player extends Rooms.DatabaseObject implements Rooms.Movable {

	private static final long serialVersionUID = 1L;
	private CharacterProff gameClass;
	private int Charisma;
	private int Romance;
	private int Atracctive;
	private int Intelligence;
	private int Luck;
	//private transient server.Client client;
	private String password;
	//private GearList gearList;
	private int roomId;
	private Player mySelf;

	/**
	 * The Player constructor takes a name to set the name of the player.
	 * Default stats, weapon, and equipment are added here as well.
	 * 
	 * @param name
	 *            - The String that represents the players name.
	 */
	public Player(String name) {
		super(name);
		this.roomId = 1;

		setCharacterClass(Accountant.getInstance());



		// This is the default _default_ gear.
		weaponEquipped = new Weapon("Fist",
				"It's a fist, at least you know how to make one", 1, 1);
		armorEquipped = new Armor(
				"Jumpsuit",
				"basic clothes. You wouldn't like to be fighting in just these.",
				1, 'L');

		// Default gear. not equipped, but available.
		Rooms.World
				.getInstance()
				.addGearToWorld(
						new HealthOrb(
								"Life Orb",
								"(A health orb, adds 5 hit points when used. Regenerates every 10 seconds. Dropping it would be a bad idea.)",
								5), this);

		Rooms.World.getInstance().addGearToWorld(
				new Weapon("Light Pistol", "(Not a Knife.)", 1, 3), this);

		Rooms.World.getInstance().addGearToWorld(
				new Weapon("Combat Knife", "(THIS is a Knife.)", 1, 3), this);

		Rooms.World.getInstance().addGearToWorld(
				new Armor("Light Combat Armor", "(Better than a flightsuit.)"),
				this);
		this.getArmor().setArmorType('M');

		mySelf = this;
	}

	@Override
	public void moveToRoom(Room destination) {

		if (this.getLocation() != null) {
			((Room) this.getLocation()).remove(this.getName());
		}
		this.setLocation(destination);
		destination.add(this);

		this.roomId = ((Room) this.getLocation()).getDatabaseRef();
		sendToPlayer(((Room) this.getLocation()).generateDescription());
	}



	@Override
	public int getStat(Trait stat) {
		if (stat == Trait.ROMANCE)
			return Romance;
		else if (stat == Trait.LUCK)
			return Luck;
		else if (stat == Trait.INTELLIGENCE)
			return Intelligence;
		else if (stat == Trait.ATRACTIVE)
			return Atracctive;
		else if (stat == Trait.CHARISMA)
			return Charisma;
		return 0;
	}

	@Override
	public void sendToPlayer(String message) {
		if (client != null) {
			client.sendReply(message);
		}
	}

	@Override
	public int getRoomId() {
		return this.roomId;
	}



	/**
	 * The toString method overrides Object's toString method. This String is
	 * the way that the player will be seen in the room, and uses the name
	 * inherited from Database Object.
	 * 
	 * @return String of text from the room that the Mobile sees.
	 */
	@Override
	public String toString() {
		return this.getName() + " (DB:" + this.getDatabaseRef() + ")";
	}

	/**
	 * This method drops a player's gear item with the specified name.
	 * 
	 * @param itemName
	 *            - The name of the gear to drop.
	 */
	public void dropGear(String itemName) {
		this.dropGear(itemName, (Room) this.getLocation(), this);
	}

	/**
	 * getStats() will return a multiple-line report of the user's current
	 * statistics.
	 * 
	 * 
	 * @return String to be displayed to Player to show current status.
	 */
	public String getStats() {
		String result = this.getName() + ", the "
				+ this.getCharacterClass().toString() + "\n";
		result += "Romance: " + this.Romance + "\n";
		result += "Intellect: " + this.Intelligence + "\n";
		result += "Atractiveness: " + this.Atracctive + "\n";
		result += "Luck: " + this.Luck + "\n";
		result += "Charisma" + this.Charisma + "\n";
		return result;
	}

	/**
	 * This method sets a player's password.
	 * 
	 * @param password
	 *            - A String that represents the players password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * This method validates a player's password.
	 * 
	 * @param password
	 * @return a boolean, true if player password matches passed parameter
	 *         password.
	 */
	public boolean validatePassword(String password) {
		return this.password.equals(password);
	}

	/**
	 * setClient() associates a Client object with the Player object, allowing
	 * methods to send text back to the player.
	 * 
	 * @param client
	 *            The Client object to associate with the player object.
	 */
	public void setClient(server.Client client) {
		this.client = client;
	}

	/**
	 *
	 * 
	 * setClass will be used whenever a new player is created. When the user
	 * selects a class for there character setClass will be called.
	 * 
	 * @param classToSet
	 *            The class that is selected by the user
	 */
	public void setCharacterClass(CharacterProff classToSet) {
		if (gameClass != null) {
			this.setStat(Romance - gameClass.getMod(Trait.ROMANCE),
					Trait.ROMANCE);
			this.setStat(Luck - gameClass.getMod(Trait.LUCK),
					Trait.LUCK);
			;
			this.setStat(Atracctive - gameClass.getMod(Trait.ATRACTIVE),
					Trait.ATRACTIVE);
			;
			this.setStat(Intelligence - gameClass.getMod(Trait.INTELLIGENCE),
					Trait.INTELLIGENCE);
			;
			this.setStat(Charisma - gameClass.getMod(Trait.CHARISMA),
					Trait.CHARISMA);
			;
		}

		gameClass = classToSet;

		Romance = Romance + gameClass.getMod(Trait.ROMANCE);
		Luck = Luck + gameClass.getMod(Trait.LUCK);
		Atracctive = Atracctive + gameClass.getMod(Trait.ATRACTIVE);
		Intelligence = Intelligence + gameClass.getMod(Trait.INTELLIGENCE);
		Charisma = Charisma + gameClass.getMod(Trait.CHARISMA);
		this.sendToPlayer("Your class has been set to "
				+ this.getCharacterClass().toString());
	}

	/**
	 * not implemented yet.
	 * 
	 * getCharacterClass will return the class of the player object.
	 * 
	 * @return The class of the current character
	 */
	public CharacterProff getCharacterClass() {
		return this.gameClass;

	}

	@Override
	public void setStat(int value) {
		// TODO Auto-generated method stub
		
	}

}
