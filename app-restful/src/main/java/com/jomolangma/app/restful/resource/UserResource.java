package com.jomolangma.app.restful.resource;

import javax.sound.midi.SysexMessage;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

/**
 * Created by ZhangLijun on 4/19/16.
 */

@Path("user")
public class UserResource {

    @GET
    @Path("{username: [a-zA-Z][a-zA-Z_0-9]*}")
    @Produces("text/plain")
    public String getUser(@PathParam("username") String userName) {
        return userName;
    }

    @GET
    @Path("{username: [a-zA-Z][a-zA-Z_0-9]*}")
    @Produces("text/html")
    public String getUser1(@PathParam("username") String userName) {
        return "html" + userName;
    }

    @GET
    @Path("/group/{groupname}/{username}")
    public String get(@Context UriInfo ui) {
        MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
        MultivaluedMap<String, String> pathParams = ui.getPathParameters();

        String queryParamStr = "";
        for (String key:queryParams.keySet()){
            queryParamStr = queryParamStr + queryParams.getFirst(key);
        }

        String pathParamStr = "";
        for (String key:pathParams.keySet()){
            pathParamStr = pathParamStr + pathParams.getFirst(key);
        }

        return "queryParamStr:" + queryParamStr + " pathParamStr:" + pathParamStr;
    }
}
