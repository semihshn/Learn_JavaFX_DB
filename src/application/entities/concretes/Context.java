package application.entities.concretes;

import java.sql.Connection;
import java.sql.DriverManager;

public class Context {

static Connection con=null;
	
	public static Connection Connect() {
		try {
			//"jdbc:mysql://ServerIPAdresi/db_ismi", "usern_name","password"
			con=DriverManager.getConnection("jdbc:mysql://localhost/exampledb","root","");
			return con;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
			return null;
		}
	}
}
