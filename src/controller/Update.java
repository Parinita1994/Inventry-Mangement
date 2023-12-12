package controller;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Update extends Search
{
	public void u() throws SQLException, ClassNotFoundException
	{
		JButton update = new JButton("Update");
		update.setFont(new Font("Algerian", Font.BOLD, 17));
		update.setBounds(320, 80, 130, 40);
		
		Connection con = Add.connect();
		Statement st = con.createStatement();
		
		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame f2 = new JFrame("Update");

				JTextField t5 = new JTextField();
				t5.setFont(new Font("Algerian", Font.BOLD, 17));
				t5.setBounds(50, 90, 300, 40);
				JLabel l5 = new JLabel("Product Name");
				l5.setFont(new Font("Algerian", Font.BOLD, 17));
				l5.setBounds(60, 50, 200, 40);

				JTextField t6 = new JTextField();
				t6.setFont(new Font("Algerian", Font.BOLD, 17));
				t6.setBounds(50, 180, 200, 40);
				JLabel l6 = new JLabel("Product Quantity");
				l6.setFont(new Font("Algerian", Font.BOLD, 17));
				l6.setBounds(60, 140, 200, 40);

				JTextField t7 = new JTextField();
				t7.setFont(new Font("Algerian", Font.BOLD, 17));
				t7.setBounds(50, 270, 200, 40);
				JLabel l7 = new JLabel("Per Product Rate");
				l7.setFont(new Font("Algerian", Font.BOLD, 17));
				l7.setBounds(60, 230, 200, 40);

				JButton B2 = new JButton("UPDATE");
				B2.setFont(new Font("Algerian", Font.BOLD, 17));
				B2.setBounds(50, 550, 160, 30);
				B2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String name = t5.getText();
						int quantity = Integer.parseInt(t6.getText());
						int rate = Integer.parseInt(t7.getText());

						try {
							int c = st.executeUpdate("update i1l set quantity=" + quantity + " where name='" + name + "'");
							int d = st.executeUpdate("update i1l set rate=" + rate + " where name='" + name + "'");
							int g = st.executeUpdate("update i1l set Price=" + quantity * rate + " where name='" + name + "'");
						}
						catch (SQLException e1) {
							e1.printStackTrace();
						}

						JOptionPane.showMessageDialog(B2, "Data- UPDATE");
					}
				});

				JButton up = new JButton("TOTAL");
				up.setFont(new Font("Algerian", Font.BOLD, 17));
				up.setBounds(50, 350, 160, 30);

				JLabel label3 = new JLabel("TOTAL");
				label3.setFont(new Font("Algerian", Font.BOLD, 20));
				label3.setBounds(60, 390, 300, 40);

				up.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						int ProductQ = Integer.parseInt(t6.getText());
						int Rate = Integer.parseInt(t7.getText());
						label3.setText("TOTAL AMOUNT : " + (ProductQ * Rate));
					}
				});

				JButton clear2 = new JButton("clear");
				clear2.setFont(new Font("Algerian", Font.BOLD, 17));
				clear2.setBounds(250, 550, 160, 30);
				clear2.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						t5.setText("");
						t6.setText("");
						t7.setText("");
					}
				});

				f2.add(t5);// TextField 1-Product Name
				f2.add(t6);//TextField 2-Product Quantity
				f2.add(t7);//TextField 1-Product Rate
				f2.add(B2);//Button-update 
				f2.add(clear2);//Button-Claer 
				f2.add(up);//Button-total
				f2.add(label3);//Total-Label
				f2.add(l5);	//Label 1-Product Name
				f2.add(l6);//Label 2-Product Quantity
				f2.add(l7);	//Label 3-Product Rate
				f2.setSize(500, 800);
				f2.setLayout(null);
				f2.setVisible(true);
			}
		});	
        f.add(update);
		
		}
}
