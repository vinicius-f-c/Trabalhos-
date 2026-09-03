package com.example.filmes.controller;

import com.example.filmes.model.Filme;
import com.example.filmes.service.FilmeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    private final FilmeService filmeService;

    @Autowired
    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }


    @PostMapping
    public ResponseEntity<Filme> criar(@Valid @RequestBody Filme filme) {
        Filme filmeCriado = filmeService.criar(filme);
        return ResponseEntity.status(HttpStatus.CREATED).body(filmeCriado);
    }


    @GetMapping
    public ResponseEntity<List<Filme>> listarTodos() {
        return ResponseEntity.ok(filmeService.listarTodos());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Filme> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(filmeService.buscarPorId(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Filme> atualizar(@PathVariable Long id, @Valid @RequestBody Filme filme) {
        Filme filmeAtualizado = filmeService.atualizar(id, filme);
        return ResponseEntity.status(HttpStatus.CREATED).body(filmeAtualizado);
    }

  
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        filmeService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
