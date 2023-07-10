package com.mjv.contrateme.services;
import com.mjv.contrateme.dtos.CadastroCandidatoDto;
import com.mjv.contrateme.exceptions.NotFoundException;
import com.mjv.contrateme.models.CadastroCandidato;
import com.mjv.contrateme.repositories.CandidatoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public CadastroCandidato create(CadastroCandidatoDto cadastroCandidatoDto) {

        CadastroCandidato cadastroCandidato = modelMapper.map(cadastroCandidatoDto, CadastroCandidato.class);

        return candidatoRepository.save(cadastroCandidato);

    }

}