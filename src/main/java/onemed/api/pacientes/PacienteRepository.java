package onemed.api.pacientes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface PacienteRepository extends JpaRepository<Paciente, Long>  {
    @Query(value = "SELECT p FROM Paciente p WHERE p.nome like %?1%")
    ArrayList<Paciente> getPacienteByName(String nome);
}
