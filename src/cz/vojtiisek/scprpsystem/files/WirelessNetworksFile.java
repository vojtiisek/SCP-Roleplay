package cz.vojtiisek.scprpsystem.files;

import java.io.File;

import cz.vojtiisek.scprpsystem.SCPRP;

public class WirelessNetworksFile {
	private static SCPRP main;
	private static SCPFileManager wirelessNetworksFile = new SCPFileManager(SCPRP.getInstace(), SCPRP.getInstace().getDataFolder() + File.separator, "wireless-networks", true, true);
	
	public WirelessNetworksFile(SCPRP main) {
		this.main = main;		
	}
	
	public static SCPFileManager getWirelessNetworksFile() {
		return wirelessNetworksFile;
	}
}
