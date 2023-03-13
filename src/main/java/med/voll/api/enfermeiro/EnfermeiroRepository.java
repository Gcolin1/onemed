package med.voll.api.enfermeiro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;


public interface EnfermeiroRepository extends JpaRepository <Enfermeiro, Long> {

    @Query(value = "Select m FROM Enfermeiro m WHERE m.nome like %?1%")
    ArrayList<Enfermeiro> getEnfermeiroName(String nome);

}
