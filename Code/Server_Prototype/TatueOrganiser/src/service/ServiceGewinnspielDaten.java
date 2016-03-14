package service;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import database.Database;
import model.*;

@Path("/api/gewinnspieldaten")
public class ServiceGewinnspielDaten {  
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateGewinnspielDaten (@PathParam("q_id") int q_id, GewinnspielDaten gd){
		ResponseBuilder rb = null; 
		try{
			Database db = new Database();
			db.updateGewinnspielDaten(gd);
			rb = Response.ok();
		}
		catch(SQLException ex){
			ex.printStackTrace();
			rb = Response.status(500);
		}
		return rb.build();
	}
	
	@DELETE
	@Path("/{gd_id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteGewinnspielDaten (@PathParam("gd_id") int gd_id){
		ResponseBuilder rb = null; 
		try{
			Database db = new Database();
			db.deleteGewinnspielDaten(gd_id);
			rb = Response.ok();
		}
		catch(SQLException ex){
			ex.printStackTrace();
			rb = Response.status(500);
		}
		return rb.build();
	}
}