package com.example.dbarber.services;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dbarber.repository.HorarioRepository;

import jakarta.persistence.EntityNotFoundException;

import com.example.dbarber.repository.ClienteRepository;
import com.example.dbarber.models.Cliente;
import com.example.dbarber.models.Horario;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horaRepo;

    @Autowired
    private ClienteRepository clienteRepo;

    public Horario marcarHorario(Long clienteId, OffsetDateTime startAt, OffsetDateTime endAt) {
        if (startAt.isAfter(endAt) || startAt.equals(endAt)) {
            throw new IllegalArgumentException("O horário de início deve ser antes do horário de término.");
        }
        
        Cliente cliente = clienteRepo.findById(clienteId)
        .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        // Verificar se já existe um horário conflitante
        boolean conflito = horaRepo.existsByOverlappingTime(startAt, endAt);
        if (conflito) {
            throw new IllegalStateException("Horário indisponível");
        }

        Horario horario = new Horario();
        horario.setStartAt(startAt);
        horario.setEndAt(endAt);
        horario.setClient(cliente);

        return horaRepo.save(horario);
    }
    public Horario editarHorario(Long id, OffsetDateTime startAt, OffsetDateTime endAt) {
        if (startAt.isAfter(endAt) || startAt.equals(endAt)) {
            throw new IllegalArgumentException("O horário de início deve ser antes do horário de término.");
        }

        return horaRepo.findById(id)
            .map(horario -> {
                // Ajuste na verificação de conflito
                boolean conflito = horaRepo.existsByStartAtBetweenOrEndAtBetween(startAt, endAt, startAt, endAt);
                
                if (conflito && !horario.getId().equals(id)) {
                    throw new IllegalStateException("Horário indisponível");
                }
                
                horario.setStartAt(startAt);
                horario.setEndAt(endAt);
                return horaRepo.save(horario);
            }).orElseThrow(() -> new EntityNotFoundException("Horário não Disponível"));
    }

    public void excluirHorario(Long id) {
        Horario horario = horaRepo.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Horário não encontrado"));
        horaRepo.delete(horario);
    }

}
