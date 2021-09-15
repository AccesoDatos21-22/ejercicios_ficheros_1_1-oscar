package org.madrid.ad.ut01.ficheros;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 
 * @author Oscar Gonzalez Strozzi
 * @date
 * @version
 * @license GPLv3
 *
 */

public interface InterfazEjercicios {

	/**
	 * 
	 * @param directorio
	 * @return contenido de la ruta recibido
	 */

	public void borrarCarpetas();

	public String[] listarDirectorio(String directorio);

	/**
	 * crea un directorio llamado NUEVODIR en el directorio actual a continuación
	 * que cree dos ficheros vacíos llamados FICHERO1.TXT y FICHERO2.TXT. Se
	 * renombrará el FICHERO1.TXT por FICHERO1NUEVO. Se creará un nuevo fichero
	 * dentro de NUEVODIR llamado FICHERO3.TXT. Por último, se borrará el fichero
	 * FICHERO2.TXT
	 * 
	 */
	public void crearFicherosIO();

	/**
	 * realice la misma función que el método listarDirectorio pero utilizando las
	 * clases Path y Files de java.nio.
	 */
	public void crearFicherosNIO();

	/**
	 * imprima en la consola toda información sobre un fichero recibido por un
	 * parámetro de tipo String con la clase java.io.File.
	 * 
	 * @param fichero
	 */
	public void infoFicheroIO(String fichero);

	/**
	 * imprima en la consola toda información sobre un fichero recibido por un
	 * parámetro de tipo String utilizando las clases Path y Files de java.nio
	 * 
	 * @param fichero
	 */
	public void infoFicheroNIO(String fichero);

	/**
	 * 
	 * @param fichero
	 */
	static void pruebasRutas01(String fichero) {
		Path path = Paths.get(fichero);

		System.out.format("toString: %s%n", path.toString());
		System.out.format("getFileName: %s%n", path.getFileName());
		System.out.format("getName(0): %s%n", path.getName(0));
		System.out.format("getNameCount: %d%n", path.getNameCount());
		System.out.format("subpath(0,2): %s%n", path.subpath(0, 2));
		System.out.format("getParent: %s%n", path.getParent());
		System.out.format("getRoot: %s%n", path.getRoot());
	}

	static void pruebasRutas02(String fichero) {
		Path path1 = Paths.get("/home/./pepe/fotos");
		Path path2 = Paths.get("/home/juan/../pepe/fotos");
		System.out.println("a." + path1.toString() + " pasa a->" + path1.normalize().toString());
		System.out.println("b" + path2.toString() + " pasa a->" + path2.normalize().toString());
	}

	/*
	 * Para ejecutar Botón derecho --> Run as --> Run Configurations --> En
	 * argumentos escribir entrada.txt. Es necesario para probar toAbsolutePath
	 */
	public static void pruebasRutas03(String[] args) {

		// Primer método toUri
		Path p1 = Paths.get("entrada.txt");

		System.out.format("%s%n", "URI " + p1.toUri());

		// -------------------------------------------------------------------------------

		// Segundo método toAbsolutePath

		if (args.length < 1) {
			System.out.println("debes pasar un nombre de archivo como argumento");
			System.exit(-1);
		}

		Path inputPath = Paths.get(args[1]);
		Path fullPath = inputPath.toAbsolutePath();

		System.out.println("Path absoluto " + fullPath);

		// -------------------------------------------------------------------------------

		// Tercer método toRealPath

		Path p2 = Paths.get("./entrada.txt");
		try {

			Path fp = p2.toRealPath();

			System.out.println("Path real " + fp);
		} catch (NoSuchFileException x) {

			System.err.format("%s: no existe" + " el fichero o directorio %n", p2);

		} catch (IOException x) {

			System.err.format("%s%n", x);

		}

	}

	public static void pruebasRutas04() {

		Path p0 = Paths.get("C:\\Usuarios\\pepe\\fotos");

		System.out.format("%s%n", p0.resolve("docs"));

		Path p1 = Paths.get("fotos");

		System.out.format("%s%n", p1.resolve("C:\\Usuarios\\pepe"));
		// ------------------------------------------------------------------

		Path p2 = Paths.get("pepe");
		Path p3 = Paths.get("juan");

		Path p2_to_p3 = p2.relativize(p3);
		System.out.format("%s%n", p2_to_p3);

		Path p3_to_p2 = p3.relativize(p2);
		System.out.format("%s%n", p3_to_p2);

		Path p4 = Paths.get("Usuarios");
		Path p5 = Paths.get("Usuarios\\juan\\docs");

		Path p4_to_p5 = p4.relativize(p5);
		System.out.format("%s%n", p4_to_p5);

		Path p5_to_p4 = p5.relativize(p4);
		System.out.format("%s%n", p5_to_p4);

	}

	public static void pruebasRutas05(String ruta1, String ruta2) {

	}

	public static void pruebasRutas05(String ruta1, String ruta2, String comienzo) {
		Path path1 = Paths.get(ruta1);
		Path path2 = Paths.get(ruta2);
		Path inicio = Paths.get(comienzo);
		if (path1.startsWith(comienzo) && path2.startsWith(comienzo)) {
			System.out.println("Ruta 1 y 2 comienzan por: " + comienzo.toString());
		} else
			System.out.println("Las rutas dadas no empiezan igual.");

	}

	public static void pruebasRutas05(String ruta1, String ruta2, String comienzo, String fin) {
		Path path1 = Paths.get(ruta1);
		Path path2 = Paths.get(ruta2);
		Path inicio = Paths.get(comienzo);
		Path pathFin = Paths.get(fin);
		if(path1.equals(path2)) {
			System.out.println("Ruta 1 y 2 comienzan por: " + comienzo.toString()+", y acaban por: "+ fin.toString());
		}
		if (path1.startsWith(comienzo) && path2.startsWith(comienzo)) {
			System.out.println("Ruta 1 y 2 comienzan por: " + comienzo.toString());
		
		} 
		if(path1.endsWith(fin) && path2.endsWith(fin)){
			System.out.println("Ruta 1 y 2 finalizan por: " + fin.toString());
		
		}
		else System.out.println("Las rutas dadas no empiezan ni acaban igual.");
	}

}
