package com.mjv.contrateme.services;
import com.mjv.contrateme.dtos.CadastroCandidatoDto;
import com.mjv.contrateme.dtos.CadastroExperienciaDto;
import com.mjv.contrateme.dtos.CadastroCandidatoDto;
import com.mjv.contrateme.exceptions.NotFoundException;
import com.mjv.contrateme.models.*;
import com.mjv.contrateme.repositories.CandidatoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.transaction.Transactional;
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

    public Page<CadastroCandidato> findAll(Pageable pageable) {
        return this.candidatoRepository.findAll(pageable);
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
    @Transactional
    public CadastroCandidato update(CadastroCandidatoDto cadastroCandidatoDto, Integer id) {
        Optional<CadastroCandidato> optCandidato = this.candidatoRepository.findById(id);

        if (optCandidato.isEmpty()) {
            throw new NotFoundException("CANDIDATO não encontrado (a) na base de dados!");
        }

        CadastroCandidato cadastroCandidatoAtualizado = this.modelMapper.map(cadastroCandidatoDto, CadastroCandidato.class);
        cadastroCandidatoAtualizado.setId(optCandidato.get().getId());
        cadastroCandidatoAtualizado.setCpf(optCandidato.get().getCpf());

        return this.candidatoRepository.save(cadastroCandidatoAtualizado);

    }
  
      @Transactional
    public void delete(Integer id) {
        Optional<CadastroCandidato> optCandidato = candidatoRepository.findById(id);

        if (optCandidato.isEmpty()) {
            throw new NotFoundException("Candidato não encotrado!");
        }

        candidatoRepository.deleteById(id);
}
  
}