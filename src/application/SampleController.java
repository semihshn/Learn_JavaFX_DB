package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.ÝsteMySQL.Util.DatabaseUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.*;

public class SampleController {
	
	public SampleController() {
		// TODO Auto-generated constructor stub
		this.connection=DatabaseUtil.Connect();
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_update;

    @FXML
    private Button btn_login;

    @FXML
    private TextField txt_usrName;

    @FXML
    private TextField txt_psw;

    @FXML
    private Label lbl_result;
    
    Connection connection=null;
    PreparedStatement query=null;
    ResultSet result=null;
    String sql;

    @FXML
    void btn_add_click(ActionEvent event) {
    	sql="insert into login (user_name,password,authority) values (?,?,?)";
    	try {
			query=connection.prepareStatement(sql);
			//DatabaseUtil.encryptMD5(txt_psw.getText().trim())
			query.setString(1, txt_usrName.getText());
			query.setString(2, DatabaseUtil.encryptMD5(txt_psw.getText().trim()));
			query.setInt(3, 1);
			query.executeUpdate();
			lbl_result.setText("Kullanýcý eklendi");
		} catch (Exception e) {
			// TODO: handle exception
			new Exception(e);
		}
    }

    @FXML
    void btn_delete_click(ActionEvent event) {
    	sql="delete from login where user_name=? and password=?";
    	try {
			query=connection.prepareStatement(sql);
			query.setString(1, txt_usrName.getText().trim());
			query.setString(2, txt_psw.getText().trim());
			query.executeUpdate();
			lbl_result.setText("Kullanýcý silindi");
		} catch (Exception e) {
			// TODO: handle exception
			new Exception(e);
		}
    }

    @FXML
    void btn_login_click(ActionEvent event) {
    	sql="select * from login where user_name=? and password=?";
    	try {
			query=connection.prepareStatement(sql);
			query.setString(1, txt_usrName.getText().trim());
			query.setString(2, txt_psw.getText().trim());
			
			ResultSet result=query.executeQuery();
			
			if (!result.next()) {
				lbl_result.setText("Kullanýcý adý veya þifre hatalý");
			}
			else {
				System.out.println("ID : "+result.getString("id"));
				System.out.println("User Name : "+result.getString("user_name")+" giriþ baþarýlý");
			}
		} catch (Exception e) {
			// TODO: handle exception
			lbl_result.setText(e.getMessage().toString());
		}
    }

    @FXML
    void btn_update_click(ActionEvent event) {
    	//MD5 veya SHA256
    	sql="update login set password=? where user_name=?";
    	try {
			query=connection.prepareStatement(sql);
			query.setString(1, DatabaseUtil.encryptMD5(txt_psw.getText().trim()));
			query.setString(2, txt_usrName.getText().trim());
			query.executeUpdate();
			lbl_result.setText("Þifre güncelleme iþlemi tamamlandý");
		} catch (Exception e) {
			// TODO: handle exception
			new Exception(e);
		}
    }

    @FXML
    void initialize() {
    	
    }
}
