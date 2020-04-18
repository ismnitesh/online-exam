package onlineexam.resources;

import com.codahale.metrics.annotation.Timed;
import onlineexam.dao.UserDao;
import onlineexam.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class UsersResource {

    public UsersResource() {
    }

    @GET
    @Timed
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("id") int id) {
        return UserDao.getUserById(id);
    }

    @POST
    @Timed
    @Path("/save")
    @Produces()
    @Consumes({MediaType.APPLICATION_JSON})
    public Response saveUser(User user) {
        boolean save = UserDao.save(user);
        if(save)
            return Response.ok().build();
        else
            return Response.serverError().build();
    }
}
