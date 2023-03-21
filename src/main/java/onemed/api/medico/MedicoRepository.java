package onemed.api.medico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

//jpa recebe passa dois tipos de objetos tipo da entidade a ser trabalhada e tipo de atributo da chave primaria
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    @Query(value = "SELECT m FROM Medico m WHERE m.nome like %?1%")
    ArrayList<Medico> getMedicoByName(String nome);

}
