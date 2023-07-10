package com.mjv.contrateme.services;
import com.mjv.contrateme.dtos.EmpresaDto;
import com.mjv.contrateme.exceptions.NotFoundException;
import com.mjv.contrateme.models.Empresa;
import com.mjv.contrateme.repositories.EmpresaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final ModelMapper modelMapper;

    public EmpresaService(EmpresaRepository empresaRepository, ModelMapper modelMapper) {
        this.empresaRepository = empresaRepository;
        this.modelMapper = modelMapper;
    }

    public Empresa findById(Integer id) {

        Optional<Empresa> optEmpresa = this.empresaRepository.findById(id);

        return optEmpresa.orElseThrow(() -> new NotFoundException("Empresa não encontrada na base de dados."));

    }

    @Transactional
    public Empresa create(EmpresaDto empresaDto) {

        Empresa empresa = modelMapper.map(empresaDto, Empresa.class);

        return empresaRepository.save(empresa);

    }

}