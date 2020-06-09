package ui;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import domain.Operacao;

@SuppressWarnings("serial")
public class OperacoesTableModel extends AbstractTableModel {

	private final List<Operacao> operacoes;

	public OperacoesTableModel(List<Operacao> op) {
		this.operacoes = op;
	}

	@Override
	public int getRowCount() {
		return operacoes.size();
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "Acao";
		case 1:
			return "Ferramenta";
		case 2:
			return "Gaveta";
		case 3:
			return "Quantidade";
		case 4:
			return "Data";
		}
		return "";
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Operacao op = operacoes.get(rowIndex);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String dataAcao = (sdf.format(op.getDataAcao()));
		getColumnName(columnIndex);
		switch (columnIndex) {
		case 0:
			return op.getClass().getSimpleName();
		case 1:
			return op.getFerramenta().getNome();
		case 2:
			return op.getGaveta().getNome();
		case 3:
			return op.getQuantidade();
		case 4:
			return dataAcao;
		}
		return "";
	}
}
