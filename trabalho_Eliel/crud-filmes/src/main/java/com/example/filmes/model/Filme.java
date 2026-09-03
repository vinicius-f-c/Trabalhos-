package com.example.filmes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "filmes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    @Column(nullable = false)
    private String titulo;

    @NotBlank(message = "O diretor é obrigatório")
    @Column(nullable = false)
    private String diretor;

    @NotBlank(message = "O gênero é obrigatório")
    @Column(nullable = false)
    private String genero;

    @NotNull(message = "O ano de lançamento é obrigatório")
    @Min(value = 1888, message = "Ano de lançamento inválido")
    @Column(name = "ano_lancamento", nullable = false)
    private Integer anoLancamento;

    @Min(value = 0, message = "A duração não pode ser negativa")
    @Column(name = "duracao_minutos")
    private Integer duracaoMinutos;
}
