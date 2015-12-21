package service;

import java.sql.SQLException;
import java.util.Vector;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addSchuelerJSON(Schueler s) throws SQLException{
		try{
			Database db = new Database();
			db.addSchueler(s);
			return Response.ok().build();
		}
			catch(SQLException ex){
			ex.printStackTrace();
		}
		return Response.status(500).build();
	}
	
	@DELETE
	@Path("/{s_id}")
	public Response deleteSchuelerJSON(@PathParam("s_id") int s_id) throws SQLException{
		try{
			Database db = new Database();
			db.deleteSchueler(s_id);
			return Response.ok().build();
		}
			catch(SQLException ex){
			ex.printStackTrace();
		}
		return Response.status(500).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateSchuelerJSON(Schueler s) throws SQLException{
		try{
			Database db = new Database();
			db.updateSchueler(s);
			return Response.ok().build();
		}
			catch(SQLException ex){
			ex.printStackTrace();
		}
		return Response.status(500).build();
	}
}