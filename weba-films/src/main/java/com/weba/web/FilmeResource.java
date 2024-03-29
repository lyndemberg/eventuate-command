package com.weba.web;

import com.weba.domain.Filme;
import com.weba.service.FilmeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/filme")
public class FilmeResource {
    private final FilmeService filmeService;

    public FilmeResource(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @PostMapping
    public ResponseEntity<Filme> cadastrarFilme(@RequestBody Filme novo){
        Filme filme = filmeService.salvarFilme(novo);
        return ResponseEntity.status(HttpStatus.CREATED).body(filme);
    }

    @GetMapping
    public ResponseEntity<Filme> buscarFilme(@RequestParam("id") Long id){
        Filme filme = filmeService.buscarPorId(id);
        return ResponseEntity.ok(filme);
    }

}
