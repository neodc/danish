package danish.db;

import danish.dto.GameDto;
import danish.dto.PlayerDto;
import danish.view.img.Images;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class PlayerDB{
	
	public static PlayerDto getPlayer(int id) throws DBException{
		try{
			String q = "SELECT player.id, player.player_name, player.preferred_style, player.is_reverse, COUNT(*) as nb_game, AVG(game.score) as average_score, SUM(victory) as nb_victory "
					+ "FROM player "
					+ "JOIN game ON player_id = player.id "
					+ "WHERE player.id = ? "
					+ "GROUP BY player.id, player.player_name, player.preferred_style, player.is_reverse";
			Connection connexion = DBManager.getConnection();
			
			PreparedStatement stmt = connexion.prepareStatement(q);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			rs.next();
			
			return new PlayerDto(
					rs.getInt("id"),
					rs.getString("player_name"),
					Images.Style.valueOf(rs.getString("preferred_style")),
					(rs.getInt("is_reverse") != 0),
					rs.getInt("nb_game"),
					rs.getInt("nb_victory"),
					rs.getInt("average_score")
			);
			
		}catch( DBException | SQLException eSQL ){
			throw new DBException("ERR:\nSQLException: " + eSQL.getMessage());
		}
	}
	
	public static Collection<PlayerDto> getAllPlayer() throws DBException{
		Collection<PlayerDto> al = new ArrayList<>();
		try{
			String q = "SELECT player.id, player.player_name, player.preferred_style, player.is_reverse, COUNT(*) as nb_game, AVG(game.score) as average_score, SUM(victory) as nb_victory "
					+ "FROM player "
					+ "JOIN game ON player_id = player.id "
					+ "GROUP BY player.id, player.player_name, player.preferred_style, player.is_reverse";
			Connection connexion = DBManager.getConnection();
			
			PreparedStatement stmt = connexion.prepareStatement(q);
			
			ResultSet rs = stmt.executeQuery();
			
			while( rs.next() ){
				al.add(new PlayerDto(
						rs.getInt("id"),
						rs.getString("player_name"),
						Images.Style.valueOf(rs.getString("preferred_style")),
						(rs.getInt("is_reverse") != 0),
						rs.getInt("nb_game"),
						rs.getInt("nb_victory"),
						rs.getInt("average_score")
				));
			}
			
			
		}catch( DBException | SQLException eSQL ){
			throw new DBException("ERR:\nSQLException: " + eSQL.getMessage());
		}
		return al;
	}
	
	public static void createPlayer( PlayerDto player ) throws DBException{
		try{
			String q = "INSERT INTO player (id, player_name, preferred_style, is_reverse) VALUES (?, ?, ?, ?)";
			Connection connexion = DBManager.getConnection();
			
			PreparedStatement stmt = connexion.prepareStatement(q);
			stmt.setInt(1, SequencesDB.getNextId("player"));
			stmt.setString(2, player.getName());
			stmt.setString(3, player.getPreferredStyle().name());
			stmt.setInt(4, player.isReverse() ? 1 : 0);
			
			stmt.executeUpdate();
			
		}catch( DBException | SQLException eSQL ){
			throw new DBException("ERR:\nSQLException: " + eSQL.getMessage());
		}
	}
	
	public static void updatePlayer( PlayerDto player ) throws DBException{
		try{
			String q = "UPDATE player SET player_name = ?, preferred_style = ?, is_reverse = ? WHERE id = ?";
			Connection connexion = DBManager.getConnection();
			
			PreparedStatement stmt = connexion.prepareStatement(q);
			stmt.setString(1, player.getName());
			stmt.setString(2, player.getPreferredStyle().name());
			stmt.setInt(3, player.isReverse() ? 1 : 0);
			stmt.setInt(4, player.getId());
			
			stmt.executeUpdate();
			
		}catch( DBException | SQLException eSQL ){
			throw new DBException("ERR:\nSQLException: " + eSQL.getMessage());
		}
	}
	
}
