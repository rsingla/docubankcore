package com.doc.banks.mongo;

import java.nio.file.Files;
import java.util.List;

public interface StoreFiles {
	
	public void storeFile(Files file);
	
	public Files getFile(String fileId);

	public List<Files> getAllFiles(String userId);
	
}
