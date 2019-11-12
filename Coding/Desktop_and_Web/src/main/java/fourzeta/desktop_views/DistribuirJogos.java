package fourzeta.desktop_views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import fourzeta.controllers.desktop.DistribuirJogosController;
import fourzeta.models.Gerente;
import fourzeta.models.Torneio;

public class DistribuirJogos extends JFrame {
	private final Dimension SIZE = new Dimension(350, 320);
	private Gerente gerente;
	private Torneio torneio;
	private JLabel lblDistribirJogos;
	private JLabel lblCategoria;
	private JComboBox comboBoxCategoria;
	private JComboBox comboBoxQuadra;
	private DistribuirJogosController controller;
	private JLabel lblQuadra;
	private JButton btnVoltar;
	private JButton btnDistribuir;

	public DistribuirJogos(Gerente gerente, Torneio torneio) {
		this.gerente = gerente;
		this.torneio = torneio;
		setTitle("Distribuir Jogos");
		this.setSize(SIZE);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		lblDistribirJogos = new JLabel("Distribuir Jogos");
		lblDistribirJogos.setHorizontalAlignment(SwingConstants.CENTER);
		lblDistribirJogos.setFont(new Font("Dialog", Font.BOLD, 30));
		lblDistribirJogos.setBounds(12, 22, 321, 35);
		getContentPane().add(lblDistribirJogos);

		lblCategoria = new JLabel("Categoria:");
		lblCategoria.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCategoria.setBounds(22, 75, 100, 15);
		getContentPane().add(lblCategoria);

		comboBoxCategoria = new JComboBox();
		comboBoxCategoria.addItem("SELECIONAR");
		comboBoxCategoria.addItem("PRIMEIRA");
		comboBoxCategoria.addItem("SEGUNDA");
		comboBoxCategoria.addItem("TERCEIRA");
		comboBoxCategoria.addItem("QUARTA");
		comboBoxCategoria.addItem("QUINTA");
		comboBoxCategoria.addItem("INICIANTES");
		getContentPane().add(comboBoxCategoria);
		comboBoxCategoria.setBounds(22, 98, 300, 24);
		getContentPane().add(comboBoxCategoria);

		lblQuadra = new JLabel("Quadra:");
		lblQuadra.setFont(new Font("Dialog", Font.BOLD, 14));
		lblQuadra.setBounds(22, 150, 70, 15);
		getContentPane().add(lblQuadra);

		comboBoxQuadra = new JComboBox();
		comboBoxQuadra.addItem("SELECIONAR");	
		comboBoxQuadra.addItem("LARANJA");
		comboBoxQuadra.addItem("AZUL");
		comboBoxQuadra.addItem("VERDE");

		getContentPane().add(comboBoxQuadra);
		comboBoxQuadra.setBounds(22, 173, 300, 24);
		getContentPane().add(comboBoxQuadra);

		
		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				GradeJogos gj = null;
				try {
					gj = new GradeJogos(gerente, torneio);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setVisible(false);
				gj.setVisible(true);
			}
		});
		btnVoltar.setBounds(22, 215, 114, 25);
		getContentPane().add(btnVoltar);

		btnDistribuir = new JButton("Distribuir");
		btnDistribuir.setBounds(208, 215, 114, 25);
		controller = new DistribuirJogosController(gerente,torneio,this);
		btnDistribuir.addActionListener(controller);
	
		getContentPane().add(btnDistribuir);
		
		JLabel lblselecioneACategoria = new JLabel("*Distribuir jogos por categoria na quadra desejada");
		lblselecioneACategoria.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblselecioneACategoria.setBounds(22, 249, 311, 43);
		getContentPane().add(lblselecioneACategoria);
	}
	
	

	public Gerente getGerente() {
		return gerente;
	}



	public void setGerente(Gerente gerente) {
		this.gerente = gerente;
	}



	public Torneio getTorneio() {
		return torneio;
	}



	public void setTorneio(Torneio torneio) {
		this.torneio = torneio;
	}



	public JLabel getLblDistribirJogos() {
		return lblDistribirJogos;
	}



	public void setLblDistribirJogos(JLabel lblDistribirJogos) {
		this.lblDistribirJogos = lblDistribirJogos;
	}



	public JLabel getLblCategoria() {
		return lblCategoria;
	}



	public void setLblCategoria(JLabel lblCategoria) {
		this.lblCategoria = lblCategoria;
	}



	public JComboBox getComboBoxCategoria() {
		return comboBoxCategoria;
	}



	public void setComboBoxCategoria(JComboBox comboBoxCategoria) {
		this.comboBoxCategoria = comboBoxCategoria;
	}



	public JComboBox getComboBoxQuadra() {
		return comboBoxQuadra;
	}



	public void setComboBoxQuadra(JComboBox comboBoxQuadra) {
		this.comboBoxQuadra = comboBoxQuadra;
	}



	public JLabel getLblQuadra() {
		return lblQuadra;
	}



	public void setLblQuadra(JLabel lblQuadra) {
		this.lblQuadra = lblQuadra;
	}



	public JButton getBtnVoltar() {
		return btnVoltar;
	}



	public void setBtnVoltar(JButton btnVoltar) {
		this.btnVoltar = btnVoltar;
	}



	public JButton getBtnDistribuir() {
		return btnDistribuir;
	}



	public void setBtnDistribuir(JButton btnDistribuir) {
		this.btnDistribuir = btnDistribuir;
	}



	public Dimension getSIZE() {
		return SIZE;
	}

	public void notifyInformarQuadra() {
		JOptionPane.showMessageDialog(this, "Informe uma Quadra.");

	}
	
	public void notifyInformarCategoria() {
		JOptionPane.showMessageDialog(this, "Informe uma Categoria.");

	}
	
	public void notifySucesso() {
		JOptionPane.showMessageDialog(this, "Jogos distribuidos com Sucesso!!");

	}


	public static void main(String[] args) {
		Gerente g = new Gerente();
		g.setNome("Wagner");
		Torneio t = new Torneio();
		DistribuirJogos frame = new DistribuirJogos(g, t);
		frame.setVisible(true);
		frame.setSize(frame.SIZE);
		frame.setLocationRelativeTo(null);
	}
}
