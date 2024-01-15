package cz.vojtiisek.scprpsystem.files;

import java.io.File;

import cz.vojtiisek.scprpsystem.SCPRP;

public class CellsFile {
	private static SCPRP main;
	private static SCPFileManager cellsFile = new SCPFileManager(SCPRP.getInstace(), SCPRP.getInstace().getDataFolder() + File.separator, "cells", true, true);
	
	public CellsFile(SCPRP main) {
		this.main = main;		
	}
	
	public static SCPFileManager getCellsFile() {
		return cellsFile;
	}
}