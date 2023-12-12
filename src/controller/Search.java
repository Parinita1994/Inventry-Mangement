package controller;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Search extends Add
{
	 public void s () throws SQLException, ClassNotFoundException
	 {
	 	  
	 	 
	 	    JButton ser = new JButton("Search");
	 		ser.setFont(new Font("Algerian", Font.BOLD, 17));
	 		ser.setBounds(170, 80, 130, 40);
	 		
	 		Connection con = Add.connect();
	 		Statement st = con.createStatement();
	 		
	 		
	 		ser.addActionListener(new ActionListener() {

	 			@Override
	 			public void actionPerformed(ActionEvent e) {

	 				JFrame f4 = new JFrame("Search Product");

	 				JTextField s1 = new JTextField();
	 				s1.setFont(new Font("Algerian", Font.BOLD, 17));
	 				s1.setBounds(50, 90, 300, 40);
	 				
	 				JLabel sl1 = new JLabel("Product Name");
	 				sl1.setFont(new Font("Algerian", Font.BOLD, 17));
	 				sl1.setBounds(60, 50, 200, 40);

	 				JButton B4 = new JButton("Search");
	 				B4.setFont(new Font("Algerian", Font.BOLD, 17));
	 				B4.setBounds(60, 150, 200, 40);
	 				

	 				JLabel ll1 = new JLabel("Product Name");
	 				ll1.setFont(new Font("Algerian", Font.BOLD, 17));
	 				ll1.setBounds(60, 50, 200, 40);

	 				JTextField t11 = new JTextField();
	 				t11.setFont(new Font("Algerian", Font.BOLD, 17));
	 				t11.setBounds(50, 90, 390, 40);

	 				JButton search = new JButton("SEARCH");
	 				search.setFont(new Font("Algerian", Font.BOLD, 17));
	 				search.setBounds(60, 150, 200, 40);
	 				B4.addActionListener(new ActionListener() {

	 					@Override
	 					public void actionPerformed(ActionEvent e) {

	 						String name = s1.getText();
	 						
	 						String sql = "Select * from i1l where name LIKE'" + name + "%'";
	 						
	 						ResultSet resultSet = null;
	 						try {
	 							resultSet = st.executeQuery(sql);
	 						} catch (SQLException e23) {
	 							e23.printStackTrace();
	 						}
	 						// Create a JFrame to display the data
	 						JFrame frame = new JFrame("SHOW");
	 						// Create a JTable to display the data
	 						JTable table = null;
	 						try {
	 							table = new JTable(buildTableModel(resultSet));
	 						} catch (SQLException e1) {
	 							// TODO Auto-generated catch block
	 							e1.printStackTrace();
	 						}
	 						table.setFont(new Font("Algerian", Font.BOLD, 17));
	 						// Add the JTable to the JFrame
	 						frame.add(new JScrollPane(table));
	 						table.setFont(new Font("Algerian", Font.BOLD, 17));

	 						// Display the JFrame
	 						frame.pack();
	 						frame.setBounds(350, 450, 700, 200);
	 						frame.setVisible(true);
	 					}
	 					
	 					DefaultTableModel buildTableModel(ResultSet resultSet) throws SQLException {
	 						// Create a table model to hold the data
	 						DefaultTableModel tableModel = new DefaultTableModel();

	 						// Get the column names from the database
	 						ResultSetMetaData metaData = resultSet.getMetaData();

	 						for (int i = 1; i <= metaData.getColumnCount(); i++) {
	 							tableModel.addColumn(metaData.getColumnName(i));
	 						}
	 						// Get the data from the database
	 						while (resultSet.next()) {
	 							// Create a new row in the table model
	 							Object[] row = new Object[tableModel.getColumnCount()];

	 							for (int i = 0; i < row.length; i++) {
	 								row[i] = resultSet.getObject(i + 1);
	 							}
	 							tableModel.addRow(row);
	 						}
	 						return tableModel;
	 					}				
	 					
	 				});	
	 				
	 				JButton clear1 = new JButton("clear");
	 				clear1.setFont(new Font("Algerian", Font.BOLD, 17));
	 				clear1.setBounds(60, 200, 200, 40);
	 				clear1.addActionListener(new ActionListener() {
	 					@Override
	 					public void actionPerformed(ActionEvent e) {

	 						s1.setText("");
	 						
	 					}				
	 				});				
	 				f4.add(s1);
	 				f4.add(sl1);
	 				f4.add(B4);
	 				
	                 f4.add(clear1);
	 				f4.setBounds(250, 100, 700, 600);
	 				f4.setLayout(null);
	 				f4.setVisible(true);
	 			}					
	 		});		
	 		
	 		f.add(ser);	
	 		
	 }
}
