package danish.dto;

import danish.view.img.Images;

public class PlayerDto{
	private int id;
	private String name;
	private Images.Style preferredStyle;
	private boolean reverse;
	
	private int nbGame;
	private int nbVictory;
	private float averageScore;

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
