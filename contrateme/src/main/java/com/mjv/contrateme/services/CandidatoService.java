package com.mjv.contrateme.services;
import com.mjv.contrateme.dtos.CadastroCandidatoDto;
import com.mjv.contrateme.exceptions.NotFoundException;
import com.mjv.contrateme.models.CadastroCandidato;
import com.mjv.contrateme.repositories.CandidatoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CandidatoService {

    private final CandidatoRepository candidatoRepository;
    private final ModelMapper modelMapper;

    public CandidatoService(CandidatoRepository candidatoRepository, ModelMapper modelMapper) {
        this.candidatoRepository = candidatoRepository;
        this.modelMapper = modelMapper;
    }

    public CadastroCandidato findById(Integer id) {

        Optional<CadastroCandidato> optCandidato = this.candidatoRepository.findById(id);

        return optCandidato.orElseThrow(() -> new NotFoundException("Candidato não encontrada na base de dados."));

    }

    @Transactional
    public CadastroCandidato update(CadastroCandidatoDto cadastroCandidatoDto, Integer id) {
        Optional<CadastroCandidato> optCandidato = this.candidatoRepository.findById(id);

        if (optCandidato.isEmpty()) {
            throw new NotFoundException("CANDIDATO não encontrado (a) na base de dados!");
        }

        CadastroCandidato cadastroCandidatoAtualizado = this.modelMapper.map(cadastroCandidatoDto, CadastroCandidato.class);
        cadastroCandidatoAtualizado.setId(optCandidato.get().getId());
        cadastroCandidatoAtualizado.setCpf(optCandidato.get().getCpf());

        return this.candidatoRepository.save(cadastroCandidatoAtualizado);
    }

}