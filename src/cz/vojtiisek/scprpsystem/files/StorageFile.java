package cz.vojtiisek.scprpsystem.files;

import java.io.File;

import cz.vojtiisek.scprpsystem.SCPRP;

public class StorageFile {
	private static SCPRP main;
	private static SCPFileManager storageFile = new SCPFileManager(SCPRP.getInstace(), SCPRP.getInstace().getDataFolder() + File.separator, "storage-db", true, true);
	
	public StorageFile(SCPRP main) {
		this.main = main;		
	}
	
	public static SCPFileManager getStorageFile() {
		return storageFile;
	}
}