package com.yammer.maze.solver.resources;

import com.google.gson.Gson;
import com.yammer.maze.solver.LabEscape;
import com.yammer.maze.solver.Labyrinth;
import com.yammer.maze.solver.exceptions.InsufficientSizeException;
import com.yammer.maze.solver.exceptions.MalformedLabyrinthException;
import com.yammer.maze.solver.exceptions.NoEscapeException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/lab-escape")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LabEscapeResource {

    @POST
    public Response getEscape(String requestBody) {
        Labyrinth labyrinth = new Gson().fromJson(requestBody, Labyrinth.class);
        try {
            return Response.ok((new LabEscape()).drawPathForEscape(labyrinth.labyrinth, labyrinth.startX, labyrinth.startY)).build();
        } catch (NoEscapeException e) {
            return Response.serverError().entity(new Gson().toJson(e.getMessage())).build();
        } catch (InsufficientSizeException e) {
            return Response.serverError().entity(new Gson().toJson(e.getMessage())).build();
        } catch (MalformedLabyrinthException e) {
            return Response.serverError().entity(new Gson().toJson(e.getMessage())).build();
        }
    }
}