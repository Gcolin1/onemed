package med.voll.api.enfermeiro;

import med.voll.api.endereco.DadosEndereco;

public record DadosCadastroEnfermeiros(
        String nome,
        String cpf,
        String email,
        String cre,
        String telefone,
        DadosEndereco endereco

        ) {
}
