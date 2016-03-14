package service;

import java.sql.SQLException;

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
import javax.ws.rs.core.Response.ResponseBuilder;

import database.Database;
import model.*;

@Path("/api/schueler")
public class ServiceSchueler {  
	@GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response getSchuelerJSON() throws SQLException{
		ResponseBuilder rb = null; 
		try{
			  Database db = new Database();
			  rb = Response.ok().entity(db.getAllSchueler()); 
		  }
		  catch(SQLException ex){
			  ex.printStackTrace();
			  rb = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(null);
		  }
		  
		  return rb.build();
	  }
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addSchuelerJSON(Schueler s) throws SQLException{
		ResponseBuilder rb = null; 
		try{
			Database db = new Database();
			db.addSchueler(s);
			rb = Response.ok();
		}
			catch(SQLException ex){
			ex.printStackTrace();
			rb = Response.status(500);
		}
		return rb.build();
	}
	
	@DELETE
	@Path("/{s_id}")
	public Response deleteSchuelerJSON(@PathParam("s_id") int s_id) throws SQLException{
		ResponseBuilder rb = null; 
		try{
			Database db = new Database();
			db.deleteSchueler(s_id);
			rb = Response.ok();
		}
			catch(SQLException ex){
			ex.printStackTrace();
			rb = Response.status(500);
		}
		return rb.build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateSchuelerJSON(Schueler s) throws SQLException{
		ResponseBuilder rb = null; 
		try{
			Database db = new Database();
			db.updateSchueler(s);
			rb = Response.ok();
		}
			catch(SQLException ex){
			ex.printStackTrace();
			rb = Response.status(500);
		}
		return rb.build();
	}
}