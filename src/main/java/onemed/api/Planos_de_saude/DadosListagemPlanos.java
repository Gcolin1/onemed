package onemed.api.Planos_de_saude;


public record DadosListagemPlanos(String nome, String telefone, String coberturas, String cnpj) {

    public DadosListagemPlanos(PlanoDeSaude plano){
        this(plano.getNome(), plano.getTelefone(), plano.getCnpj(), plano.getCoberturas());
    }
}
