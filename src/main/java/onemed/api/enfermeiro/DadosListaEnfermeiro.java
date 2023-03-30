package onemed.api.enfermeiro;

public record DadosListaEnfermeiro(Long id, String nome, String email, String cre, String cpf) {
    public DadosListaEnfermeiro(Enfermeiro enfermeiros){
        this(enfermeiros.getId(), enfermeiros.getNome(), enfermeiros.getEmail(), enfermeiros.getCre(), enfermeiros.getCpf());
    }
}
