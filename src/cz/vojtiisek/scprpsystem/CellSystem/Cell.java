package cz.vojtiisek.scprpsystem.CellSystem;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import cz.vojtiisek.scprpsystem.SCPRP;

public class Cell { 
	private static SCPRP main;
	private String xPos;
	private String yPos;
	private String zPos;
	private String world;
	private String owner;
	private String level;
	private String designation;
	
	public Cell(SCPRP main, String xPos, String yPos, String zPos, String world, String owner, String level, String designation) {
		this.main = main;
		this.xPos = xPos;
		this.yPos = yPos;
		this.zPos = zPos;
		this.world = world;
		this.owner = owner;
		this.level = level;
		this.designation = designation;
	}
	
	public String getX() {
		return this.xPos;
	}
	
	public String getY() {
		return this.yPos;
	}
	
	public String getZ() {
		return this.zPos;
	}
	
	public String getWorld() {
		return this.world;
	}
	
	public String getOwner() {
		return this.owner;
	}
	
	public void setOwner(String str) {
		this.owner = str;
	}
	
	public String getDesignation() {
		return this.designation;
	}
	
	public String getLevel() {
		return this.level;
	}
	
	public Location getLocation() {
		Location loc = new Location(Bukkit.getWorld(this.world), Integer.parseInt(this.xPos), Integer.parseInt(this.yPos), Integer.parseInt(this.zPos));
		return loc;
	}
}