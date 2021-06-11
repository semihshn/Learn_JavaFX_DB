package application;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import application.dataAccess.abstracts.TransactionDao;
import application.dataAccess.concretes.HibernateTransactionDao;
import application.entities.concretes.Transaction;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

public class TableDbSearchsController {
	
	TransactionDao transactionDao=new HibernateTransactionDao();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txt_search;

    @FXML
    private ComboBox<?> combo_user;

    @FXML
    private DatePicker inception_date;

    @FXML
    private DatePicker due_date;

    @FXML
    private Button btn_question;

    @FXML
    private TableView<Transaction> tableView;

    @FXML
    private TableColumn<Transaction, Integer> col_id;

    @FXML
    private TableColumn<Transaction, String> col_User;

    @FXML
    private TableColumn<Transaction, String> col_Description;

    @FXML
    private TableColumn<Transaction, Double> col_bill;

    @FXML
    private TableColumn<Transaction, LocalDate> col_date;

    @FXML
    void btn_questionClicked(ActionEvent event) {

    	col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
    	col_User.setCellValueFactory(new PropertyValueFactory<>("user"));
    	col_Description.setCellValueFactory(new PropertyValueFactory<>("islemAciklama"));
    	col_bill.setCellValueFactory(new PropertyValueFactory<>("islemTutar"));
    	col_date.setCellValueFactory(new PropertyValueFactory<>("islemTarih"));
    	tableView.setItems((ObservableList<Transaction>)transactionDao.getByTransactionDate(inception_date.getValue(),due_date.getValue()));
    }

    @FXML
    void txt_searchClicked(ActionEvent event) {
    
    	
    }
    
    @FXML
    void txt_searchKeyPress(KeyEvent event) {
    	
    	col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
    	col_User.setCellValueFactory(new PropertyValueFactory<>("user"));
    	col_Description.setCellValueFactory(new PropertyValueFactory<>("islemAciklama"));
    	col_bill.setCellValueFactory(new PropertyValueFactory<>("islemTutar"));
    	col_date.setCellValueFactory(new PropertyValueFactory<>("islemTarih"));
    	tableView.setItems((ObservableList<Transaction>)transactionDao.getByDescriptionOrUser(txt_search.getText()));
    }
    
    public void getValues() {
    	
    	col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
    	col_User.setCellValueFactory(new PropertyValueFactory<>("user"));
    	col_Description.setCellValueFactory(new PropertyValueFactory<>("islemAciklama"));
    	col_bill.setCellValueFactory(new PropertyValueFactory<>("islemTutar"));
    	col_date.setCellValueFactory(new PropertyValueFactory<>("islemTarih"));
    	tableView.setItems((ObservableList<Transaction>)transactionDao.getAll());
    }

    @FXML
    void initialize() {
    	
    	this.getValues();

    }
}
