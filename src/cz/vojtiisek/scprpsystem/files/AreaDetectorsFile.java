package cz.vojtiisek.scprpsystem.files;

import java.io.File;

import cz.vojtiisek.scprpsystem.SCPRP;

public class AreaDetectorsFile {
	private static SCPRP main;
	private static SCPFileManager areaDetectorsFile = new SCPFileManager(SCPRP.getInstace(),
			SCPRP.getInstace().getDataFolder() + File.separator, "area-detectors", true, true);

	public AreaDetectorsFile(SCPRP main) {
			this.main = main;		
		}

	public static SCPFileManager getAreaDetectorsFile() {
		return areaDetectorsFile;
	}
}