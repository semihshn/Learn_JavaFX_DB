package application.dataAccess.abstracts;

import java.time.LocalDate;
import java.util.List;

import application.entities.concretes.Transaction;

public interface TransactionDao {

	void add(Transaction transaction);
	void update(Transaction transaction);
	void delete(Transaction transaction);
	
	Transaction get(int id);
	List<Transaction> getByDescriptionOrUser(String param);
	List<Transaction> getByTransactionDate(LocalDate startingDate,LocalDate dueDate);
	List<Transaction> getAll();
}
