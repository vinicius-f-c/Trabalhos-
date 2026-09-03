package com.example.filmes.service;

import com.example.filmes.exception.ResourceNotFoundException;
import com.example.filmes.model.Filme;
import com.example.filmes.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;

    @Autowired
    public FilmeService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    // CREATE
    public Filme criar(Filme filme) {
        filme.setId(null); // garante que é sempre um filme novo
        return filmeRepository.save(filme);
    }

    // READ - todos
    public List<Filme> listarTodos() {
        return filmeRepository.findAll();
    }

    // READ - por id
    public Filme buscarPorId(Long id) {
        return filmeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Filme com id " + id + " não encontrado"));
    }

    // UPDATE
    public Filme atualizar(Long id, Filme dadosAtualizados) {
        Filme filmeExistente = buscarPorId(id);

        filmeExistente.setTitulo(dadosAtualizados.getTitulo());
        filmeExistente.setDiretor(dadosAtualizados.getDiretor());
        filmeExistente.setGenero(dadosAtualizados.getGenero());
        filmeExistente.setAnoLancamento(dadosAtualizados.getAnoLancamento());
        filmeExistente.setDuracaoMinutos(dadosAtualizados.getDuracaoMinutos());

        return filmeRepository.save(filmeExistente);
    }

    // DELETE
    public void deletar(Long id) {
        Filme filme = buscarPorId(id);
        filmeRepository.delete(filme);
    }
}
