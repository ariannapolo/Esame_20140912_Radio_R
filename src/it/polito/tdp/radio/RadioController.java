/**
 * Sample Skeleton for 'Radio.fxml' Controller Class
 */

package it.polito.tdp.radio;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.radio.bean.Citta;
import it.polito.tdp.radio.bean.Model;
import it.polito.tdp.radio.bean.Ponte;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class RadioController {
	Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cmbCitta1"
    private ComboBox<Citta> cmbCitta1; // Value injected by FXMLLoader

    @FXML // fx:id="cmbCitta2"
    private ComboBox<Citta> cmbCitta2; // Value injected by FXMLLoader

    @FXML // fx:id="cmbCitta3"
    private ComboBox<Citta> cmbCitta3; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaPonti"
    private Button btnCercaPonti; // Value injected by FXMLLoader

    @FXML // fx:id="btnCoperturaOttima"
    private Button btnCoperturaOttima; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCercaPonti(ActionEvent event) {
    	txtResult.clear();
    	Citta c1 = cmbCitta1.getValue();
    	Citta c2 = cmbCitta2.getValue();
    	
    	if(c1==null||c2==null){
    		txtResult.appendText("Errore. Selezionare una citta.");
    		return;
    	}else if(c1.equals(c2)){
    		txtResult.appendText("Errore. Selezionare citta diverse.");
    		return;
       	}
    	
    	txtResult.appendText("Ponti in comune: \n");
    	for(Ponte p : model.cercaPonti(c1, c2)){
    		txtResult.appendText(p+"\n");
    	}
    	

    }

    @FXML
    void doCoperturaOttima(ActionEvent event) {
    	txtResult.clear();
    	Citta c1 = cmbCitta1.getValue();
    	Citta c2 = cmbCitta2.getValue();
    	Citta c3 = cmbCitta3.getValue();
    	
    	if(c1==null||c2==null || c3==null){
    		txtResult.appendText("Errore. Selezionare una citta.");
    		return;
    	}else if(c1.equals(c2)||c1.equals(c3)||c2.equals(c3)){
    		txtResult.appendText("Errore. Selezionare citta diverse.");
    		return;
       	}
    	txtResult.appendText("Copertura ottima: \n");
    	for(Ponte p : model.coperturaOttima(c1, c2, c3)){
    		txtResult.appendText(p+"\n");
    	}

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cmbCitta1 != null : "fx:id=\"cmbCitta1\" was not injected: check your FXML file 'Radio.fxml'.";
        assert cmbCitta2 != null : "fx:id=\"cmbCitta2\" was not injected: check your FXML file 'Radio.fxml'.";
        assert cmbCitta3 != null : "fx:id=\"cmbCitta3\" was not injected: check your FXML file 'Radio.fxml'.";
        assert btnCercaPonti != null : "fx:id=\"btnCercaPonti\" was not injected: check your FXML file 'Radio.fxml'.";
        assert btnCoperturaOttima != null : "fx:id=\"btnCoperturaOttima\" was not injected: check your FXML file 'Radio.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Radio.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		//riempo menu a tendina
		cmbCitta1.getItems().clear();
		cmbCitta2.getItems().clear();
		cmbCitta3.getItems().clear();
		cmbCitta1.getItems().addAll(model.getAllCitta());
		cmbCitta2.getItems().addAll(model.getAllCitta());
		cmbCitta3.getItems().addAll(model.getAllCitta());
		
		
	}
}
