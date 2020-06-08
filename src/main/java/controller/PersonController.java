package controller;

import data.DTU.Person;
import data.Data;

import javax.ws.rs.WebApplicationException;
import java.sql.SQLException;
import java.util.List;

public class PersonController {
    private Data data =  new Data();

    public List<Person> getallPersons (){
        return data.getAllPersons();
    }

    public Person getPerson (int id) throws SQLException {
        return data.getPerson(id);
    }
    public Person updatePerson (Person person){
        return data.setPerson(person);
    }
    public void addperson (Person person) throws WebApplicationException, SQLException {
        data.addPerson(person);
    }
    public Person remove(Person person){
        data.removePerson(person);
        return person;
    }
}
