package cz.vojtiisek.scprpsystem.files;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import cz.vojtiisek.scprpsystem.SCPRP;

public class SCPFileManager {
	private static SCPRP main;
	
	  protected final boolean createIfNotExist, resource;

	  protected FileConfiguration config;
	  protected File file, path;
	  protected String name;
	 
	
	public SCPFileManager(SCPRP main, File path, String name, boolean createIfNotExist, boolean resource) {
		this.resource = resource;
		this.path = path;
		this.name = name + ".yml";
		this.createIfNotExist = createIfNotExist;
		this.main = main;
		create();
	}
	

	public SCPFileManager(SCPRP main, String path, String name, boolean createIfNotExist, boolean resource) {
		this(main, new File(path), name, createIfNotExist, resource);
	}
	
	public FileConfiguration getConfig() {
		return config;
	}
	
	public void save() {
		try {
			config.save(file);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public File reloadFile() {
		file = new File(path, name);
		return file;
	}
	
	public FileConfiguration reloadConfig() {
		config = YamlConfiguration.loadConfiguration(file);
		return config;
	}
	
	public void reload() {
		reloadFile();
		reloadConfig();
	}
	
	public void create() {
		if(file == null) reloadFile();
		if(!createIfNotExist || file.exists()) return;
		
		file.getParentFile().mkdirs();
		
		if(resource) {
			main.saveResource(name, false);
		} else {
			try {
				file.createNewFile();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(config == null) reloadConfig();
	}
}