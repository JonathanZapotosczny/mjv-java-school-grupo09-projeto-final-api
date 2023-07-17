package com.mjv.contrateme.services;

import com.mjv.contrateme.dtos.ProfissaoDto;
import com.mjv.contrateme.exceptions.NotFoundException;
import com.mjv.contrateme.models.Profissao;
import com.mjv.contrateme.repositories.ProfissaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProfissaoService {

    private final ProfissaoRepository profissaoRepository;

    private final ModelMapper modelMapper;

    public ProfissaoService(ProfissaoRepository profissaoRepository, ModelMapper modelMapper) {
        this.profissaoRepository = profissaoRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public Profissao create(ProfissaoDto profissaoDto) {

        Profissao profissao = modelMapper.map(profissaoDto, Profissao.class);
        return profissaoRepository.save(profissao);
    }

    public Profissao findById(Integer id) {

        Optional<Profissao> optProfissao = this.profissaoRepository.findById(id);
        return optProfissao.orElseThrow(() -> new NotFoundException("PROFISSÃO não encontrada na base de dados!"));
    }

    public Page<Profissao> findAll(Pageable pageable) {
        return this.profissaoRepository.findAll(pageable);
    }

    @Transactional
    public Profissao update(ProfissaoDto profissaoDto, Integer id) {

        Optional<Profissao> optProfissao = this.profissaoRepository.findById(id);

        if (optProfissao.isEmpty()) {
            throw new NotFoundException("PROFISSÃO não encontrada na base de dados!");
        }

        Profissao profissaoAtualizada = this.modelMapper.map(profissaoDto, Profissao.class);
        profissaoAtualizada.setId(optProfissao.get().getId());

        return this.profissaoRepository.save(profissaoAtualizada);
    }

    @Transactional
    public void delete(Integer id) {

        Optional<Profissao> optProfissao = profissaoRepository.findById(id);

        if (optProfissao.isEmpty()) {
            throw new NotFoundException("PROFISSÃO não encontrada na base de dados!");
        }

        profissaoRepository.deleteById(id);
    }
}