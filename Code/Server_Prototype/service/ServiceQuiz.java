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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import database.Database;
import model.*;

@Path("/api/quizzes")
public class ServiceQuiz {  
	
	@GET
	@Path("/{q_id}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public Quiz getQuizJSON(@PathParam("q_id") int q_id) throws SQLException{
		  try{
			  Database db = new Database();
			  return db.getQuiz(q_id);
		  }
		  catch(SQLException ex){
			  ex.printStackTrace();
		  }
		  
		  return null;
	  }
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Quiz getQuizVonAbteilung(@QueryParam("ab_id") int ab_id, @Context UriInfo uriInfo) {
		try{
			  Database db = new Database();
			  return db.getQuizVonAbteilung(ab_id);
		  }
		  catch(SQLException ex){
			  ex.printStackTrace();
		  }
		  
		  return null;
	}
	 
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addQuiz(@QueryParam("ab_id") int ab_id, @Context UriInfo uriInfo, Quiz q) {
	    try{
	    	Database db = new Database();
	    	db.addQuizZuAbteilung(ab_id, q);
		 	return Response.ok().build();
		}
			catch(SQLException ex){
			ex.printStackTrace();
		}
		return Response.status(500).build();
	}
	
	@DELETE
	@Path("/{q_id}")
	public Response deleteQuizJSON(@PathParam("q_id") int q_id) throws SQLException{
		try{
			Database db = new Database();
			db.deleteQuiz(q_id);
			return Response.ok().build();
		}
			catch(SQLException ex){
			ex.printStackTrace();
		}
		return Response.status(500).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateQuizJSON(Quiz q) throws SQLException{
		try{
			Database db = new Database();
			db.updateQuiz(q);
			return Response.ok().build();
		}
			catch(SQLException ex){
			ex.printStackTrace();
		}
		return Response.status(500).build();
	}
	
	@POST
	@Path("/{q_id}/gewinnspieldaten")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addGewinnspielDaten (@PathParam("q_id") int q_id, GewinnspielDaten gd){
		try{
	    	Database db = new Database();
	    	db.addGewinnspielDatenZuQuiz(q_id, gd);
		 	return Response.ok().build();
		}
			catch(SQLException ex){
			ex.printStackTrace();
		}
		return Response.status(500).build();
	}
	
	@GET
	@Path("/{q_id}/gewinnspieldaten")
	@Produces(MediaType.APPLICATION_JSON)
	public Vector<GewinnspielDaten> getQuizzesVonAbteilung(@PathParam("q_id") int q_id) {
		try{
			  Database db = new Database();
			  return db.getGewinnspielDatenVonQuiz(q_id);
		  }
		  catch(SQLException ex){
			  ex.printStackTrace();
		  }
		  
		  return null;
	}
}