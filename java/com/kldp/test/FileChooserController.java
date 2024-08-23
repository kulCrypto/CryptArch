package com.kldp.test;

import java.io.File;
import java.io.IOException;

import com.kldp.second.archive.Compressor;
import com.kldp.second.archive.Extractor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileChooserController {
	@FXML
	Label source,destin,done;
	@FXML
	Button sourceDir,destDir,proceed,okButton;
	@FXML
	RadioButton compress,extract;
	@FXML
	PasswordField passKey;
	@FXML
	ProgressBar progress;
	@FXML
	GridPane container;
	@FXML
	 public void chooseSourceFile(ActionEvent ae) {
		if(extract.isSelected())
			setFile(source);
		else
			setDirectory(source);
		//else
	    	  // setDirectory(destin);
		}
	@FXML
	public void chooseDestFile(ActionEvent ae) 
	{		
			setDirectory(destin);
	}
	public void setFile(Label lbl) 
	{
		FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) 
        {
            lbl.setText(selectedFile.getAbsolutePath());
        }
	}
	 public void setDirectory(Label lbl) {
		 DirectoryChooser directoryChooser = new DirectoryChooser();
	        directoryChooser.setTitle("Open Resource File");
	        File selectedFile = directoryChooser.showDialog(new Stage());
	        if (selectedFile != null) 
	        {
	            lbl.setText(selectedFile.getAbsolutePath());
	        }
	        }
	 public void resetGUI() 
	 {
		 source.setText("");
		 destin.setText("");
		 passKey.clear();
	 }
	 @FXML
	  public void action(ActionEvent ae) throws IOException {
		 //System.out.println("Action");
		  if(validate()) 
		  {
			  container.setDisable(true);
			  if(compress.isSelected()) 
			  {				 
			  Compressor compressor=new Compressor(source.getText(),destin.getText(),passKey.getText());
			  compressor.messageProperty().addListener((observable,oldValue,newValue)-> done.setText(newValue));
			  compressor.valueProperty().addListener((observable,oldValue,newValue)->
			  {
				  if(newValue!=null)
				  	{
					  okButton.setVisible(true);
				  	}
			  });
			  progress.progressProperty().bind(compressor.progressProperty());
			Thread th=new Thread(compressor);
			th.setDaemon(true);
			th.start();
			progress.setVisible(true);
					
			  		//App.setRoot("progress");

			  
			  //done.setText("Done !");
			  }
			  else  
			  {
				  /*
				 * App.setRoot("/com/kldp/second/archive/progress");
				 * 
				 * Task<Void> task=new Task<Void>() {
				 * 
				 * @Override protected Void call() throws Exception { // TODO Auto-generated
				 * method stub Archive.getInstance().extract(source.getText(),destin.getText(),
				 * passKey.getText()); return null; } };
				 */
				  Extractor extractor=new Extractor(source.getText(),destin.getText(),passKey.getText());
				  progress.progressProperty().bind(extractor.progressProperty());
				  extractor.messageProperty().addListener((observable,oldValue,newValue)-> done.setText(newValue));
				  extractor.valueProperty().addListener((observable,oldValue,newValue)->
				  {
					  if(newValue!=null)
					  	{
						 okButton.setVisible(true);
					  	}
				  }); 
				  Thread th=new Thread(extractor);
				  th.setDaemon(true);
				  th.start();  
				  progress.setVisible(true);
				  //resetGUI();
				  //done.setText("Done !");
			  }
			
		  }
	  }
	        
	  public boolean validate() throws IOException {
		  //System.out.println("Action "+source.getText());
		  if((source.getText().isBlank()) || (destin.getText().isBlank()))
			  {
			 // warning.setText("Please select source and destination Folders");
			  App.setRoot("secondary");
			  //System.out.println("source required");
			  return false;
			  }
		  else if(passKey.getText()==null) 
		  {
			  //warning.setText("Please provide strong password for data security");
			  App.setRoot("secondary");
			  return false;
		  }
		  else return true;
	  }
	   
	     //okButton cleans the scene to look like initial   
		public void okButtonHandeler(ActionEvent ae) {
			 container.setDisable(false);
			  resetGUI();
			  done.setText("");
			  progress.setVisible(false);
			  okButton.setVisible(false);
		}
	}

