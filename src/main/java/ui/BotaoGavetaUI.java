package ui;

import java.awt.BorderLayout;
import java.awt.Color;
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
import domain.Gaveta;

@SuppressWarnings({ "serial", "deprecation" })
public class BotaoGavetaUI extends JFrame {

	private JTextField tfID;
	private JTextField tfNome;
	private JButton btSalvar;
	private JButton btVoltar;

	private ControleCadastro cc;

	private PresentationModel<Gaveta> pmModel = new PresentationModel<>();

	public BotaoGavetaUI(ControleCadastro cc) {
		this.cc = cc;
		setTitle("Gaveta");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBackground(Color.WHITE);

		initModels();
		initComponents();
		initLayout();
		pack();
	}

	private void initModels() {
		pmModel.setBean(new Gaveta());
	}

	private void salvarGaveta() {
		if (!cc.verificaGaveta(pmModel.getBean())) {
			cc.saveGaveta(pmModel.getBean());
			System.out.println("ID: " + pmModel.getBean().getId() + " nome: " + pmModel.getBean().getNome());
			cc.gavetasCadastradas().forEach(g -> g.getNome());
			JOptionPane.showMessageDialog(this, "Gaveta cadastrada!");
			setVisible(false);
		} else {
			JOptionPane.showMessageDialog(this, "Id ja cadastrado!");
		}
	}

	private void initComponents() {
		tfID = BasicComponentFactory.createIntegerField(pmModel.getModel("id"));
		tfNome = BasicComponentFactory.createTextField(pmModel.getModel("nome"));

		btSalvar = new JButton("Salvar");
		btSalvar.addActionListener((e) -> salvarGaveta());

		btVoltar = new JButton("Voltar");
		btVoltar.addActionListener((e) -> setVisible(false));
	}

	
	private void initLayout() {
		setLayout(new BorderLayout());

		JLabel title = new JLabel("Adicionar Gaveta");
		title.setFont(new Font("Arial", 3, 20));
		add(title, BorderLayout.NORTH);

		FormLayout layout = new FormLayout("10px, pref, 5px, 60dlu", "pref, pref");
		DefaultFormBuilder builder = new DefaultFormBuilder(layout);
		builder.nextColumn();
		builder.append("ID  ", tfID);
		builder.nextLine();
		builder.nextColumn();
		builder.append("Nome  ", tfNome);
		add(builder.build(), BorderLayout.CENTER);

		JPanel panelBotao = new JPanel(new FlowLayout());
		panelBotao.add(btSalvar);
		panelBotao.add(btVoltar);
		add(panelBotao, BorderLayout.SOUTH);

	}

}
