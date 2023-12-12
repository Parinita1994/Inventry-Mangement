package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Purchase extends Delete
{
	public void p() throws ClassNotFoundException, SQLException {
        JButton purchase = new JButton("Purchase");
        purchase.setFont(new Font("Algerian", Font.BOLD, 17));
        purchase.setBounds(620, 80, 130, 40);

        Connection con = Add.connect();
        Statement st = con.createStatement();

        purchase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JFrame f3 = new JFrame("Add to cart");

                JLabel dateLabel = new JLabel();
                dateLabel.setFont(new Font("Algerian", Font.BOLD, 15));
                dateLabel.setBounds(260, 10, 500, 40);
                dateLabel.setForeground(Color.black);

                f3.add(dateLabel);

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = dateFormat.format(new Date());
                dateLabel.setText("Date: " + formattedDate);

                JTextField p1 = new JTextField();
                p1.setFont(new Font("Algerian", Font.BOLD, 17));
                p1.setBounds(30, 60, 200, 30);

                JTextField p2 = new JTextField();
                p2.setFont(new Font("Algerian", Font.BOLD, 17));
                p2.setBounds(30, 120, 200, 30);

                JTextField p3 = new JTextField();
                p3.setFont(new Font("Algerian", Font.BOLD, 17));
                p3.setBounds(30, 180, 200, 30);

                JLabel pl1 = new JLabel("Add Product ");
                pl1.setFont(new Font("Algerian", Font.BOLD, 17));
                pl1.setBounds(40, 30, 250, 30);

                JLabel p22 = new JLabel("Quantity ");
                p22.setFont(new Font("Algerian", Font.BOLD, 17));
                p22.setBounds(40, 90, 250, 30);

                JLabel p33 = new JLabel("Rate ");
                p33.setFont(new Font("Algerian", Font.BOLD, 17));
                p33.setBounds(40, 150, 250, 30);

                JButton B5 = new JButton("Add product");
                B5.setFont(new Font("Algerian", Font.BOLD, 17));
                B5.setBounds(250, 60, 170, 30);

                B5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int id = 0;
                        String name = p1.getText();
                        int quantity = Integer.parseInt(p2.getText());
                        int rate = Integer.parseInt(p3.getText());

                        try {
                        	int c = st
									.executeUpdate("update i1l set quantity=" + quantity + " where name='" + name + "'");
							int d = st.executeUpdate("update i1l set rate=" + rate + " where name='" + name + "'");
							int g = st.executeUpdate(
									"update i1l set price=" + quantity * rate + " where name='" + name + "'");
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        JOptionPane.showMessageDialog(B5, "DATA -INSETED");
                        p1.setText("");
                        p2.setText("");
                        p3.setText("");
                    }
                });

                JButton sp = new JButton("Show product");
                sp.setFont(new Font("Algerian", Font.BOLD, 17));
                sp.setBounds(250, 120, 170, 30);

                sp.addActionListener(new ActionListener() {
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
                        JFrame frame = new JFrame("SHOW DATA");
                        // Create a JTable to display the data
                        JTable table ;
                        try {
                            table = new JTable(buildTableModel(resultSet));
                            table.setFont(new Font("Algerian", Font.BOLD, 17));

                            
                          
                            
                            JButton deleteButton = new JButton("Delete");
                            deleteButton.setFont(new Font("Algerian", Font.BOLD, 17));
                            deleteButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    int selectedRow = table.getSelectedRow();
                                    if (selectedRow != -1) {
                                        String name = table.getValueAt(selectedRow, 1).toString();
                                        try {
                                            int result = st.executeUpdate(
                                                    "DELETE FROM i1l WHERE name='" + name + "'");
                                            if (result > 0) {
                                                JOptionPane.showMessageDialog(f3, "Data deleted successfully");
                                            } else {
                                                JOptionPane.showMessageDialog(f3, "Failed to delete data");
                                            }
                                        } catch (SQLException ex) {
                                            ex.printStackTrace();
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(f3, "No row selected");
                                    }
                                }
                            });

                            JPanel buttonPanel = new JPanel();
//                            buttonPanel.add(updateButton);
                            buttonPanel.add(deleteButton);

                            frame.setLayout(new BorderLayout());
                            frame.add(new JScrollPane(table), BorderLayout.CENTER);
                            frame.add(buttonPanel, BorderLayout.SOUTH);
                            frame.setSize(500, 140);
                            frame.setVisible(true);
                           } 
                        catch (SQLException e) 
                        {
                            e.printStackTrace();
                        }
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

                JButton QR = new JButton("QR CODE");
                QR.setFont(new Font("Algerian", Font.BOLD, 17));
                QR.setBounds(250, 180, 170, 30);

                QR.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        JFrame frame = new JFrame("Background Image Example");

                        // Set the size of the JFrame
                    	frame.setBounds(250, 150, 500, 600);
                        // Create a JPanel with a background image
                        JPanel panel = new JPanel() {
                            @Override
                            protected void paintComponent(Graphics g) {
                                super.paintComponent(g);
                                Image image = new ImageIcon(
                                        "C:\\Users\\ASUS\\OneDrive\\Desktop\\QR1.jpg").getImage();
                                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                            }
                        };

                        // Set the layout manager for the panel
                        panel.setLayout(new BorderLayout());

                        // Add other components to the panel
                        // ...

                        // Add the panel to the frame
                        frame.add(panel);

                        // Set the frame visible
                        frame.setVisible(true);

                        try {
                            BufferedImage image = ImageIO.read(new File(
                                    "C:\\Users\\ASUS\\OneDrive\\Desktop\\Nakshtra photo.jpg"));
                        } catch (IOException e1) {
                            
                            e1.printStackTrace();
                        }

                    }

                });

                JButton clear5 = new JButton("clear");
                clear5.setFont(new Font("Algerian", Font.BOLD, 17));
                clear5.setBounds(250, 600, 100, 30);
                clear5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        p1.setText("");
                    }
                });

                JButton PO = new JButton("Purchase Order");
                PO.setFont(new Font("Algerian", Font.BOLD, 17));
                PO.setBounds(30, 600, 200, 30);

               
                
                
             
                f3.add(p1);
                f3.add(p2);
                f3.add(p3);
                f3.add(pl1);
                f3.add(p22);
                f3.add(p33);
                f3.add(B5);
                f3.add(sp);
               
                f3.add(PO);
                f3.add(QR);
                f3.add(clear5);

                f3.setSize(450, 700);
                f3.setLayout(null);
                f3.setVisible(true);

            }

        });

        f.add(purchase);
    }
			}
