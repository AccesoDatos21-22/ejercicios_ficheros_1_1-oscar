# EjerciciosFicheros1.1 , de Oscar Gonzalez Strozzi
Podemos encontrar los ejercicios, enumerados por orden, con una breve explicacion de su funcionamiento.
Ademas, tenemos al principio un Main para poder probar dichos ejercicios.
## Main.java
#### En la clase <b>*MAIN*</b> se ejecutaran todos los ejercicios, usando sus respectivos métodos.


```javascript
public class Main {
		public static void main(String[]args) {
			Ejercicios ejercicios = new Ejercicios();
			
			
			System.out.println("------------ Ejercicio 1 ------------\n");
			String[]lista = ejercicios.listarDirectorio(args[0);
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

## Ejercicio 1
```javascript
	public String[] listarDirectorio(String directorio) {
		File file = new File(directorio);
		if(file.list().length==0) return null;
		return file.list();

	}
```

## Metodo _borrarCarpetas()_ 
#### Este metodo se crea para poder ejecutar el ejercicio 2 y 3, sin tener conflicto entre ellos. 
#### Lo que hace este metodo es borrar todo contenido del directorio _NUEVODIR_, en caso de que existiese.
```javascript
	public void borrarCarpetas() {

		Path file = Paths.get("NUEVODIR");

		try(DirectoryStream<Path> stream = Files.newDirectoryStream(file)){
			
			for(Path a: stream) {
				Files.delete(a);
				
			}
			Files.deleteIfExists(file);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
```
## Ejercicio 2
```javascript
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

```
## Ejercicio 3
```javascript
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

```
## Ejercicio 4
```javascript
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

```
## Ejercicio 5
```javascript
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

## Ejercicio 6
|  Método invocado 	|   Valor devuelto en GNU/Linux	|   Valor devuelto en Windows	|   Descripción del método	|
| ---	| ---	| ---	| ---	| 
|   _toString_ 	|   	|   C:\Usuarios\pepe\fotos	|   Te muestra la ruta del archivo/carpeta en un string	|
|   _getFileName_	|   	|   fotos	|   Te muestra el nombre del archivo seleccionado	|
|   _getName(0)_	|   	|   Usuarios	|   Devuelve el nombre del primer elemento de la ruta	|
|   _getNameCount_ |	|  3 	|  DEvuelve el numero de elementos por los que pasa hasta llegar a la ruta 	|   	
|   _subpath(0,2)_ |	|   Usuarios\pepe	|   crea una sub Path 	|   
|   _getParent_ |   	|   C:\Usuarios\pepe	|   devuelve la carpeta padre del archivo	|
|   _getRoot_ |   	|   C:\	|   devuelve el Root del archivo o Null	|

