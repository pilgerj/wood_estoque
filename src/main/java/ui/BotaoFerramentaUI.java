/*
package ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.forms.layout.Size;
import com.jgoodies.forms.layout.FormLayout.Measure;

import teste.domain.ControleCadastro;
import teste.domain.Ferramenta;

public class BotaoFerramentaUI {

	private JFrame janelaTelaCompra;
	// private JFrame janela;
	private JPanel painelTitulo;
	private JPanel painelCampos;
	private JPanel painelBotoes;
	private JTextField txtNome;
	private JTextField txtID;
	private JLabel tituloTelaCompra;
	private JButton botaoAdd;
	private JButton botaoVoltar;
	// private Ferramenta ferramenta;
	private PresentationModel<Ferramenta> pmModel = new PresentationModel<>();

	private ControleCadastro cc;

	public BotaoFerramentaUI(ControleCadastro cc) {
		this.cc = cc;
		montaTelaCompra();

	}

	private void montaTelaCompra() {
		pmModel.setBean(new Ferramenta());
		// Frame{
		janelaTelaCompra = new JFrame("new");
		janelaTelaCompra.setResizable(false);
		//

		painelTitulo = new JPanel();

		// Titulo
		tituloTelaCompra = new JLabel("Novo Produto", SwingConstants.CENTER);
		tituloTelaCompra.setFont(new Font("Verdana", Font.BOLD, 20));
		painelTitulo.add(tituloTelaCompra);
		// }

		painelCampos = new JPanel();

		// campoNome
		painelCampos.add(new JLabel("Nome: "));
		txtNome = BasicComponentFactory.createTextField(pmModel.getModel("nome"));
		txtNome.setPreferredSize(new Dimension(150, 30));
		painelCampos.add(txtNome);

		// campoID
		painelCampos.add(new JLabel("ID: "));
		txtID = BasicComponentFactory.createIntegerField(pmModel.getModel("id"));
		txtID.setPreferredSize(new Dimension(150, 30));
		painelCampos.add(txtID);

		//

		// }

		painelBotoes = new JPanel();

		// botaoAdicionar{
		botaoAdd = new JButton("Adicionar");
		painelBotoes.add(botaoAdd);
		botaoAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cc.verificaFerramenta(pmModel.getBean())) {
					cc.novaFerramenta(pmModel.getBean());
					JOptionPane.showMessageDialog(janelaTelaCompra, "Ferramenta cadastrada com sucesso! ");
					System.out.println(pmModel.getBean().getNome());
					System.out.println(pmModel.getBean().getId());
					janelaTelaCompra.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(janelaTelaCompra, "Id já cadastrad, informe um novo Id! ");
				}
			}

		});

		// botaoVoltar
		botaoVoltar = new JButton("Voltar");
		painelBotoes.add(botaoVoltar);
		botaoVoltar.addActionListener((e) -> janelaTelaCompra.setVisible(false));

		// }

		janelaTelaCompra.add(painelTitulo, BorderLayout.NORTH);
		janelaTelaCompra.add(painelCampos, BorderLayout.CENTER);
		janelaTelaCompra.add(painelBotoes, BorderLayout.SOUTH);
		janelaTelaCompra.setSize(200, 220);
		janelaTelaCompra.setVisible(true);
		janelaTelaCompra.pack();

	}
	
}
*/
