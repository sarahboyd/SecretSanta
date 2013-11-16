

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import java.awt.*;

public class View3 extends JPanel implements IView {

	private SantaModel model;

	private JTable table;

	private MyTableModel table_model = new MyTableModel();

	private JLabel info;

	View3(SantaModel model_) {
		this.setLayout(new BorderLayout());

		model = model_;

		info = new JLabel();
		info.setText("Entered names:");
		
		table = new JTable(table_model);
		//table.setPreferredScrollableViewportSize(new Dimension(150, 200));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
        
		this.add(info, BorderLayout.PAGE_START);
		this.add(scrollPane, BorderLayout.CENTER);

	}

	public void updateView() {
		System.out.println("View3: updateView");
		createRow(model.getCurName(), model.getCurEmail());
	}

	public void createRow(String name, String email){
		table_model.setValueAt(name, model.getNameCount(), 0);
		table_model.setValueAt(email, model.getNameCount(), 1);
	}
	
	class MyTableModel extends AbstractTableModel {

		private boolean DEBUG = true;

		private String[] columnNames = { "Name", "Email" };

		private Object[][] data = new Object[30][2];

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return data.length;
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		public Object getValueAt(int row, int col) {
			return data[row][col];
		}

		public void setValueAt(Object value, int row, int col) {
			if (DEBUG) {
				System.out.println("Setting value at " + row + "," + col
						+ " to " + value + " (an instance of "
						+ value.getClass() + ")");
			}

			data[row][col] = value;
			fireTableCellUpdated(row, col);

			if (DEBUG) {
				System.out.println("New value of data:");
				printDebugData();
			}
		}

		private void printDebugData() {
			int numRows = getRowCount();
			int numCols = getColumnCount();

			for (int i = 0; i < numRows; i++) {
				System.out.print("    row " + i + ":");
				for (int j = 0; j < numCols; j++) {
					System.out.print("  " + data[i][j]);
				}
				System.out.println();
			}
			System.out.println("--------------------------");
		}

	}
}
