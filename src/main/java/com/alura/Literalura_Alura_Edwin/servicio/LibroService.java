package com.alura.Literalura_Alura_Edwin.servicio;

import com.alura.Literalura_Alura_Edwin.cliente.GutendexCliente;
import com.alura.Literalura_Alura_Edwin.dto.AutorDTO;
import com.alura.Literalura_Alura_Edwin.dto.LibroDTO;
import com.alura.Literalura_Alura_Edwin.modelo.Autor;
import com.alura.Literalura_Alura_Edwin.modelo.Libro;
import com.alura.Literalura_Alura_Edwin.repositorio.AutorRepository;
import com.alura.Literalura_Alura_Edwin.repositorio.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibroService {

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    public LibroService(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    // 1. Buscar libro por título en la API y guardar
    public String buscarYGuardarLibro(String titulo) {
        GutendexCliente cliente = new GutendexCliente();
        Optional<LibroDTO> libroOpt = cliente.buscarLibroPorTitulo(titulo);

        if (libroOpt.isEmpty()) {
            return "❌ Libro no encontrado en la API.";
        }

        LibroDTO libroDTO = libroOpt.get();
        if (libroRepository.existsByTitulo(libroDTO.getTitle())) {
            return "⚠️ Ese libro ya existe en la base de datos.";
        }

        AutorDTO autorDTO = (libroDTO.getAuthors() != null && !libroDTO.getAuthors().isEmpty()) ? libroDTO.getAuthors().get(0) : null;
        Autor autor = null;

        if (autorDTO != null) {
            autor = autorRepository.findByNombreIgnoreCase(autorDTO.getName())
                    .orElseGet(() -> {
                        Autor nuevo = new Autor();
                        nuevo.setNombre(autorDTO.getName());
                        nuevo.setAnoNacimiento(autorDTO.getAnoNacimiento());
                        nuevo.setAnoMuerte(autorDTO.getAnoMuerte());
                        return autorRepository.save(nuevo);
                    });
        }

        Libro libro = new Libro();
        libro.setTitulo(libroDTO.getTitle());
        libro.setIdioma(libroDTO.getIdiomas() != null && !libroDTO.getIdiomas().isEmpty()
                ? libroDTO.getIdiomas().get(0) : "desconocido");
        libro.setDescargas(libroDTO.getDescargas());
        libro.setAutor(autor);

        libroRepository.save(libro);
        return "✅ Libro y autor guardados exitosamente.";
    }

    // 2. Listar todos los libros registrados (presentación mejorada)
    public void mostrarTodosLosLibros() {
        List<Libro> libros = libroRepository.findAll();
        if (libros.isEmpty()) {
            System.out.println("📚 No hay libros registrados en el catálogo.");
            return;
        }
        System.out.println("\n=========== LIBROS REGISTRADOS ===========");
        int contador = 1;
        for (Libro libro : libros) {
            System.out.println("Libro #" + contador++);
            System.out.println("  Título    : " + libro.getTitulo());
            System.out.println("  Idioma    : " + libro.getIdioma());
            System.out.println("  Descargas : " + libro.getDescargas());
            if (libro.getAutor() != null) {
                System.out.println("  Autor     : " + libro.getAutor().getNombre());
                System.out.println("  Nacimiento: " + (libro.getAutor().getAnoNacimiento() != null ? libro.getAutor().getAnoNacimiento() : "Desconocido"));
                System.out.println("  Fallecimiento: " + (libro.getAutor().getAnoMuerte() != null ? libro.getAutor().getAnoMuerte() : "Desconocido"));
            } else {
                System.out.println("  Autor     : Desconocido");
            }
            System.out.println("-------------------------------------------");
        }
    }

    // 3. Listar todos los autores registrados (presentación mejorada)
    public void mostrarTodosLosAutores() {
        List<Autor> autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("👤 No hay autores registrados en la base.");
            return;
        }
        System.out.println("\n=========== AUTORES REGISTRADOS ===========");
        int contador = 1;
        for (Autor autor : autores) {
            System.out.println("Autor #" + contador++);
            System.out.println("  Nombre         : " + autor.getNombre());
            System.out.println("  Año nacimiento : " + (autor.getAnoNacimiento() != null ? autor.getAnoNacimiento() : "Desconocido"));
            System.out.println("  Año fallecimiento: " + (autor.getAnoMuerte() != null ? autor.getAnoMuerte() : "Desconocido"));
            System.out.println("-------------------------------------------");
        }
    }

    // 4. Listar autores vivos en un año específico (presentación mejorada)
    public void mostrarAutoresVivosEnAnio(int anio) {
        List<Autor> autores = autorRepository.findByAnoNacimientoLessThanEqualAndAnoMuerteGreaterThanEqualOrAnoMuerteIsNull(anio, anio)
                .stream()
                .filter(autor -> autor.getAnoNacimiento() != null)
                .collect(Collectors.toList());
        if (autores.isEmpty()) {
            System.out.println("👤 No se encontraron autores vivos en el año " + anio);
            return;
        }
        System.out.println("\n======= AUTORES VIVOS EN EL AÑO " + anio + " =======");
        int contador = 1;
        for (Autor autor : autores) {
            System.out.println("Autor #" + contador++);
            System.out.println("  Nombre         : " + autor.getNombre());
            System.out.println("  Año nacimiento : " + (autor.getAnoNacimiento() != null ? autor.getAnoNacimiento() : "Desconocido"));
            System.out.println("  Año fallecimiento: " + (autor.getAnoMuerte() != null ? autor.getAnoMuerte() : "Desconocido"));
            System.out.println("-------------------------------------------");
        }
    }


    // 5. Listar libros por idioma (presentación mejorada)
    public void mostrarLibrosPorIdioma(String idioma) {
        List<Libro> libros = libroRepository.findByIdiomaIgnoreCase(idioma);
        if (libros.isEmpty()) {
            System.out.println("📚 No hay libros registrados en el idioma '" + idioma + "'.");
            return;
        }
        System.out.println("\n======= LIBROS EN IDIOMA '" + idioma + "' =======");
        int contador = 1;
        for (Libro libro : libros) {
            System.out.println("Libro #" + contador++);
            System.out.println("  Título    : " + libro.getTitulo());
            System.out.println("  Descargas : " + libro.getDescargas());
            if (libro.getAutor() != null) {
                System.out.println("  Autor     : " + libro.getAutor().getNombre());
            } else {
                System.out.println("  Autor     : Desconocido");
            }
            System.out.println("-------------------------------------------");
        }
    }

    // 6. Estadísticas: cantidad de libros por idioma (presentación mejorada)
    public void mostrarEstadisticasPorIdioma() {
        List<Libro> libros = libroRepository.findAll();
        if (libros.isEmpty()) {
            System.out.println("📚 No hay libros registrados.");
            return;
        }
        var stats = libros.stream()
                .collect(Collectors.groupingBy(Libro::getIdioma, Collectors.counting()));
        System.out.println("\n======= CANTIDAD DE LIBROS POR IDIOMA =======");
        stats.forEach((idioma, cantidad) ->
                System.out.println("  • " + idioma + ": " + cantidad + " libro(s)"));
    }

    // 7. Estadísticas de descargas de libros
    public void mostrarEstadisticasDescargas() {
        List<Libro> libros = libroRepository.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
            return;
        }
        DoubleSummaryStatistics stats = libros.stream()
                .filter(l -> l.getDescargas() != null)
                .mapToDouble(Libro::getDescargas)
                .summaryStatistics();

        System.out.println("\n======= ESTADÍSTICAS DE DESCARGAS =======");
        System.out.println("Cantidad de libros : " + stats.getCount());
        System.out.println("Total descargas    : " + stats.getSum());
        System.out.println("Descargas promedio : " + stats.getAverage());
        System.out.println("Máximo descargas   : " + stats.getMax());
        System.out.println("Mínimo descargas   : " + stats.getMin());
    }

    // 8. Estadísticas de descargas de libros
    public void mostrarTop10MasDescargados() {
        List<Libro> libros = libroRepository.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
            return;
        }
        System.out.println("\n======= TOP 10 LIBROS MÁS DESCARGADOS =======");
        libros.stream()
                .filter(l -> l.getDescargas() != null)
                .sorted((a, b) -> b.getDescargas().compareTo(a.getDescargas()))
                .limit(10)
                .forEachOrdered(libro -> {
                    System.out.println("Título   : " + libro.getTitulo());
                    System.out.println("Autor    : " + (libro.getAutor() != null ? libro.getAutor().getNombre() : "Desconocido"));
                    System.out.println("Descargas: " + libro.getDescargas());
                    System.out.println("-------------------------------------------");
                });
    }

    // 9. Buscar autor por nombre
    public void buscarAutorPorNombre(String nombre) {
        var autorOpt = autorRepository.findByNombreIgnoreCase(nombre);
        if (autorOpt.isPresent()) {
            Autor autor = autorOpt.get();
            System.out.println("\n======= BÚSQUEDA DE AUTOR =======");
            System.out.println("Nombre         : " + autor.getNombre());
            System.out.println("Año nacimiento : " + (autor.getAnoNacimiento() != null ? autor.getAnoNacimiento() : "Desconocido"));
            System.out.println("Año fallecimiento: " + (autor.getAnoMuerte() != null ? autor.getAnoMuerte() : "Desconocido"));
        } else {
            System.out.println("No se encontró ningún autor con ese nombre.");
        }
    }

    // 10. Mostrar autores nacidos entre 2 años
    public void mostrarAutoresNacidosEntre(int desde, int hasta) {
        List<Autor> autores = autorRepository.findByAnoNacimientoBetween(desde, hasta);
        if (autores.isEmpty()) {
            System.out.println("No hay autores nacidos entre " + desde + " y " + hasta);
            return;
        }
        System.out.println("\n======= AUTORES NACIDOS ENTRE " + desde + " y " + hasta + " =======");
        int contador = 1;
        for (Autor autor : autores) {
            System.out.println("Autor #" + contador++);
            System.out.println("  Nombre         : " + autor.getNombre());
            System.out.println("  Año nacimiento : " + autor.getAnoNacimiento());
            System.out.println("  Año fallecimiento: " + (autor.getAnoMuerte() != null ? autor.getAnoMuerte() : "Desconocido"));
            System.out.println("-------------------------------------------");
        }
    }


}
