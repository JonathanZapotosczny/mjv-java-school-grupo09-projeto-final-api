package com.mjv.contrateme.services;

import com.mjv.contrateme.dtos.CadastroExperienciaDto;
import com.mjv.contrateme.dtos.HabilidadeDto;
import com.mjv.contrateme.exceptions.NotFoundException;
import com.mjv.contrateme.models.CadastroExperiencia;
import com.mjv.contrateme.models.Habilidade;
import com.mjv.contrateme.repositories.HabilidadeRepository;
import org.modelmapper.ModelMapper;
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

    public Habilidade findById(Integer id) {

        Optional<Habilidade> optExperiencia = this.habilidadeRepository.findById(id);

        return optExperiencia.orElseThrow(() -> new NotFoundException("Experiencia não encontrada na base de dados."));

    }

    @Transactional
    public Habilidade create(HabilidadeDto habilidadeDto) {

        Habilidade habilidade = modelMapper.map(habilidadeDto, Habilidade.class);

        return habilidadeRepository.save(habilidade);

    }

    public List<Habilidade> findByIdList(List<Integer> ids) {

        return this.habilidadeRepository.findAllById(ids);
    }

}