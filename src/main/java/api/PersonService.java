package api;


import controller.PersonController;
import data.DTU.Person;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("persons")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PersonService {
    private PersonController personController = new PersonController();

    @GET
    public List<Person> getPersons ()
    {
        return personController.getallPersons();
    }

    @GET
    @Path("Person/{id}")
    public Person getAddress(@PathParam("id") int id){
        try {
            return personController.getPerson(id);
        }catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }

    @POST
    public Person createPerson (Person person){
        System.out.println(person);
        try {
            personController.addperson(person);
        }catch (WebApplicationException | SQLException e){
            e.printStackTrace();
        }
        return person;
    }


    @PUT
    @Path("updateperson")
    public Person updatePerson (Person person){
        return personController.updatePerson(person);
    }

    @DELETE
    public Person deletePerson (Person person){
        return personController.remove(person);
    }
}
