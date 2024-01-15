package cz.vojtiisek.scprpsystem.files;

import java.io.File;

import cz.vojtiisek.scprpsystem.SCPRP;

public class ClassDDesigFile {
	private static SCPRP main;
	private static SCPFileManager classDDesigFile = new SCPFileManager(SCPRP.getInstace(), SCPRP.getInstace().getDataFolder() + File.separator, "class-d-designations", true, true);
	
	public ClassDDesigFile(SCPRP main) {
		this.main = main;		
	}
	
	public static SCPFileManager getClassDDesigFile() {
		return classDDesigFile;
	}
}