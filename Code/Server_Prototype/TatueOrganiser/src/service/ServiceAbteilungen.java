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
import model.Abteilung;
import model.Quiz;
import model.Stand;

@Path("/api/abteilungen")
public class ServiceAbteilungen {
	@GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public Vector<Abteilung> getAbteilungenJSON() throws SQLException{
		  try{
			  Database db = new Database();
			  return db.getAllAbteilungen(); 
		  }
		  catch(SQLException ex){
			  ex.printStackTrace();
		  }
		  
		  return null;
	  }
	
	@GET
	@Path("/{a_id}/staende")
	  @Produces(MediaType.APPLICATION_JSON)
	  public Vector<Stand> getStaendeByAbteilungenJSON(@PathParam("a_id") int a_id) throws SQLException{
		  try{
			  Database db = new Database();
			  return db.getStaendeVonAbteilungen(a_id);
		  }
		  catch(SQLException ex){
			  ex.printStackTrace();
		  }
		  
		  return null;
	  }
	
	@POST
	@Path("/{a_id}/staende")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addStandZuAbteilungJSON(@PathParam("a_id") int a_id, Stand s) throws SQLException{
		try{
			Database db = new Database();
			db.addStandZuAbteilung(a_id, s);
			return Response.ok().build();
		}
			catch(SQLException ex){
			ex.printStackTrace();
		}
		return Response.status(500).build();
	}
	  
	  @POST
		@Consumes(MediaType.APPLICATION_JSON)
		public Response addAbteilungJSON(Abteilung a) throws SQLException{
			try{
				Database db = new Database();
				db.addAbteilung(a);
				return Response.ok().build();
			}
				catch(SQLException ex){
				ex.printStackTrace();
			}
			return Response.status(500).build();
		}
	  
	  @POST
	  @Path("/{a_id}/quiz")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response addQuizZuAbteilungJSON(@PathParam("a_id") int a_id, Quiz q) throws SQLException{
			try{
				Database db = new Database();
				db.addQuizZuAbteilung(a_id, q);
				return Response.ok().build();
			}
				catch(SQLException ex){
				ex.printStackTrace();
			}
			return Response.status(500).build();
		}
		
		@DELETE
		@Path("/{a_id}")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response deleteSchuelerJSON(@PathParam("a_id") int a_id) throws SQLException{
			try{
				Database db = new Database();
				db.deleteAbteilung(a_id);
				return Response.ok().build();
			}
				catch(SQLException ex){
				ex.printStackTrace();
			}
			return Response.status(500).build();
		}
		
		@PUT
		@Consumes(MediaType.APPLICATION_JSON)
		public Response updateAbteilungJSON(Abteilung a) throws SQLException{
			try{
				Database db = new Database();
				db.updateAbteilung(a);
				return Response.ok().build();
			}
				catch(SQLException ex){
				ex.printStackTrace();
			}
			return Response.status(500).build();
		}
	  
}