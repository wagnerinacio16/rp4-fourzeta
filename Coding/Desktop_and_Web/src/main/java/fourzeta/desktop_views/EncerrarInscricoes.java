package fourzeta.desktop_views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import fourzeta.controllers.desktop.EncerrarController;
import fourzeta.models.Circuito;
import fourzeta.models.Gerente;
import fourzeta.models.Torneio;
import fourzeta.repository.CircuitoResource;

public class EncerrarInscricoes extends JFrame {

	private final Dimension SIZE = new Dimension(600, 250);
	private EncerrarController controllerInscricao;
	private JButton btnInscricao;
	private JComboBox comboCircuito;
	private JComboBox comboTorneio;
	private JLabel lblIcon;
	private Torneio torneio;
	private Gerente gerente;

	public EncerrarInscricoes(Gerente gerente, Torneio torneio) throws ParseException, IOException, NotBoundException {
		this.torneio = torneio;
		this.gerente = gerente;
		setTitle("Sistema de Gerenciamento de Padel");
		this.setSize(SIZE);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// mudando o icon
		ImageIcon imgLogin = new ImageIcon("assets\\FourZeta.png");
		setIconImage(imgLogin.getImage());
		lblIcon = new JLabel(imgLogin);
		getContentPane().add(lblIcon);

		JLabel Titulo = new JLabel("Encerrar Inscrições");
		Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo.setBounds(0, 12, 600, 58);
		Titulo.setFont(new Font("Times New Roman", Font.BOLD, 45));
		getContentPane().add(Titulo);

		CircuitoResource cr = new CircuitoResource();
		comboCircuito = new JComboBox();
		comboCircuito.setBounds(26, 119, 165, 22);
		comboCircuito.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboTorneio = new JComboBox();
		comboTorneio.setBounds(225, 119, 165, 22);
		comboTorneio.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		comboCircuito.addItem("Selecionar");
		comboTorneio.addItem("Selecionar");
		for (Circuito circuito : cr.listaCircuitos()) {
			comboCircuito.addItem(circuito.getNome());
		}
		comboCircuito.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				comboTorneio.removeAllItems();
				comboTorneio.addItem("Selecionar");
				for (Circuito circuito : cr.listaCircuitos()) {

					if (circuito.getNome().equalsIgnoreCase(comboCircuito.getSelectedItem().toString())) {
						for (int i = 0; i < circuito.getTorneios().size(); i++) {
							comboTorneio.addItem(circuito.getTorneios().get(i).getNome());
						}
					}
				}
			}
		});
		getContentPane().add(comboCircuito);
		getContentPane().add(comboTorneio);

		btnInscricao = new JButton("Encerrar");
		controllerInscricao = new EncerrarController(gerente, torneio, this);
		btnInscricao.addActionListener(controllerInscricao);
		btnInscricao.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnInscricao.setBounds(410, 118, 159, 23);
		getContentPane().add(btnInscricao);

		JLabel lblCircuito = new JLabel("Circuito");
		lblCircuito.setBounds(26, 102, 66, 15);
		getContentPane().add(lblCircuito);

		JLabel lblTorneio = new JLabel("Torneio");
		lblTorneio.setBounds(225, 102, 66, 15);
		getContentPane().add(lblTorneio);

		JButton btnVoltar = new JButton("Voltar");

		btnVoltar.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnVoltar.addActionListener(new ActionListener() { // Implementando Voltar

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Inicial inicial = null;
				try {
					inicial = new Inicial(gerente);
				} catch (ParseException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setVisible(false);
				inicial.setVisible(true);
			}
		});
		btnVoltar.setBounds(480, 198, 89, 23);
		getContentPane().add(btnVoltar);

		JLabel lblselecioneOCircuito = new JLabel(
				"*Selecione o Circuito e o Torneio que deseja encerrar as inscrições.");
		lblselecioneOCircuito.setBounds(12, 181, 494, 58);
		getContentPane().add(lblselecioneOCircuito);

	}

	public void notifyEncerrarInscricaoCircuito() {
		JOptionPane.showMessageDialog(this, "Selecione um Circuito para encerrar inscrições.");

	}

	public void notifyEncerrarInscricaoTorneio() {
		JOptionPane.showMessageDialog(this, "Selecione um Torneio para encerrar inscrições.");

	}

	public void notifyEncerramentoSucesso() {
		JOptionPane.showMessageDialog(this, "Inscrições encerradas com sucesso!!");

	}

	public JComboBox getComboCircuito() {
		return comboCircuito;
	}

	public void setComboCircuito(JComboBox comboCircuito) {
		this.comboCircuito = comboCircuito;
	}

	public JComboBox getComboTorneio() {
		return comboTorneio;
	}

	public void setComboTorneio(JComboBox comboTorneio) {
		this.comboTorneio = comboTorneio;
	}

	public JButton getBtnInscricao() {
		return btnInscricao;
	}

	public void setBtnInscricao(JButton btnInscricao) {
		this.btnInscricao = btnInscricao;
	}

	public Dimension getSIZE() {
		return SIZE;
	}

//	public static void main(String[] args) throws IOException, ParseException, NotBoundException {
//
//		EncerrarInscricoes frame = new EncerrarInscricoes(null);
//		frame.setVisible(true);
//		frame.setSize(frame.SIZE);
//		frame.setLocationRelativeTo(null);
//
//	}
}
