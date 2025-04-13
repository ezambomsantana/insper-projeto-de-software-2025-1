package br.edu.insper.musica.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MusicaController {


    @GetMapping("/musica")
    public String getMusicasPrivate() {
        return "Hellop Private";
    }

}
