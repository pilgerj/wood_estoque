package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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

import application.VendaAplicacao;
import application.VendaFachada;
import domain.Ferramenta;
import domain.Gaveta;

@SuppressWarnings({ "deprecation", "serial" })
public class BotaoVendaUI extends JFrame {

	private VendaFachada vendaFachada;
	private PresentationModel<VendaAplicacao> pmModel = new PresentationModel<>();
	private JComboBox<Ferramenta> cbFerramenta;
	private SelectionInList<Ferramenta> selectionFerramenta = new SelectionInList<>();
	private JTextField totalFerramentas;
	private JComboBox<Gaveta> cbGaveta;
	private SelectionInList<Gaveta> selectionGaveta = new SelectionInList<>();
	private JTextField totalNaGaveta;
	private JLabel title;
	private JPanel painel;
	private JTextField quantidadeVenda;
	private JButton botaoVender;
	private JButton botaoVoltar;
	private JPanel painelBotoes;

	public BotaoVendaUI(VendaFachada vendaFachada) {
		this.vendaFachada = vendaFachada;

		initModels();
		initComponents();
		initLayout();

	}

	private void initModels() {
		pmModel.setBean(new VendaAplicacao());
		selectionFerramenta.setList(vendaFachada.getFerramentas());
		selectionGaveta.setList(vendaFachada.getGavetas());

	}

	private void initComponents() {

		compsFerramenta();
		compsGaveta();
		compsQuantidade();
		compsBotoes();
	}

	@SuppressWarnings("unchecked")
	private void compsFerramenta() {

		totalFerramentas = new JTextField();
		totalFerramentas.setEnabled(false);

		ListCellRenderer<Ferramenta> cellRendererFerramenta = new ListCellRenderer<Ferramenta>() {
			DefaultListCellRenderer delegateFerramenta = new DefaultListCellRenderer();

			@Override
			public Component getListCellRendererComponent(JList<? extends Ferramenta> list, Ferramenta value, int index,
					boolean isSelected, boolean cellHasFocus) {
				return delegateFerramenta.getListCellRendererComponent(list, value == null ? "" : value.getNome(),
						index, isSelected, cellHasFocus);
			}
		};

		cbFerramenta = BasicComponentFactory.createComboBox(selectionFerramenta, cellRendererFerramenta);
		selectionFerramenta.setSelectionHolder(pmModel.getModel("ferramenta"));
		selectionFerramenta.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName() == SelectionInList.PROPERTY_SELECTION) {
					Long total = vendaFachada.getTotalFerramenta(pmModel.getBean().getFerramenta());
					totalFerramentas.setText(total.toString());

					Long totalG = vendaFachada.getQtdGaveta(pmModel.getBean().getFerramenta(),
							pmModel.getBean().getGaveta());
					totalNaGaveta.setText(totalG.toString());
				}
			}
		});
	}

	@SuppressWarnings("unchecked")
	private void compsGaveta() {
		totalNaGaveta = new JTextField();
		totalNaGaveta.setEnabled(false);

		ListCellRenderer<Gaveta> cellRendererGaveta = new ListCellRenderer<Gaveta>() {
			DefaultListCellRenderer delegateGaveta = new DefaultListCellRenderer();

			@Override
			public Component getListCellRendererComponent(JList<? extends Gaveta> list, Gaveta value, int index,
					boolean isSelected, boolean cellHasFocus) {
				return delegateGaveta.getListCellRendererComponent(list, value == null ? "" : value.getNome(), index,
						isSelected, cellHasFocus);

			}

		};

		cbGaveta = BasicComponentFactory.createComboBox(selectionGaveta, cellRendererGaveta);
		selectionGaveta.setSelectionHolder(pmModel.getModel("gaveta"));
		selectionGaveta.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName() == SelectionInList.PROPERTY_SELECTION) {
					Long total = vendaFachada.getQtdGaveta(pmModel.getBean().getFerramenta(),
							pmModel.getBean().getGaveta());
					if (total.equals(null)) {
						totalNaGaveta.setText("0");
					} else
						totalNaGaveta.setText(total.toString());

				}
			}
		});

	}

	private void compsQuantidade() {
		quantidadeVenda = BasicComponentFactory.createLongField(pmModel.getModel("quantidade"));
	}

	private void compsBotoes() {
		painelBotoes = new JPanel();
		botaoVender = new JButton("Vender");
		botaoVender.addActionListener((e) -> venderFerramenta());

		botaoVoltar = new JButton("Voltar");
		botaoVoltar.addActionListener((e) -> this.setVisible(false));

		painelBotoes.add(botaoVender);
		painelBotoes.add(botaoVoltar);
	}

	private void venderFerramenta() {
		if (verifica()) {
			vendaFachada.vendaFerramenta(pmModel.getBean().getFerramenta(), pmModel.getBean().getGaveta(),
					pmModel.getBean().getQuantidade());
			JOptionPane.showMessageDialog(null, "Ferramenta vendida com sucesso!");
			setVisible(false);
		}
	}

	private boolean verifica() {

		if (selectionFerramenta.isSelectionEmpty() || selectionGaveta.isSelectionEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha as caixas acima para efetuar uma venda.");
			return false;
		}
		if (pmModel.getBean().getQuantidade() > vendaFachada.getQtdGaveta(pmModel.getBean().getFerramenta(),
				pmModel.getBean().getGaveta())) {
			JOptionPane.showMessageDialog(null, "Quantidade a ser vendida excede estoque. ");
			return false;
		}

		if (pmModel.getBean().getQuantidade() == 0 || pmModel.getBean().getQuantidade() < 0) {
			JOptionPane.showMessageDialog(this, "Digite uma quantidade maior que zero. ");
			return false;
		}

		return true;
	}

	private void initLayout() {

		setTitle("Venda");
		setPreferredSize(new Dimension(570, 230));
		setResizable(false);
		painel = new JPanel();

		title = new JLabel("Venda de Ferramenta");
		title.setFont(new Font("Verdana", 3, 20));
		painel.add(title, BorderLayout.NORTH);

		FormLayout layout = new FormLayout("pref, 5px, 70dlu, 5px, pref, 5px, 50dlu");
		DefaultFormBuilder builder = new DefaultFormBuilder(layout);

		builder.append("Ferramenta ", cbFerramenta);
		builder.append("Quantidade Total ", totalFerramentas);
		builder.nextLine();
		builder.append("Gaveta ", cbGaveta);
		builder.append("Quantidade na Gaveta", totalNaGaveta);
		builder.nextLine();
		builder.append("Quantidade a ser vendida", quantidadeVenda);

		painel.add(builder.build(), BorderLayout.CENTER);
		add(painel, BorderLayout.CENTER);
		add(painelBotoes, BorderLayout.SOUTH);
		pack();
		setVisible(true);

	}

}
