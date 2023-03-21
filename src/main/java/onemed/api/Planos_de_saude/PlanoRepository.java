package onemed.api.Planos_de_saude;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface PlanoRepository extends JpaRepository<PlanoDeSaude, Long> {
    @Query(value = "SELECT m FROM plano_de_saude m WHERE m.nome like %?1%")
    ArrayList<PlanoDeSaude> getPlanoByName(String nome);

}
