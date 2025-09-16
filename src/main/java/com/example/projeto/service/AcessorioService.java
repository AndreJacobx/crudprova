package com.example.projeto.service;

import com.example.projeto.dto.AcessorioDTO;
import com.example.projeto.model.Acessorio;
import com.example.projeto.repository.AcessorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AcessorioService {

    @Autowired
    private AcessorioRepository repository;

    public List<AcessorioDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public AcessorioDTO buscarPorId(Long id) {
        Optional<Acessorio> acessorio = repository.findById(id);
        return acessorio.map(this::toDTO).orElse(null);
    }

    public AcessorioDTO salvar(AcessorioDTO dto) {
        Acessorio acessorio = toEntity(dto);
        Acessorio salvo = repository.save(acessorio);
        return toDTO(salvo);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public AcessorioDTO atualizar(Long id, AcessorioDTO dto) {
        Optional<Acessorio> acessorioExistente = repository.findById(id);
        if (acessorioExistente.isPresent()) {
            Acessorio acessorio = acessorioExistente.get();
            acessorio.setNome(dto.getNome());
            Acessorio atualizado = repository.save(acessorio);
            return toDTO(atualizado);
        }
        return null;
    }


    private AcessorioDTO toDTO(Acessorio a) {
        return new AcessorioDTO(a.getId(), a.getNome());
    }

    private Acessorio toEntity(AcessorioDTO dto) {
        Acessorio a = new Acessorio();
        a.setId(dto.getId());
        a.setNome(dto.getNome());
        return a;
    }
}