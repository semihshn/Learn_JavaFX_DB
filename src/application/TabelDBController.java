package application;

import java.net.URL;
import java.util.ResourceBundle;

import application.dataAccess.abstracts.UserDao;
import application.dataAccess.concretes.HibernateUserDao;
import application.entities.concretes.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class TabelDBController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<User> tableViewRegisters;

    @FXML
    private TableColumn<User, Integer> col_id;

    @FXML
    private TableColumn<User, String> col_userName;

    @FXML
    private TableColumn<User, String> col_password;

    @FXML
    private TableColumn<User, Integer> col_authority;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_userName;

    @FXML
    private TextField txt_password;

    @FXML
    private TextField txt_authority;

    @FXML
    private Label lbl_id;

    @FXML
    private Label lbl_authority;

    @FXML
    private Label lbl_password;

    @FXML
    private Label lbl_userName;

    @FXML
    private Button btn_refresh;

    @FXML
    void btn_refreshClick(ActionEvent event) {
    	this.getValues();
    }

    @FXML
    void tableViewMouseClicked(MouseEvent event) {
    	User user=new User();
    	user=(User)tableViewRegisters.getItems().get(tableViewRegisters.getSelectionModel().getSelectedIndex());
    	txt_id.setText(String.valueOf(user.getId()));
    	txt_userName.setText(user.getUserName());
    	txt_password.setText(user.getPassword());
    	txt_authority.setText(String.valueOf(user.getAuthority()));
    }
    
    public void getValues() {
    	
    	UserDao userDao=new HibernateUserDao();
    	
    	col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
    	col_userName.setCellValueFactory(new PropertyValueFactory<>("userName"));
    	col_password.setCellValueFactory(new PropertyValueFactory<>("password"));
    	col_authority.setCellValueFactory(new PropertyValueFactory<>("authority"));
    	tableViewRegisters.setItems((ObservableList<User>)userDao.getAll());
    }

    @FXML
    void initialize() {
    	this.getValues();
    }
}
