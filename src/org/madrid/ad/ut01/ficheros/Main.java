package org.madrid.ad.ut01.ficheros;

public class Main {
		public static void main(String[]args) {
			Ejercicios ejercicios = new Ejercicios();
			
			
			System.out.println("------------ Ejercicio 1 ------------\n");
			String[]lista = ejercicios.listarDirectorio(args[0]);
			if(lista==null) System.out.println("El directorio esta vacio");
			else for(int i=0;i<lista.length;i++) System.out.println(lista[i]);
			
			
			ejercicios.borrarCarpetas();
			System.out.println("\n------------ Ejercicio 2 ------------\n");
			ejercicios.crearFicherosIO();
			
			ejercicios.borrarCarpetas();
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
			
		}
}
