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

        Integer indiceAtual = 0;
        List<Qualidade> qualidadesDaLinhaAtual = filterLinha(0, tipo);
        List<Qualidade> qualidadesPerguntadas = new ArrayList<>();

        Integer linhaAtual = 0;

        while (indiceAtual < qualidadesDaLinhaAtual.size()) {

            String[] options = new String[2];
            options[0] = "Sim";
            options[1] = "Não";

            Integer resposta = JOptionPane.showOptionDialog(frame, "O prato que você pensou é " + qualidadesDaLinhaAtual.get(indiceAtual).getNome() +"?", "Confirme",0, JOptionPane.QUESTION_MESSAGE, null, options, null);
            if(resposta == 0) {
                qualidadesPerguntadas.add( qualidadesDaLinhaAtual.get(indiceAtual));
                indiceAtual ++;
            } else {
                linhaAtual = qualidadesDaLinhaAtual.get(indiceAtual).getLinha();
                indiceAtual = 0;
                qualidadesDaLinhaAtual = filterLinha(linhaAtual, tipo);
            }
        }

        Alimento alimentoPergunta = alimentos.stream().filter(a -> a.getQualidades().equals(qualidadesPerguntadas)).findFirst().get();
        Boolean acertou = perguntarAlimento(alimentoPergunta);

        if(acertou) {
            JOptionPane.showMessageDialog(frame, "Acertei denovo!");
        } else {
            String nomeDoNovoAlimento = JOptionPane.showInputDialog(frame, "Qual prato você pensou?");
            String nomeDaNovaQualidade = JOptionPane.showInputDialog(frame, nomeDoNovoAlimento + " é ____ mas " + alimentoPergunta.getNome() + " não.");
            Qualidade novaQualidade = new Qualidade(nomeDaNovaQualidade, tipo, this.alimentos.size(), linhaAtual);
            qualidadesPerguntadas.add(novaQualidade);
            Alimento novoAlimento = new Alimento(nomeDoNovoAlimento, tipo, qualidadesPerguntadas);
            this.alimentos.add(novoAlimento);
            this.qualidades.add(novaQualidade);
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
