package fourzeta.desktop_views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import fourzeta.controllers.desktop.ExcluirTorneioController;
import fourzeta.models.Gerente;
import fourzeta.models.Torneio;
import fourzeta.repository.TorneioResource;

public class ExcluirTorneio extends JFrame {

	private final Dimension SIZE = new Dimension(350, 200);
	private JLabel lblTitulo;
	private JLabel lblNome;
	private JButton btnVoltar;
	private JButton btnExcluir;
	private JComboBox comboBoxTorneio;

	public ExcluirTorneio(Gerente gerente) throws ParseException, IOException, NotBoundException {

		setTitle("Excluir Torneio");
		this.setSize(SIZE);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		lblTitulo = new JLabel("Excluir Torneio");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 30));
		lblTitulo.setBounds(12, 12, 321, 26);
		getContentPane().add(lblTitulo);

		lblNome = new JLabel("Selecione um torneio:");
		lblNome.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNome.setBounds(22, 66, 300, 15);
		getContentPane().add(lblNome);

		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				SelecionarTorneio inicio = null;
				try {
					inicio = new SelecionarTorneio(gerente);
				} catch (ParseException | IOException | NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setVisible(false);
				inicio.setVisible(true);
			}
		});
		btnVoltar.setBounds(22, 133, 114, 25);
		getContentPane().add(btnVoltar);

		btnExcluir = new JButton("Excluir");
		ExcluirTorneioController controller = new ExcluirTorneioController(gerente, this);
		btnExcluir.addActionListener(controller);
		btnExcluir.setBounds(212, 133, 114, 25);
		getContentPane().add(btnExcluir);

		TorneioResource tr = new TorneioResource();
		comboBoxTorneio = new JComboBox();
		comboBoxTorneio.addItem("Selecionar");
		for (Torneio torneio : tr.listaTorneios()) {
			comboBoxTorneio.addItem(torneio.getNome());
		}
		comboBoxTorneio.setBounds(22, 86, 301, 24);
		getContentPane().add(comboBoxTorneio);
	}
	

	public JComboBox getComboBoxTorneio() {
		return comboBoxTorneio;
	}


	public void setComboBoxTorneio(JComboBox comboBoxTorneio) {
		this.comboBoxTorneio = comboBoxTorneio;
	}

	public void notifyExluirTorneio() {
		JOptionPane.showMessageDialog(null, "Torneio excluido com Sucesso!");

	}
	public static void main(String[] args) throws IOException, ParseException, NotBoundException {
		Gerente g = new Gerente();
		g.setNome("Teste");
		ExcluirTorneio frame = new ExcluirTorneio(g);
		frame.setVisible(true);
		frame.setSize(frame.SIZE);
		frame.setLocationRelativeTo(null);
	}
}