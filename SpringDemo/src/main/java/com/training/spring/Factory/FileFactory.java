package com.training.spring.Factory;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileFactory {
	
	private static final Log log = LogFactory.getLog(FileFactory.class);
			
	private File candidateRepoFile;
	private File testRepoFile;
	
	private static FileFactory instance;
	
	private FileFactory(){
		
	}
	
	public static final FileFactory newInstance(){
		
		if(instance==null){
			instance = new FileFactory();
			log.info("NEW FILEFACTORY INSTANCE CREATED");
		}
			
		return instance;
	}

	public File getCandidateRepoFile() {
		return candidateRepoFile;
	}

	public void setCandidateRepoFile(File candidateRepoFile) {
		this.candidateRepoFile = candidateRepoFile;
		log.info("CANDIDATE REPO FILE LOADED");
	}

	public File getTestRepoFile() {
		return testRepoFile;
	}

	public void setTestRepoFile(File testRepoFile) {
		this.testRepoFile = testRepoFile;
		log.info("TEST REPO FILE LOADED");
	}
	
	
		
}
