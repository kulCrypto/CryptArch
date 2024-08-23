package com.kldp.test;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class WarningController {
@FXML
Button okButton;

public void okButtonHandeler(ActionEvent ae) throws IOException 
	{
		App.setRoot("primaryTest");
	}
	

}
