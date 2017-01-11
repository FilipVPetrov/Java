package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.FlowLayout;

import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

import DBO.SpendingDBO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import java.util.List;

import Core.*;

public class SpendingApp extends JFrame {

	private JPanel contentPane;
	private JTextField yearTextField;
	private JTextField monthTextField;
	private JTextField dayTextField;
	private JTable table;
	private SpendingDBO spendingDBO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpendingApp frame = new SpendingApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SpendingApp() {
		setTitle("Spending App");
		try {
			spendingDBO = new SpendingDBO();
		} catch (Exception exe) {
			JOptionPane.showMessageDialog(SpendingApp.this, "Error " + exe,
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 733, 468);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(20);
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel lblEnterYear = new JLabel("Enter year");
		lblEnterYear.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel.add(lblEnterYear);

		yearTextField = new JTextField();
		yearTextField.setFont(UIManager.getFont("Button.font"));
		panel.add(yearTextField);
		yearTextField.setColumns(10);

		JLabel lblMonth = new JLabel("month");
		lblMonth.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel.add(lblMonth);

		monthTextField = new JTextField();
		panel.add(monthTextField);
		monthTextField.setColumns(10);

		JLabel lblDay = new JLabel("day");
		lblDay.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel.add(lblDay);

		dayTextField = new JTextField();
		panel.add(dayTextField);
		dayTextField.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					List<Spending> spending = null;
					String year = yearTextField.getText();
					String month = monthTextField.getText();
					String day = dayTextField.getText();
					int y, m, d;
					y = m = d = 0;
					if (!(year.equals("")) && !(month.equals(""))
							&& !(day.equals(""))) {
						y = Integer.parseInt(year);
						m = Integer.parseInt(month);
						d = Integer.parseInt(day);
						spending = spendingDBO.searchSpending(y, m, d);
					} else if (!(year.equals("")) && !(month.equals(""))
							&& (day.equals(""))) {
						y = Integer.parseInt(year);
						m = Integer.parseInt(month);
						spending = spendingDBO.searchSpending(y, "year", m,
								"month");
					} else if (!(year.equals("")) && (month.equals(""))
							&& !(day.equals(""))) {
						y = Integer.parseInt(year);
						d = Integer.parseInt(day);
						spending = spendingDBO.searchSpending(y, "year", d,
								"day");
					} else if ((year.equals("")) && !(month.equals(""))
							&& !(day.equals(""))) {
						m = Integer.parseInt(month);
						d = Integer.parseInt(day);
						spending = spendingDBO.searchSpending(m, "month", d,
								"day");
					}

					else if (!(year.equals("")) && (month.equals(""))
							&& (day.equals(""))) {
						y = Integer.parseInt(year);
						spending = spendingDBO.searchSpending(y, "year");
					} else if ((year.equals("")) && !(month.equals(""))
							&& (day.equals(""))) {
						m = Integer.parseInt(month);
						spending = spendingDBO.searchSpending(m, "month");
					} else if ((year.equals("")) && (month.equals(""))
							&& !(day.equals(""))) {
						d = Integer.parseInt(day);
						spending = spendingDBO.searchSpending(d, "day");
					} else {
						spending = spendingDBO.getAllSpendings();
					}
					SpendingTableModel model = new SpendingTableModel(spending);
					table.setModel(model);

				} catch (Exception exe) {
					JOptionPane.showMessageDialog(SpendingApp.this, "Error "
							+ exe, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(btnSearch);

		JLabel timeLabel = new JLabel("");
		timeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(timeLabel);
		Date_Banner myBanner = new Date_Banner(timeLabel);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		scrollPane.setViewportView(table);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);

		JButton btnAddSpending = new JButton("Add Spending");
		btnAddSpending.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddSpendingDialog dialog = new AddSpendingDialog(
						SpendingApp.this, spendingDBO);
				dialog.setVisible(true);
			}
		});

		JButton btnUpdateSpending = new JButton("Update Spending");
		btnUpdateSpending.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int row = table.getSelectedRow();

				if (row < 0) {
					JOptionPane.showMessageDialog(SpendingApp.this,
							"You must select first a spending.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				Spending tempSpending = (Spending) table.getValueAt(row,
						SpendingTableModel.OBJECT_COL);

				UpdateDialog dialog = new UpdateDialog(SpendingApp.this,
						spendingDBO, tempSpending);
				dialog.setVisible(true);
			}
		});
		panel_1.add(btnUpdateSpending);
		panel_1.add(btnAddSpending);

		JButton btnDeleteSpending = new JButton("Delete Spending");
		btnDeleteSpending.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int row = table.getSelectedRow();
					if (row < 0) {
						JOptionPane.showMessageDialog(SpendingApp.this,
								"You must select first a spending.", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					int response = JOptionPane.showConfirmDialog(
							SpendingApp.this, "Delete this spending?",
							"Confirm", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					if(response!=JOptionPane.YES_OPTION)
						return;
					Spending tempSpending= (Spending) table.getValueAt(row,SpendingTableModel.OBJECT_COL);
					spendingDBO.deleteSpending(tempSpending.getId());
					refreshSpendingsView();
				} catch (Exception exe) {
					JOptionPane.showMessageDialog(SpendingApp.this,
							"Error: "+ exe, "Error",
							JOptionPane.ERROR_MESSAGE);
					exe.printStackTrace();
				}
			}
		});
		panel_1.add(btnDeleteSpending);
		
		JButton btnViewHistory = new JButton("View History");
		btnViewHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();

				if (row < 0) {
					JOptionPane.showMessageDialog(SpendingApp.this,
							"You must select first a spending.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				Spending tempSpending = (Spending) table.getValueAt(row,
						SpendingTableModel.OBJECT_COL);
				
				try{
					int spendingId=tempSpending.getId();
					List<History> historyList= spendingDBO.getHistory(spendingId);
					
					HistoryDialog dialog= new HistoryDialog(historyList,tempSpending);
					dialog.setVisible(true);
				}catch(Exception exe){
					JOptionPane.showMessageDialog(SpendingApp.this,
							"Error "+exe, "Error",
							JOptionPane.ERROR_MESSAGE);
				}							
			}
		});
		panel_1.add(btnViewHistory);
	}

	public void refreshSpendingsView() {
		try {
			List<Spending> spending = spendingDBO.getAllSpendings();
			SpendingTableModel model = new SpendingTableModel(spending);
			table.setModel(model);
		} catch (Exception exe) {
			JOptionPane.showMessageDialog(SpendingApp.this, "Error " + exe,
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
