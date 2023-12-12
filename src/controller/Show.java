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

public class Show extends History
{
	public void sh() throws SQLException, ClassNotFoundException
	{
	JButton show = new JButton("Show");
	show.setFont(new Font("Algerian", Font.BOLD, 17));
	show.setBounds(880, 80, 130, 40);
	
	Connection con = Add.connect();
	Statement st = con.createStatement();
	
	
	show.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e1) {
			Statement statement = null;
			try {
				statement = con.createStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// Execute a SELECT query to get data from the database
			String sql = "SELECT * FROM  i1l";
			
			ResultSet resultSet = null;
			try {
				resultSet = statement.executeQuery(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// Create a JFrame to display the data
			JFrame frame = new JFrame("SHOW");
			// Create a JTable to display the data
			JTable table = null;
			try {
				table = new JTable(buildTableModel(resultSet));
				table.setFont(new Font("Algerian", Font.BOLD, 17));

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Add the JTable to the JFrame
			frame.add(new JScrollPane(table));
			table.setFont(new Font("Algerian", Font.BOLD, 17));

			// Display the JFrame
			frame.pack();
			frame.setSize(500, 140);
			frame.setVisible(true);

		}

		private DefaultTableModel buildTableModel(ResultSet resultSet) throws SQLException {
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
	f.add(show);
	f.setSize(1200, 700);
	f.setLayout(null);
	f.setVisible(true);
}

}
