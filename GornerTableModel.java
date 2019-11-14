package lab3B;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class GornerTableModel extends AbstractTableModel {
	
	private Double[] coefficients;
	private Double from;
	private Double to;
	private Double step;
	
	public GornerTableModel(Double from, Double to, Double step, Double[] coefficients) {	
		this.from = from;
		this.to = to;
		this.step = step;	
		this.coefficients = coefficients;
	}

	public Double getFrom() {
		return from;
	}

	public Double getTo() {
		return to;
	}

	public Double getStep() {
		return step;
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@SuppressWarnings("deprecation")
	@Override
	public int getRowCount() {
		return new Double(Math.ceil((to - from)/step)).intValue() + 1;
	}

	@Override
	public Object getValueAt(int row, int col) {
		double x = from + step * row;
		
		switch (col) {
		case 0: return x;
		case 1: Double result = 0.0;
				for (int i = 0; i < coefficients.length; i++)
					result = result * x + coefficients[i];
				return result;
		default: return ifСonsecutive();
		}
	}
	
	public String getColumnName(int col) {
		switch (col) {
		case 0: return "Значение X";
		case 1: return "Значение многочлена";
		default: return "Последовательный ряд?"; 
		}
	}
	
	public Class<?> getColumnClass(int col) {
		switch (col) {
		case 0:
		case 1: return Double.class;
		default: return boolean.class;
		}
	}

}
