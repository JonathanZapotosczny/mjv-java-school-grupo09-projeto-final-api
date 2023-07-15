package com.mjv.contrateme.services;

import com.mjv.contrateme.dtos.CadastroCandidatoDto;
import com.mjv.contrateme.dtos.CadastroCandidatoDtoResponse;
import com.mjv.contrateme.dtos.CadastroExperienciaDto;
import com.mjv.contrateme.enums.Sexo;
import com.mjv.contrateme.exceptions.NotFoundException;
import com.mjv.contrateme.models.CadastroCandidato;
import com.mjv.contrateme.models.Cidade;
import com.mjv.contrateme.models.Habilidade;
import com.mjv.contrateme.models.Profissao;
import com.mjv.contrateme.repositories.CandidatoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
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

    public CadastroCandidato findById(Integer id) {

        Optional<CadastroCandidato> optCandidato = this.candidatoRepository.findById(id);
        return optCandidato.orElseThrow(() -> new NotFoundException("CANDIDATO(a) não encontrado(a) na base de dados!"));
    }

    public Page<CadastroCandidato> findAll(Pageable pageable) {
        return this.candidatoRepository.findAll(pageable);
    }

    public Integer contarCandidatoComHabilidade(String nome) {
        return this.candidatoRepository.contarCandidatosComHabilidade(nome);
    }

    public List<CadastroCandidato> buscarCandidatoSemHabilidade(String nome) {

        if(nome.isBlank()) {
            return this.candidatoRepository.buscarCandidatosSemHabilidade();
        }
        return this.candidatoRepository.buscarCandidatosSemHabilidade(nome);
    }

    public List<String> quantidadeProfissionaisPorCidade(String nome) {
        return this.candidatoRepository.quantidadeProfissionaisPorCidade(nome);
    }

    public List<CadastroCandidato> candidatosComExperienciaPorData(LocalDate dataInicio, LocalDate dataFim) {
        return this.candidatoRepository.candidatosComExperienciaPorData(dataInicio, dataFim);
    }

    public List<CadastroCandidato> candidatoPorExperiencia(String nome) {
        return this.candidatoRepository.candidatoPorExperiencia(nome);
    }

    public List<CadastroCandidato> candidatoPorExperienciaAtual(String nome) {
        return this.candidatoRepository.candidatoPorExperienciaAtual(nome);
    }

    public List<CadastroCandidato> candidatoTrabalhando() {
        return this.candidatoRepository.candidatoTrabalhando();
    }

    public List<CadastroCandidatoDtoResponse> profissaoDoCandidato() {

        List<CadastroCandidato> cadastroCandidatos = this.candidatoRepository.findNomeAndProfissaoNomeBy();
        List<CadastroCandidatoDtoResponse> cadastroCandidatoDtoResponses = new ArrayList<>();

        for (CadastroCandidato cadastroCandidato: cadastroCandidatos) {
            CadastroCandidatoDtoResponse cadastroCandidatoDtoResponse = new CadastroCandidatoDtoResponse(cadastroCandidato);
            cadastroCandidatoDtoResponses.add(cadastroCandidatoDtoResponse);
        }

        return cadastroCandidatoDtoResponses;
    }

    public List<CadastroCandidatoDtoResponse> profissaoPorId(Integer id) {

        List<CadastroCandidato> cadastroCandidatos = this.candidatoRepository.findNomeAndProfissaoByProfissaoId(id);
        List<CadastroCandidatoDtoResponse> cadastroCandidatoDtoResponses = new ArrayList<>();

        for (CadastroCandidato cadastroCandidato: cadastroCandidatos) {
            CadastroCandidatoDtoResponse cadastroCandidatoDtoResponse = new CadastroCandidatoDtoResponse(cadastroCandidato);
            cadastroCandidatoDtoResponses.add(cadastroCandidatoDtoResponse);
        }

        System.out.println(cadastroCandidatos.size());

        return cadastroCandidatoDtoResponses;
    }

    public List<String> candidatosPorProfissao() {
        return this.candidatoRepository.candidatosPorProfissao();
    }

    public List<CadastroCandidato> candidatosPorProfisaoESalario(String nome) {

        if(nome.isBlank()) {
            return this.candidatoRepository.candidatosPorProfissaoESalario();
        }
        return this.candidatoRepository.candidatosESalarioPorProfissao(nome);
    }

    public List<CadastroCandidato> candidatosPorSexoEEndereco(Sexo sexo, String sigla) {

        if(sigla.isBlank()) {
            return this.candidatoRepository.candidatosPorSexoEEndereco(sexo);
        }
        return this.candidatoRepository.candidatosPorSexoEEndereco(sexo, sigla);
    }


    @Transactional
    public CadastroCandidato update(CadastroCandidatoDto cadastroCandidatoDto, Integer id) {

        Optional<CadastroCandidato> optCandidato = this.candidatoRepository.findById(id);

        if (optCandidato.isEmpty()) {
            throw new NotFoundException("CANDIDATO(a) não encontrado(a) na base de dados!");
        }

        CadastroCandidato cadastroCandidatoAtualizado = this.modelMapper.map(cadastroCandidatoDto, CadastroCandidato.class);

        Cidade cidade = cidadeService.findById(cadastroCandidatoDto.getEndereco().getCidade());
        Profissao profissao = profissaoService.findById(cadastroCandidatoDto.getProfissao());
        List<Habilidade> habilidades = habilidadeService.findByIdList(cadastroCandidatoDto.getHabilidades());
        List<CadastroExperienciaDto> experiencias = cadastroCandidatoDto.getExperiencias();

        for (int i = 0; i < experiencias.size(); i++) {
            Profissao p = profissaoService.findById(experiencias.get(i).getProfissao());
            cadastroCandidatoAtualizado.getExperiencias().get(i).setProfissao(p);
        }

        cadastroCandidatoAtualizado.setId(optCandidato.get().getId());
        cadastroCandidatoAtualizado.setCpf(optCandidato.get().getCpf());
        cadastroCandidatoAtualizado.getEndereco().setCidade(cidade);
        cadastroCandidatoAtualizado.setProfissao(profissao);
        cadastroCandidatoAtualizado.setHabilidades(habilidades);

        return this.candidatoRepository.save(cadastroCandidatoAtualizado);
    }

    @Transactional
    public void delete(Integer id) {

        Optional<CadastroCandidato> optCandidato = candidatoRepository.findById(id);

        if (optCandidato.isEmpty()) {
            throw new NotFoundException("CANDIDATO(a) não encontrado(a) na base de dados!");
        }

        candidatoRepository.deleteById(id);
    }
}