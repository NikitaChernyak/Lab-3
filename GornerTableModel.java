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
		Double result = 0.0;
		
		switch (col) {
		case 0: return x;
		case 1: for (int i = 0; i < coefficients.length; i++)
					result = result * x + coefficients[i];
				return result;
		default: return ifConsecutive(result);
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
	
	public boolean ifConsecutive(Double number) {
		
		int integer = number.intValue();
		Double tempFract = number - integer;    
		tempFract *= 1e5;
		int fract = tempFract.intValue();
		  
		int nNumbersInInteger = (int)Math.log10(integer) + 1;
	    int nNumbersInFract = 5; //(int)Math.log10(fract) + 1; 
	    
	    int[] numbersFromInt = new int[nNumbersInInteger];
	    int[] numbersFromFract = new int[nNumbersInFract];
	       
	    for (int i = nNumbersInInteger - 1; i >= 0; i--) {
	    	numbersFromInt[i] = integer % 10;
	        integer /= 10;
	    }
	        
		for (int i = nNumbersInFract - 1; i >= 0; i--) {
	        numbersFromFract[i] = fract % 10;
	        fract /= 10;
	    }   
			        
		if (nNumbersInInteger >= 3)
			for (int j = 0; j < nNumbersInInteger - 2; j++)
				if ((numbersFromInt[j] + 1) == numbersFromInt[j+1] && (numbersFromInt[j] + 2) == (numbersFromInt[j+2]))
					return true;
					            
		//if (nNumbersInFract >= 3)
		for (int j = 0; j < nNumbersInFract - 2; j++)
			if ((numbersFromFract[j] + 1) == numbersFromFract[j+1] && (numbersFromFract[j] + 2) == (numbersFromFract[j+2]))
				return true;
					         
		return false;  
	}

}
