package service;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/ping")
public class ServiceStatus {

	@GET
	public Response isOnline() throws SQLException{
		return Response.ok().build();
	}
}
