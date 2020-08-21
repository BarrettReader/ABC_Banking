/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bank;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Barrett
 */
public class AccountController implements Initializable {
    
    //elements
    private PrintWriter fileOut;
    
    @FXML
    private Label lblAccountResult, lblServiceResult;
    
    @FXML
    private TextField txtAmount, txtBalance, txtInterestRate;
    
    @FXML
    private Button btnUpdate, btnExit, btnSaveTransaction;
    
    @FXML
    private ComboBox<String> ddlAccount;
    
    @FXML
    private ListView<String> lstService;
    
    //------------------------------------------------------------------
    
    //SAVE TRANSACTION BUTTON
    @FXML
    private void save(ActionEvent event) throws FileNotFoundException {
        fileOut.print(String.valueOf(ddlAccount.getSelectionModel().getSelectedItem()) + ",");
        fileOut.println(lstService.getSelectionModel().getSelectedItem());   
    }
    
    //UPDATE BUTTON
    @FXML
    private void update(ActionEvent event) {
        Object acctSelected = ddlAccount.getSelectionModel().getSelectedItem();
        Account temp = (Account) acctSelected;
        if ("Deposit".equals(lblServiceResult.getText())){
            temp.setBalance(Double.parseDouble(txtBalance.getText()) + Double.parseDouble(txtAmount.getText()));
        }else{
            temp.setBalance(Double.parseDouble(txtBalance.getText()) - Double.parseDouble(txtAmount.getText()));
        }
        temp.setInterestRate(Double.parseDouble(txtInterestRate.getText()));
        
        //Update fields
        int index1 = ddlAccount.getSelectionModel().getSelectedIndex();
        if (index1 == 2){
            ddlAccount.getSelectionModel().select(index1 - 2);
            ddlAccount.getSelectionModel().select(index1);
        }else{
            ddlAccount.getSelectionModel().select(index1 + 1);
            ddlAccount.getSelectionModel().select(index1);
        }
        
        lblAccountResult.setText(String.valueOf(temp));
        txtAmount.setText("0.0");
        txtBalance.setText(Double.toString(temp.getBalance()));
        txtInterestRate.setText(Double.toString(temp.getInterestRate()));
    }
    
    //EXIT BUTTON
    @FXML
    private void exit(ActionEvent event) {
        fileOut.close();
        Platform.exit();   
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //print writter
        try {
            //fileOut = new PrintWriter(new BufferedWriter(new FileWriter("Assignment5.dat", true)));
            fileOut = new PrintWriter("Assignment5.dat");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        //Account 1
        Account acct1 = new Account();
        acct1.setId("c-500");
        acct1.setBalance(1000);
        acct1.setInterestRate(2.5);
        
        //Account 2
        Account acct2 = new Account();
        acct2.setId("c-700");
        acct2.setBalance(2000);
        acct2.setInterestRate(3.5);
        
        //Account 3
        Account acct3 = new Account();
        acct3.setId("c-900");
        acct3.setBalance(3000);
        acct3.setInterestRate(4.5);
        
        //Send accounts to drop down list
        Account[] accounts = {acct1, acct2, acct3};
        ObservableList obsAccounts = FXCollections.observableArrayList(accounts);
        ddlAccount.setItems(obsAccounts);
        
        //send information from ddl to TextFields
        ddlAccount.setOnAction((ActionEvent event) -> {
            Object acctSelected = ddlAccount.getSelectionModel().getSelectedItem();
            Account temp = (Account) acctSelected;
            lblAccountResult.setText(String.valueOf(temp));
            txtAmount.setText("0.0");
            txtBalance.setText(Double.toString(temp.getBalance()));
            txtInterestRate.setText(Double.toString(temp.getInterestRate()));
        });
        
        //BOTTOM SERVICE INDICATOR
        ObservableList<String> services = FXCollections.observableArrayList("Deposit", "Withdraw");
        lstService.setItems(services);
        lstService.getSelectionModel().selectedItemProperty().addListener((Observable observable) -> {
            lblServiceResult.setText(lstService.getSelectionModel().getSelectedItem());
        });
        
    }          
}