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

import database.Database;
import model.*;

@Path("/api/fragen")
public class ServiceFrage {  
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addFrage(@QueryParam("q_id") int q_id, @Context UriInfo uriInfo, Frage f) {
	    try{
	    	Database db = new Database();
	    	db.addFrageZuQuiz(q_id, f);
		 	return Response.ok().build();
		}
			catch(SQLException ex){
			ex.printStackTrace();
		}
		return Response.status(500).build();
	}
	
	@DELETE
	@Path("/{q_id}")
	public Response deleteFrageJSON(@PathParam("f_id") int f_id) throws SQLException{
		try{
			Database db = new Database();
			db.deleteFrage(f_id);
			return Response.ok().build();
		}
			catch(SQLException ex){
			ex.printStackTrace();
		}
		return Response.status(500).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateFrageJSON(Frage f) throws SQLException{
		try{
			Database db = new Database();
			db.updateFrage(f);
			return Response.ok().build();
		}
			catch(SQLException ex){
			ex.printStackTrace();
		}
		return Response.status(500).build();
	}
}