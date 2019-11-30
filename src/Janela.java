import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeListener;

public class Janela extends Frame {

    JogoGourmet jogoGourmet = new JogoGourmet();

    public Janela () {
        super("Jogo Gourmet");

        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
        Long posX = Math.round(tela.getWidth() / 2) - 150;
        Long posY = Math.round(tela.getHeight() / 2) - 75;

        this.setBounds(Integer.parseInt(posX.toString()), Integer.parseInt(posY.toString()), 300, 150);
        this.setResizable(false);
        setLayout(null);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        JLabel menssage = new JLabel("Pense em um prato que gosta");
        menssage.setBounds(50, 45, 230, 30);
        add(menssage);

        JButton button = new JButton("OK");
        button.setBounds(115, 90, 70, 30);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jogoGourmet.iniciarPerguntas();
            }
        });
        add(button);

        this.setVisible(true);

    }
}
