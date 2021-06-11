package application.dataAccess.concretes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

import application.dataAccess.abstracts.TransactionDao;
import application.entities.concretes.Context;
import application.entities.concretes.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HibernateTransactionDao implements TransactionDao{
	
	Connection connection=null;
    PreparedStatement query=null;
    ResultSet result=null;
    String sql;
    
	public HibernateTransactionDao() {
		// TODO Auto-generated constructor stub
		connection=Context.Connect();
	}
	
	@Override
	public void add(Transaction transaction) {
		// TODO Auto-generated method stub
		sql="insert into islemler (user,islemAciklama,islemTutar,islemTarih) values (?,?,?,?)";
    	try {
			query=connection.prepareStatement(sql);
			query.setString(1, transaction.getUser());
			query.setString(2, transaction.getIslemAciklama());
			query.setDouble(3, transaction.getIslemTutar());
			query.setDate(4, transaction.getIslemTarih());
			query.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			new Exception(e);
		}
		
	}

	@Override
	public void update(Transaction transaction) {
		// TODO Auto-generated method stub
		sql="update islemler set user=?,islemAciklama=?,islemTutar=?,islemTarih=? where id=?";
    	try {
			query=connection.prepareStatement(sql);
			query.setString(1, transaction.getUser());
			query.setString(2, transaction.getIslemAciklama());
			query.setDouble(3, transaction.getIslemTutar());
			query.setDate(4, transaction.getIslemTarih());
			query.setInt(5, transaction.getId());
			query.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			new Exception(e);
		}
	}

	@Override
	public void delete(Transaction transaction) {
		// TODO Auto-generated method stub
    	sql="delete from islemler where id=?";
    	try {
			query=connection.prepareStatement(sql);
			query.setInt(1, transaction.getId());
			query.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			new Exception(e);
		}
	}

	@Override
	public Transaction get(int id) {
		// TODO Auto-generated method stub
		
    	try {
    		sql="select * from islemler where id=?";
    		query.setInt(1, id);
    		
			query=connection.prepareStatement(sql);
			
			ResultSet result=query.executeQuery();
			
			Transaction transaction=new Transaction(result.getInt("id"),result.getString("user"),result.getString("islemAciklama"),result.getDouble("islemTutar"),result.getDate("islemTarih"));
			
			return transaction;
		} catch (Exception e) {
			// TODO: handle exception
			new Exception(e.getMessage());
		}
		
		return null;
	}

	@Override
	public ObservableList<Transaction> getAll() {
		// TODO Auto-generated method stub
		
		sql="select * from islemler";
		
		ObservableList<Transaction> transaction=FXCollections.observableArrayList();
		
    	try {
			query=connection.prepareStatement(sql);
			
			ResultSet result=query.executeQuery();
			
			while (result.next()) {
				transaction.add(new Transaction(result.getInt("id"),result.getString("user"),result.getString("islemAciklama"),result.getDouble("islemTutar"),result.getDate("islemTarih")));
				
			}
			
			return transaction;
		} catch (Exception e) {
			// TODO: handle exception
			new Exception(e.getMessage());
		}
		
		return null;
	}

	@Override
	public List<Transaction> getByDescriptionOrUser(String param) {
		// TODO Auto-generated method stub
		
		sql="select * from islemler where user like '%"+param+"%' or islemAciklama like '%"+param+"%'";

		
		ObservableList<Transaction> transaction=FXCollections.observableArrayList();
		
    	try {
    		//sql="select * from islemler where user like ? or islemAciklama like ?";
    		//query.setString(1, param);
    		//query.setString(2, param);
    		//Bu yöntem yemiyor
    		
			query=connection.prepareStatement(sql);
			
			ResultSet result=query.executeQuery();
			
			while (result.next()) {
				transaction.add(new Transaction(result.getInt("id"),result.getString("user"),result.getString("islemAciklama"),result.getDouble("islemTutar"),result.getDate("islemTarih")));
				
			}
			
			return transaction;
		} catch (Exception e) {
			// TODO: handle exception
			new Exception(e.getMessage());
		}
		
		return null;
	}

	@Override
	public List<Transaction> getByTransactionDate(LocalDate startingDate, LocalDate dueDate) {
		// TODO Auto-generated method stub
		
		sql="select * from islemler where islemTarih<'"+dueDate+"' and islemTarih>'"+startingDate+"'";

		
		ObservableList<Transaction> transaction=FXCollections.observableArrayList();
		
    	try {
    		//sql="select * from islemler where islemTarih<? and islemTarih>?";
    		//query.setDate(1, Date.valueOf(dueDate));
    		//query.setDate(2, Date.valueOf(startingDate));
    		
			query=connection.prepareStatement(sql);
			
			ResultSet result=query.executeQuery();
			
			while (result.next()) {
				transaction.add(new Transaction(result.getInt("id"),result.getString("user"),result.getString("islemAciklama"),result.getDouble("islemTutar"),result.getDate("islemTarih")));
				
			}
			
			return transaction;
		} catch (Exception e) {
			// TODO: handle exception
			new Exception(e.getMessage());
		}
		
		return null;
	}

}
