package cz.vojtiisek.scprpsystem.files;

import java.io.File;

import cz.vojtiisek.scprpsystem.SCPRP;

public class ConstructionsFile {
	private static SCPRP main;
	private static SCPFileManager constructionsFile = new SCPFileManager(SCPRP.getInstace(), SCPRP.getInstace().getDataFolder() + File.separator, "constructions", true, true);
	
	public ConstructionsFile(SCPRP main) {
		this.main = main;		
	}
	
	public static SCPFileManager getConstructionsFile() {
		return constructionsFile;
	}
}
