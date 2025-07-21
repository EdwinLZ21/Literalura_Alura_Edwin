
# Literalura_Alura_Edwin 📚

**Aplicación backend de catálogo literario interactivo en consola**  
Desarrollada como parte del programa Oracle Next Education + Alura Latam.

---

## 💡 Descripción

**Literalura_Alura_Edwin** permite explorar, almacenar y analizar un catálogo personalizado de libros y autores utilizando la API pública de Gutendex (Project Gutenberg).  
Todo se gestiona a través de un menú interactivo en consola, y los datos se almacenan en una base de datos PostgreSQL.

---

## 🚀 Tecnologías y requisitos

- Java 17+
- Spring Boot 3.2.3
- Maven 4+
- PostgreSQL 16+
- Jackson (para manejo de JSON)
- HttpClient (incluido en JDK)

---

## ⚙️ Instalación y uso rápido

1. **Clona este repositorio:**

   ```bash
   git clone https://github.com/tu_usuario/Literalura_Alura_Edwin.git
   cd Literalura_Alura_Edwin

2. **Crea la base de datos en PostgreSQL:**

   ```sql
   CREATE DATABASE literalura_db;
   ```

3. **Configura `src/main/resources/application.properties`:**

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/literalura_db
   spring.datasource.username=postgres
   spring.datasource.password=tu_contraseña
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=false
   ```

4. **Compila y ejecuta el proyecto:**

   ```bash
   mvn spring-boot:run
   ```

   O abre el proyecto en tu IDE favorito y ejecuta la clase principal.

---

## 🖥️ Menú interactivo de consola

```
===== MENÚ LITERALURA =====
1. Buscar libro por título (API y guardar en base)
2. Listar todos los libros registrados
3. Listar todos los autores registrados
4. Listar autores vivos en un año específico
5. Listar libros por idioma
6. Mostrar cantidad de libros por idioma
7. Ver estadísticas de descargas
8. Ver TOP 10 libros más descargados
9. Buscar autor por nombre
10. Listar autores nacidos entre dos años
0. Salir
```

---

## ✅ Funcionalidades principales

* 🔎 **Buscar libro por título:** Consulta la API de Gutendex, procesa los resultados y guarda libros/autores si no están registrados.
* 📚 **Listados claros:** Muestra libros y autores con información relevante, formateada de forma legible.
* 📊 **Filtrado y estadísticas:** Autores vivos por año, libros por idioma, estadísticas de descargas y TOP 10 libros más descargados.
* 🧠 **Consultas avanzadas:** Búsqueda exacta por nombre de autor y listados por rangos de fechas de nacimiento.

---

## 💎 Ejemplo de salida en consola

```
Introduce el título del libro a buscar: Alice
✅ Libro y autor guardados exitosamente.

=========== LIBROS REGISTRADOS ===========
Libro #1
  Título        : Alice's Adventures in Wonderland
  Idioma        : en
  Descargas     : 48516
  Autor         : Carroll, Lewis
  Nacimiento    : 1832
  Fallecimiento : 1898
-------------------------------------------
```

---

## 👨‍💻 Autor

**Edwin Lopez**
Desarrollado como parte del desafío de Oracle Next Education + Alura Latam · 2025

---

