package com.mjv.contrateme.services;
import com.mjv.contrateme.exceptions.NotFoundException;
import com.mjv.contrateme.models.Empresa;
import com.mjv.contrateme.repositories.EmpresaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
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

    public Page<Empresa> findAll(Pageable pageable) {
        return this.empresaRepository.findAll(pageable);
    }

    @Transactional
    public void delete(Integer id) {
        Optional<Empresa> optEmpresa = empresaRepository.findById(id);

        if (optEmpresa.isEmpty()) {
            throw new NotFoundException("Empresa não encontrada!");
        }

        empresaRepository.deleteById(id);
    }

}