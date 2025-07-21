package com.alura.Literalura_Alura_Edwin;

import com.alura.Literalura_Alura_Edwin.servicio.LibroService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class LiteraluraAluraEdwinApplication implements CommandLineRunner {

	private final LibroService libroService;

	public LiteraluraAluraEdwinApplication(LibroService libroService) {
		this.libroService = libroService;
	}

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraAluraEdwinApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Scanner scanner = new Scanner(System.in);
		int opcion;

		do {
			System.out.println("\n===== Menú LITERALURA =====");
			System.out.println("1. Buscar libro por título (API y guardar en base)");
			System.out.println("2. Listar todos los libros registrados");
			System.out.println("3. Listar todos los autores registrados");
			System.out.println("4. Listar autores vivos en un año específico");
			System.out.println("5. Listar libros por idioma");
			System.out.println("6. Mostrar cantidad de libros por idioma");
			System.out.println("7. Ver estadísticas de descargas");
			System.out.println("8. Ver TOP 10 libros más descargados");
			System.out.println("9. Buscar autor por nombre");
			System.out.println("10. Listar autores nacidos entre dos años");
			System.out.println("0. Salir");
			System.out.print("Elige una opción: ");

			// Protección de entrada numérica
			while (!scanner.hasNextInt()) {
				System.out.print("Por favor, ingresa un número de opción: ");
				scanner.next();
			}
			opcion = scanner.nextInt();
			scanner.nextLine();

			switch (opcion) {
				case 1:
					System.out.print("Introduce el título del libro a buscar: ");
					String titulo = scanner.nextLine();
					String resultado = libroService.buscarYGuardarLibro(titulo);
					System.out.println(resultado);
					break;
				case 2:
					libroService.mostrarTodosLosLibros();
					break;
				case 3:
					libroService.mostrarTodosLosAutores();
					break;
				case 4:
					System.out.print("Introduce el año a consultar: ");
					while (!scanner.hasNextInt()) {
						System.out.print("Por favor, ingresa un año válido: ");
						scanner.next();
					}
					int anio = scanner.nextInt();
					scanner.nextLine();
					libroService.mostrarAutoresVivosEnAnio(anio);
					break;
				case 5:
					System.out.print("Introduce el idioma a consultar (ej: en, es): ");
					String idiomaLibro = scanner.nextLine();
					libroService.mostrarLibrosPorIdioma(idiomaLibro);
					break;
				case 6:
					libroService.mostrarEstadisticasPorIdioma();
					break;
				case 7:
					libroService.mostrarEstadisticasDescargas();
					break;
				case 8:
					libroService.mostrarTop10MasDescargados();
					break;
				case 9:
					System.out.print("Introduce el nombre del autor: ");
					String nombreBuscar = scanner.nextLine();
					libroService.buscarAutorPorNombre(nombreBuscar);
					break;
				case 10:
					System.out.print("Año inicial: ");
					int desde = scanner.nextInt();
					System.out.print("Año final: ");
					int hasta = scanner.nextInt();
					scanner.nextLine();
					libroService.mostrarAutoresNacidosEntre(desde, hasta);
					break;
				case 0:
					System.out.println("¡Hasta luego!");
					break;
				default:
					System.out.println("Opción no válida. Intenta de nuevo.");
			}
		} while (opcion != 0);
	}
}
