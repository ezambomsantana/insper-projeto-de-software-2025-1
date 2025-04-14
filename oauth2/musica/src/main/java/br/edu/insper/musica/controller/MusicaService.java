package br.edu.insper.musica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MusicaService {

    @Autowired
    private MusicaRepository musicaRepository;


    public Musica save(Musica musica) {
        musica.setId(UUID.randomUUID().toString());
        return musicaRepository.save(musica);
    }

    public List<Musica> list() {
        return musicaRepository.findAll();
    }
}
