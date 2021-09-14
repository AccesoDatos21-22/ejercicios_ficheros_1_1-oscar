# EjerciciosFicheros1.1 , de Oscar Gonzalez Strozzi
### Incompleto
## Main.java
#### En la clase <b>*MAIN*</b> se ejecutaran todos los ejercicios, usando sus respectivos métodos.
#### Hay que aclarar que el *ejercicio 3* no se ejecutara si el *ejercicio 2* ha sido completado con exito, ya que dara error en un directorio ya creado, por lo cual, no puede crearlo.

```javascript
	//Abrir Windows Explorer para verificar que ha funcionado correctamente
package org.madrid.ad.ut01.ficheros;

public class Main {
		public static void main(String[]args) {
			Ejercicios ejercicios = new Ejercicios();
			
			
			System.out.println("------------ Ejercicio 1 ------------\n");
			String[]lista = ejercicios.listarDirectorio("F:\\Programacion\\Nueva carpeta");
			if(lista==null) System.out.println("El directorio esta vacio");
			else for(int i=0;i<lista.length;i++) System.out.println(lista[i]);
			
			
			System.out.println("\n------------ Ejercicio 2 ------------\n");
			ejercicios.crearFicherosIO();
			
		
			System.out.println("\n------------ Ejercicio 3 ------------\n");
			ejercicios.crearFicherosNIO();
		
			System.out.println("\n------------ Ejercicio 4 ------------\n");
			ejercicios.infoFicheroIO("C:\\Users\\triss\\Downloads\\a.png");
			
		
			System.out.println("\n------------ Ejercicio 5 ------------\n");
			ejercicios.infoFicheroNIO("C:\\Users\\triss\\Downloads\\a.png");
		}
}

```

## Ejercicios.java
```javascript
package org.madrid.ad.ut01.ficheros;

import java.io.IOException;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 *  
 * @author Oscar
 * @date 14-09-21
 * @version 0.1
 * @license GPLv3
 *
 */
public class Ejercicios implements InterfazEjercicios {

	@Override
	public String[] listarDirectorio(String directorio) {
		File file = new File(directorio);
		if(file.list().length==0) return null;
		return file.list();

	}

	@Override
	public void crearFicherosIO() {
		File directorio = new File("NUEVODIR");
		directorio.mkdir();
		try {
			File file1 =new File(directorio, "FICHERO1.txt");
			File file2 =new File(directorio, "FICHERO2.txt");
			File file3 =new File(directorio, "FICHERO3.txt");
			
		file1.createNewFile();
		file2.createNewFile();
		file1.renameTo(new File(directorio, "FICHERO1NUEVO.txt"));
		file2.delete();
		file3.createNewFile();
		
		//Abrir Windows Explorer para verificar que ha funcionado correctamente
		Runtime.getRuntime().exec("explorer.exe /select," +directorio.getAbsolutePath());
		System.out.println("Archivos creados y modificados correctamente.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void crearFicherosNIO() {
		Path pathCarpeta = Paths.get("NUEVODIR");
		Path pathArchivo1 = Paths.get("FICHERO1.TXT");
		Path pathArchivo1_1 = Paths.get("NUEVODIR/FICHERO1NUEVO.TXT");
		Path pathArchivo2 = Paths.get("NUEVODIR/FICHERO2.TXT");
		Path pathArchivo3 = Paths.get("NUEVODIR/FICHERO3.TXT");
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
		System.out.println("Nombre: "+ f1.getName());
		System.out.println("Carpeta padre: "+ f1.getParent());
		System.out.println("Path: "+ f1.getPath());
		System.out.println("Path Absoluta: "+ f1.getAbsolutePath());
		System.out.println("Espacio Total: "+ f1.getTotalSpace());
		System.out.println("Espacio libre: "+ f1.getFreeSpace());
		System.out.println("Espacio Usable: "+ f1.getUsableSpace());
	}

	@Override
	public void infoFicheroNIO(String fichero) {
		Path path = Paths.get(fichero);
		System.out.println("Nombre: "+path.getFileName());
		System.out.println("Ruta: "+path.toString());
		System.out.println("Padre: "+path.getParent());
		System.out.println("Raiz: "+path.getRoot());
		System.out.println("Elementos : "+path.getNameCount());
	}



}

```
