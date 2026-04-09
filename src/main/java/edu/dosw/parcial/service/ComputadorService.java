package edu.dosw.parcial.service;

import edu.dosw.parcial.dto.ComputadorRequest;
import edu.dosw.parcial.dto.ComputadorResponse;
import edu.dosw.parcial.dto.PerifericoRequest;
import edu.dosw.parcial.dto.PerifericoResponse;
import edu.dosw.parcial.entity.ColaboradorEntity;
import edu.dosw.parcial.entity.ComputadorEntity;
import edu.dosw.parcial.entity.PerifericoEntity;
import edu.dosw.parcial.exception.ResourceNotFoundException;
import edu.dosw.parcial.mapper.ComputadorMapper;
import edu.dosw.parcial.mapper.PerifericoMapper;
import edu.dosw.parcial.model.PermissionName;
import edu.dosw.parcial.model.RoleName;
import edu.dosw.parcial.repository.ColaboradorRepository;
import edu.dosw.parcial.repository.ComputadorRepository;
import edu.dosw.parcial.repository.PerifericoRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ComputadorService {

    private static final Logger log = LoggerFactory.getLogger(ComputadorService.class);

    private final ComputadorRepository computadorRepository;
    private final ColaboradorRepository colaboradorRepository;
    private final PerifericoRepository perifericoRepository;
    private final AuthorizationService authorizationService;
    private final ComputadorMapper computadorMapper;
    private final PerifericoMapper perifericoMapper;

    public ComputadorService(
            ComputadorRepository computadorRepository,
            ColaboradorRepository colaboradorRepository,
            PerifericoRepository perifericoRepository,
            AuthorizationService authorizationService,
            ComputadorMapper computadorMapper,
            PerifericoMapper perifericoMapper) {
        this.computadorRepository = computadorRepository;
        this.colaboradorRepository = colaboradorRepository;
        this.perifericoRepository = perifericoRepository;
        this.authorizationService = authorizationService;
        this.computadorMapper = computadorMapper;
        this.perifericoMapper = perifericoMapper;
    }

    public List<ComputadorResponse> findAll(RoleName actorRole) {
        authorizationService.requirePermission(actorRole, PermissionName.CONSULTAR_COMPUTADORES);
        log.info("Consulta de computadores ejecutada por rol {}", actorRole);
        return computadorMapper.toResponseList(computadorRepository.findAll());
    }

    public ComputadorResponse findById(Long id, RoleName actorRole) {
        authorizationService.requirePermission(actorRole, PermissionName.CONSULTAR_COMPUTADORES);
        log.info("Consulta de computador por id {} ejecutada por rol {}", id, actorRole);

        ComputadorEntity entity = computadorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Computador no encontrado: " + id));
        return computadorMapper.toResponse(entity);
    }

    public ComputadorResponse create(RoleName actorRole, ComputadorRequest request) {
        authorizationService.requirePermission(actorRole, PermissionName.CRUD_COMPUTADORES);
        ColaboradorEntity registradoPor = findColaborador(request.registradoPorId());

        ComputadorEntity entity = computadorMapper.toEntity(request);
        entity.setRegistradoPor(registradoPor);
        return computadorMapper.toResponse(computadorRepository.save(entity));
    }

    public ComputadorResponse update(Long id, RoleName actorRole, ComputadorRequest request) {
        authorizationService.requirePermission(actorRole, PermissionName.CRUD_COMPUTADORES);
        ColaboradorEntity registradoPor = findColaborador(request.registradoPorId());

        ComputadorEntity entity = computadorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Computador no encontrado: " + id));
        computadorMapper.updateEntity(request, entity);
        entity.setRegistradoPor(registradoPor);
        return computadorMapper.toResponse(computadorRepository.save(entity));
    }

    public void delete(Long id, RoleName actorRole) {
        authorizationService.requirePermission(actorRole, PermissionName.CRUD_COMPUTADORES);
        if (!computadorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Computador no encontrado: " + id);
        }
        computadorRepository.deleteById(id);
    }

    public PerifericoResponse addPeripheral(Long computadorId, RoleName actorRole, PerifericoRequest request) {
        authorizationService.requirePermission(actorRole, PermissionName.CRUD_COMPUTADORES);

        ComputadorEntity computador = computadorRepository.findById(computadorId)
                .orElseThrow(() -> new ResourceNotFoundException("Computador no encontrado: " + computadorId));
        ColaboradorEntity registradoPor = findColaborador(request.registradoPorId());

        PerifericoEntity periferico = perifericoMapper.toEntity(request);
        periferico.setRegistradoPor(registradoPor);
        periferico.setComputador(computador);

        return perifericoMapper.toResponse(perifericoRepository.save(periferico));
    }

    public List<PerifericoResponse> findPeripherals(RoleName actorRole) {
        authorizationService.requirePermission(actorRole, PermissionName.CONSULTAR_COMPUTADORES);
        log.info("Consulta de perifericos ejecutada por rol {}", actorRole);
        return perifericoMapper.toResponseList(perifericoRepository.findAll());
    }

    private ColaboradorEntity findColaborador(Long id) {
        return colaboradorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Colaborador no encontrado: " + id));
    }

}