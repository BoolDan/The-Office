package Sims;

import Sims.CharacterProff;
import Sims.Accountant;
import Rooms.Movable;
import Sims.Player;

public class Accountant extends CharacterProff {

	
	private static final long serialVersionUID = 1L;
	private static Accountant instance = new Accountant();

	/**
	 * Dreadnaught's constructor defines the trait modifier integers which are
	 * applied to the characters when the class is loaded to the player.
	 * 
	 */
	private Accountant() {
		this.setMod(8, Trait.INTELLIGENCE);
		this.setMod(6, Trait.ATRACTIVE);
		this.setMod(3, Trait.LUCK);
		this.setMod(5, Trait.ROMANCE);
		this.setMod(3, Trait.CHARISMA);

	}

	
	public static CharacterProff getInstance() {
		return instance;
	}

/*	public void Wound(Player player, Movable target) {
		if (player.getStat(Trait.TECHNIQUE) > 5) {
			player.sendToPlayer("You strike " + target.getName()
					+ " and wound them!");
			player.setStat(player.getStat(Trait.TECHNIQUE)-5,Trait.TECHNIQUE);
			player.attack(target);
			CombatManager attack = new CombatManager(player, target);
		}

	}*/

	@Override
	public String toString() {
		return "Accountant";
	}


}
