package cz.vojtiisek.scprpsystem.files;

import java.io.File;

import cz.vojtiisek.scprpsystem.SCPRP;

public class BlockDataFile {
	private static SCPRP main;
	private static SCPFileManager blockData = new SCPFileManager(SCPRP.getInstace(), SCPRP.getInstace().getDataFolder() + File.separator, "blocks-data", true, true);
	
	public BlockDataFile(SCPRP main) {
		this.main = main;		
	}
	
	public static SCPFileManager getBlockDataFile() {
		return blockData;
	}
}