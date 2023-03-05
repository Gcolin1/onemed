package med.voll.api.medico;

import org.springframework.data.jpa.repository.JpaRepository;
//jpa recebe passa dois tipos de objetos tipo da entidade a ser trabalhada e tipo de atributo da chave primaria
public interface MedicoRepository extends JpaRepository<Medico, Long> {

}
