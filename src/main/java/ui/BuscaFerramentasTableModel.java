package ui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import domain.Ferramenta;

@SuppressWarnings("serial")
public class BuscaFerramentasTableModel extends AbstractTableModel {

	private final List<Ferramenta> ferramentas;

	public BuscaFerramentasTableModel(List<Ferramenta> f) {
		this.ferramentas = f;
	}

	@Override
	public int getRowCount() {
		return ferramentas.size();
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "ID";
		case 1:
			return "Nome";
		}
		return "";
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Ferramenta f = ferramentas.get(rowIndex);
		getColumnName(columnIndex);
		switch (columnIndex) {
		case 0:
			return f.getId();
		case 1:
			return f.getNome();
		}
		return "";
	}
}
