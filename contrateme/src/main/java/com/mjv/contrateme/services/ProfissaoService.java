package com.mjv.contrateme.services;
import com.mjv.contrateme.exceptions.NotFoundException;
import com.mjv.contrateme.models.Profissao;
import com.mjv.contrateme.repositories.ProfissaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ProfissaoService {

    private final ProfissaoRepository profissaoRepository;
    private final ModelMapper modelMapper;

    public ProfissaoService(ProfissaoRepository profissaoRepository, ModelMapper modelMapper) {
        this.profissaoRepository = profissaoRepository;
        this.modelMapper = modelMapper;
    }

    public Profissao findById(Integer id) {

        Optional<Profissao> optProfissao = this.profissaoRepository.findById(id);

        return optProfissao.orElseThrow(() -> new NotFoundException("Profissão não encontrada na base de dados."));

    }

    public Page<Profissao> findAll(Pageable pageable){
        return this.profissaoRepository.findAll(pageable);
    }

    @Transactional
    public void delete(Integer id) {
        Optional<Profissao> optProfissao = profissaoRepository.findById(id);

        if (optProfissao.isEmpty()) {
            throw new NotFoundException("Profissão não encontrada!");
        }

        profissaoRepository.deleteById(id);
    }

}