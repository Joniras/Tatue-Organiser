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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import database.Database;
import model.*;

@Path("/api/quizzes")
public class ServiceQuiz {  
	
	@GET
	@Path("/{q_id}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response getQuizJSON(@PathParam("q_id") int q_id) throws SQLException{
		ResponseBuilder rb = null; 
		try{
			  Database db = new Database();
			  rb = Response.ok().entity(db.getQuiz(q_id)); 
		  }
		  catch(SQLException ex){
			  ex.printStackTrace();
			  rb = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(null);
		  }
		  
		  return rb.build();
	  }
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getQuizVonAbteilung(@QueryParam("ab_id") int ab_id, @Context UriInfo uriInfo) {
		ResponseBuilder rb = null; 
		try{
			  Database db = new Database();
			  rb = Response.ok().entity(db.getQuizVonAbteilung(ab_id)); 
		  }
		  catch(SQLException ex){
			  ex.printStackTrace();
			  rb = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(null);
		  }
		  
		  return rb.build();
	}
	 
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addQuiz(@QueryParam("ab_id") int ab_id, @Context UriInfo uriInfo, Quiz q) {
		ResponseBuilder rb = null; 
		try{
			Database db = new Database();
			db.addQuizZuAbteilung(ab_id, q);
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
	public Response deleteQuizJSON(@PathParam("q_id") int q_id) throws SQLException{
		ResponseBuilder rb = null; 
		try{
			Database db = new Database();
			db.deleteQuiz(q_id);
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
	public Response updateQuizJSON(Quiz q) throws SQLException{
		ResponseBuilder rb = null; 
		try{
			Database db = new Database();
			db.updateQuiz(q);
			rb = Response.ok();
		}
		catch(SQLException ex){
			ex.printStackTrace();
			rb = Response.status(500);
		}
		return rb.build();
	}
	
	@POST
	@Path("/{q_id}/gewinnspieldaten")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addGewinnspielDaten (@PathParam("q_id") int q_id, GewinnspielDaten gd){
		ResponseBuilder rb = null; 
		try{
			Database db = new Database();
			db.addGewinnspielDatenZuQuiz(q_id, gd);
			rb = Response.ok();
		}
		catch(SQLException ex){
			ex.printStackTrace();
			rb = Response.status(500);
		}
		return rb.build();
	}
	
	@GET
	@Path("/{q_id}/gewinnspieldaten")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGewinnspielDatenVonQuiz(@PathParam("q_id") int q_id) {
		ResponseBuilder rb = null; 
		try{
			  Database db = new Database();
			  rb = Response.ok().entity(db.getGewinnspielDatenVonQuiz(q_id)); 
		  }
		  catch(SQLException ex){
			  ex.printStackTrace();
			  rb = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(null);
		  }
		  
		  return rb.build();
	}
	
	@DELETE
	@Path("/{q_id}/gewinnspieldaten")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteGewinnspielDatenVonQuiz (@PathParam("q_id") int q_id){
		ResponseBuilder rb = null; 
		try{
			Database db = new Database();
			db.deleteGewinnspielDatenVonQuiz(q_id);
			rb = Response.ok();
		}
		catch(SQLException ex){
			ex.printStackTrace();
			rb = Response.status(500);
		}
		return rb.build();
	}
}