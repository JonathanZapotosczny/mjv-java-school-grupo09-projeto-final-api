package com.mjv.contrateme.services;

import com.mjv.contrateme.dtos.CadastroExperienciaDto;
import com.mjv.contrateme.exceptions.NotFoundException;
import com.mjv.contrateme.models.CadastroExperiencia;
import com.mjv.contrateme.models.Profissao;
import com.mjv.contrateme.repositories.CadastroExperienciaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienciaService {

    private final CadastroExperienciaRepository experienciaRepository;

    @Autowired
    private ProfissaoService profissaoService;

    private final ModelMapper modelMapper;

    public ExperienciaService(CadastroExperienciaRepository experienciaRepository, ModelMapper modelMapper) {
        this.experienciaRepository = experienciaRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public CadastroExperiencia create(CadastroExperienciaDto experienciaDto) {

        Profissao profissao = profissaoService.findById(experienciaDto.getProfissao());
        CadastroExperiencia experiencia = modelMapper.map(experienciaDto, CadastroExperiencia.class);
        experiencia.setProfissao(profissao);

        return experienciaRepository.save(experiencia);
    }

    public CadastroExperiencia findById(Integer id) {

        Optional<CadastroExperiencia> optExperiencia = this.experienciaRepository.findById(id);
        return optExperiencia.orElseThrow(() -> new NotFoundException("EXPERIÊNCIA não encontrada na base de dados!"));
    }

    public List<CadastroExperiencia> findByIdList(List<Integer> ids) {
        return this.experienciaRepository.findAllById(ids);
    }
}