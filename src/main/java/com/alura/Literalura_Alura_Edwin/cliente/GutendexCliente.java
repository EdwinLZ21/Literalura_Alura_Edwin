package com.alura.Literalura_Alura_Edwin.cliente;

import com.alura.Literalura_Alura_Edwin.dto.LibroDTO;
import com.alura.Literalura_Alura_Edwin.dto.RespuestaGutendexDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class GutendexCliente {

    public Optional<LibroDTO> buscarLibroPorTitulo(String titulo) {
        try {
            String url = "https://gutendex.com/books/?search=" +
                    URLEncoder.encode(titulo, StandardCharsets.UTF_8);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();

            ObjectMapper mapper = new ObjectMapper();
            RespuestaGutendexDTO resultado =
                    mapper.readValue(json, RespuestaGutendexDTO.class);

            if (resultado.getResults() != null && !resultado.getResults().isEmpty()) {
                // Devuelve el primer libro encontrado (requisito del desafío)
                return Optional.of(resultado.getResults().get(0));
            } else {
                // No hay resultados
                return Optional.empty();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
