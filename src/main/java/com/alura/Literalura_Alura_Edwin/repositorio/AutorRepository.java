package com.alura.Literalura_Alura_Edwin.repositorio;

import com.alura.Literalura_Alura_Edwin.modelo.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombreIgnoreCase(String nombre);

    List<Autor> findByAnoNacimientoLessThanEqualAndAnoMuerteGreaterThanEqualOrAnoMuerteIsNull(int nacimiento, int muerte);
    List<Autor> findByAnoNacimientoBetween(Integer desde, Integer hasta);
}
