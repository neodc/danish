package danish.business;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class Pack implements Queue<Card>{
	
	private Card head;
	private int size;

	public Pack(){
		this.head = null;
		this.size = 0;
	}
	
	public Pack(Collection<? extends Card> c){
		this.addAll(c);
	}

	@Override
	public boolean add( Card c ){
		if( c == null ){
			throw new NullPointerException();
		}
		
		if( !( c instanceof Card ) ){
			throw new ClassCastException();
		}
		
		Card card = new Card( c.getRank(), c.getSuit() );
		
		if( head == null ){
			head = card;
		}else{
			card.setNext( head );
			head = card;
		}
		
		++this.size;
		return true;
	}

	@Override
	public boolean offer( Card e ){
		return this.add(e);
	}

	@Override
	public Card remove(){
		if( this.isEmpty() ){
			throw new NoSuchElementException();
		}
		
		return poll();
	}

	@Override
	public Card poll(){
		Card ret = this.head;
		
		if( head != null ){
			this.head = head.getNext();
			--this.size;
		}
		
		return ret;
	}

	@Override
	public Card element(){
		if( this.isEmpty() ){
			throw new NoSuchElementException();
		}
		return this.peek();
	}

	@Override
	public Card peek(){
		return head;
	}

	@Override
	public int size(){
		return this.size;
	}

	@Override
	public boolean isEmpty(){
		return this.head == null;
	}

	@Override
	public boolean contains( Object o ){
		if( o == null ){
			throw new NullPointerException();
		}
		
		if( !( o instanceof Card ) ){
			throw new ClassCastException();
		}
		
		Card card = (Card) o;
		
		for( Card c : this ){
			if( c.equals(card) ){
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<Card> iterator(){
		return new Iterator<Card>() {
			
			private Card next = head;
			
			@Override
			public boolean hasNext(){
				return next != null;
			}

			@Override
			public Card next(){
				if( !this.hasNext() ){
					throw new NoSuchElementException();
				}
				
				Card ret = next;
				next = next.getNext();
				return ret;
			}

			@Override
			public void remove(){
				throw new UnsupportedOperationException();
			}
		};
	}

	@Override
	public Object[] toArray(){
		Object[] ret = new Object[ this.size() ];
		
		int i = 0;
		for( Card c : this ){
			ret[i++] = c;
		}
		return ret;
	}

	@Override
	public <T> T[] toArray( T[] array ){
		int size = size();
		if( array.length < size ){
			array = (T[]) Array.newInstance( array.getClass().getComponentType(), size );
		}else if( array.length > size ){
			array[size] = null;
		}

		int i = 0;
		for( Card e : this ){
			array[i++] = (T) e;
		}
		return array;
	}

	@Override
	public boolean remove( Object o ){
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll( Collection<?> c ){
		Iterator<?> e = c.iterator();
		
		while( e.hasNext() ){
			if( !contains( e.next() ) ){
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean addAll( Collection<? extends Card> c ){
		Iterator<?> e = c.iterator();
		
		while( e.hasNext() ){
			this.add((Card) e.next());
		}
		return true;
	}

	@Override
	public boolean removeAll( Collection<?> clctn ){
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll( Collection<?> clctn ){
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear(){
		this.head = null;
		this.size = 0;
	}

}
