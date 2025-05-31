package com.laboratorio.laboratorio_ms.repository;

import com.laboratorio.laboratorio_ms.model.Laboratorio;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long> {
    List<Laboratorio> findByLocalizacao(String localizacao);
}