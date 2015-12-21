package service;

import java.sql.SQLException;
import java.util.Vector;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import database.Database;
import model.*;

@Path("/api/guides")
public class ServiceGuide {  
	@GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public Vector<Schueler> getGuideJSON() throws SQLException{
		  try{
			  Database db = new Database();
			  return db.getAllGuides();
		  } 
		  catch(SQLException ex){
			  ex.printStackTrace();
		  }
		  
		  return null;
	  }
	
	@GET
    @Path("/{s_id}/ratings")
	@Produces(MediaType.APPLICATION_JSON)
	public Vector<GuideRating> getGuideRatingsJSON(@PathParam("s_id") int s_id) throws SQLException{
		try{
			Database db = new Database();
			return db.getAllRatingsVonGuide(s_id);
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		  
		return null;
	}
	
	@POST
	@Path("/{s_id}/ratings")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addGuideRatingsJSON(@PathParam("s_id") int s_id, GuideRating gr) throws SQLException{
		try{
			Database db = new Database();
			db.addRatingZuGuide(s_id, gr);
			return Response.ok().build();
		}
			catch(SQLException ex){
			ex.printStackTrace();
		}
		return Response.status(500).build();
	}
	
	@DELETE
	@Path("/{s_id}/ratings")
	public Response deleteStandRatings(@PathParam("s_id") int s_id) throws SQLException{
		try{
			Database db = new Database();
			db.deleteRatingsVonGuide(s_id);
			return Response.ok().build();
		}
			catch(SQLException ex){
			ex.printStackTrace();
		}
		return Response.status(500).build();
	}
}