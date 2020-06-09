package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import application.CompraAplicacao;
import application.CompraFachada;
import domain.Ferramenta;
import domain.Gaveta;

@SuppressWarnings({ "deprecation", "serial" })
public class BotaoEstocarUI extends JFrame {

	private CompraFachada compraFach;

	private JPanel pBotoes;
	private JButton bEstocar;
	private JButton bVoltar;
	private JTextField txtQtd;
	private JLabel titulo;
	private PresentationModel<CompraAplicacao> pmModel = new PresentationModel<>();
	private SelectionInList<Ferramenta> selectionFerramentas = new SelectionInList<>();
	private SelectionInList<Gaveta> selectionGavetas = new SelectionInList<>();
	private JComboBox<Ferramenta> cbFerramenta;
	private JComboBox<Gaveta> cbGaveta;

	public BotaoEstocarUI(CompraFachada compraFach) {
		this.compraFach = compraFach;

		setResizable(false);

		iniciaModel();
		iniciaComponentes();
		iniciaLayout();
		pack();
		setVisible(true);

	}

	private void iniciaModel() {
		pmModel.setBean(new CompraAplicacao());

		selectionFerramentas.getList().addAll(compraFach.getFerramentas());
		selectionGavetas.getList().addAll(compraFach.getGavetas());

	}

	@SuppressWarnings("unchecked")
	public void iniciaComponentes() {
		// ferramenta
		ListCellRenderer<Ferramenta> cellRendererFerramenta = new ListCellRenderer<Ferramenta>() {
			private DefaultListCellRenderer delegateFerramenta = new DefaultListCellRenderer();

			@Override
			public Component getListCellRendererComponent(JList<? extends Ferramenta> list, Ferramenta value, int index,
					boolean isSelected, boolean cellHasFocus) {
				System.out.println(value);
				return delegateFerramenta.getListCellRendererComponent(list, value == null ? "" : value.getNome(),
						index, isSelected, cellHasFocus);
			}
		};

		cbFerramenta = BasicComponentFactory.createComboBox(selectionFerramentas, cellRendererFerramenta);
		selectionFerramentas.setSelectionHolder(pmModel.getModel("ferramenta"));

		// gaveta
		ListCellRenderer<Gaveta> cellRendererGaveta = new ListCellRenderer<Gaveta>() {
			private DefaultListCellRenderer delegateGaveta = new DefaultListCellRenderer();

			@Override
			public Component getListCellRendererComponent(JList<? extends Gaveta> list, Gaveta value, int index,
					boolean isSelected, boolean cellHasFocus) {
				return delegateGaveta.getListCellRendererComponent(list, value == null ? "" : value.getNome(), index,
						isSelected, cellHasFocus);
			}
		};

		cbGaveta = BasicComponentFactory.createComboBox(selectionGavetas, cellRendererGaveta);
		selectionGavetas.setSelectionHolder(pmModel.getModel("gaveta"));

		// qtd
		txtQtd = BasicComponentFactory.createLongField(pmModel.getModel("quantidade"));
		
		bEstocar = new JButton("Estocar");
		bEstocar.addActionListener((e) -> estocaFerramenta());

		bVoltar = new JButton("Voltar");
		bVoltar.addActionListener((e) -> setVisible(false));

		// cbFerramenta = new JComboBox<>();

	}

	public void iniciaLayout() {

		setSize(250, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setLayout(new BorderLayout());
		titulo = new JLabel("Estocar");
		titulo.setFont(new Font("Verdana", 3, 20));
		add(titulo, BorderLayout.NORTH);

		DefaultFormBuilder builder = new DefaultFormBuilder(new FormLayout("pref, 5px, 80dlu"));

		builder.append("Ferramenta", cbFerramenta);
		builder.nextLine();
		builder.append("Gaveta", cbGaveta);
		builder.nextLine();
		builder.append("Quantidade", txtQtd);
		getContentPane().add(builder.build(), BorderLayout.CENTER);

		pBotoes = new JPanel(new FlowLayout());
		pBotoes.add(bEstocar);
		pBotoes.add(bVoltar);
		add(pBotoes, BorderLayout.SOUTH);

	}

	private void estocaFerramenta() {
		if (pmModel.getBean().getQuantidade() == 0 || pmModel.getBean().getQuantidade() < 0) {
			JOptionPane.showMessageDialog(this, " Digite uma quantidade maior que zero. ");
		} else {
			compraFach.adicionaCompra(pmModel.getBean().getFerramenta(), pmModel.getBean().getGaveta(),
					pmModel.getBean().getQuantidade());

			JOptionPane.showMessageDialog(this, " Ferramenta estocada com sucesso! ");
			this.setVisible(false);
		}
	}
}
