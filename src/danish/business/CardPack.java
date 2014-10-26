package danish.business;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class CardPack implements Queue<CardDanish>{
	
	private CardDanish head;
	private int size;

	public CardPack(){
		this.head = null;
		this.size = 0;
	}
	
	public CardPack(Collection<? extends CardDanish> c){
		this.addAll(c);
	}
	
	public CardPack( CardPack c ){
		this.size = c.size;
		if( c.head != null ){
			this.head = new CardDanish(c.head, true);
		}else{
			this.head = null;
		}
	}

	public void pour( CardPack source ){
		
		if( source.isEmpty() ){
			return;
		}
		
		if( this.isEmpty() ){
			this.head = source.head;
		}else{
			Iterator<CardDanish> i = this.iterator();

			CardDanish c = i.next();

			while( i.hasNext() ){
				c = i.next();
			}
			
			c.setNext( source.head );
		}
		
		this.size += source.size;
		
		source.clear();
	}
	
	public int getNumberSimilarCard(){
		if( this.isEmpty() ){
			return 0;
		}
		int ret = 1;
		CardDanish c = head;
		
		while( c.getNext() != null && c.getRank() == c.getNext().getRank() ){
			++ret;
			c = c.getNext();
		}
		
		return ret;
	}
	
	@Override
	public boolean add( CardDanish c ){
		if( c == null ){
			throw new NullPointerException();
		}
		
		if( !( c instanceof CardDanish ) ){
			throw new ClassCastException();
		}
		
		CardDanish card = new CardDanish(c, false);
		
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
	public boolean offer( CardDanish e ){
		return this.add(e);
	}

	@Override
	public CardDanish remove(){
		if( this.isEmpty() ){
			throw new NoSuchElementException();
		}
		
		return poll();
	}

	@Override
	public CardDanish poll(){
		CardDanish ret = this.head;
		
		if( head != null ){
			this.head = head.getNext();
			--this.size;
		}
		
		return ret;
	}

	@Override
	public CardDanish element(){
		if( this.isEmpty() ){
			throw new NoSuchElementException();
		}
		return this.peek();
	}

	@Override
	public CardDanish peek(){
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
		
		if( !( o instanceof CardDanish ) ){
			throw new ClassCastException();
		}
		
		CardDanish card = (CardDanish) o;
		
		for( CardDanish c : this ){
			if( c.equals(card) ){
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<CardDanish> iterator(){
		return new Iterator<CardDanish>() {
			
			private CardDanish next = head;
			
			@Override
			public boolean hasNext(){
				return next != null;
			}

			@Override
			public CardDanish next(){
				if( !this.hasNext() ){
					throw new NoSuchElementException();
				}
				
				CardDanish ret = next;
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
		for( CardDanish c : this ){
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
		for( CardDanish e : this ){
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
			if( !this.contains( e.next() ) ){
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean addAll( Collection<? extends CardDanish> c ){
		Iterator<?> e = c.iterator();
		
		while( e.hasNext() ){
			this.add((CardDanish) e.next());
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
