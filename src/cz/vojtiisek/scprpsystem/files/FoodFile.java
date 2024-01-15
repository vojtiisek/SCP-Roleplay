package cz.vojtiisek.scprpsystem.files;

import java.io.File;

import cz.vojtiisek.scprpsystem.SCPRP;

public class FoodFile {
	private static SCPRP main;
	private static SCPFileManager foodFile = new SCPFileManager(SCPRP.getInstace(), SCPRP.getInstace().getDataFolder() + File.separator, "food-machines", true, true);
	
	public FoodFile(SCPRP main) {
		this.main = main;		
	}
	
	public static SCPFileManager getFoodFile() {
		return foodFile;
	}
}