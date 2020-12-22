package gui;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.UIManager;

import core.Pinger;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;


@SuppressWarnings("all")
public class Frame {
	
	private JFrame mainFrame;
	private JLabel pageTitle;
	private JDesktopPane mainPanel;
	private JLabel yourIPlbl;
	private JLabel iplbl;
	
	private Pinger pinger;
	private JLabel macTargetLbl;
	private JTextField MACtextField;
	private JLabel warnLabel;
	private JTextField broadcastField;
	
	public Frame() {
		mainFrame = new JFrame();
		mainFrame.setTitle("WakeOnLan Waker 1.0.2 | by dpetrou");
		mainFrame.setSize(600,400);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);
		
		pinger = new Pinger();
		constructLayout();
	}
	
	
	private void constructLayout() {
		
		mainPanel = new JDesktopPane();
		mainPanel.setBackground(UIManager.getColor("Button.background"));
		mainPanel.setSize(600,400);
		mainFrame.getContentPane().add(mainPanel);
		
		pageTitle = new JLabel("WakeOnLan Waker");
		pageTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		pageTitle.setHorizontalAlignment(SwingConstants.CENTER);
		pageTitle.setBounds(160, 11, 254, 35);
		mainPanel.add(pageTitle);
		
		yourIPlbl = new JLabel("Your local IP:");
		yourIPlbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		yourIPlbl.setBounds(194, 58, 120, 14);
		mainPanel.add(yourIPlbl);
		
		iplbl = new JLabel(pinger.getLocalIP());
		iplbl.setHorizontalAlignment(SwingConstants.CENTER);
		iplbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		iplbl.setForeground(new Color(25, 25, 112));
		iplbl.setBounds(261, 58, 153, 14);
		mainPanel.add(iplbl);
		
		macTargetLbl = new JLabel("Target MAC: ");
		macTargetLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		macTargetLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		macTargetLbl.setBounds(126, 198, 110, 22);
		mainPanel.add(macTargetLbl);
		
		broadcastField = new JTextField("192.168.1.255");
		broadcastField.setFont(new Font("Tahoma", Font.BOLD, 13));
		broadcastField.setColumns(10);
		broadcastField.setBorder(new CompoundBorder(new LineBorder(new Color(105, 105, 105)), new LineBorder(new Color(255, 255, 255), 5)));
		broadcastField.setBounds(261, 147, 197, 35);
		mainPanel.add(broadcastField);
		
		MACtextField = new JTextField("");
		MACtextField.setFont(new Font("Tahoma", Font.BOLD, 13));
		MACtextField.setBorder(new CompoundBorder(new LineBorder(new Color(105, 105, 105)), new LineBorder(new Color(255, 255, 255), 5)));
		MACtextField.setColumns(10);
		MACtextField.setBounds(261, 193, 197, 35);
		mainPanel.add(MACtextField);
		
		warnLabel = new JLabel();
		warnLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		warnLabel.setHorizontalAlignment(SwingConstants.CENTER);
		warnLabel.setForeground(new Color(128, 0, 0));
		warnLabel.setBounds(194, 310, 187, 25);
		mainPanel.add(warnLabel);
		
		JButton wakeupBTN = new JButton("Wake Up");
		wakeupBTN.setBackground(new Color(211, 211, 211));
		wakeupBTN.setForeground(new Color(25, 25, 112));
		wakeupBTN.setBorder(new LineBorder(new Color(0, 0, 128)));
		wakeupBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					warnLabel.setText("");
				    pinger.wakeUp(broadcastField.getText().trim(), MACtextField.getText().trim());
					warnLabel.setForeground(new Color(46,139,87));
					warnLabel.setText("MagicPacket sent");					
				} catch (Exception e1) {
					warnLabel.setForeground(new Color(128,0,0));
					warnLabel.setText("Error!");
				}
			}
		});
		
		wakeupBTN.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				wakeupBTN.setBackground(new Color(25, 25, 112));
				wakeupBTN.setForeground(new Color(255,255,255));
			}
			
			public void mouseExited(MouseEvent e) {
				wakeupBTN.setBackground(new Color(211,211,211));
				wakeupBTN.setForeground(new Color(25,25,112));
			}
		});
		wakeupBTN.setFocusPainted(false);
		wakeupBTN.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		wakeupBTN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		wakeupBTN.setBounds(348, 266, 110, 35);
		mainPanel.add(wakeupBTN);
		
		JLabel lblBroadcastNetwork = new JLabel("Broadcast Network:");
		lblBroadcastNetwork.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBroadcastNetwork.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBroadcastNetwork.setBounds(77, 152, 153, 22);
		mainPanel.add(lblBroadcastNetwork);
		
	}
	
	public static void main(String[] args) {
		Frame appFrame = new Frame();
	}
}
