package com.group86.ws;

import java.net.*;
import java.util.*;
 
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.group86.ws.ComplianDao;
import com.group86.model.Complain;

@Path("/complains")
public class ComplianResource {

	private static ComplianDao dao;
	
	
	public ComplianResource() {
		dao= new ComplianDao();
	}
	
	// get all complains
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Complain> list() {
		List<Complain> list =dao.getAll();
		System.out.println(list);
	    return list;
	}
	
	// get single complain by id
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") Long id) {
	    Complain complain = dao.get(id);
	    if (complain != null) {
	        return Response.ok(complain, MediaType.APPLICATION_JSON).build();
	    } else {
	        return Response.status(Response.Status.NOT_FOUND).build();
	    }
	}
	
	// add new complain
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(Complain complain) throws URISyntaxException {
	    Long newComplainId = dao.add(complain);
	    URI uri = new URI("/complains/" + newComplainId);
	    return Response.created(uri).build();
	}
	
	// update complain
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response update(@PathParam("id") Long id, Complain complain) {
	    complain.setId(id);
	    if (dao.update(complain)) {
	        return Response.ok().build();
	    } else {
	        return Response.notModified().build();
	    }
	}
	
	// delete a complain
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") Long id) {
	    if (dao.delete(id)) {
	        return Response.ok().build();
	    } else {
	        return Response.notModified().build();
	    }
	}
}
