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

@Path("/api/staende")
public class ServiceStand {  
	
	@DELETE
	@Path("/{s_id}/ratings")
	public Response deleteStandRatings(@PathParam("s_id") int s_id) throws SQLException{
		try{
			Database db = new Database();
			db.deleteRatingsVonStand(s_id);
			return Response.ok().build();
		}
			catch(SQLException ex){
			ex.printStackTrace();
		}
		return Response.status(500).build();
	}
	
	@DELETE
	@Path("/{s_id}")
	public Response deleteStand(@PathParam("s_id") int s_id) throws SQLException{
		try{
			Database db = new Database();
			db.deleteStand(s_id);
			return Response.ok().build();
		}
			catch(SQLException ex){
			ex.printStackTrace();
		}
		return Response.status(500).build();
	}
	
	@PUT
	@Path("/{s_id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateStandJSON(@PathParam("s_id") int s_id, Stand s) throws SQLException{
		try{
			Database db = new Database();
			s.setSt_id(s_id);
			db.updateStand(s);
			return Response.ok().build();
		}
			catch(SQLException ex){
			ex.printStackTrace();
		}
		return Response.status(500).build();
	}
	
	@GET
    @Path("/{st_id}/ratings")
	@Produces(MediaType.APPLICATION_JSON)
	public Vector<StandRating> getStandRatingsJSON(@PathParam("s_id") int s_id) throws SQLException{
		try{
			Database db = new Database();
			return db.getAllRatingsVonStand(s_id);
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		  
		return null;
	}
	
	@POST
	@Path("/{s_id}/ratings")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addStandRatingsJSON(@PathParam("s_id") int s_id, StandRating sr) throws SQLException{
		try{
			Database db = new Database();
			db.addRatingZuStand(s_id, sr);
			return Response.ok().build();
		}
			catch(SQLException ex){
			ex.printStackTrace();
		}
		return Response.status(500).build();
	}
}