package com.example.projeto.controller;

import com.example.projeto.dto.AcessorioDTO;
import com.example.projeto.service.AcessorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acessorios")
public class AcessorioController {

    @Autowired
    private AcessorioService service;

    @GetMapping
    public ResponseEntity<List<AcessorioDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcessorioDTO> buscarPorId(@PathVariable Long id) {
        AcessorioDTO dto = service.buscarPorId(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<AcessorioDTO> salvar(@RequestBody AcessorioDTO dto) {
        AcessorioDTO salvo = service.salvar(dto);
        return ResponseEntity.ok(salvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AcessorioDTO> atualizar(@PathVariable Long id, @RequestBody AcessorioDTO dto) {
        AcessorioDTO atualizado = service.atualizar(id, dto);
        return atualizado != null ? ResponseEntity.ok(atualizado) : ResponseEntity.notFound().build();
    }

}