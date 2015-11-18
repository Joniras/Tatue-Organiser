package service;

import java.sql.SQLException;
import java.util.Vector;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import database.Database;
import model.Abteilung;

@Path("/api/abteilungen")
public class ServiceAbteilungen {
	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public Vector<Abteilung> getAbteilungenJSON() throws SQLException{
		  try{
			  Database db = new Database();
			  return db.getAllAbteilungen();
		  }
		  catch(SQLException ex){
			  ex.printStackTrace();
		  }
		  
		  return null;
	  }
}