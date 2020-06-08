package data;

import data.DTU.Person;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Data {
    MySqlConnector database;

    public Data (){
        database =  new MySqlConnector("root","Captan123","localhost",3306);
    }


public List<Person> getAllPersons ()
{
    database.connect();
    ResultSet rs = database.query("SELECT * FROM Person");

    List<Person> personList = new ArrayList<>();
    try {
        //We do as in getUser, except we make new user until rs is empty
        while (rs.next()) {
            Person person = new Person();
            person.setId(rs.getInt("PrID"));
            person.setName(rs.getString("PrName"));
            person.setAge(rs.getInt("prAge"));
            person.setAddress(rs.getString("PrAddress"));
            List<String> roles = new ArrayList<>();
            personList.add(person);
        }
        rs.close();

    } catch (
            SQLException e) {
        e.printStackTrace();
    }
    database.close();
    return personList;

}


public Person getPerson(int id) throws SQLException {
        database.connect();
        ResultSet rs = database.query("SELECT * FROM Person WHERE PrID=" + id);
        if(rs.next()) {
            if (rs.getInt("PrID") == id) {
                Person person = new Person();
                try {
                    rs.next();
                    //We do as in getUser, except we make new user until rs is empty
                    person.setId(rs.getInt("PrID"));
                    person.setName(rs.getString("PrName"));
                    person.setAge(rs.getInt("prAge"));
                    person.setAddress(rs.getString("PrAddress"));
                    rs.close();

                } catch (
                        SQLException e) {
                    e.printStackTrace();
                }
                database.close();
                return person;
            }
        }else
            return null;
    return null;
}

    public int getPersonID(Person person){
        return person.getId();
    }

    public Person setPerson (Person person){
       database.connect();
       try {
           ResultSet rs = database.query("SELECT * FROM Person WHERE PrID=" + person.getId());
           rs.next();
           if(rs.getInt("PrID")== person.getId()){
               database.update("UPDATE Person SET PrId = '" + person.getId() + "'WHERE (PrId = '" + person.getId()+ "');");
               database.update("UPDATE Person SET PrName = '" + person.getName() + "'WHERE (PrId = '" + person.getId()+ "');");
               database.update("UPDATE userdto SET PrAge = '" + person.getAge() + "'WHERE (PrId = '" + person.getId()+ "');");
               database.update("UPDATE userdto SET PrAddress = '" + person.getAddress() + "'WHERE (PrId = '" + person.getId()+ "');");
           }
           rs.close();
       }catch (SQLException e){
           System.out.println(e);
       }
       database.close();
       return person;

    }

    public Person addPerson (Person person) throws WebApplicationException, SQLException {
        if(getPerson(person.getId())==null) {
            try {
                database.update("insert into Person (PrID, PrName, prAge, PrAddress) VALUE ('" + person.getId() + "','" + person.getName() + "','" + person.getAge() + "','" + person.getAddress() + "')");
            } catch (Exception e) {
                System.out.println(e);
            }
            database.close();
            return person;
        }else
        throw new WebApplicationException("id is taken", Response.Status.NOT_FOUND.getStatusCode()); //status 400
    }

    public Person removePerson (Person person){
        database.connect();
        try {
            database.update("DELETE FROM Person WHERE PrID=" + person.getId());
        }catch (Exception e){
            System.out.println(e);
        }
        return person;
    }
}
