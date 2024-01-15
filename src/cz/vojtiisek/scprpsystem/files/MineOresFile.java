package cz.vojtiisek.scprpsystem.files;

import java.io.File;

import cz.vojtiisek.scprpsystem.SCPRP;

public class MineOresFile {
	private static SCPRP main;
	private static SCPFileManager mineOresFile = new SCPFileManager(SCPRP.getInstace(), SCPRP.getInstace().getDataFolder() + File.separator, "mine-ores", true, true);
	
	public MineOresFile(SCPRP main) {
		this.main = main;		
	}
	
	public static SCPFileManager getMineOresFile() {
		return mineOresFile;
	}
}