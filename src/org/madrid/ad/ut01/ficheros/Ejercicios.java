package org.madrid.ad.ut01.ficheros;

import java.io.IOException;

import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 
 * @author Escribe_aqui_tu_nombre
 * @date
 * @version
 * @license GPLv3
 *
 */
public class Ejercicios implements InterfazEjercicios {

	@Override
	public String[] listarDirectorio(String directorio) {
		File file = new File(directorio);
		if (file.list().length == 0) return null;
		return file.list();

	}

	@Override
	public void crearFicherosIO() {
		borrarCarpetas();
		File directorio = new File("NUEVODIR");
		directorio.mkdir();
		try {
			File file1 = new File(directorio, "FICHERO1.txt");
			File file2 = new File(directorio, "FICHERO2.txt");
			File file3 = new File(directorio, "FICHERO3.txt");

			file1.createNewFile();
			file2.createNewFile();
			file1.renameTo(new File(directorio, "FICHERO1NUEVO.txt"));
			file2.delete();
			file3.createNewFile();

			// Abrir Windows Explorer para verificar que ha funcionado correctamente
			Runtime.getRuntime().exec("explorer.exe /select," + directorio.getAbsolutePath());
			System.out.println("Archivos creados y modificados correctamente.");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void crearFicherosNIO() {
		borrarCarpetas();
		Path pathCarpeta = Paths.get("NUEVODIR");
		Path pathArchivo1 = pathCarpeta.resolve("FICHERO1.TXT");
		Path pathArchivo1_1 = pathCarpeta.resolve("FICHERO1NUEVO.TXT");
		Path pathArchivo2 = pathCarpeta.resolve("FICHERO2.TXT");
		Path pathArchivo3 = pathCarpeta.resolve("FICHERO3.TXT");
		try {
			Files.createDirectory(pathCarpeta);
			Files.createFile(pathArchivo1);
			Files.createFile(pathArchivo2);
			Files.move(pathArchivo1, pathArchivo1_1);
			Files.delete(pathArchivo2);
			Files.createFile(pathArchivo3);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void infoFicheroIO(String fichero) {
		File f1 = new File(fichero);
		System.out.println("Nombre: " + f1.getName());
		System.out.println("Carpeta padre: " + f1.getParent());
		System.out.println("Path: " + f1.getPath());
		System.out.println("Path Absoluta: " + f1.getAbsolutePath());
		System.out.println("Espacio Total: " + f1.getTotalSpace());
		System.out.println("Espacio libre: " + f1.getFreeSpace());
		System.out.println("Espacio Usable: " + f1.getUsableSpace());
	}

	@Override
	public void infoFicheroNIO(String fichero) {
		Path path = Paths.get(fichero);
		System.out.println("Nombre: " + path.getFileName());
		System.out.println("Ruta: " + path.toString());
		System.out.println("Padre: " + path.getParent());
		System.out.println("Raiz: " + path.getRoot());
		System.out.println("Elementos : " + path.getNameCount());
	}

	@Override
	public void borrarCarpetas() {

		Path file = Paths.get("NUEVODIR");

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(file)) {
			for (Path a : stream) Files.delete(a);
			Files.deleteIfExists(file);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}


}
