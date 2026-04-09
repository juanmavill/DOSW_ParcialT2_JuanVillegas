package edu.dosw.parcial.config;

import edu.dosw.parcial.entity.ColaboradorEntity;
import edu.dosw.parcial.entity.ComputadorEntity;
import edu.dosw.parcial.entity.PerifericoEntity;
import edu.dosw.parcial.entity.PermissionEntity;
import edu.dosw.parcial.entity.RoleEntity;
import edu.dosw.parcial.model.PermissionName;
import edu.dosw.parcial.model.PeripheralType;
import edu.dosw.parcial.model.RoleName;
import edu.dosw.parcial.repository.ColaboradorRepository;
import edu.dosw.parcial.repository.ComputadorRepository;
import edu.dosw.parcial.repository.PerifericoRepository;
import edu.dosw.parcial.repository.PermissionRepository;
import edu.dosw.parcial.repository.RoleRepository;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;
    private final ColaboradorRepository colaboradorRepository;
    private final ComputadorRepository computadorRepository;
    private final PerifericoRepository perifericoRepository;

    public DataInitializer(
            PermissionRepository permissionRepository,
            RoleRepository roleRepository,
            ColaboradorRepository colaboradorRepository,
            ComputadorRepository computadorRepository,
            PerifericoRepository perifericoRepository) {
        this.permissionRepository = permissionRepository;
        this.roleRepository = roleRepository;
        this.colaboradorRepository = colaboradorRepository;
        this.computadorRepository = computadorRepository;
        this.perifericoRepository = perifericoRepository;
    }

    @Override
    public void run(String... args) {
        seedPermissions();
        seedRoles();
        seedColaboradores();
        seedComputadores();
        seedPerifericos();
    }

    private void seedPermissions() {
        for (PermissionName permissionName : PermissionName.values()) {
            permissionRepository.findByNombre(permissionName).orElseGet(() -> {
                PermissionEntity permission = new PermissionEntity();
                permission.setNombre(permissionName);
                return permissionRepository.save(permission);
            });
        }
    }

    private void seedRoles() {
        createRoleIfMissing(
                RoleName.ADMINISTRADOR,
                PermissionName.CRUD_COLABORADORES,
                PermissionName.CONSULTAR_COLABORADORES,
                PermissionName.CRUD_COMPUTADORES,
                PermissionName.CONSULTAR_COMPUTADORES);

        createRoleIfMissing(
                RoleName.LIDER,
                PermissionName.CONSULTAR_COLABORADORES,
                PermissionName.CRUD_COMPUTADORES,
                PermissionName.CONSULTAR_COMPUTADORES);

        createRoleIfMissing(
                RoleName.ASISTENTE,
                PermissionName.CONSULTAR_COMPUTADORES);
    }

    private void createRoleIfMissing(RoleName roleName, PermissionName... permissions) {
        roleRepository.findByNombre(roleName).orElseGet(() -> {
            RoleEntity role = new RoleEntity();
            role.setNombre(roleName);
            role.setPermisos(new HashSet<>(
                    List.of(permissions).stream()
                            .map(permission -> permissionRepository.findByNombre(permission).orElseThrow())
                            .toList()));
            return roleRepository.save(role);
        });
    }

    private void seedColaboradores() {
        if (colaboradorRepository.count() >= 3) {
            return;
        }

        if (colaboradorRepository.findByCorreo("admin@escuelaing.edu.co").isEmpty()) {
            colaboradorRepository.save(buildColaborador(
                    "admin@escuelaing.edu.co",
                    "admin123",
                    "Ana",
                    "Admin",
                    "3001112233",
                    LocalDate.of(1990, 1, 10),
                    RoleName.ADMINISTRADOR));
        }

        if (colaboradorRepository.findByCorreo("lider@escuelaing.edu.co").isEmpty()) {
            colaboradorRepository.save(buildColaborador(
                    "lider@escuelaing.edu.co",
                    "lider123",
                    "Luis",
                    "Lider",
                    "3001112244",
                    LocalDate.of(1992, 5, 20),
                    RoleName.LIDER));
        }

        if (colaboradorRepository.findByCorreo("asistente@escuelaing.edu.co").isEmpty()) {
            colaboradorRepository.save(buildColaborador(
                    "asistente@escuelaing.edu.co",
                    "asistente123",
                    "Sara",
                    "Asistente",
                    "3001112255",
                    LocalDate.of(1998, 9, 15),
                    RoleName.ASISTENTE));
        }
    }

    private ColaboradorEntity buildColaborador(
            String correo,
            String contrasena,
            String nombre,
            String apellido,
            String telefono,
            LocalDate fechaNacimiento,
            RoleName roleName) {
        ColaboradorEntity c = new ColaboradorEntity();
        c.setCorreo(correo);
        c.setContrasena(contrasena);
        c.setNombre(nombre);
        c.setApellido(apellido);
        c.setTelefono(telefono);
        c.setFechaNacimiento(fechaNacimiento);
        c.setRol(roleRepository.findByNombre(roleName).orElseThrow());
        return c;
    }

    private void seedComputadores() {
        if (computadorRepository.count() >= 3) {
            return;
        }

        ColaboradorEntity admin = colaboradorRepository.findByCorreo("admin@escuelaing.edu.co").orElseThrow();
        ColaboradorEntity lider = colaboradorRepository.findByCorreo("lider@escuelaing.edu.co").orElseThrow();

        if (computadorRepository.findBySerial("SER-001").isEmpty()) {
            computadorRepository.save(buildComputador("SER-001", "Dell", 1, false, admin));
        }
        if (computadorRepository.findBySerial("SER-002").isEmpty()) {
            computadorRepository.save(buildComputador("SER-002", "Lenovo", 2, true, lider));
        }
        if (computadorRepository.findBySerial("SER-003").isEmpty()) {
            computadorRepository.save(buildComputador("SER-003", "HP", 3, false, admin));
        }
    }

    private ComputadorEntity buildComputador(
            String serial,
            String marca,
            Integer numeroLaboratorio,
            boolean ocupado,
            ColaboradorEntity registradoPor) {
        ComputadorEntity c = new ComputadorEntity();
        c.setSerial(serial);
        c.setMarca(marca);
        c.setNumeroLaboratorio(numeroLaboratorio);
        c.setOcupado(ocupado);
        c.setRegistradoPor(registradoPor);
        return c;
    }

    private void seedPerifericos() {
        if (perifericoRepository.count() >= 3) {
            return;
        }

        ColaboradorEntity admin = colaboradorRepository.findByCorreo("admin@escuelaing.edu.co").orElseThrow();
        ColaboradorEntity lider = colaboradorRepository.findByCorreo("lider@escuelaing.edu.co").orElseThrow();

        ComputadorEntity c1 = computadorRepository.findBySerial("SER-001").orElseThrow();
        ComputadorEntity c2 = computadorRepository.findBySerial("SER-002").orElseThrow();
        ComputadorEntity c3 = computadorRepository.findBySerial("SER-003").orElseThrow();

        perifericoRepository.save(buildPeripheral(PeripheralType.ENTRADA, "Teclado", false, admin, c1));
        perifericoRepository.save(buildPeripheral(PeripheralType.SALIDA, "Monitor", false, lider, c2));
        perifericoRepository.save(buildPeripheral(PeripheralType.COMUNICACION, "Adaptador USB WiFi", true, admin, c3));
    }

    private PerifericoEntity buildPeripheral(
            PeripheralType tipo,
            String nombre,
            boolean inalambrico,
            ColaboradorEntity registradoPor,
            ComputadorEntity computador) {
        PerifericoEntity p = new PerifericoEntity();
        p.setTipo(tipo);
        p.setNombre(nombre);
        p.setInalambrico(inalambrico);
        p.setRegistradoPor(registradoPor);
        p.setComputador(computador);
        return p;
    }
}