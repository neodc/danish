package danish.dto;

import danish.view.img.Images;

public class PlayerDto{
	private final int id;
	private final String name;
	private final Images.Style preferredStyle;
	private final boolean reverse;
	
	private final int nbGame;
	private final int nbVictory;
	private final float averageScore;

	public PlayerDto( int id, String name, Images.Style preferredStyle, boolean isReverse, int nbGame, int nbVictory, float averageScore){
		this.id = id;
		this.name = name;
		this.preferredStyle = preferredStyle;
		this.reverse = isReverse;
		this.nbGame = nbGame;
		this.nbVictory = nbVictory;
		this.averageScore = averageScore;
	}

	public PlayerDto( String name, Images.Style preferredStyle, boolean isReverse, int nbGame, int nbVictory, float averageScore ){
		this(-1, name, preferredStyle, isReverse, nbGame, nbVictory, averageScore);
	}

	public PlayerDto( int id, String name, Images.Style preferredStyle, boolean isReverse ){
		this(id, name, preferredStyle, isReverse, -1, -1, -1);
	}

	public PlayerDto( String name, Images.Style preferredStyle, boolean isReverse ){
		this(-1, name, preferredStyle, isReverse);
	}
	
	public PlayerDto( String name ){
		this(name, Images.Style.PONY, false);
	}

	public int getId(){
		return id;
	}

	public String getName(){
		return name;
	}

	public Images.Style getPreferredStyle(){
		return preferredStyle;
	}

	public boolean isReverse(){
		return reverse;
	}

	public int getNbGame(){
		return nbGame;
	}

	public int getNbVictory(){
		return nbVictory;
	}

	public float getAverageScore(){
		return averageScore;
	}
}
