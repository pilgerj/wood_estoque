package ui;

import java.awt.BorderLayout;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

public class ProdutoForm extends JFrame {
	
	private PresentationModel<Produto> model;
	private JFormattedTextField codigoField;
	private JTextField descricaoField;
	private JButton setBeanButton;
	private JButton showBeanButton;
	private JButton alteraBeanButton;

	public static void main(String[] args) {
		ProdutoForm form = new ProdutoForm();
		form.setVisible(true);
	}
	
	public ProdutoForm() {
		initModel();
		initComponents();
		initLayout();
	}

	private void initModel() {
		model = new PresentationModel<>();
		
		model.addBeanPropertyChangeListener(e -> {
			if (e.getPropertyName().equals("codigo")) {
				JOptionPane.showMessageDialog(this, "Codigo Alterado para " + e.getNewValue());
			}
		});
	}

	private void initLayout() {
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		DefaultFormBuilder builder = new DefaultFormBuilder(new FormLayout("pref, 5px, 80dlu"));
		
		builder.append("Codigo", codigoField);
		builder.nextLine();
		builder.append("Descricao", descricaoField);
		builder.nextLine();
		builder.append(setBeanButton, showBeanButton);
		builder.nextLine();
		builder.append(alteraBeanButton);
		
		getContentPane().add(builder.build(), BorderLayout.CENTER);
	}

	private void initComponents() {
		
		//SelectionInList<Produto> selectionInListProduto = new Sele
		codigoField = BasicComponentFactory.createIntegerField(model.getModel("codigo"));
		descricaoField = BasicComponentFactory.createTextField(model.getModel("descricao"));
		
		setBeanButton = new JButton("Seta Bean");
		showBeanButton = new JButton("Apresenta Bean");
		alteraBeanButton = new JButton("Altera Bean");
		
		setBeanButton.addActionListener(e -> {
			Produto p = new Produto();
			p.setCodigo(new Random(100).nextInt());
			p.setDescricao("Produto " + p.getCodigo());
			model.setBean(p);
		});
		
		showBeanButton.addActionListener(e -> {
			Produto bean = model.getBean();
			JOptionPane.showMessageDialog(this, bean.toString());
		});
		alteraBeanButton.addActionListener(e -> {
			Produto bean = model.getBean();
			bean.setCodigo(new Random().nextInt());
			bean.setDescricao("Produto Alterado");
		});
	}

}
