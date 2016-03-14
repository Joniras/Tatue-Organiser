package service;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import database.Database;
import model.*;

@Path("/api/fragen")
public class ServiceFrage {  
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addFrage(@QueryParam("q_id") int q_id, @Context UriInfo uriInfo, Frage f) {
		ResponseBuilder rb = null; 
		try{
			Database db = new Database();
			db.addFrageZuQuiz(q_id, f);
			rb = Response.ok();
		}
			catch(SQLException ex){
			ex.printStackTrace();
			rb = Response.status(500);
		}
		return rb.build();
	}
	
	@DELETE
	@Path("/{q_id}")
	public Response deleteFrageJSON(@PathParam("f_id") int f_id) throws SQLException{
		ResponseBuilder rb = null; 
		try{
			Database db = new Database();
			db.deleteFrage(f_id);
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
	public Response updateFrageJSON(Frage f) throws SQLException{
		ResponseBuilder rb = null; 
		try{
			Database db = new Database();
			db.updateFrage(f);
			rb = Response.ok();
		}
			catch(SQLException ex){
			ex.printStackTrace();
			rb = Response.status(500);
		}
		return rb.build();
	}
}