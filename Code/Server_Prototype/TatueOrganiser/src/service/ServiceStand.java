package service;

import java.sql.SQLException;
import java.util.Vector;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import database.Database;
import model.*;

@Path("/api/staende")
public class ServiceStand {  
	
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