package com.kldp.second.archive;

import java.io.File;

import javafx.concurrent.Task;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import net.lingala.zip4j.progress.ProgressMonitor;

public class Compressor extends Task<Boolean> {
	private String source,dest,passKey;
	

	public Compressor(String source, String dest, String passKey) {
		super();
		this.source = source;
		this.dest = dest;
		this.passKey = passKey;
	}


	@Override
	protected Boolean call() throws Exception {
		// TODO Auto-generated method stub
		ZipParameters zipParameters = new ZipParameters();
		zipParameters.setCompressionMethod(CompressionMethod.DEFLATE);
		zipParameters.setEncryptFiles(true);
		zipParameters.setEncryptionMethod(EncryptionMethod.AES);
		// Below line is optional. AES 256 is used by default. You can override it to use AES 128. AES 192 is supported only for extracting.
		zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256); 
		File sourceDirectory=new File(source);
		ZipFile zipFile = new ZipFile(dest+"/"+sourceDirectory.getName()+".zip", passKey.toCharArray());
		ProgressMonitor progressMonitor = zipFile.getProgressMonitor();
		zipFile.setRunInThread(true);
		zipFile.addFolder(sourceDirectory, zipParameters);
		//App.setRoot("progress");
		while (!progressMonitor.getState().equals(ProgressMonitor.State.READY)) {
			  updateMessage("Wait..Processing..");
			  updateProgress(progressMonitor.getPercentDone(),100);
			  //currentFile.setText("Current file: " + progressMonitor.getFileName());
			  //System.out.println("Current task: " + progressMonitor.getCurrentTask());
			  Thread.sleep(100);
			}
		if (progressMonitor.getResult().equals(ProgressMonitor.Result.SUCCESS)) {
				updateMessage("Successfully compressed");
				updateValue(true);
			} else if (progressMonitor.getResult().equals(ProgressMonitor.Result.ERROR)) {
				System.out.println("\n\n"+progressMonitor.getException().toString());
			  	updateMessage("Error occurred.");
			  updateValue(false);
			 // currentFile.setText(progressMonitor.getException().getMessage());
			} else if (progressMonitor.getResult().equals(ProgressMonitor.Result.CANCELLED)) {
			  updateMessage("Task cancelled");
			  updateValue(false);
			}
		zipFile.close();
		return null;
	}

}
