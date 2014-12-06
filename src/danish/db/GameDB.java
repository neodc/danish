package danish.db;

import danish.dto.GameDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class GameDB {

	public static GameDto getGame(int id) throws DBException {

		try {
			String q = "SELECT id, player_id, victory, score, nb_cards_played, nb_opponents FROM game WHERE id = ?";
			Connection connexion = DBManager.getConnection();

			PreparedStatement stmt = connexion.prepareStatement(q);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			rs.next();

			return new GameDto(
					rs.getInt("id"),
					rs.getInt("player_id"),
					(rs.getInt("victory") != 0),
					rs.getInt("score"),
					rs.getInt("nb_cards_played"),
					rs.getInt("nb_opponents")
			);

		} catch (DBException | SQLException eSQL) {
			throw new DBException("ERR:\nSQLException: " + eSQL.getMessage());
		}

	}

	public static Collection<GameDto> getAllGame() throws DBException {
		Collection<GameDto> al = new ArrayList<>();
		try {
			String query = "SELECT id, player_id, victory, score, nb_cards_played, nb_opponents FROM game";
			java.sql.Connection connexion = DBManager.getConnection();
			java.sql.PreparedStatement stmt = connexion.prepareStatement(query);
			java.sql.ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				al.add(new GameDto(
						rs.getInt("id"),
						rs.getInt("player_id"),
						(rs.getInt("victory") != 0),
						rs.getInt("score"),
						rs.getInt("nb_cards_played"),
						rs.getInt("nb_opponents")
				));
			}
		} catch (java.sql.SQLException eSQL) {
			throw new DBException("ERR:\nSQLException: " + eSQL.getMessage());
		}
		return al;
	}

	public static GameDto createGame(GameDto game) throws DBException {

		try {
			String q = "INSERT INTO game (id, player_id, victory, score, nb_cards_played, nb_opponents) VALUES (?, ?, ?, ?, ?, ?)";
			Connection connexion = DBManager.getConnection();

			int newId = SequencesDB.getNextId("game");

			PreparedStatement stmt = connexion.prepareStatement(q);
			stmt.setInt(1, newId);
			stmt.setInt(2, game.getPlayerId());
			stmt.setInt(3, game.isVictory() ? 1 : 0);
			stmt.setInt(4, game.getScore());
			stmt.setInt(5, game.getNbCardsPlayed());
			stmt.setInt(6, game.getNbOpponents());

			stmt.executeUpdate();

			return new GameDto(newId, game.getPlayerId(), game.isVictory(), game.getScore(), game.getNbCardsPlayed(), game.getNbOpponents());

		} catch (DBException | SQLException eSQL) {
			throw new DBException("ERR:\nSQLException: " + eSQL.getMessage());
		}

	}
}
