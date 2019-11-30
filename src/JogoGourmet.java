import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JogoGourmet {

    List<Qualidade> qualidades = new ArrayList<>();
    List<Alimento> alimentos = new ArrayList<>();

    JFrame frame = new JFrame("MainFrame");

    public JogoGourmet() {
        Alimento macarrao = new Alimento("Lasanha", "Massa", new ArrayList<>());
        Alimento boloDeChocolate = new Alimento("Bolo de Chocolate", "NãoMassa", new ArrayList<>());
        alimentos.add(macarrao);
        alimentos.add(boloDeChocolate);
    }

    public void iniciarPerguntas() {

        String[] options = new String[2];
        options[0] = "Sim";
        options[1] = "Não";

        Integer isMassa = JOptionPane.showOptionDialog(frame, "O prato que você pensou é massa?", "Confirme",0, JOptionPane.QUESTION_MESSAGE, null, options, null);

        if(isMassa == 0) {
            List<Alimento> massas = alimentos.stream().filter(a -> a.getTipo().equals("Massa")).collect(Collectors.toList());
            perguntasDeQualidades(massas, "Massa");
        } else {
            List<Alimento> naoMassas = alimentos.stream().filter(a -> a.getTipo().equals("NãoMassa")).collect(Collectors.toList());
            perguntasDeQualidades(naoMassas, "NãoMassa");
        }
    }

    public void perguntasDeQualidades (List<Alimento> alimentos, String tipo) {

        Integer qualidadeAtual = 0;
        List<Qualidade> qualidadesDaLinha = filterLinha(0, tipo);
        List<String> qualidadesPerguntadas = new ArrayList<>();

        Integer resposta;
        Integer linhaAtual = 0;

        while (qualidadeAtual < qualidadesDaLinha.size()) {

            String[] options = new String[2];
            options[0] = "Sim";
            options[1] = "Não";

            resposta = JOptionPane.showOptionDialog(frame, "O prato que você pensou é " + qualidadesDaLinha.get(qualidadeAtual).getNome() +"?", "Confirme",0, JOptionPane.QUESTION_MESSAGE, null, options, null);
            if(resposta == 0) {
                qualidadesPerguntadas.add( qualidadesDaLinha.get(qualidadeAtual).getNome());
                qualidadeAtual ++;
            } else {
                linhaAtual = qualidadesDaLinha.get(qualidadeAtual).getLinha();
                qualidadeAtual = 0;
                qualidadesDaLinha = filterLinha(linhaAtual, tipo);
            }
        }

        Alimento alimentoPergunta = alimentos.stream().filter(a -> a.getQualidades().equals(qualidadesPerguntadas)).findFirst().get();
        Boolean acertou = perguntarAlimento(alimentoPergunta);

        if(acertou) {
            JOptionPane.showMessageDialog(frame, "Acertei denovo!");
        } else {
            String nome = JOptionPane.showInputDialog(frame, "Qual prato você pensou?");
            String qualidade = JOptionPane.showInputDialog(frame, nome + " é ____ mas " + alimentoPergunta.getNome() + " não.");
            qualidadesPerguntadas.add(qualidade);
            Alimento novoAlimento = new Alimento(nome, tipo, qualidadesPerguntadas);
            this.alimentos.add(novoAlimento);
            this.qualidades.add(new Qualidade(qualidade, tipo, this.alimentos.size(), linhaAtual));
        }
    }

    public List<Qualidade> filterLinha(Integer linha, String tipo) {
        return this.qualidades.stream().filter(q -> q.getLinhaPai() == linha && q.tipo.equals(tipo)).collect(Collectors.toList());
    }

    public Boolean perguntarAlimento(Alimento alimento) {

        String[] options = new String[2];
        options[0] = "Sim";
        options[1] = "Não";

        Integer acertou = JOptionPane.showOptionDialog(frame, "O prato que você pensou é " + alimento.getNome() +"?", "Confirme",0, JOptionPane.QUESTION_MESSAGE, null, options, null);

        return acertou == 0 ? true : false;
    }

}
