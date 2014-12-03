package danish.dto;

public class GameDto{
	private int id;
	private int playerId;
	private boolean victory;
	private int score;
	private int nbCardsPlayed;
	private int nbOpponents;

	public GameDto( int id, int playerId, boolean victory, int score, int nbCardsPlayed, int nbOpponents ){
		this.id = id;
		this.playerId = playerId;
		this.victory = victory;
		this.score = score;
		this.nbCardsPlayed = nbCardsPlayed;
		this.nbOpponents = nbOpponents;
	}
	
	public GameDto( int playerId, boolean victory, int score, int nbCardsPlayed, int nbOpponents ){
		this(-1, playerId, victory, score, nbCardsPlayed, nbOpponents);
	}

	public int getId(){
		return id;
	}

	public int getPlayerId(){
		return playerId;
	}

	public boolean isVictory(){
		return victory;
	}

	public int getScore(){
		return score;
	}

	public int getNbCardsPlayed(){
		return nbCardsPlayed;
	}

	public int getNbOpponents(){
		return nbOpponents;
	}
}
