package service;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import database.Database;
import model.*;

@Path("/api/guides")
public class ServiceGuide {  
	@GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response getGuidesJSON() throws SQLException{
		ResponseBuilder rb = null; 
		try{
			Database db = new Database();
			rb = Response.ok().entity(db.getAllGuides()); 
		}
		catch(SQLException ex){
			ex.printStackTrace();
			rb = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(null);
		}
		  
		return rb.build();
	}
	
	@GET
    @Path("/{s_id}/ratings")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGuideRatingsJSON(@PathParam("s_id") int s_id) throws SQLException{
		ResponseBuilder rb = null; 
		try{
			  Database db = new Database();
			  rb = Response.ok().entity(db.getAllRatingsVonGuide(s_id)); 
		  }
		  catch(SQLException ex){
			  ex.printStackTrace();
			  rb = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(null);
		  }
		  
		  return rb.build();
	}
	
	@POST
	@Path("/{s_id}/ratings")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addGuideRatingsJSON(@PathParam("s_id") int s_id, GuideRating gr) throws SQLException{
		ResponseBuilder rb = null; 
		try{
			Database db = new Database();
			db.addRatingZuGuide(s_id, gr);
			rb = Response.ok();
		}
		catch(SQLException ex){
			ex.printStackTrace();
			rb = Response.status(500);
		}
		return rb.build();
	}
	
	@DELETE
	@Path("/{s_id}/ratings")
	public Response deleteStandRatings(@PathParam("s_id") int s_id) throws SQLException{
		ResponseBuilder rb = null; 
		try{
			Database db = new Database();
			db.deleteRatingsVonGuide(s_id);
			rb = Response.ok();
		}
		catch(SQLException ex){
			ex.printStackTrace();
			rb = Response.status(500);
		}
		return rb.build();
	}
}