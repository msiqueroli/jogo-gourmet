import java.util.List;

public class Alimento {

    private String nome;
    private String tipo;
    private List<String> qualidades;

    public Alimento(String nome, String tipo, List<String> qualidades) {
        this.nome = nome;
        this.tipo = tipo;
        this.qualidades = qualidades;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<String> getQualidades() {
        return qualidades;
    }

    public void setQualidades(List<String> qualidades) {
        this.qualidades = qualidades;
    }

    @Override
    public String toString() {
        return "Alimento{" +
                "nome='" + nome + '\'' +
                ", tipo='" + tipo + '\'' +
                ", qualidades=" + qualidades +
                '}';
    }
}
