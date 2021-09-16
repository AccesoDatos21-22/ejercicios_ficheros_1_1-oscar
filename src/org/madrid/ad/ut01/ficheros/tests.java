package org.madrid.ad.ut01.ficheros;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class tests {
	public  static void probarCarpetasBorradas(){
		Ejercicios ej =new Ejercicios();
		Path dir = Paths.get("NUEVODIR");
		if(Files.exists(dir)) System.out.println("El archivo existe> "+ dir.getFileName());
		if(ej.listarDirectorio(dir.toString())==null) System.out.println("El contenido esta vacio en la carpeta > "+ dir.getFileName());
		if(Files.exists(dir.resolve("FICHERO1.TXT"))) System.out.println("Fichero1 existe");
		if(Files.exists(dir.resolve("FICHERO2.TXT"))) System.out.println("Fichero2 existe");
		if(Files.exists(dir.resolve("FICHERO3.TXT"))) System.out.println("Fichero3 existe");
		if(Files.exists(dir.resolve("FICHERO1NUEVO.TXT"))) System.out.println("Fichero1NUEVO existe");
		else System.out.println("Ningun archivo existe");
	}
}
