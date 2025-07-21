package com.alura.Literalura_Alura_Edwin.repositorio;

import com.alura.Literalura_Alura_Edwin.modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    boolean existsByTitulo(String titulo);
    List<Libro> findByIdiomaIgnoreCase(String idioma);
    long countByIdioma(String idioma);
}
