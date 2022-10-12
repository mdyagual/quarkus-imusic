package ec.com.imusic.resource;

import ec.com.imusic.entity.Client;
import ec.com.imusic.repository.ClientRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/client")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientResource {

    @Inject
    ClientRepository cs;

    @GET
    public Response getAll(){
        return Response.ok(cs.listAll()).build();
    }

    @GET
    @Path("/{idClient}")
    public Response getClient(@PathParam("idClient") String idClient){
        return Response.ok(cs.get(idClient)).build();
    }

    @POST
    public Response save(Client c){
        cs.add(c);
        return Response.ok().build();

    }

    @PUT
    @Path("/{idClient}")
    public Response updateClient(@PathParam("idClient") String idClient, Client c){
        cs.update(idClient, c);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{idClient}")
    public Response deleteClient(@PathParam("idClient") String idClient){
        cs.delete(idClient);
        return Response.ok().build();
    }

}