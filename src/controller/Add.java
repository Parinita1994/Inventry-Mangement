package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Add 
{
	JFrame f = new JFrame("Inventry Mangement System");

	public static Connection connect() throws ClassNotFoundException, SQLException
	{
		String url = "jdbc:mysql://localhost:3306/batchdb", uname = "root", pass = "abc123";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, uname, pass);
		Statement st = con.createStatement();
	
		return con;
	}
	
	public void a() throws ClassNotFoundException, SQLException
	{
		
		 JButton add = new JButton("Add");
		 add.setFont(new Font("Algerian", Font.BOLD, 17));
		 add.setBounds(50, 80, 100, 40) ;
	
		Connection con = Add.connect();
		Statement st = con.createStatement();
		
		JLabel dateLabel = new JLabel();
		dateLabel.setFont(new Font("Algerian", Font.BOLD, 27));
		dateLabel.setBounds(400, 10, 500, 40);
		dateLabel.setForeground(Color.red);	
		f.add(dateLabel);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = dateFormat.format(new Date());
		dateLabel.setText("Date: " + formattedDate);
		
		
		 add.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame f1 = new JFrame("Add Product");

					JTextField t1 = new JTextField();
					t1.setFont(new Font("Algerian", Font.BOLD, 17));
					t1.setBounds(50, 90, 300, 40);
					JLabel l1 = new JLabel("Product Name");
					l1.setFont(new Font("Algerian", Font.BOLD, 17));
					l1.setBounds(60, 50, 200, 40);

					JTextField t2 = new JTextField();
					t2.setFont(new Font("Algerian", Font.BOLD, 17));
					t2.setBounds(50, 180, 300, 40);
					JLabel l2 = new JLabel("Product Quantity");
					l2.setFont(new Font("Algerian", Font.BOLD, 17));
					l2.setBounds(60, 140, 200, 40);

					JTextField t3 = new JTextField();
					t3.setFont(new Font("Algerian", Font.BOLD, 17));
					t3.setBounds(50, 270, 300, 40);
					JLabel l3 = new JLabel("Per Product Rate");
					l3.setFont(new Font("Algerian", Font.BOLD, 17));
					l3.setBounds(60, 230, 200, 40);
					
					JButton B1 = new JButton("TOTAL");
					B1.setFont(new Font("Algerian", Font.BOLD, 17));
					B1.setBounds(50, 330, 160, 30);

					JLabel l = new JLabel("TOTAL AMOUNT");
					l.setFont(new Font("Algerian", Font.BOLD, 17));
					l.setBounds(50, 390, 250, 40);

					
					JTextField label = new JTextField();
					label.setFont(new Font("Algerian", Font.BOLD, 20));
					label.setBounds(50, 430, 250, 40);

					B1.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {

							int quantity = Integer.parseInt(t2.getText());
							int rate = Integer.parseInt(t3.getText());
							label.setText(""+(quantity * rate));

						}
					});				
					JButton addp = new JButton("Add Product");
					addp.setFont(new Font("Algerian", Font.BOLD, 17));
					addp.setBounds(50, 500, 160, 30);
					
					addp.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
                            int id=0;
							String name = t1.getText();
							int quantity = Integer.parseInt(t2.getText());
							int rate = Integer.parseInt(t3.getText());

							try {
								int a = st.executeUpdate("Insert into i1l values("+id+",'" + name + "'," + quantity + "," + rate
										+ "," + quantity * rate + ")");
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							JOptionPane.showMessageDialog(addp, "DATA -INSETED");
							t1.setText("");
							t2.setText("");
							t3.setText("");		
							label.setText("");
						}
					});
					
					
					JButton clear = new JButton("clear");
					clear.setFont(new Font("Algerian", Font.BOLD, 17));
					clear.setBounds(230, 500, 100, 30);
					clear.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {

							t1.setText("");
							t2.setText("");
							t3.setText("");		
							label.setText("");
						}
					});
						
					f1.add(t1);//TextField 1-Product Name
					f1.add(t2);//TextField 2-Product Quantity
					f1.add(t3);//TextField 1-Product Rate
					f1.add(l1);//Label 1-Product Name
					f1.add(l2);//Label 2-Product Quantity
					f1.add(l3);//Label 3-Product Rate
					f1.add(B1);//Total Button
					f1.add(label);//Total Text
					f1.add(addp);//Button-Add Product
					f1.add(clear);//Button Clear
					f1.add(l);
					
					f1.setSize(500, 800);
					f1.setLayout(null);
					f1.setVisible(true);
				}
			});			
		f.add(add);		
		f.setSize(1200, 800);
		f.setLayout(null);
		f.setVisible(true);
		}
}
