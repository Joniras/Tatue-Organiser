package service;

import java.sql.SQLException;
import java.util.Vector;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import database.Database;
import model.*;

@Path("/api/schueler")
public class ServiceSchueler {  
	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public Vector<Schueler> getSchuelerJSON() throws SQLException{
		  try{
			  Database db = new Database();
			  return db.getAllSchueler();
		  }
		  catch(SQLException ex){
			  ex.printStackTrace();
		  }
		  
		  return null;
	  }
}