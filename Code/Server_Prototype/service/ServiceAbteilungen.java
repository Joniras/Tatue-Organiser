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
import model.Abteilung;
import model.Quiz;
import model.Stand;
	
@Path("/api/abteilungen")
public class ServiceAbteilungen {
	@GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response getAbteilungenJSON() throws SQLException{
		ResponseBuilder rb = null; 
		try{
			  Database db = new Database();
			  rb = Response.ok().entity(db.getAllAbteilungen()); 
		  }
		  catch(SQLException ex){
			  ex.printStackTrace();
			  rb = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(null);
		  }
		  
		  return rb.build();
	  }
	
	@GET
	@Path("/{a_id}/staende")
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response getStaendeByAbteilungenJSON(@PathParam("a_id") int a_id) throws SQLException{
		ResponseBuilder rb = null; 
		try{
			  Database db = new Database();
			  rb = Response.ok().entity(db.getStaendeVonAbteilungen(a_id));
		  }
		  catch(SQLException ex){
			  ex.printStackTrace();
			  rb = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(null);
		  }
		  
		  return rb.build();
	  }
	
	@POST
	@Path("/{a_id}/staende")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addStandZuAbteilungJSON(@PathParam("a_id") int a_id, Stand s) throws SQLException{
		ResponseBuilder rb = null; 
		try{
			Database db = new Database();
			db.addStandZuAbteilung(a_id, s);
			rb = Response.ok();
		}
			catch(SQLException ex){
			ex.printStackTrace();
			rb = Response.status(500);
		}
		return rb.build();
	}
	  
	  @POST
		@Consumes(MediaType.APPLICATION_JSON)
		public Response addAbteilungJSON(Abteilung a) throws SQLException{
		  ResponseBuilder rb = null; 
			try{
				Database db = new Database();
				db.addAbteilung(a);
				rb = Response.ok();
			}
				catch(SQLException ex){
				ex.printStackTrace();
				rb = Response.status(500);
			}
			return rb.build();
		}
	  
		@POST
		@Path("/{a_id}/quiz")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response addQuizZuAbteilungJSON(@PathParam("a_id") int a_id, Quiz q) throws SQLException{
			ResponseBuilder rb = null; 
			try{
				Database db = new Database();
				db.addQuizZuAbteilung(a_id, q);
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
		@Consumes(MediaType.APPLICATION_JSON)
		public Response deleteAbteilungJSON(@PathParam("a_id") int a_id) throws SQLException{
			ResponseBuilder rb = null; 
			try{
				Database db = new Database();
				db.deleteAbteilung(a_id);
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
		public Response updateAbteilungJSON(Abteilung a) throws SQLException{
			ResponseBuilder rb = null; 
			try{
				Database db = new Database();
				db.updateAbteilung(a);
				rb = Response.ok();
			}
				catch(SQLException ex){
				ex.printStackTrace();
				rb = Response.status(500);
			}
			return rb.build();
		}
	  
}