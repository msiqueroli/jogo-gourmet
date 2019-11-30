public class Qualidade {

    String nome;
    String tipo;
    Integer linha;
    Integer linhaPai;

    public Qualidade(String nome, String tipo, Integer linha, Integer linhaPai) {
        this.nome = nome;
        this.tipo = tipo;
        this.linha = linha;
        this.linhaPai = linhaPai;
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

    public Integer getLinha() {
        return linha;
    }

    public void setLinha(Integer linha) {
        this.linha = linha;
    }

    public Integer getLinhaPai() {
        return linhaPai;
    }

    public void setLinhaPai(Integer linhaPai) {
        this.linhaPai = linhaPai;
    }
}
