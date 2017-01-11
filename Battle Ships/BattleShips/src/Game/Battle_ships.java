package Game;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;

public class Battle_ships extends JFrame {

	private JPanel contentPane;
	private Ship me, enemy;
	private int upg, tur, coo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Battle_ships frame = new Battle_ships();
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
	public Battle_ships() {
		super("Battel ship");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1155, 869);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Battle_ships.class
				.getResource("/Battleships/Jackdaw.jpg")));
		label.setBounds(12, 13, 267, 377);
		contentPane.add(label);

		final JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Battle_ships.class
				.getResource("/Battleships/Gunboat.jpg")));
		label_1.setBounds(850, 13, 267, 377);
		contentPane.add(label_1);

		JLabel lblInformationForOur = new JLabel("Information for our ship:");
		lblInformationForOur.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblInformationForOur.setBounds(12, 398, 267, 34);
		contentPane.add(lblInformationForOur);

		JLabel lblInformationForEnemy = new JLabel(
				"Information for enemy ship:");
		lblInformationForEnemy.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblInformationForEnemy.setBounds(850, 398, 257, 34);
		contentPane.add(lblInformationForEnemy);

		String a = "The strength of ship: ";
		String b = "The attacking power: ";
		String c = "The defence power :";
		// lblNewLabel.setText("<html>"+ a+" <html>" + "<br>"+b +" </html>");
		final JLabel label_2 = new JLabel("");
		label_2.setText("<html>" + a + " <html>" + "<br>" + b + "<html> <br>"
				+ c + "<html>" + "</html>");
		label_2.setBounds(12, 450, 267, 54);
		contentPane.add(label_2);

		final JLabel label_3 = new JLabel("<html>The strength of ship:  <html>"
				+ "" + "<br>The attacking power: <html>" + ""
				+ " <br>The defence power :<html>" + "" + "</html>");
		label_3.setVerticalAlignment(SwingConstants.TOP);
		label_3.setBounds(850, 450, 267, 77);
		contentPane.add(label_3);

		final JToggleButton nextrund = new JToggleButton("Next round");
		nextrund.setBounds(492, 191, 141, 54);
		nextrund.setVisible(false);
		contentPane.add(nextrund);

		final JPanel upgrade = new JPanel();
		upgrade.setBounds(22, 587, 314, 154);
		upgrade.setVisible(false);
		contentPane.add(upgrade);
		upgrade.setLayout(null);

		final JCheckBox strength_3 = new JCheckBox("");
		strength_3.setBounds(60, 37, 25, 25);
		upgrade.add(strength_3);
		final JCheckBox attack_3 = new JCheckBox("");
		attack_3.setBounds(60, 67, 25, 25);
		upgrade.add(attack_3);
		final JCheckBox defence_3 = new JCheckBox("");
		defence_3.setBounds(60, 97, 25, 25);
		upgrade.add(defence_3);
		final JCheckBox strength_1 = new JCheckBox("");
		strength_1.setBounds(5, 37, 25, 25);
		upgrade.add(strength_1);
		final JCheckBox strength_2 = new JCheckBox("");
		strength_2.setBounds(31, 37, 25, 25);
		upgrade.add(strength_2);
		final JCheckBox attack_1 = new JCheckBox("");
		attack_1.setBounds(5, 67, 25, 25);
		upgrade.add(attack_1);
		final JCheckBox attack_2 = new JCheckBox("");
		attack_2.setBounds(31, 67, 25, 25);
		upgrade.add(attack_2);
		final JCheckBox defence_2 = new JCheckBox("");
		defence_2.setBounds(31, 97, 25, 25);
		upgrade.add(defence_2);
		final JCheckBox defence_1 = new JCheckBox("");
		defence_1.setBounds(5, 97, 25, 25);
		upgrade.add(defence_1);

		JLabel nextlevel_notification = new JLabel(
				"<html>Congratulations, you achieve the next level.<html> <br> Please select.");
		nextlevel_notification.setBounds(0, 0, 260, 34);
		upgrade.add(nextlevel_notification);

		JLabel lblStrength = new JLabel("Strength");
		lblStrength.setBounds(86, 41, 60, 16);
		upgrade.add(lblStrength);

		JLabel lblAttack = new JLabel("Attack");
		lblAttack.setBounds(86, 70, 56, 16);
		upgrade.add(lblAttack);

		JLabel lblDefence = new JLabel("Defence");
		lblDefence.setBounds(86, 101, 56, 16);
		upgrade.add(lblDefence);

		defence_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (defence_3.isSelected()) {// strength_1
					upg++;
					me.defence++;
					me.showing_1(label_2);
					strength_3.setVisible(false);
					attack_3.setVisible(false);
					if (upg == 3)
						nextrund.setVisible(true);
					else
						nextrund.setVisible(false);
				} else {
					upg--;
					if (upg != 3)
						nextrund.setVisible(false);
					me.defence--;
					me.showing_1(label_2);
					strength_3.setVisible(true);
					attack_3.setVisible(true);
				}

			}
		});
 
		defence_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (defence_1.isSelected()) {// defence_2
					strength_1.setVisible(false);
					attack_1.setVisible(false);
					upg++;
					me.defence++;
					me.showing_1(label_2);
					if (upg == 3)
						nextrund.setVisible(true);
					else
						nextrund.setVisible(false);
				}

				else {
					upg--;
					if (upg != 3)
						nextrund.setVisible(false);
					me.defence--;
					me.showing_1(label_2);
					strength_1.setVisible(true);
					attack_1.setVisible(true);
				}
			}
		});
		  
		defence_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (defence_2.isSelected()) {// defence_2
					upg++;
					me.defence++;
					me.showing_1(label_2);
					strength_2.setVisible(false);
					attack_2.setVisible(false);
					if (upg == 3)
						nextrund.setVisible(true);
				} else {
					upg--;
					if (upg != 3)
						nextrund.setVisible(false);
					me.defence--;
					me.showing_1(label_2);
					strength_2.setVisible(true);
					attack_2.setVisible(true);
				}
			}
		});
	  
		
		attack_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (attack_2.isSelected()) {
					upg++;
					me.attack++;
					me.showing_1(label_2);
					strength_2.setVisible(false);
					defence_2.setVisible(false);
					if (upg == 3)
						nextrund.setVisible(true);
				} else {
					upg--;
					if (upg != 3)
						nextrund.setVisible(false);
					me.attack--;
					me.showing_1(label_2);
					strength_2.setVisible(true);
					defence_2.setVisible(true);
				}
			}
		});

		attack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (attack_1.isSelected()) {
					upg++;
					me.attack++;
					me.showing_1(label_2);
					strength_1.setVisible(false);
					defence_1.setVisible(false);
					if (upg == 3)
						nextrund.setVisible(true);

				} else {
					upg--;
					if (upg != 3)
						nextrund.setVisible(false);
					me.attack--;
					me.showing_1(label_2);
					strength_1.setVisible(true);
					defence_1.setVisible(true);
				}
			}
		});

		strength_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (strength_2.isSelected()) {
					upg++;
					me.strength++;
					me.showing_1(label_2);
					attack_2.setVisible(false);
					defence_2.setVisible(false);
					if (upg == 3)
						nextrund.setVisible(true);
				} else {
					upg--;
					if (upg != 3)
						nextrund.setVisible(false);
					me.strength--;
					me.showing_1(label_2);
					attack_2.setVisible(true);
					defence_2.setVisible(true);
				}
			}
		});

		strength_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (strength_1.isSelected()) {
					upg++;
					me.strength++;
					me.showing_1(label_2);
					attack_1.setVisible(false);
					defence_1.setVisible(false);
					if (upg == 3)
						nextrund.setVisible(true);
					else
						nextrund.setVisible(false);
				} else {
					upg--;
					if (upg != 3)
						nextrund.setVisible(false);
					me.strength--;
					me.showing_1(label_2);
					attack_1.setVisible(true);
					defence_1.setVisible(true);
				}
			}
		});
		strength_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (strength_3.isSelected()) {
					upg++;
					me.strength++;
					me.showing_1(label_2);
					attack_3.setVisible(false);
					defence_3.setVisible(false);
					if (upg == 3)
						nextrund.setVisible(true);
					else
						nextrund.setVisible(false);
				} else {
					upg--;
					if (upg != 3)
						nextrund.setVisible(false);
					me.strength--;
					me.showing_1(label_2);
					attack_3.setVisible(true);
					defence_3.setVisible(true);
				}
			}
		});
		attack_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (attack_3.isSelected()) {// strength_1
					strength_3.setVisible(false);
					upg++;
					me.attack++;
					me.showing_1(label_2);
					defence_3.setVisible(false);
					if (upg == 3)
						nextrund.setVisible(true);
				} else {
					upg--;
					if (upg != 3)
						nextrund.setVisible(false);
					me.attack--;
					me.showing_1(label_2);
					strength_3.setVisible(true);
					defence_3.setVisible(true);
				}
			}
		});

		final JLabel lblYouWin = new JLabel("");
		lblYouWin.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouWin.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblYouWin.setBounds(382, 376, 400, 77);
		contentPane.add(lblYouWin);

		final JCheckBox chckbxEasy = new JCheckBox("Easy");

		chckbxEasy.setFont(new Font("Tahoma", Font.PLAIN, 19));
		chckbxEasy.setBounds(382, 63, 92, 47);
		contentPane.add(chckbxEasy);

		final JCheckBox chckbxNormal = new JCheckBox("Normal");

		chckbxNormal.setFont(new Font("Tahoma", Font.PLAIN, 19));
		chckbxNormal.setBounds(478, 63, 92, 47);
		contentPane.add(chckbxNormal);

		final JCheckBox chckbxHard = new JCheckBox("Hard");

		chckbxHard.setFont(new Font("Tahoma", Font.PLAIN, 19));
		chckbxHard.setBounds(592, 63, 92, 47);
		contentPane.add(chckbxHard);

		final JLabel turn = new JLabel("Turn:");
		turn.setHorizontalAlignment(SwingConstants.CENTER);
		turn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		turn.setBounds(403, 268, 304, 71);
		contentPane.add(turn);

		final JLabel item = new JLabel("");
		item.setBounds(22, 517, 314, 60);
		contentPane.add(item);


		chckbxEasy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tur++;
				coo = 1;
				turn.setText("Turn: " + tur);
				float dif_lev = 0.5f;
				me = new Ship(1, 1, 1, 1);
				me.picture_show(label_1);
				enemy = new Ship((1 * dif_lev), (1 * dif_lev), (1 * dif_lev), 1);
				me.showing(label_2, label_3, enemy);
				me.battle(label_2, label_3, label_1, lblYouWin, item, enemy);
				chckbxEasy.setVisible(false);
				chckbxNormal.setVisible(false);
				chckbxHard.setVisible(false);
				upgrade.setVisible(true);

			}
		});
		chckbxNormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tur++;
				coo = 2;
				turn.setText("Turn: " + tur);
				float dif_lev = 0.7f;
				me = new Ship(1, 1, 1, 1);
				me.picture_show(label_1);
				enemy = new Ship((1 * dif_lev), (1 * dif_lev), (1 * dif_lev), 1);
				me.showing(label_2, label_3, enemy);
				me.battle(label_2, label_3, label_1, lblYouWin, item, enemy);
				chckbxEasy.setVisible(false);
				chckbxNormal.setVisible(false);
				chckbxHard.setVisible(false);
				upgrade.setVisible(true);
			}
		});
		chckbxHard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tur++;
				coo = 3;
				turn.setText("Turn: " + tur);
				float dif_lev = 0.9f;
				me = new Ship(1, 1, 1, 1);
				me.picture_show(label_1);
				enemy = new Ship((1 * dif_lev), (1 * dif_lev), (1 * dif_lev), 1);
				me.showing(label_2, label_3, enemy);
				me.battle(label_2, label_3, label_1, lblYouWin, item, enemy);
				chckbxEasy.setVisible(false);
				chckbxNormal.setVisible(false);
				chckbxHard.setVisible(false);
				upgrade.setVisible(true);

			}
		});
		nextrund.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				upg = 0;
				tur++;
				float dif_lev = 0;
				me.strength += 0.35f;
				turn.setText("Turn: " + tur);
				upgrade.setVisible(false);
				nextrund.setSelected(false);
				nextrund.setVisible(false);
				me.picture_show(label_1);
				if (coo == 1)
					dif_lev = 0.5f;
				else if (coo == 2)
					dif_lev = 0.7f;
				else if (coo == 3)
					dif_lev = 0.9f;
				float now = tur * dif_lev;
				enemy = new Ship(now, now, now, 1);
				me.showing(label_2, label_3, enemy);
				item.setText("");
				me.battle(label_2, label_3, label_1, lblYouWin, item, enemy);
				if (me.strength > 0.0f) {
					strength_1.setSelected(false);
					strength_1.setVisible(true);
					strength_2.setSelected(false);
					strength_2.setVisible(true);
					strength_3.setVisible(true);
					strength_3.setSelected(false);
					strength_3.setVisible(true);
					attack_1.setSelected(false);
					attack_1.setVisible(true);
					attack_2.setSelected(false);
					attack_2.setVisible(true);
					attack_3.setSelected(false);
					attack_3.setVisible(true);
					defence_1.setSelected(false);
					defence_1.setVisible(true);
					defence_2.setSelected(false);
					defence_2.setVisible(true);
					defence_3.setSelected(false);
					defence_3.setVisible(true);
					upgrade.setVisible(true);
				}
			}
		});

	}
}
