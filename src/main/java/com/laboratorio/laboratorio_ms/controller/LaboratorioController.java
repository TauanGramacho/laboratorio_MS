package com.laboratorio.laboratorio_ms.controller;

import com.laboratorio.laboratorio_ms.model.Laboratorio;
import com.laboratorio.laboratorio_ms.repository.LaboratorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/laboratorios")
public class LaboratorioController {

    @Autowired
    private LaboratorioRepository laboratorioRepository;

    @GetMapping
    public ResponseEntity<List<Laboratorio>> getAllLaboratorios() {
        return new ResponseEntity<>(laboratorioRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Laboratorio> getLaboratorioById(@PathVariable("id") Long id) {
        Optional<Laboratorio> laboratorio = laboratorioRepository.findById(id);
        return laboratorio.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/localizacao/{localizacao}")
    public ResponseEntity<List<Laboratorio>> getLaboratoriosByLocalizacao(@PathVariable("localizacao") String localizacao) {
        List<Laboratorio> laboratorios = laboratorioRepository.findByLocalizacao(localizacao);
        if (laboratorios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(laboratorios, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Laboratorio> createLaboratorio(@RequestBody Laboratorio laboratorio) {
        Laboratorio savedLaboratorio = laboratorioRepository.save(laboratorio);
        return new ResponseEntity<>(savedLaboratorio, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Laboratorio> updateLaboratorio(@PathVariable("id") Long id, @RequestBody Laboratorio laboratorioDetails) {
        Optional<Laboratorio> laboratorioData = laboratorioRepository.findById(id);
        if (laboratorioData.isPresent()) {
            Laboratorio laboratorio = laboratorioData.get();
            laboratorio.setNome(laboratorioDetails.getNome());
            laboratorio.setLocalizacao(laboratorioDetails.getLocalizacao());
            // Atualize outros atributos conforme necessário
            Laboratorio updatedLaboratorio = laboratorioRepository.save(laboratorio);
            return new ResponseEntity<>(updatedLaboratorio, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteLaboratorio(@PathVariable("id") Long id) {
        try {
            laboratorioRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}