package Sims;

import Sims.CharacterProff;
import Sims.Secretary;
import Rooms.Movable;
import Sims.Player;

public class Secretary extends CharacterProff {

	
	private static final long serialVersionUID = 1L;
	private static Secretary instance = new Secretary();

	/**
	 * Dreadnaught's constructor defines the trait modifier integers which are
	 * applied to the characters when the class is loaded to the player.
	 * 
	 */
	private Secretary() {
		this.setMod(3, Trait.INTELLIGENCE);
		this.setMod(7, Trait.ATRACTIVE);
		this.setMod(6, Trait.LUCK);
		this.setMod(4, Trait.ROMANCE);
		this.setMod(8, Trait.CHARISMA);

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
		return "Secretary";
	}


}
