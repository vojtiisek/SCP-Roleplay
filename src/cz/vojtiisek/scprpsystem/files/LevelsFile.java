package cz.vojtiisek.scprpsystem.files;

import java.io.File;

import cz.vojtiisek.scprpsystem.SCPRP;

public class LevelsFile {
	private static SCPRP main;
	private static SCPFileManager levelsFile = new SCPFileManager(SCPRP.getInstace(), SCPRP.getInstace().getDataFolder() + File.separator, "levelling-system", true, true);
	
	public LevelsFile(SCPRP main) {
		this.main = main;		
	}
	
	public static SCPFileManager getLevelsFile() {
		return levelsFile;
	}
}
