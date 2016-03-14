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

@Path("/api/antworten")
public class ServiceAntwort {  
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addAntwort(@QueryParam("f_id") int f_id, @Context UriInfo uriInfo, Antwort a) {
		ResponseBuilder rb = null; 
		try{
			Database db = new Database();
			db.addAntwortZuFrage(f_id, a);
			rb = Response.ok();
		}
			catch(SQLException ex){
			ex.printStackTrace();
			rb = Response.status(500);
		}
		return rb.build();
	}
	
	@DELETE
	@Path("/{a_id}")
	public Response deleteAntwortJSON(@PathParam("a_id") int a_id) throws SQLException{
		ResponseBuilder rb = null; 
		try{
			Database db = new Database();
			db.deleteAntwort(a_id);
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
	public Response updateAntwortJSON(Antwort a) throws SQLException{
		ResponseBuilder rb = null; 
		try{
			Database db = new Database();
			db.updateAntwort(a);
			rb = Response.ok();
		}
			catch(SQLException ex){
			ex.printStackTrace();
			rb = Response.status(500);
		}
		return rb.build();
	}
}