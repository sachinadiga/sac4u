package com.sac4u;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("service")
public class RestService {
	@Path("getapps")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllApps(){
		System.out.println("came inside getAllApps()");
        return Response.status(200).entity(new Apps().getAllApps().toString()).build();
	}
	
	@Path("getapp/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getApp(@PathParam("id") int id){
		System.out.println("came inside getAllApp("+id+")");
        return Response.status(200).entity(new Apps().getApp(id).toString()).build();
	}
	
	@Path("deleteapp/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteApp(@PathParam("id") int id){
		System.out.println("came inside deleteApp("+id+")");
        return Response.status(200).entity(new Apps().deleteApp(id)).build();
	}
	
	@PUT
	@Path("/putapps")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	public Response createApp(String jsonBody){
		System.out.println("putApps()");
		String r = new Apps().insertApps(jsonBody);
		return Response.status(201).entity(jsonBody).build();
	}

}
