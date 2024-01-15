package cz.vojtiisek.scprpsystem.files;

import java.io.File;

import cz.vojtiisek.scprpsystem.SCPRP;

public class PlayerInfoFile {
	private static SCPRP main;
	private static SCPFileManager playerInfoFile = new SCPFileManager(SCPRP.getInstace(), SCPRP.getInstace().getDataFolder() + File.separator, "player-data", true, true);
	
	public PlayerInfoFile(SCPRP main) {
		this.main = main;		
	}
	
	public static SCPFileManager getPlayerInfoFile() {
		return playerInfoFile;
	}
}