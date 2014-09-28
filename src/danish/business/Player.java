package danish.business;

import java.util.ArrayList;
import java.util.List;

public class Player{
	
	private String name;
	
	private List<Card> hand;
	private List<Card> visible;
	private List<Card> hidden;

	public Player( String name ){
		this.name = name;
		
		hand = new ArrayList<>();
		hidden = new ArrayList<>();
		visible = new ArrayList<>();
	}

	public List<Card> getHand(){
		return hand;
	}

	public List<Card> getVisible(){
		return new ArrayList<>(visible);
	}

	public List<Card> getHidden(){
		return new ArrayList<>(hidden);
	}

	public String getName(){
		return name;
	}
	
	public boolean addVisible(Card c){
		if( visible.size() < 3 ){
			return visible.add(c);
		}
		return false;
	}
	
	public boolean addHidden(Card c){
		if( hidden.size() < 3 ){
			return hidden.add(c);
		}
		return false;
	}
	
	public boolean removeVisible(Card c){
		return visible.remove(c);
	}
	
	public boolean removeHidden(Card c){
		return hidden.remove(c);
	}
	
	public void switchCard( Card visible, Card hand ){
		
		int iVisible, iHand;
		
		if( (iHand = this.hand.indexOf(hand)) == -1 || (iVisible = this.visible.indexOf(visible)) == -1 ){
			return;
		}
		
		this.hand.remove(iHand);
		this.visible.remove(iVisible);
		
		this.hand.add(iHand, visible);
		this.visible.add(iVisible, hand);
		
	}
	
	public boolean draw(){
		
		if( !hand.isEmpty() ){
		}else if( hand.addAll(visible) ){
			visible.clear();
		}else if( !hidden.isEmpty() ){
			hand.add( hidden.remove(0) );
		}else{
			return true;
		}
		
		return false;
	}
}
