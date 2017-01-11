package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Core.Spending;
import DBO.SpendingDBO;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField spendTextField;
	private JLabel yearLabel, monthLabel, dayLabel;
	private SpendingApp spendingApp;
	private SpendingDBO spendingDBO;
	private Spending previousSpending = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UpdateDialog dialog = new UpdateDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public UpdateDialog(SpendingApp theSpendingApp, SpendingDBO theSpendingDBO,
			Spending thePreviousSpending) {
		this();
		spendingDBO = theSpendingDBO;
		spendingApp = theSpendingApp;
		previousSpending = thePreviousSpending;
		setToTextField(previousSpending);

	}

	private void setToTextField(Spending thepreviousSpending) {
		yearLabel.setText("" + thepreviousSpending.getYear());
		monthLabel.setText("" + thepreviousSpending.getMonth());
		dayLabel.setText("" + thepreviousSpending.getDay());
		spendTextField.setText("" + thepreviousSpending.getSpend());
	}

	/**
	 * Create the dialog.
	 */
	public UpdateDialog() {
		setTitle("Update Spending");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"), }, new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));
		{
			JLabel lblYear = new JLabel("Year:");
			lblYear.setFont(new Font("Tahoma", Font.PLAIN, 17));
			contentPanel.add(lblYear, "2, 2");
		}
		{
			yearLabel = new JLabel("");
			yearLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
			contentPanel.add(yearLabel, "4, 2, left, default");
		}
		{
			JLabel lblMonth = new JLabel("Month:");
			lblMonth.setFont(new Font("Tahoma", Font.PLAIN, 17));
			contentPanel.add(lblMonth, "2, 4");
		}
		{
			monthLabel = new JLabel("");
			monthLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
			contentPanel.add(monthLabel, "4, 4, left, default");
		}
		{
			JLabel lblDay = new JLabel("Day:");
			lblDay.setFont(new Font("Tahoma", Font.PLAIN, 17));
			contentPanel.add(lblDay, "2, 6");
		}
		{
			dayLabel = new JLabel("");
			dayLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
			contentPanel.add(dayLabel, "4, 6, left, default");
		}
		{
			JLabel lblSpending = new JLabel("Spending: ");
			lblSpending.setFont(new Font("Tahoma", Font.PLAIN, 17));
			contentPanel.add(lblSpending, "2, 8, right, default");
		}
		{
			spendTextField = new JTextField();
			spendTextField.setFont(new Font("Tahoma", Font.PLAIN, 17));
			contentPanel.add(spendTextField, "4, 8, left, default");
			spendTextField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						saveSpending();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	protected void saveSpending() {

		try {
			int tempYear, tempMonth, tempDay;
			float tempSpend;
			Spending spending = null;
			String tempSp = spendTextField.getText();
			tempSpend = Float.parseFloat(tempSp);
			spending = new Spending(previousSpending.getId(),
					previousSpending.getYear(), previousSpending.getMonth(),
					previousSpending.getDay(), tempSpend);

			spendingDBO.updateSpending(spending);
			setVisible(false);
			dispose();
			spendingApp.refreshSpendingsView();
			JOptionPane.showMessageDialog(spendingApp,
					"The Spending is updated succesfully.", "Spending Updated",
					JOptionPane.INFORMATION_MESSAGE);
			// else
			// JOptionPane.showMessageDialog(spendingApp,
			// "The Spending is added succesfully.", "Spending Added",
			// JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception exe) {
			exe.printStackTrace();
			JOptionPane.showMessageDialog(spendingApp,
					"Error saving Spending: " + exe, "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

}
