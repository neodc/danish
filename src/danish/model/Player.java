package danish.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The class representing a player.
 *
 * @author Noé, Julien, Loup.
 */
public class Player {

	private final String name;

	protected final List<CardDanish> hand;
	protected final List<CardDanish> visible;
	protected final List<CardDanish> hidden;

	/**
	 * Player constructor with one parameter.
	 *
	 * @param name The player's name.
	 */
	Player(String name) {
		this.name = name;

		hand = new ArrayList<>();
		hidden = new ArrayList<>();
		visible = new ArrayList<>();
	}

	/**
	 * Getter of the player's hand.
	 *
	 * @return The player's hand.
	 */
	public List<CardDanish> getHand() {
		return new ArrayList<>(hand);
	}

	/**
	 * Getter of the player's visible cards.
	 *
	 * @return The player's visible cards.
	 */
	public List<CardDanish> getVisible() {
		return new ArrayList<>(visible);
	}

	/**
	 * Getter of the player's hidden cards.
	 *
	 * @return The player's hidden cards.
	 */
	public List<CardDanish> getHidden() {
		return new ArrayList<>(hidden);
	}

	/**
	 * Getter of the player's name.
	 *
	 * @return the player's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Adds a visible card if there are less than 3 cards.
	 *
	 * @param c The card to add.
	 * @return If the card has been added.
	 */
	boolean addVisible(CardDanish c) {
		if (visible.size() < 3) {
			return visible.add(c);
		}
		return false;
	}

	/**
	 * Adds a hidden card if there are less than 3 cards.
	 *
	 * @param c The card to add.
	 * @return If the card has been added.
	 */
	boolean addHidden(CardDanish c) {
		if (hidden.size() < 3) {
			return hidden.add(c);
		}
		return false;
	}

	/**
	 * Removes a card from the visible cards.
	 *
	 * @param c The card to remove.
	 * @return If the card has been removed.
	 */
	boolean removeVisible(CardDanish c) {
		return visible.remove(c);
	}

	/**
	 * Removes a card from the hidden cards.
	 *
	 * @param c The card to remove.
	 * @return If the card has been removed.
	 */
	boolean removeHidden(CardDanish c) {
		return hidden.remove(c);
	}

	/**
	 * Switches a card in hand with a visible one.
	 *
	 * @param visible The visible card to take in hand.
	 * @param hand The card in hand to make visible.
	 */
	void switchCard(CardDanish visible, CardDanish hand) {

		int iVisible, iHand;

		if ((iHand = this.hand.indexOf(hand)) == -1 || (iVisible = this.visible.indexOf(visible)) == -1) {
			return;
		}

		this.hand.remove(iHand);
		this.visible.remove(iVisible);

		this.hand.add(iHand, visible);
		this.visible.add(iVisible, hand);

	}

	/**
	 * Makes the player fill his hand with his visible or hidden cards. He will
	 * take all the visible cards if his hand is empty. He will take one hidden
	 * card if he has no card in hand and no visible card.
	 *
	 * @return If he has no card left.
	 */
	boolean draw() {

		if (!hand.isEmpty()) {
		} else if (hand.addAll(visible)) {
			visible.clear();
		} else if (!hidden.isEmpty()) {
			hand.add(hidden.remove(0));
		} else {
			return true;
		}

		return false;
	}
}
