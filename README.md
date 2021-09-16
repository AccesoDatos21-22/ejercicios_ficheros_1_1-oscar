# EjerciciosFicheros1.1 , de Oscar Gonzalez Strozzi
Podemos encontrar los ejercicios, enumerados por orden, con una breve explicacion de su funcionamiento.  
Ademas, tenemos al principio un Main para poder probar dichos ejercicios.  
### Puedes econtrar el archivo de las actividades <a href="https://docs.google.com/document/d/1S2EpQpp3fqFGomqee0weC8Ub5bsevZ_VAzoEUYSCYCw/edit" target="_blank">aqui</a>.



## Main.java
En la clase <b>*Main*</b> se ejecutaran todos los ejercicios, usando sus respectivos métodos.<br />



```javascript
public class Main {
		public static void main(String[]args) {
			Ejercicios ejercicios = new Ejercicios();
			
			
			System.out.println("------------ Ejercicio 1 ------------\n");
			String[]lista = ejercicios.listarDirectorio(args[0]);
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
			
			System.out.println("\n------------ Ejercicio 6 ------------\n");
			InterfazEjercicios.pruebasRutas01("Usuarios\\Pepe"); 
			 			
			System.out.println("\n------------ Ejercicio 7 ------------\n");
			InterfazEjercicios.pruebasRutas02("\\user\\.\\pepe\\fotos"); 

			System.out.println("\n------------ Ejercicio 8 ------------\n");
			InterfazEjercicios.pruebasRutas03(args); 
			
			System.out.println("\n------------ Ejercicio 9 ------------\n");
			InterfazEjercicios.pruebasRutas04(); 
			
			System.out.println("\n------------ Ejercicio 10 ------------\n");
			InterfazEjercicios.pruebasRutas05("Usuarios\\juan\\fotos","C:\\Usuarios\\juan\\fotos","Usuarios","fotos"); 
			
		}
}
```

- ## Ejercicio 1
Creamos un archivo File con el directorio pasado por parametro. Verificamos si existe contenido en la carpeta, y en caso contrario devolvemos _null_.    
Si existen ficheros, el metodo devuelve la lista en un *array[]* usando _.list()_.  
```javascript
	public String[] listarDirectorio(String directorio) {
		File file = new File(directorio);
		if(file.list().length==0) return null;
		return file.list();

	}
```
### Pruebas
Este ejercicio esta testeado. En caso de no tener ningun contenido dentro del directorio, el programa devolvería null, y en consola te avisaria.

- ## Metodo _borrarCarpetas()_ 
Este metodo se crea para poder ejecutar el ejercicio 2 y 3, sin tener conflicto entre ellos.  
Lo que hace este metodo es borrar todo contenido del directorio _NUEVODIR_ y la misma carpeta, en caso de que existiese.  
```javascript
	public void borrarCarpetas() {
		
		Path file = Paths.get("NUEVODIR");

		try(DirectoryStream<Path> stream = Files.newDirectoryStream(file)){
			
			for(Path a: stream) Files.delete(a);
			Files.deleteIfExists(file);

		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
```
- ## Ejercicio 2
Para crear ficheros usando el paquete *IO*, usamos la clase _File_.  
Primero debemos crear el directorio. Esto lo hacemos creando un objeto File (_al que llamaremos directorio_), y crearemos dicha carpeta con el comando _.mkdir()_.  
  
Para crear el archivo 1 y 2 hacemos el mismo proceso, solo que colocando la nueva ruta(en nuestro caso la nueva carpeta creada), y creamos el archivo con _.createNewFile()_.

Para renombrar un archivo, debemos crear un objeto File intermedio, al que pondremos el nuevo nombre. Luego al objeto que queremos cambiar usamos _.renameTo()_ y meter dentro el nuevo objeto _File_. 

Por ultimo, borramos el segundo con el comando _.delete()_ y creamos el tercer archivo.  
```javascript
	public void crearFicherosIO() {
		borrarCarpetas();
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
### Pruebas
Este ejercicio puede fallar si la carpeta que creamos y sus archivos existen ya. Por ello, se ejecuta el metodo borrarCarpetas(), para poder ejecutarlo sin problemas.
En caso contrario, crearía ficheros extra y en casos extremos, saltar una excepción.


- ## Ejercicio 3
Para poder hacer la misma tarea que el ejercicio anterior, pero usando el paquete NIO, cambia la cosa.  
Debes importar del paquete las clases Paths, Path y Files. De momento son las unicas que usaremos.

Para crear una instancia del archivo, o en este caso, un Path debemos usar _Paths_ con el metodo _.get()_, con el nombre del archivo.  
Esto se aplica tanto a carpetas como a archivos.

Para crear un directorio se usa el metodo _.createDirectory_ de la clase *Files* ,para los archivos, usaremos _.createFile_.  

Para renombrar un archivo usaremos, tambien de la clase *Files*, el metodo _.move()_, y como primer parametro pasaremos el archivo original y de segundo el nuevo archivo.  

Es necesario meter estas lineas en un _try catch_, ya que es necesario para controlar las posibles Excepciones.  

```javascript
	public void crearFicherosNIO() {
		borrarCarpetas();
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
### Pruebas
El paquete NIO tiene mas excepciones que handlear, por lo que hay que hacer mas pruebas.
El problema de archivos ya existentes lo solucionamos igual que el ejercicio anterior (con el metodo borrarCarpetas).

#### Para el Ejercicio 2 y 3 se han aplicado las mismas pruebas y tests
Como veremos a continuacion, se crea un metodo para comprobar resultados
```javascript
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
```


- ## Ejercicio 4
En este ejercicio mostraremos la informacion sobre un archivo o directorio usando el paquete IO.
Para ello usaremos un objeto _File_ con el path del mismo.

Mostraremos atributos como:
- *.getName()*  - Devuelve el nombre del archivo
- *.getParent()* - Devuelve la carpeta Padre
- *.getPath()* - Devuelve la ruta 
- *.getAbsolutePath()* - Devuelve la ruta Absoluta
- *.getTotalSpace()* - Devuelve el espacio total
- *.getFreeSpace()* - Devuelve el espacio libre
- *.getUsableSpace()* - Devuelve el espacio usado
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
- ## Ejercicio 5
En este ejercicio mostraremos la informacion sobre un archivo o directorio, pero esta vez, con el paquete NIO.
Debemos usar Path para crear una instancia del archivo, y pasaremos atributos como:
- *.getFileName()*  - Devuelve el nombre del archivo
- *.getParent()* - Devuelve la carpeta Padre
- *.toString()* - Devuelve la ruta 
- *.getRoot()* - Devuelve el Root del archivo
- *.getNameCount()* - Devuelve el numero de carpetas de la ruta
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

- ## Ejercicio 6
Al ejecutar el codigo dado en el ejercicio 6, nos salen una serie de valores. Estos son rellenados en la siguiente tabla.

|  Método invocado 	|   Valor devuelto en GNU/Linux	|   Valor devuelto en Windows	|   Descripción del método	|
| ---	| ---	| ---	| ---	| 
|   _toString_ 	|   	|   C:\Usuarios\pepe\fotos	|   Te muestra la ruta del archivo/carpeta en un string	|
|   _getFileName_	|   	|   fotos	|   Te muestra el nombre del archivo seleccionado	|
|   _getName(0)_	|   	|   Usuarios	|   Devuelve el nombre del primer elemento de la ruta	|
|   _getNameCount_ |	|  3 	|  DEvuelve el numero de elementos por los que pasa hasta llegar a la ruta 	|   	
|   _subpath(0,2)_ |	|   Usuarios\pepe	|   crea una sub Path 	|   
|   _getParent_ |   	|   C:\Usuarios\pepe	|   devuelve la carpeta padre del archivo	|
|   _getRoot_ |   	|   C:\	|   devuelve el Root del archivo o Null	|
### Ejercicio 6b
En el caso de tener un Usuario llamado Pepe y la ruta existiese, el output seria distinto, no detectaría el Root del archivo, ni la ruta absoluta,  ya que al pasarle solo la ruta relativa, no sabe donde se ubica dicho archivo.
Este es el resultado:

- toString: Usuarios\Pepe
- getFileName: Pepe
- getName(0): Usuarios
- getNameCount: 2
- subpath(0,2): Usuarios\Pepe
- getParent: Usuarios
- getRoot: null

- ## Ejercicio 7
Para borrar datos redundantes en un path, utilizamos el metodo _.normalize()_, de la clase *Path*.

Como observamos, en la primera, la parte "_/./_" es eliminada, ya que redunda en su propia carpeta.
En el segundo caso, primero entramos a la carpeta "_/juan/_", despues salimos "_/../_", y posteriormente accedemos nuevamente a "_/pepe/fotos_". Esto hace que las primeras partes del path sean innecesarias, por lo que podemos quitarlas.

Esto es lo que hace el metodo _.normalize()_. Es muy util para arreglar Paths redundantes.
```javascript
	static void pruebasRutas02() {
		Path path1 = Paths.get("/home/./pepe/fotos");
		Path path2= Paths.get("/home/juan/../pepe/fotos");
		System.out.println("a."+ path1.toString()+" pasa a->" +path1.normalize().toString());
		System.out.println("b"+ path2.toString()+" pasa a->" +path2.normalize().toString());
	}
```
- ## Ejercicio 8
### .toURi()

Este metodo devuelve la ruta real a traves de un URI
```javascript
		Path p1 = Paths.get("entrada.txt");
		System.out.format("%s%n", "URI " + p1.toUri());
```
Salida:
>  URI _file:///F:/Programacion/Proyectos/Eclipse%20workspace/github-classroom/AccesoDatos21-22/ejercicios_ficheros_1_1-oscar/entrada.txt_
	<hr/>
### .toAbsolutePath()
Este metodo devuelve la ruta absoluta del archivo
```javascript
		if (args.length < 1) {
			System.out.println("debes pasar un nombre de archivo como argumento");
			System.exit(-1);
		}
		Path inputPath = Paths.get(args[1]);
		Path fullPath = inputPath.toAbsolutePath();
		System.out.println("Path absoluto " + fullPath);

```
Salida:
>  Path absoluto _F:\Programacion\Proyectos\Eclipse workspace\github-classroom\AccesoDatos21-22\ejercicios_ficheros_1_1-oscar\entrada.txt_
	<hr/>
### .toRealPath()
Este metodo devuelve la ruta real del archivo
```javascript
		Path p2 = Paths.get("./entrada.txt");
		try {
			Path fp = p2.toRealPath();
			System.out.println("Path real " + fp);
		} 
```
Todos estos metodos necesitan controlar las excepciones mediannte _NoSuchFileException_ y _IOException_.
```javascript
		catch (NoSuchFileException x) {
			System.err.format("%s: no existe" + " el fichero o directorio %n",p2);

		} catch (IOException x) {
			System.err.format("%s%n", x);
		}
```
Salida: 
> _Path real F:\Programacion\Proyectos\Eclipse workspace\github-classroom\AccesoDatos21-22\ejercicios_ficheros_1_1-oscar\entrada.txt_
- ## Ejercicio 9
### .resolve()
Este metodo nos ayuda a cambiar la ruta del Path, ya sea añadiendolo al final, como en el primer caso:
```javascript
		Path p0 = Paths.get("C:\\Usuarios\\pepe\\fotos");
		System.out.format("%s%n", p0.resolve("docs"));
```
Salida:
> C:\Usuarios\pepe\fotos\docs

O tambien se puede cambiar la ruta completa:
```javascript
		Path p1=Paths.get("fotos");
		System.out.format("%s%n", p1.resolve("C:\\Usuarios\\pepe"));
```
Salida:
> C:\Usuarios\pepe

### .relativize()
Este metodo nos permite hacer una ruta relativa entre las dos rutas dadas. 

Hay que aclarar, que si una de las rutas contiene el directorio Raiz (C:/), no se podra aplicar el metodo.
```javascript
		Path p2 = Paths.get("pepe");
		Path p3 = Paths.get("juan");
		Path p2_to_p3 = p2.relativize(p3);
		System.out.format("%s%n", p2_to_p3);

		Path p3_to_p2 = p3.relativize(p2);
		System.out.format("%s%n", p3_to_p2);
```
Resultado:
> ..\juan  
> ..\pepe

```javascript
		Path p4 = Paths.get("Usuarios");
		Path p5 = Paths.get("Usuarios\\juan\\docs");

		Path p4_to_p5 = p4.relativize(p5);
		System.out.format("%s%n", p4_to_p5);

		Path p5_to_p4 = p5.relativize(p4);
		System.out.format("%s%n",p5_to_p4);
```
Resultado:
> juan\docs  
> ..\\..
- ## Ejercicio 10
### .equals()
Este metodo te permite comparar 2 Path para ver si la ruta es igual.

Pasas como argumento el segundo path a comparar. 
> Devuelve *True* en caso de que se cumpla. En caso contrario, devuelve *False*.
### .startsWith()
Este metodo te permite comparar 2 Path para ver si las rutas empiezan igual.

Se pasa como tercer argumento el path que contiene el trozo de ruta inicial que quieres comparar.
> Devuelve *True* en caso de que se cumpla. En caso contrario, devuelve *False*.

### .endsWith()
Este metodo te permite comparar 2 Path para ver si las rutas acaban igual.

Se pasa como tercer argumento el path que contiene el trozo de ruta final que quieres comparar.
> Devuelve *True* en caso de que se cumpla. En caso contrario, devuelve *False*.

#### Ejemplo
```javascript
	public static void pruebasRutas05(String ruta1, String ruta2, String comienzo, String fin) {
		Path path1 = Paths.get(ruta1);
		Path path2 = Paths.get(ruta2);
		if(path1.equals(path2)) {
			System.out.println("Ruta 1 y 2 son iguales");
		}
		if (path1.startsWith(comienzo) && path2.startsWith(comienzo)) {
			System.out.println("Ruta 1 y 2 comienzan por: " + comienzo);
		
		} 
		if(path1.endsWith(fin) && path2.endsWith(fin)){
			System.out.println("Ruta 1 y 2 finalizan por: " + fin);
		
		}
		else System.out.println("Las rutas dadas no empiezan ni acaban igual.");
	}
```
#### Ejemplo 2
```javascript
	public static void pruebasRutas05(String ruta1, String ruta2, String comienzo) {
		Path path1 = Paths.get(ruta1);
		Path path2 = Paths.get(ruta2);
		if (path1.startsWith(comienzo) && path2.startsWith(comienzo)) {
			System.out.println("Ruta 1 y 2 comienzan por: " + comienzo);
		} else
			System.out.println("Las rutas dadas no empiezan igual.");

	}
```

