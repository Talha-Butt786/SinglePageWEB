import com.mysql.cj.protocol.Resultset;
import data.DTU.Person;
import data.Data;
import data.MySqlConnector;

import javax.ws.rs.WebApplicationException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class main {
    public static void main(String[] args) {
        Data data = new Data();
//        System.out.println(data.getSize());
        Person volkan = new Person();
        volkan.setId(1);
        volkan.setName("volkan");
        volkan.setAge(26);
        volkan.setAddress("anyway 123");
        try {
            Person person = data.addPerson(volkan);
          //  Person mikkel = data.getPerson(3);
            System.out.println(person);
        }catch (WebApplicationException | SQLException e){
            System.out.println(e);
        }
//
//        System.out.println(data.getAllPersons().toString());
//        System.out.println(data.getSize());
//        MySqlConnector database = new MySqlConnector("root","Captan123","localhost",3306);
//        database.connect();
//        Person person = data.getPerson(12);
//        System.out.println(person);


    }
}
