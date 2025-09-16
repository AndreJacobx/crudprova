package com.example.projeto.service;

import com.example.projeto.dto.VeiculoDTO;
import com.example.projeto.model.Veiculo;
import com.example.projeto.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository repository;

    public List<VeiculoDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public VeiculoDTO buscarPorId(Long id) {
        Optional<Veiculo> veiculo = repository.findById(id);
        return veiculo.map(this::toDTO).orElse(null);
    }

    public VeiculoDTO salvar(VeiculoDTO dto) {
        Veiculo veiculo = toEntity(dto);
        Veiculo salvo = repository.save(veiculo);
        return toDTO(salvo);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public VeiculoDTO atualizar(Long id, VeiculoDTO dto) {
        Optional<Veiculo> veiculoExistente = repository.findById(id);
        if (veiculoExistente.isPresent()) {
            Veiculo v = veiculoExistente.get();
            v.setModelo(dto.getModelo());
            v.setAnoFabricacao(dto.getAnoFabricacao());
            v.setPlaca(dto.getPlaca());
            Veiculo atualizado = repository.save(v);
            return toDTO(atualizado);
        }
        return null;
    }

    private VeiculoDTO toDTO(Veiculo v) {
        return new VeiculoDTO(v.getId(), v.getModelo(), v.getAnoFabricacao(), v.getPlaca());
    }

    private Veiculo toEntity(VeiculoDTO dto) {
        Veiculo v = new Veiculo();
        v.setId(dto.getId());
        v.setModelo(dto.getModelo());
        v.setAnoFabricacao(dto.getAnoFabricacao());
        v.setPlaca(dto.getPlaca());
        return v;
    }
}