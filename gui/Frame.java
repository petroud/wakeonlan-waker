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
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;


@SuppressWarnings("all")
public class Frame {
	
	private JFrame mainFrame;
	private JLabel pageTitle;
	private JDesktopPane mainPanel;
	private JLabel yourIPlbl;
	private JLabel iplbl;
	
	private Pinger pinger;

	
	private JLabel ipTargetLbl;
	private JLabel macTargetLbl;
	private JTextField ipTextField;
	private JTextField MACtextField;
	private JLabel warnLabel;
	
	public Frame() {
		mainFrame = new JFrame("WakeOnLan Waker 1.0.0 | by dpetrou");
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
		pageTitle.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 19));
		pageTitle.setHorizontalAlignment(SwingConstants.CENTER);
		pageTitle.setBounds(201, 11, 184, 35);
		mainPanel.add(pageTitle);
		
		yourIPlbl = new JLabel("Your local IP:");
		yourIPlbl.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 13));
		yourIPlbl.setBounds(201, 57, 101, 14);
		mainPanel.add(yourIPlbl);
		
		iplbl = new JLabel(pinger.getLocalIP());
		iplbl.setHorizontalAlignment(SwingConstants.CENTER);
		iplbl.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		iplbl.setForeground(new Color(25, 25, 112));
		iplbl.setBounds(266, 57, 153, 14);
		mainPanel.add(iplbl);
		
		ipTargetLbl = new JLabel("Target IP: ");
		ipTargetLbl.setForeground(new Color(0, 0, 0));
		ipTargetLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		ipTargetLbl.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 12));
		ipTargetLbl.setBounds(131, 133, 76, 22);
		mainPanel.add(ipTargetLbl);
		
		macTargetLbl = new JLabel("Target MAC: ");
		macTargetLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		macTargetLbl.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 12));
		macTargetLbl.setBounds(131, 186, 76, 22);
		mainPanel.add(macTargetLbl);
		
		ipTextField = new JTextField();
		ipTextField.setBorder(new CompoundBorder(new LineBorder(new Color(105, 105, 105)), new LineBorder(new Color(255, 255, 255), 5)));
		ipTextField.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		ipTextField.setBounds(217, 127, 184, 35);
		mainPanel.add(ipTextField);
		ipTextField.setColumns(10);
		
		MACtextField = new JTextField("");
		MACtextField.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		MACtextField.setBorder(new CompoundBorder(new LineBorder(new Color(105, 105, 105)), new LineBorder(new Color(255, 255, 255), 5)));
		MACtextField.setColumns(10);
		MACtextField.setBounds(217, 180, 184, 35);
		mainPanel.add(MACtextField);
		
		warnLabel = new JLabel("");
		warnLabel.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		warnLabel.setHorizontalAlignment(SwingConstants.CENTER);
		warnLabel.setForeground(new Color(128, 0, 0));
		warnLabel.setBounds(246, 280, 123, 25);
		mainPanel.add(warnLabel);
		
		JButton wakeupBTN = new JButton("Wake Up");
		wakeupBTN.setBackground(new Color(211, 211, 211));
		wakeupBTN.setForeground(new Color(25, 25, 112));
		wakeupBTN.setBorder(new LineBorder(new Color(0, 0, 128)));
		wakeupBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					warnLabel.setText("");
					pinger.wakeUp(ipTextField.getText(), MACtextField.getText());
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
		wakeupBTN.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 13));
		wakeupBTN.setBounds(252, 238, 110, 35);
		mainPanel.add(wakeupBTN);
		
	}
	
	public static void main(String[] args) {
		Frame appFrame = new Frame();
	}
}
