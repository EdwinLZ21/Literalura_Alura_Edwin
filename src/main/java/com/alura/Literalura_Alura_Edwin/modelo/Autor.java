package com.alura.Literalura_Alura_Edwin.modelo;

import jakarta.persistence.*;

@Entity
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer anoNacimiento;
    private Integer anoMuerte;

    // Getters, setters, toString()
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getAnoNacimiento() { return anoNacimiento; }
    public void setAnoNacimiento(Integer anoNacimiento) { this.anoNacimiento = anoNacimiento; }

    public Integer getAnoMuerte() { return anoMuerte; }
    public void setAnoMuerte(Integer anoMuerte) { this.anoMuerte = anoMuerte; }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", año de nacimiento=" + anoNacimiento +
                ", año de muerte=" + anoMuerte +
                '}';
    }
}
