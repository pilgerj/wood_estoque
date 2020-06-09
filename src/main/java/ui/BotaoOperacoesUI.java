package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.border.LineBorder;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import application.OperacoesAplicacao;
import application.OperacoesFachada;
import domain.Ferramenta;
import domain.Gaveta;
import domain.Operacao;

@SuppressWarnings({ "serial", "deprecation" })
public class BotaoOperacoesUI extends JFrame {

	private OperacoesFachada op;
	// todas op's
	private JTabbedPane pTabs;
	private JPanel p1;
	private JLabel titleP1;
	private JTable tabelaP1;
	private JButton botaoAtt;
	private OperacoesTableModel tbModel;
	private JPanel painelTabelaOp;
	private JPanel pButton;

	// single op's
	private PresentationModel<OperacoesAplicacao> pmModel1 = new PresentationModel<>();
	private PresentationModel<OperacoesAplicacao> pmModel2 = new PresentationModel<>();
	private PresentationModel<OperacoesAplicacao> pmModel3 = new PresentationModel<>();
	private JPanel p2;
	private JPanel pTitleP2;
	private JLabel titleP2;
	private JTextField resultadoFG;
	private JComboBox<Gaveta> cbGaveta;
	private SelectionInList<Gaveta> selectionGavetas = new SelectionInList<>();
	private SelectionInList<Ferramenta> selectionFerramentas1 = new SelectionInList<>();
	private JTextField resultadoBusca;
	private JTextField resultadoTotal;
	private JComboBox<Ferramenta> cbFerramenta1;
	private JComboBox<Ferramenta> cbFerramenta2;
	private SelectionInList<Ferramenta> selectionFerramentas2 = new SelectionInList<>();

	//migrei da classe BotaoListaFerramentasUI 
	private JPanel p3;
	private JPanel painelTitulo;
	private JPanel painelTabela;
	private JLabel tituloTelaBusca;
	private JTable tabela;
	private JPanel painelBotao;
	private JButton botaoAtualizar;
	private JButton voltar;

	public BotaoOperacoesUI(OperacoesFachada op) {

		this.op = op;
		setTitle("Operacoes");

		initModels();
		initComponents();
		initLayout();

		setResizable(false);
		setVisible(true);
		pack();

	}

	private void initModels() {
		pmModel1.setBean(new OperacoesAplicacao());
		pmModel2.setBean(new OperacoesAplicacao());
		pmModel3.setBean(new OperacoesAplicacao());
		selectionGavetas.getList().addAll(op.getGavetas());
		selectionFerramentas1.getList().addAll(op.getFerramentas());
		selectionFerramentas2.getList().addAll(op.getFerramentas());
	}

	private void initComponents() {
		pTabs = new JTabbedPane();
		initComps01();
		initComps02();
		initComps03();
		initComps04();
		initComps05();

	}

	private void initComps01() {
		// todas op's
		p1 = new JPanel();
		painelTabelaOp = new JPanel();
		tabelaP1 = new JTable();
		pButton = new JPanel();

		botaoAtt = new JButton("Carregar Op's");
		botaoAtt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Operacao> list = new ArrayList<>();
				list.addAll(op.mostraOperacoes());
				tbModel = new OperacoesTableModel(list);
				tabelaP1.setModel(tbModel);
			}
		});
	}

	@SuppressWarnings({ "unchecked" })
	private void initComps02() {

		// single op's
		// num total de ferramentas nas gavetas
		p2 = new JPanel();
		resultadoFG = new JTextField();
		resultadoFG.setEnabled(false);

		ListCellRenderer<Gaveta> cellRendererGaveta = new ListCellRenderer<Gaveta>() {
			private DefaultListCellRenderer delegateGaveta = new DefaultListCellRenderer();

			@Override
			public Component getListCellRendererComponent(JList<? extends Gaveta> list, Gaveta value, int index,
					boolean isSelected, boolean cellHasFocus) {
				System.out.println(value);
				return delegateGaveta.getListCellRendererComponent(list, value == null ? "" : value.getNome(), index,
						isSelected, cellHasFocus);
			}
		};

		cbGaveta = BasicComponentFactory.createComboBox(selectionGavetas, cellRendererGaveta);
		selectionGavetas.setSelectionHolder(pmModel1.getModel("gaveta"));
		selectionGavetas.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName() == SelectionInList.PROPERTY_SELECTION) {
					Long l = op.getTotalFerramentaGaveta(pmModel1.getBean().getGaveta());
					resultadoFG.setText(l.toString());
				}

			}
		});
	}

	@SuppressWarnings({ "unchecked" })
	private void initComps03() {
		// single op's
		// encontrar ferramenta
		resultadoBusca = new JTextField();
		resultadoBusca.setEnabled(false);

		ListCellRenderer<Ferramenta> cellRendererFerramenta1 = new ListCellRenderer<Ferramenta>() {
			private DefaultListCellRenderer delegateFerramenta1 = new DefaultListCellRenderer();

			@Override
			public Component getListCellRendererComponent(JList<? extends Ferramenta> list, Ferramenta value, int index,
					boolean isSelected, boolean cellHasFocus) {
				System.out.println(value);
				return delegateFerramenta1.getListCellRendererComponent(list, value == null ? "" : value.getNome(),
						index, isSelected, cellHasFocus);
			}
		};

		cbFerramenta1 = BasicComponentFactory.createComboBox(selectionFerramentas1, cellRendererFerramenta1);
		selectionFerramentas1.setSelectionHolder(pmModel2.getModel("ferramenta"));

		selectionFerramentas1.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName() == SelectionInList.PROPERTY_SELECTION) {
					if (op.getTotalFerramenta(pmModel2.getBean().getFerramenta()) == 0) {
						resultadoBusca.setText("Sem estoque!");
					}
					else resultadoBusca.setText(op.buscaGaveta(pmModel2.getBean().getFerramenta()));
				}
			}
		});
	}

	@SuppressWarnings({ "unchecked" })
	private void initComps04() {
		// single op's
		// num total de ferramentas cadastradas.
		resultadoTotal = new JTextField();
		resultadoTotal.setEnabled(false);

		ListCellRenderer<Ferramenta> cellRendererFerramenta2 = new ListCellRenderer<Ferramenta>() {
			DefaultListCellRenderer delegateFerramenta2 = new DefaultListCellRenderer();

			@Override
			public Component getListCellRendererComponent(JList<? extends Ferramenta> list, Ferramenta value, int index,
					boolean isSelected, boolean cellHasFocus) {
				return delegateFerramenta2.getListCellRendererComponent(list, value == null ? "" : value.getNome(),
						index, isSelected, cellHasFocus);
			}

		};

		cbFerramenta2 = BasicComponentFactory.createComboBox(selectionFerramentas2, cellRendererFerramenta2);
		selectionFerramentas2.setSelectionHolder(pmModel3.getModel("ferramenta"));
		selectionFerramentas2.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName() == SelectionInList.PROPERTY_SELECTION) {
					Long total = op.getTotalFerramenta(pmModel3.getBean().getFerramenta());
					resultadoTotal.setText(total.toString());
				}
			}
		});

	}

	public void initComps05() {

		p3 = new JPanel();

		// titulo
		painelTitulo = new JPanel();
		tituloTelaBusca = new JLabel("Ferramentas Cadastradas ");
		tituloTelaBusca.setFont(new Font("Verdana", Font.BOLD, 20));
		painelTitulo.add(tituloTelaBusca);

		// tabela //
		painelTabela = new JPanel();
		tabela = new JTable();
		tabela.setBorder(new LineBorder(Color.black));
		tabela.setGridColor(Color.black);
		tabela.setShowGrid(true);
		// scroll
		JScrollPane scroll = new JScrollPane();
		scroll.getViewport().setBorder(null);
		scroll.getViewport().add(tabela);
		scroll.setSize(150, 150);
		painelTabela.add(scroll);

		// botao att
		painelBotao = new JPanel();
		botaoAtualizar = new JButton("Atualizar");
		painelBotao.add(botaoAtualizar);
		botaoAtualizar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<Ferramenta> list = new ArrayList<>();
				list = op.getFerramentas();
				BuscaFerramentasTableModel bftm = new BuscaFerramentasTableModel(list);
				tabela.setModel(bftm);
			}
		});

	}

	private void initLayout01() {
		// todas op's
		titleP1 = new JLabel("Todas Operacoes");
		titleP1.setFont(new Font("Arial", 3, 20));
		p1.add(titleP1, BorderLayout.NORTH);
		p1.add(painelTabelaOp, BorderLayout.CENTER);
		tabelaP1.setBorder(new LineBorder(Color.black));
		tabelaP1.setGridColor(Color.black);
		tabelaP1.setShowGrid(true);
		JScrollPane scroll = new JScrollPane();
		scroll.getViewport().setBorder(null);
		scroll.getViewport().add(tabelaP1);
		scroll.setSize(150, 150);
		painelTabelaOp.add(scroll);
		pButton.add(botaoAtt, BorderLayout.SOUTH);
		p1.add(pButton, BorderLayout.SOUTH);

	}

	private void initLayout02() {
		// single op's
		titleP2 = new JLabel("Consulta Operacoes");
		titleP2.setFont(new Font("Arial", 3, 20));
		pTitleP2 = new JPanel();
		pTitleP2.add(titleP2);

		FormLayout layout = new FormLayout("right:max(20dlu;pref), 5px, 60dlu, 5px, 70dlu, 5px", "pref, pref");
		DefaultFormBuilder builder = new DefaultFormBuilder(layout);

		builder.nextLine();
		builder.append("Total de Ferramentas na gaveta", cbGaveta, resultadoFG);
		builder.nextLine();
		builder.append("Encontrar Ferramenta: ", cbFerramenta1, resultadoBusca);
		builder.nextLine();
		builder.append("Numero total de ", cbFerramenta2, resultadoTotal);

		p2.add(pTitleP2, BorderLayout.NORTH);
		p2.add(builder.build(), BorderLayout.CENTER);

	}

	private void initLayout() {
		initLayout01();
		initLayout02();
		p3.add(painelTitulo, BorderLayout.NORTH);
		p3.add(painelTabela, BorderLayout.CENTER);
		p3.add(painelBotao, BorderLayout.SOUTH);

		pTabs.add(p1);
		pTabs.add(p2);
		pTabs.add(p3);
		voltar = new JButton("Voltar");
		voltar.addActionListener((e) -> this.setVisible(false));
		add(voltar, BorderLayout.SOUTH);

		add(pTabs);
		setPreferredSize(new Dimension(550, 650));
	}

}
