package com.mjv.contrateme.services;

import com.mjv.contrateme.dtos.CidadeDto;
import com.mjv.contrateme.exceptions.NotFoundException;
import com.mjv.contrateme.models.Cidade;
import com.mjv.contrateme.repositories.CidadeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CidadeService {

    private final CidadeRepository cidadeRepository;

    private final ModelMapper modelMapper;

    public CidadeService(CidadeRepository cidadeRepository, ModelMapper modelMapper) {
        this.cidadeRepository = cidadeRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public Cidade create(CidadeDto cidadeDto) {

        Cidade cidade = modelMapper.map(cidadeDto, Cidade.class);
        return cidadeRepository.save(cidade);
    }

    public Cidade findById(Integer id) {

        Optional<Cidade> optCidade = this.cidadeRepository.findById(id);
        return optCidade.orElseThrow(() -> new NotFoundException("CIDADE não encontrada na base de dados!"));
    }

    public Page<Cidade> findAll(Pageable pageable) {
        return this.cidadeRepository.findAll(pageable);
    }

    @Transactional
    public Cidade update(CidadeDto cidadeDto, Integer id) {

        Optional<Cidade> optCidade = this.cidadeRepository.findById(id);

        if (optCidade.isEmpty()) {
            throw new NotFoundException("CIDADE não encontrada na base de dados!");
        }

        Cidade cidadeAtualizada = this.modelMapper.map(cidadeDto, Cidade.class);
        cidadeAtualizada.setId(optCidade.get().getId());

        return this.cidadeRepository.save(cidadeAtualizada);
    }

    @Transactional
    public void delete(Integer id) {

        Optional<Cidade> optCidade = cidadeRepository.findById(id);

        if (optCidade.isEmpty()) {
            throw new NotFoundException("CIDADE não encontrada na base de dados!");
        }

        cidadeRepository.deleteById(id);
    }
}