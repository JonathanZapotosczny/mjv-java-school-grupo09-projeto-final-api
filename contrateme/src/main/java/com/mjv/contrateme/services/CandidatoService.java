package com.mjv.contrateme.services;

import com.mjv.contrateme.dtos.CadastroCandidatoDto;
import com.mjv.contrateme.dtos.CadastroExperienciaDto;
import com.mjv.contrateme.exceptions.NotFoundException;
import com.mjv.contrateme.models.*;
import com.mjv.contrateme.repositories.CandidatoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CandidatoService {

    private final CandidatoRepository candidatoRepository;

    @Autowired
    private ExperienciaService experienciaService;
    @Autowired
    private ProfissaoService profissaoService;

    @Autowired
    private CidadeService cidadeService;
    @Autowired
    private HabilidadeService habilidadeService;
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

        Cidade cidade = cidadeService.findById(cadastroCandidatoDto.getEndereco().getCidade());
        Profissao profissao = profissaoService.findById(cadastroCandidatoDto.getProfissao());
        List<Habilidade> habilidades = habilidadeService.findByIdList(cadastroCandidatoDto.getHabilidades());
        CadastroCandidato candidato = modelMapper.map(cadastroCandidatoDto, CadastroCandidato.class);
        List<CadastroExperienciaDto> experiencias = cadastroCandidatoDto.getExperiencias();
        for (int i = 0; i < experiencias.size(); i++) {
            Profissao p = profissaoService.findById(experiencias.get(i).getProfissao());
            candidato.getExperiencias().get(i).setProfissao(p);
        }
        candidato.getEndereco().setCidade(cidade);
        candidato.setProfissao(profissao);
        candidato.setHabilidades(habilidades);
        return candidatoRepository.save(candidato);

    }

}