package ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import application.CompraFachada;
import application.OperacoesFachada;
import application.VendaFachada;
import domain.ControleCadastro;
import domain.ControleEstoque;
import domain.EstoqueRepository;
import domain.OperacaoRepository;
import infraestructure.EstoqueDBDAO;
import infraestructure.EstoqueRepositoryImplementation;
import infraestructure.OperacaoDBDAO;
import infraestructure.OperacaoRepositoryImplementation;

public class TesteUI {
	public JFrame janela;
	private JPanel painelPrincipal;
	private JButton bCompra;
	private JButton bOperacoes;
	private JButton bEstocar;
	private JButton bVenda;
	private JButton bGavetas;
	private JButton bSair;

	private ControleEstoque ce;
	private ControleCadastro cc;

	public TesteUI(ControleEstoque ce, ControleCadastro cc) {
		this.ce = ce;
		this.cc = cc;
		montaTela();
	}
	
	public void montaTela() {
		setNimbusLookAndFeel();
		preparaJanela();
		preparaPainel();
		preparaBotaoCompra();
		preparaBotaoVenda();
		preparaBotaoEstocar();
		preparaBotaoOperacoes();
		preparaBotaoGavetas();
		preparaBotaoSair();
		mostraTela();
	}

	private void preparaJanela() {
		janela = new JFrame("WoodEstoque");
		janela.setLayout(new FlowLayout(FlowLayout.CENTER));
		janela.setResizable(false);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}

	private void preparaPainel() {
		painelPrincipal = new JPanel();
		JLabel titulo = new JLabel("WoodEstoque Gerenciamento", SwingConstants.CENTER);
		titulo.setFont(new Font("Verdana", Font.BOLD, 30));
		painelPrincipal.add(titulo);
		janela.add(painelPrincipal);

	}

	private void preparaBotaoCompra() {
		bCompra = new JButton("Compra");
		bCompra.setPreferredSize(new Dimension(100, 30));
		janela.add(bCompra);
		bCompra.addActionListener((e) -> new BotaoNovaFerramenta(cc));
	}

	private void preparaBotaoVenda() {
		bVenda = new JButton("Venda");
		bVenda.setPreferredSize(new Dimension(100, 30));
		janela.add(bVenda);
		bVenda.addActionListener((e) -> new BotaoVendaUI(new VendaFachada(ce, cc)));
	}

	private void preparaBotaoEstocar() {
		bEstocar = new JButton("Estocar");
		bEstocar.setPreferredSize(new Dimension(100, 30));
		janela.add(bEstocar);
		bEstocar.addActionListener((e) -> new BotaoEstocarUI(new CompraFachada(ce, cc)));
	}

	private void preparaBotaoOperacoes() {
		bOperacoes = new JButton("Operacoes");
		bOperacoes.setPreferredSize(new Dimension(100, 30));
		janela.add(bOperacoes);
		bOperacoes.addActionListener((e) -> new BotaoOperacoesUI(new OperacoesFachada(ce, cc)));

	}

	private void preparaBotaoGavetas() {
		bGavetas = new JButton("Gavetas");
		bGavetas.setPreferredSize(new Dimension(100, 30));
		janela.add(bGavetas);
		bGavetas.addActionListener((e) -> new BotaoGavetaUI(cc).setVisible(true));
	}

	private void preparaBotaoSair() {
		bSair = new JButton("Sair");
		bSair.setPreferredSize(new Dimension(100, 30));
		bSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		janela.add(bSair);

	}

	private void setNimbusLookAndFeel() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}
	}

	private void mostraTela() {

		janela.setSize(540, 140);
		janela.setVisible(true);

	}

}
