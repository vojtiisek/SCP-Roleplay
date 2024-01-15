package cz.vojtiisek.scprpsystem.files;

import java.io.File;

import cz.vojtiisek.scprpsystem.SCPRP;

public class JailedPlayersFile {
	private static SCPRP main;
	private static SCPFileManager jailedPlayersFile = new SCPFileManager(SCPRP.getInstace(), SCPRP.getInstace().getDataFolder() + File.separator, "jailed-players", true, true);
	
	public JailedPlayersFile(SCPRP main) {
		this.main = main;		
	}
	
	public static SCPFileManager getJailedPlayersFile() {
		return jailedPlayersFile;
	}
}
