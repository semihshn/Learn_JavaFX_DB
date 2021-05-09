package application.dataAccess.concretes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import application.dataAccess.abstracts.UserDao;
import application.entities.concretes.Context;
import application.entities.concretes.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HibernateUserDao implements UserDao{

	Connection connection=null;
    PreparedStatement query=null;
    ResultSet result=null;
    String sql;
    
	public HibernateUserDao() {
		// TODO Auto-generated constructor stub
		connection=Context.Connect();
	}
	
	@Override
	public void add(User user) {
		// TODO Auto-generated method stub
		sql="insert into login (user_name,password,authority) values (?,?,?)";
    	try {
			query=connection.prepareStatement(sql);
			query.setString(1, user.getUserName());
			query.setString(2, user.getPassword());
			query.setInt(3, user.getAuthority());
			query.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			new Exception(e);
		}
		
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		sql="update login set password=? where user_name=?";
    	try {
			query=connection.prepareStatement(sql);
			query.setString(1, user.getPassword());
			query.setString(2, user.getUserName());
			query.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			new Exception(e);
		}
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
    	sql="delete from login where user_name=? and password=?";
    	try {
			query=connection.prepareStatement(sql);
			query.setString(1, user.getUserName());
			query.setString(2, user.getPassword());
			query.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			new Exception(e);
		}
	}

	@Override
	public User get(int id) {
		// TODO Auto-generated method stub
		
    	try {
    		sql="select * from login where id=?";
    		query.setInt(1, id);
    		
			query=connection.prepareStatement(sql);
			
			ResultSet result=query.executeQuery();
			
			User user=new User(result.getInt("id"),result.getString("user_name"),result.getString("password"),result.getInt("authority"));
			
			return user;
		} catch (Exception e) {
			// TODO: handle exception
			new Exception(e.getMessage());
		}
		
		return null;
	}

	@Override
	public ObservableList<User> getAll() {
		// TODO Auto-generated method stub
		
		sql="select * from login";
		
		ObservableList<User> user=FXCollections.observableArrayList();
		
    	try {
			query=connection.prepareStatement(sql);
			
			ResultSet result=query.executeQuery();
			
			while (result.next()) {
				user.add(new User(result.getInt("id"),result.getString("user_name"),result.getString("password"),result.getInt("authority")));
				
			}
			
			return user;
		} catch (Exception e) {
			// TODO: handle exception
			new Exception(e.getMessage());
		}
		
		return null;
	}

}
