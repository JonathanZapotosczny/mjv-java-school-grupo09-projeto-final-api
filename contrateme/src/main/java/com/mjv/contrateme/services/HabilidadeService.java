package com.mjv.contrateme.services;

import com.mjv.contrateme.dtos.HabilidadeDto;
import com.mjv.contrateme.exceptions.NotFoundException;
import com.mjv.contrateme.models.Habilidade;
import com.mjv.contrateme.repositories.HabilidadeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HabilidadeService {

    private final HabilidadeRepository habilidadeRepository;

    private final ModelMapper modelMapper;

    public HabilidadeService(HabilidadeRepository habilidadeRepository, ModelMapper modelMapper) {
        this.habilidadeRepository = habilidadeRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public Habilidade create(HabilidadeDto habilidadeDto) {

        Habilidade habilidade = modelMapper.map(habilidadeDto, Habilidade.class);
        return habilidadeRepository.save(habilidade);
    }

    public Habilidade findById(Integer id) {

        Optional<Habilidade> optHabilidade = this.habilidadeRepository.findById(id);
        return optHabilidade.orElseThrow(() -> new NotFoundException("HABILIDADE não encontrada na base de dados!"));
    }

    public Page<Habilidade> findAll(Pageable pageable) {
        return this.habilidadeRepository.findAll(pageable);
    }

    public List<Habilidade> findByIdList(List<Integer> ids) {
        return this.habilidadeRepository.findAllById(ids);
    }

    @Transactional
    public Habilidade update(HabilidadeDto habilidadeDto, Integer id) {

        Optional<Habilidade> optHabilidade = this.habilidadeRepository.findById(id);

        if (optHabilidade.isEmpty()) {
            throw new NotFoundException("HABILIDADE não encontrada na base de dados!");
        }

        Habilidade habilidadeAtualizada = this.modelMapper.map(habilidadeDto, Habilidade.class);
        habilidadeAtualizada.setId(optHabilidade.get().getId());

        return this.habilidadeRepository.save(habilidadeAtualizada);
    }

    @Transactional
    public void delete(Integer id) {

        Optional<Habilidade> optHabilidade = habilidadeRepository.findById(id);

        if (optHabilidade.isEmpty()) {
            throw new NotFoundException("HABILIDADE não encontrada na base de dados!");
        }

        habilidadeRepository.deleteById(id);
    }
}