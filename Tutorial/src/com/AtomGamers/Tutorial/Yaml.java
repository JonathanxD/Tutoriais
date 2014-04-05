package com.AtomGamers.Tutorial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class Yaml extends YamlConfiguration{
	@Override
	public void save(File file){
		try {
			super.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void load(File file){
		try {
			if(!file.exists())file.createNewFile();
			super.load(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
}
