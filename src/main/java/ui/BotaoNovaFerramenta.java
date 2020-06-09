package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import domain.ControleCadastro;
import domain.Ferramenta;

@SuppressWarnings({ "serial", "deprecation" })
public class BotaoNovaFerramenta extends JFrame {

	private ControleCadastro cc;
	private PresentationModel<Ferramenta> pmModel = new PresentationModel<>();
	private JTextField campoNome;
	private JTextField campoId;
	private JButton bAdicionar;
	private JButton bVoltar;

	public BotaoNovaFerramenta(ControleCadastro cc) {
		this.cc = cc;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Nova Ferramenta");

		initModels();
		initComponents();
		initLayout();
		pack();
		setVisible(true);

	}

	private void initModels() {
		pmModel.setBean(new Ferramenta());
	}

	private void initComponents() {
		campoNome = BasicComponentFactory.createTextField(pmModel.getModel("nome"));
		campoId = BasicComponentFactory.createIntegerField(pmModel.getModel("id"));
		
		bAdicionar = new JButton("Adicionar");
		bAdicionar.addActionListener((e) -> adicionarFerramenta());

		bVoltar = new JButton("Voltar");
		bVoltar.addActionListener((e) -> setVisible(false));
	}

	private void adicionarFerramenta() {
		if (!cc.verificaFerramenta(pmModel.getBean())) {
			cc.novaFerramenta(pmModel.getBean());
			//cc.ferramentasCadastradas().forEach((f -> System.out.println(f.getId()+" "+ f.getNome())));
			JOptionPane.showMessageDialog(this, "Ferramenta cadastrada com sucesso! ");
			System.out.println(pmModel.getBean().getNome());
			System.out.println(pmModel.getBean().getId());
			setVisible(false);
		} else {
			JOptionPane.showMessageDialog(this, "Id já cadastrado, informe um novo Id! ");
		}
	}

	private void initLayout() {
		setLayout(new BorderLayout());
		// titulo
		JLabel title = new JLabel("Nova Ferramenta");
		title.setFont(new Font("Verdana", 3, 20));
		add(title, BorderLayout.NORTH);
		// centro layout
		FormLayout layout = new FormLayout("5px, pref, 5px, 50dlu", "pref, pref");
		DefaultFormBuilder builder = new DefaultFormBuilder(layout);
		builder.nextColumn();
		builder.append("ID: ", campoId);
		builder.nextLine();
		builder.nextColumn();
		builder.append("Ferramenta:", campoNome);
		add(builder.build(), BorderLayout.CENTER);
		// add botoes
		JPanel pBotoes = new JPanel(new FlowLayout());
		pBotoes.add(bAdicionar);
		pBotoes.add(bVoltar);
		add(pBotoes, BorderLayout.SOUTH);
	}
}
