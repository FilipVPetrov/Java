package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;

import Core.Spending;
import DBO.SpendingDBO;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddSpendingDialog extends JDialog {
	private JTextField todaySpendingtextField;
	private JTextField yearTextField;
	private JTextField monthTextField;
	private JTextField dayTextField;
	private JTextField spendTextField;
	private YearMonthDayThread myThread;
	private JLabel year, month, day;
	private JCheckBox checkTodayJBox;
	private JCheckBox checkAnotherDayBox;
	private JButton btnSave;
	private SpendingApp spendingApp;
	private SpendingDBO spendingDBO;
	private JPanel todayPanel;
	private JPanel anotherPanel;
	private JTabbedPane tabbedPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddSpendingDialog dialog = new AddSpendingDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AddSpendingDialog(SpendingApp spendingApp, SpendingDBO spendingDBO) {
		this();
		this.spendingApp = spendingApp;
		this.spendingDBO = spendingDBO;
		if (spendingDBO.isEnteredToday())
			tabbedPane.remove(todayPanel);
	}

	/**
	 * Create the dialog.
	 */
	public AddSpendingDialog() {
		setTitle("Add Spending");
		setBounds(100, 100, 671, 402);
		getContentPane().setLayout(new BorderLayout(0, 0));
		{
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			getContentPane().add(tabbedPane, BorderLayout.NORTH);
			{
				todayPanel = new JPanel();
				tabbedPane.addTab("Today", null, todayPanel, null);
				tabbedPane.setEnabledAt(0, true);
				todayPanel.setLayout(new FormLayout(new ColumnSpec[] {
						FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(80dlu;default)"),
						FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(14dlu;default):grow"),
						FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), }, new RowSpec[] {
						FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("max(19dlu;default)"),
						FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, }));
				{
					JLabel lblAdding = new JLabel("Please enter");
					lblAdding.setFont(new Font("Tahoma", Font.PLAIN, 17));
					todayPanel.add(lblAdding, "2, 2");
				}
				{
					JLabel lblYear = new JLabel("Year: ");
					lblYear.setFont(new Font("Tahoma", Font.PLAIN, 17));
					todayPanel.add(lblYear, "2, 4");
				}
				{
					year = new JLabel("");
					year.setFont(new Font("Tahoma", Font.PLAIN, 17));
					todayPanel.add(year, "4, 4, left, default");
				}
				{
					JLabel lblMonth = new JLabel("Month: ");
					lblMonth.setFont(new Font("Tahoma", Font.PLAIN, 17));
					todayPanel.add(lblMonth, "2, 6");
				}
				{
					month = new JLabel("");
					month.setFont(new Font("Tahoma", Font.PLAIN, 17));
					todayPanel.add(month, "4, 6, default, top");
				}
				{
					JLabel lblDay = new JLabel("Day: ");
					lblDay.setFont(new Font("Tahoma", Font.PLAIN, 17));
					todayPanel.add(lblDay, "2, 8");
				}
				{
					day = new JLabel("");
					day.setFont(new Font("Tahoma", Font.PLAIN, 17));
					todayPanel.add(day, "4, 8, left, default");
				}
				{
					JLabel lblSpending = new JLabel("Spending:");
					lblSpending.setFont(new Font("Tahoma", Font.PLAIN, 17));
					todayPanel.add(lblSpending, "2, 10, left, default");
				}
				{
					todaySpendingtextField = new JTextField();
					todayPanel.add(todaySpendingtextField,
							"4, 10, left, default");
					todaySpendingtextField.setColumns(10);
				}
				{
					checkTodayJBox = new JCheckBox("Please select to finish");
					checkTodayJBox.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							if (checkTodayJBox.isSelected())
								btnSave.setVisible(true);
							else
								btnSave.setVisible(false);
						}
					});
					todayPanel.add(checkTodayJBox, "6, 10");
				}
			}
			{
				anotherPanel = new JPanel();
				tabbedPane.addTab("Another Day", null, anotherPanel, null);
				anotherPanel.setVisible(false);
				anotherPanel.setLayout(new FormLayout(new ColumnSpec[] {
						FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), }, new RowSpec[] {
						FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, }));
				{
					JLabel label = new JLabel("Adding your spending");
					label.setFont(new Font("Tahoma", Font.PLAIN, 17));
					anotherPanel.add(label, "2, 2");
				}
				{
					JLabel label = new JLabel("Year: ");
					label.setFont(new Font("Tahoma", Font.PLAIN, 17));
					anotherPanel.add(label, "2, 4, left, default");
				}
				{
					yearTextField = new JTextField();
					anotherPanel.add(yearTextField, "4, 4, left, default");
					yearTextField.setColumns(10);
				}
				{
					JLabel label = new JLabel("Month: ");
					label.setFont(new Font("Tahoma", Font.PLAIN, 17));
					anotherPanel.add(label, "2, 6, left, default");
				}
				{
					monthTextField = new JTextField();
					anotherPanel.add(monthTextField, "4, 6, left, default");
					monthTextField.setColumns(10);
				}
				{
					JLabel label = new JLabel("Day: ");
					label.setFont(new Font("Tahoma", Font.PLAIN, 17));
					anotherPanel.add(label, "2, 8, left, default");
				}
				{
					dayTextField = new JTextField();
					anotherPanel.add(dayTextField, "4, 8, left, default");
					dayTextField.setColumns(10);
				}
				{
					JLabel label = new JLabel("Spending:");
					label.setFont(new Font("Tahoma", Font.PLAIN, 17));
					anotherPanel.add(label, "2, 10, left, default");
				}
				{
					spendTextField = new JTextField();
					anotherPanel.add(spendTextField, "4, 10, left, default");
					spendTextField.setColumns(10);
				}
				{
					checkAnotherDayBox = new JCheckBox(
							"Please select ro finish");
					checkAnotherDayBox.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (checkAnotherDayBox.isSelected())
								btnSave.setVisible(true);
							else
								btnSave.setVisible(false);
						}
					});
					anotherPanel.add(checkAnotherDayBox, "6, 10");
				}
			}
		}

		{
			myThread = new YearMonthDayThread(year, month, day);
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.SOUTH);
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				btnSave = new JButton("Save");
				btnSave.setVisible(false);
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						saveSpending();
					}
				});
				panel.add(btnSave);
			}
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}
				});
				panel.add(btnCancel);
			}
		}
	}

	protected void saveSpending() {
		try {
			int tempYear, tempMonth, tempDay;
			float tempSpend;
			Spending spending = null;
			if (checkTodayJBox.isSelected()) {
				String tempSp = todaySpendingtextField.getText();
				tempSpend = Float.parseFloat(tempSp);
				spending = new Spending(tempSpend);
			}

			else if (checkAnotherDayBox.isSelected()) {
				String tempY = yearTextField.getText();
				tempYear = Integer.parseInt(tempY);
				String tempM = monthTextField.getText();
				tempMonth = Integer.parseInt(tempM);
				String tempD = dayTextField.getText();
				tempDay = Integer.parseInt(tempD);
				String tempSp = spendTextField.getText();
				tempSpend = Float.parseFloat(tempSp);
				spending = new Spending(tempYear, tempMonth, tempDay, tempSpend);
			}
			if (spendingDBO.isEntered(spending.getYear(), spending.getMonth(),
					spending.getDay())) {
				JOptionPane.showMessageDialog(spendingApp,
						"This Spending is already inserted. ", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				spendingDBO.addSpending(spending);
				JOptionPane.showMessageDialog(spendingApp,
						"The Spending is added succesfully.", "Spending Added",
						JOptionPane.INFORMATION_MESSAGE);
			}
			dispose();
			setVisible(false);
			
			spendingApp.refreshSpendingsView();

		} catch (Exception exe) {
			JOptionPane.showMessageDialog(spendingApp,
					"Error saving Spending: " + exe.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}
}
