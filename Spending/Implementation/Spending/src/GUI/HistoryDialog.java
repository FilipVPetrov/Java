package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Core.History;
import Core.Spending;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class HistoryDialog extends JDialog {
	private JLabel historyLabel;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			HistoryDialog dialog = new HistoryDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public HistoryDialog(List<History> list,Spending tempSpending){
		this();
		HistoryTableModel model= new HistoryTableModel(list);
		table.setModel(model);
		historyLabel.setText(historyLabel.getText()+tempSpending.getDay()+"/"+tempSpending.getMonth()+"/"+tempSpending.getYear());
		TableCellRenderer tableCellRenderer = new DateTimeCellRenderer();
		table.getColumnModel().getColumn(HistoryTableModel.DATE_TIME_COL).setCellRenderer(tableCellRenderer);
	}
	
	/**
	 * Create the dialog.
	 */
	public HistoryDialog() {
		setTitle("History");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
						dispose();						 
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.NORTH);
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				historyLabel = new JLabel("History for spending:");
				historyLabel.setHorizontalAlignment(SwingConstants.LEFT);
				panel.add(historyLabel);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			{
				table = new JTable();
				scrollPane.setViewportView(table);
			}
		}
	}
	private final class DateTimeCellRenderer extends DefaultTableCellRenderer {
		SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

		public Component getTableCellRendererComponent(JTable table,
		        Object value, boolean isSelected, boolean hasFocus,
		        int row, int column) {
		    if( value instanceof Date) {
		        value = f.format(value);
		    }
		    return super.getTableCellRendererComponent(table, value, isSelected,
		            hasFocus, row, column);
		}
	}
}
