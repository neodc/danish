package danish.model;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * A class representing a queue of CardDanish.
 *
 * @author Noé, Julien, Loup.
 */
public class CardPack implements Queue<CardDanish> {

	private CardDanish head;
	private int size;

	/**
	 * CardPack contructor without parameter.
	 */
	public CardPack() {
		this.head = null;
		this.size = 0;
	}

	/**
	 * CardPack constructor with a parameter.
	 *
	 * @param c A collection of CardDanish.
	 */
	public CardPack(Collection<? extends CardDanish> c) {
		this.addAll(c);
	}

	/**
	 * CardPack copy constructor.
	 *
	 * @param c The CardPack to copy.
	 */
	public CardPack(CardPack c) {
		this.size = c.size;
		if (c.head != null) {
			this.head = new CardDanish(c.head, true);
		} else {
			this.head = null;
		}
	}

	/**
	 * Moves the cards from a CardPack to this CardPack.
	 *
	 * @param source The CardPack to empty.
	 */
	public void pour(CardPack source) {

		if (source.isEmpty()) {
			return;
		}

		if (!this.isEmpty()) {
			Iterator<CardDanish> i = source.iterator();

			CardDanish c = i.next();

			while (i.hasNext()) {
				c = i.next();
			}

			c.setNext(this.head);
		}
		this.head = source.head;

		this.size += source.size;

		source.clear();
	}

	/**
	 * Indicates how many cards of the same ranks are following on top of the
	 * CardPack.
	 *
	 * @return The number of cards of the same Rank.
	 */
	public int getNumberSimilarCard() {
		if (this.isEmpty()) {
			return 0;
		}
		int ret = 1;
		CardDanish c = head;

		while (c.getNext() != null && c.getRank() == c.getNext().getRank()) {
			++ret;
			c = c.getNext();
		}

		return ret;
	}

	/**
	 * Checks if a card is placeable on this card.
	 *
	 * @param c The card to put on this card.
	 * @return true if the card is placeable, false otherwise.
	 */
	public boolean placeable(CardDanish c) {
		if (c == null) {
			throw new NullPointerException();
		}
		return this.isEmpty() || c.isJoker() || (this.peek().getRealRank() == Rank.SEVEN && c.getRealRank().getValue() <= this.peek().getRealRank().getValue()) || (this.peek().getRealRank() != Rank.SEVEN && c.getRealRank().getValue() >= this.peek().getRealRank().getValue());
	}

	/**
	 * Adds a CardDanish.
	 *
	 * @param c The CardDanish to add.
	 * @return If the CardDanish has been added.
	 */
	@Override
	public boolean add(CardDanish c) {
		if (c == null) {
			throw new NullPointerException();
		}

		CardDanish card = new CardDanish(c, false);

		if (head == null) {
			head = card;
		} else {
			card.setNext(head);
			head = card;
		}

		++this.size;
		return true;
	}

	/**
	 * Adds a CardDanish.
	 *
	 * @param e The CardDanish to add.
	 * @return If the CardDanish has been added.
	 */
	@Override
	public boolean offer(CardDanish e) {
		return this.add(e);
	}

	/**
	 * Removes a CardDanish.
	 *
	 * @return If the CardDanish has been removed.
	 */
	@Override
	public CardDanish remove() {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}

		return poll();
	}

	/**
	 * Removes a CardDanish.
	 *
	 * @return If the CardDanish has been removed.
	 */
	@Override
	public CardDanish poll() {
		CardDanish ret = this.head;

		if (head != null) {
			this.head = head.getNext();
			--this.size;
		}

		return ret;
	}

	/**
	 * Returns the first element of the CardPack.
	 *
	 * @return The first element of the CardPack.
	 */
	@Override
	public CardDanish element() {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		return this.peek();
	}

	/**
	 * Returns the first element of the CardPack.
	 *
	 * @return The first element of the CardPack.
	 */
	@Override
	public CardDanish peek() {
		return head;
	}

	/**
	 * Returns the number of elements in the CardPack.
	 *
	 * @return The number of elements in the CardPack.
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * Indicates if the CardPack is empty.
	 *
	 * @return If the CardPack is empty.
	 */
	@Override
	public boolean isEmpty() {
		return this.head == null;
	}

	/**
	 * Indicates if the CardPack contains a specific element.
	 *
	 * @param o The searched element.
	 * @return If the element exists in the CardPack.
	 */
	@Override
	public boolean contains(Object o) {
		if (o == null) {
			throw new NullPointerException();
		}

		if (!(o instanceof CardDanish)) {
			throw new ClassCastException();
		}

		CardDanish card = (CardDanish) o;

		for (CardDanish c : this) {
			if (c.equals(card)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Creates and returns an iterator for the CardPack.
	 *
	 * @return An iterator for the CardPack.
	 */
	@Override
	public Iterator<CardDanish> iterator() {
		return new Iterator<CardDanish>() {

			private CardDanish next = head;

			@Override
			public boolean hasNext() {
				return next != null;
			}

			@Override
			public CardDanish next() {
				if (!this.hasNext()) {
					throw new NoSuchElementException();
				}

				CardDanish ret = next;
				next = next.getNext();
				return ret;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	/**
	 * Creates and returns an Array with the elements of the CardPack.
	 *
	 * @return The created Array.
	 */
	@Override
	public Object[] toArray() {
		Object[] ret = new Object[this.size()];

		int i = 0;
		for (CardDanish c : this) {
			ret[i++] = c;
		}
		return ret;
	}

	/**
	 * Creates an Array of the specified type.
	 *
	 * @param <T> The specified type.
	 * @param array The array of the specified type to place the elements in. If
	 * the array is too short, a new instance will be created.
	 * @return The array.
	 */
	@Override
	public <T> T[] toArray(T[] array) {
		int size = size();
		if (array.length < size) {
			array = (T[]) Array.newInstance(array.getClass().getComponentType(), size);
		} else if (array.length > size) {
			array[size] = null;
		}

		int i = 0;
		for (CardDanish e : this) {
			array[i++] = (T) e;
		}
		return array;
	}

	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Indicates if all the elements in a collection are in the CardPack.
	 *
	 * @param c The collection of elements we search.
	 * @return If all the elements in the collections are in the CardPack.
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		Iterator<?> e = c.iterator();

		while (e.hasNext()) {
			if (!this.contains((CardDanish) e.next())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Adds all the elements of a collection into the CardPack.
	 *
	 * @param c The collection of elements to add.
	 * @return If all the elements heve been added.
	 */
	@Override
	public boolean addAll(Collection<? extends CardDanish> c) {
		Iterator<?> e = c.iterator();

		while (e.hasNext()) {
			this.add((CardDanish) e.next());
		}
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> clctn) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> clctn) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Empties the CardPack.
	 */
	@Override
	public void clear() {
		this.head = null;
		this.size = 0;
	}
}
