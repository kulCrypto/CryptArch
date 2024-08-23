package com.kldp.second.archive;

import javafx.concurrent.Task;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.progress.ProgressMonitor;

public class Extractor extends Task<Boolean> {
	private String source,dest,passKey;
	
	

	public Extractor(String source, String dest, String passKey) {
		super();
		this.source = source;
		this.dest = dest;
		this.passKey = passKey;
	}



	@Override
	protected Boolean call() throws Exception {
		// TODO Auto-generated method stub
		ZipFile zipFile=new ZipFile(source);
		zipFile.setPassword(passKey.toCharArray());
		ProgressMonitor progressMonitor = zipFile.getProgressMonitor();
		zipFile.setRunInThread(true);
		zipFile.extractAll(dest);
		//App.setRoot("progress");
		while (!progressMonitor.getState().equals(ProgressMonitor.State.READY)) {
			  updateMessage("Wait..Work In Progress");
			  updateProgress(progressMonitor.getPercentDone(),100);
			  //currentFile.setText("Current file: " + progressMonitor.getFileName());
			  //System.out.println("Current task: " + progressMonitor.getCurrentTask());
			  Thread.sleep(100);
			}
		if (progressMonitor.getResult().equals(ProgressMonitor.Result.SUCCESS)) {
			 updateMessage("Successfully extracted the zip");
			 updateValue(true);
			} else if (progressMonitor.getResult().equals(ProgressMonitor.Result.ERROR)) {
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
