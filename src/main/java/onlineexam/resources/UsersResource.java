package onlineexam.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import lombok.extern.slf4j.Slf4j;
import onlineexam.dao.UserDao;
import onlineexam.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
@Slf4j
public class UsersResource {
    private UserDao userDao;

    public UsersResource(final UserDao userDao) {
        this.userDao = userDao;
    }

    @GET
    @Timed
    @Path("/get/{id}")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("id") int id) {
        return userDao.getUserById(id);
    }

    @POST
    @Timed
    @Path("/save")
    @UnitOfWork
    @Consumes({MediaType.APPLICATION_JSON})
    public Response saveUser(User user) {
        boolean save = userDao.save(user);
        if(save)
            return Response.ok().build();
        else
            return Response.serverError().build();
    }
}
