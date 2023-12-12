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

public class Delete extends Update
{
public void d() throws ClassNotFoundException, SQLException
{
	JButton del = new JButton("Delete");
	del.setFont(new Font("Algerian", Font.BOLD, 17));
	del.setBounds(460, 80, 130, 40);
	
	Connection con = Add.connect();
	Statement st = con.createStatement();
	
	del.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
     
			JFrame f1=new JFrame("Delete Product");
			JTextField s2 = new JTextField();
				s2.setFont(new Font("Algerian", Font.BOLD, 17));
				s2.setBounds(50, 90, 300, 40);
				
				JLabel sl2 = new JLabel("Product Name");
				sl2.setFont(new Font("Algerian", Font.BOLD, 17));
				sl2.setBounds(60, 50, 200, 40);

				JButton B5 = new JButton("Delete");
				B5.setFont(new Font("Algerian", Font.BOLD, 17));
				B5.setBounds(60, 150, 200, 40);
				

				JLabel L1 = new JLabel("Product Name");
				L1.setFont(new Font("Algerian", Font.BOLD, 17));
				L1.setBounds(60, 50, 200, 40);

				JTextField T1 = new JTextField();
				T1.setFont(new Font("Algerian", Font.BOLD, 17));
				T1.setBounds(50, 90, 390, 40);

				
				
				B5.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) 
					{
					String name=s2.getText();
					try
					{
						int d=st.executeUpdate("delete from i1l where name='"+name+"'");
						JOptionPane.showInternalMessageDialog(B5, "DATA-DELETED");

					}
					catch(Exception e1)
					{
						e1.printStackTrace();
						
					}
						
					}
				});
				
				JButton clear3 = new JButton("clear");
 				clear3.setFont(new Font("Algerian", Font.BOLD, 17));
 				clear3.setBounds(60, 200, 200, 40);
 				clear3.addActionListener(new ActionListener() {
 					@Override
 					public void actionPerformed(ActionEvent e) {

 						s2.setText("");
 						
 					}				
 				});			
				
				
				
				
				f1.add(s2);
 				f1.add(sl2);
 				f1.add(B5);	
                f1.add(clear3);
 				f1.setBounds(250, 100, 700, 600);
 				f1.setLayout(null);
 				f1.setVisible(true);
		}
	});
	
	
	
	
	f.add(del);
}
}
