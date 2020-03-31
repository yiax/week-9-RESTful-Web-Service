package edu.matc.employeeRestfulServices;

import edu.matc.entity.User;
import edu.matc.entity.Role;
import edu.matc.persistence.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/employee")
public class Employee {
    // The Java method will process HTTP GET requests
    @GET
    @Path("all")
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/html")
    public Response getEmployee() {
        String output = "";
        UserDao userDao = new UserDao();
        RoleDao roleDao = new RoleDao();

        List<User> users = userDao.getAllUsers();
        List<Role> roles = roleDao.getAllRole();

        for (int i = 0; i < users.size(); i++) {
            output += "Employee ID: " + users.get(i).getId() + "<br/>" +
                    "First Name:  " + users.get(i).getFirstName() + "<br/>" +
                    "Last Name:   " + users.get(i).getLastName() + "<br/>" +
                    "Role:        " + roles.get(i).getRole() + "<br/><br/>";
        }

        return Response.status(200).entity(output).build();
    }
}
