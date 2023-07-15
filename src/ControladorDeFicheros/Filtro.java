package ControladorDeFicheros;

import java.io.File;
import java.io.FilenameFilter;

public class Filtro implements FilenameFilter {

	private String extension;

	public Filtro(String extension)
	{
		this.extension = extension;
	}
	
	public boolean accept(File dir, String name) 
	{
		return name.endsWith(extension);
	}

}
