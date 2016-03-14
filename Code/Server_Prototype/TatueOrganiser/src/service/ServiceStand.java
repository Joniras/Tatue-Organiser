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

@Path("/api/staende")
public class ServiceStand {  
	
	@DELETE
	@Path("/{st_id}/ratings")
	public Response deleteStandRatings(@PathParam("st_id") int st_id) throws SQLException{
		ResponseBuilder rb = null; 
		try{
			Database db = new Database();
			db.deleteRatingsVonStand(st_id);
			rb = Response.ok();
		}
			catch(SQLException ex){
			ex.printStackTrace();
			rb = Response.status(500);
		}
		return rb.build();
	}
	
	@DELETE
	@Path("/{st_id}")
	public Response deleteStand(@PathParam("st_id") int st_id) throws SQLException{
		ResponseBuilder rb = null; 
		try{
			Database db = new Database();
			db.deleteStand(st_id);
			rb = Response.ok();
		}
			catch(SQLException ex){
			ex.printStackTrace();
			rb = Response.status(500);
		}
		return rb.build();
	}
	
	@PUT
	@Path("/{st_id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateStandJSON(@PathParam("st_id") int st_id, Stand s) throws SQLException{
		ResponseBuilder rb = null; 
		try{
			Database db = new Database();
			db.updateStand(s);
			rb = Response.ok();
		}
			catch(SQLException ex){
			ex.printStackTrace();
			rb = Response.status(500);
		}
		return rb.build();
	}
	
	@GET
    @Path("/{st_id}/ratings")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStandRatingsJSON(@PathParam("st_id") int st_id) throws SQLException{
		ResponseBuilder rb = null; 
		try{
			  Database db = new Database();
			  rb = Response.ok().entity(db.getAllRatingsVonStand(st_id));
		  }
		  catch(SQLException ex){
			  ex.printStackTrace();
			  rb = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(null);
		  }
		  
		  return rb.build();
	}
	
	@POST
	@Path("/{st_id}/ratings")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addStandRatingsJSON(@PathParam("st_id") int st_id, StandRating sr) throws SQLException{
		ResponseBuilder rb = null; 
		try{
			Database db = new Database();
			db.addRatingZuStand(st_id, sr);
			rb = Response.ok();
		}
			catch(SQLException ex){
			ex.printStackTrace();
			rb = Response.status(500);
		}
		return rb.build();
	}
}