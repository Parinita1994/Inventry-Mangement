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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class History extends Purchase
{
	public void H() throws ClassNotFoundException, SQLException
	{
		 JButton h = new JButton("History");
			h.setFont(new Font("Algerian", Font.BOLD, 17));
			h.setBounds(740, 80, 130, 40);
			
			Connection con = Add.connect();
	 		Statement st = con.createStatement();
	 		
	 		h.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					//Create a statement to execute SQL queries
			        Statement statement = null;
			        try 
			        {
			            statement = con.createStatement();
			        }
			        catch (SQLException e1) 
			        {
			            e1.printStackTrace();
			        }
			        // Execute a SELECT query to get data from the database
			        String sql = "SELECT * FROM  i1l ORDER BY id DESC";
			        ResultSet resultSet = null;
			        try
			        {
			            resultSet = statement.executeQuery(sql);
			        } 
			        catch (SQLException e1) 
			        {
			            e1.printStackTrace();
			        }
			        // Create a JFrame to display the data
			        JFrame frame = new JFrame("Database Data");
			        // Create a JTable to display the data
			        JTable table = null;
					try {
						table = new JTable(buildTableModel(resultSet));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			        // Add the JTable to the JFrame
			        frame.add(new JScrollPane(table));
			        // Display the JFrame
			        frame.pack();
			        frame.setSize(500,140);
			        frame.setVisible(true);
					
			    }
			    private  DefaultTableModel buildTableModel(ResultSet resultSet) throws SQLException
			    {
			        // Create a table model to hold the data
			        DefaultTableModel tableModel = new DefaultTableModel();
			        
			        // Get the column names from the database
			        ResultSetMetaData metaData = resultSet.getMetaData();
			        
			        for (int i = 1; i <= metaData.getColumnCount(); i++)
			        {
			            tableModel.addColumn(metaData.getColumnName(i));
			        }
			        // Get the data from the database
			        while (resultSet.next()) 
			        {
			            // Create a new row in the table model
			            Object[] row = new Object[tableModel.getColumnCount()];
			            
			            for (int i = 0; i < row.length; i++) 
			            {
			                row[i] = resultSet.getObject(i + 1);
			            }
			            tableModel.addRow(row);
			        }
					return tableModel;
						
					
				}
			});
	 		f.add(h);
	 		f.setSize(800, 900);
	 		f.setLayout(null);
	 		f.setVisible(true);
}
}
