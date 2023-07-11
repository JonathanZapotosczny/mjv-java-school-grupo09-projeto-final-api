package com.mjv.contrateme.services;
import com.mjv.contrateme.exceptions.NotFoundException;
import com.mjv.contrateme.models.CadastroCandidato;
import com.mjv.contrateme.repositories.CandidatoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


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

    public Page<CadastroCandidato> findAll(Pageable pageable) {
        return this.candidatoRepository.findAll(pageable);
    }

    @Transactional
    public void delete(Integer id) {
        Optional<CadastroCandidato> optCandidato = candidatoRepository.findById(id);

        if (optCandidato.isEmpty()) {
            throw new NotFoundException("Candidato não encotrado!");
        }

        candidatoRepository.deleteById(id);
    }
}