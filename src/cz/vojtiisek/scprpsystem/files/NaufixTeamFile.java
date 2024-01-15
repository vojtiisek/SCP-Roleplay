package cz.vojtiisek.scprpsystem.files;

import java.io.File;

import cz.vojtiisek.scprpsystem.SCPRP;

public class NaufixTeamFile {
	private static SCPRP main;
	private static SCPFileManager naufixTeamFile = new SCPFileManager(SCPRP.getInstace(), SCPRP.getInstace().getDataFolder() + File.separator, "naufix-team", true, true);
	
	public NaufixTeamFile(SCPRP main) {
		this.main = main;		
	}
	
	public static SCPFileManager getNaufixTeamFile() {
		return naufixTeamFile;
	}
}