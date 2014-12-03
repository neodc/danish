package danish.business;

import danish.db.DBException;
import danish.db.DBManager;
import danish.db.GameDB;
import danish.db.PlayerDB;
import danish.dto.GameDto;
import danish.dto.PlayerDto;
import java.util.Collection;

public class DanishFacade{
	
	public static PlayerDto getPlayer(int id) throws PersistanceException{
		
		try{
			DBManager.startTransaction();
			
			PlayerDto ret = PlayerDB.getPlayer( id );
			
			DBManager.valideTransaction();
			
			return ret;
		}catch( DBException ex1 ){
			String msg = ex1.getMessage();
            try {
                DBManager.annuleTransaction();
            } catch (DBException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new PersistanceException("Impossible de modifier les stats du mot \n" + msg); // TODO
            }
		}
		
	}
	
	public static Collection<PlayerDto> getAllPlayer() throws PersistanceException{
		
		try{
			DBManager.startTransaction();
			
			Collection<PlayerDto> ret = PlayerDB.getAllPlayer();
			
			DBManager.valideTransaction();
			
			return ret;
		}catch( DBException ex1 ){
			String msg = ex1.getMessage();
            try {
                DBManager.annuleTransaction();
            } catch (DBException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new PersistanceException("Impossible de modifier les stats du mot \n" + msg); // TODO
            }
		}
		
	}
	
	public static GameDto getGame(int id) throws PersistanceException{
		
		try{
			DBManager.startTransaction();
			
			GameDto ret = GameDB.getGame( id );
			
			DBManager.valideTransaction();
			
			return ret;
		}catch( DBException ex1 ){
			String msg = ex1.getMessage();
            try {
                DBManager.annuleTransaction();
            } catch (DBException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new PersistanceException("Impossible de modifier les stats du mot \n" + msg); // TODO
            }
		}
		
	}
	
	public static Collection<GameDto> getAllGame() throws PersistanceException{
		
		try{
			DBManager.startTransaction();
			
			Collection<GameDto> ret = GameDB.getAllGame();
			
			DBManager.valideTransaction();
			
			return ret;
		}catch( DBException ex1 ){
			String msg = ex1.getMessage();
            try {
                DBManager.annuleTransaction();
            } catch (DBException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new PersistanceException("Impossible de modifier les stats du mot \n" + msg); // TODO
            }
		}
		
	}
	
	public static void createPlayer(PlayerDto p) throws PersistanceException{
		
		try{
			DBManager.startTransaction();
			
			PlayerDB.createPlayer(p);
			
			DBManager.valideTransaction();
		}catch( DBException ex1 ){
			String msg = ex1.getMessage();
            try {
                DBManager.annuleTransaction();
            } catch (DBException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new PersistanceException("Impossible de modifier les stats du mot \n" + msg); // TODO
            }
		}
		
	}
	
	public static void updatePlayer(PlayerDto p) throws PersistanceException{
		
		try{
			DBManager.startTransaction();
			
			PlayerDB.updatePlayer(p);
			
			DBManager.valideTransaction();
		}catch( DBException ex1 ){
			String msg = ex1.getMessage();
            try {
                DBManager.annuleTransaction();
            } catch (DBException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new PersistanceException("Impossible de modifier les stats du mot \n" + msg); // TODO
            }
		}
		
	}
	
	public static void createGame(GameDto g) throws PersistanceException{
		
		try{
			DBManager.startTransaction();
			
			GameDB.createGame(g);
			
			DBManager.valideTransaction();
		}catch( DBException ex1 ){
			String msg = ex1.getMessage();
            try {
                DBManager.annuleTransaction();
            } catch (DBException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new PersistanceException("Impossible de modifier les stats du mot \n" + msg); // TODO
            }
		}
		
	}
}
