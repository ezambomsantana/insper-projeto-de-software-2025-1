package br.edu.insper.musica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MusicaController {

    @Autowired
    private MusicaService musicaService;

    @GetMapping("/musica")
    public List<Musica> getMusicasPrivate() {
        return musicaService.list();
    }

    @PostMapping("/musica")
    public Musica saveMusica(@RequestBody Musica musica) {
        return musicaService.save(musica);
    }

}
