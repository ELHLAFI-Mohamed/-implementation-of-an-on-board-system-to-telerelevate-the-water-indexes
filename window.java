import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Vector;
import java.util.logging.FileHandler;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class window extends JFrame implements ActionListener{
	 Compteur c=new Compteur();
	
	
	 JScrollPane scrollPane;
	JPanel panel;
    JLabel user_label, password_label, message;
    JTextField userName_text;
    JPasswordField password_text;
    JButton submit, cancel;
    Panneau pan=new Panneau();
    JTable jt;
	Vector<Vector> data=new Vector();
	Vector<Vector> data1=new Vector();
	Vector<String> columns=new Vector();
	Vector<String> columns1=new Vector();
	 DefaultTableModel model = new DefaultTableModel(data,columns);
  
    
	public window() {
		 // User Label
        user_label = new JLabel();
        user_label.setText("User Name :");
        userName_text = new JTextField();
        
        // Password

        password_label = new JLabel();
        password_label.setText("Password :");
        password_text = new JPasswordField();

        // Submit

        submit = new JButton("Login");

       panel = new JPanel(new GridLayout(3, 1));

        panel.add(user_label);
        panel.add(userName_text);
        panel.add(password_label);
        panel.add(password_text);

        message = new JLabel();
        panel.add(message);
        panel.add(submit);
        panel.add(submit);
        panel.setPreferredSize(new Dimension(500, 100));
       
        pan.add(panel, BorderLayout.CENTER);

        
       
        setContentPane(pan);
        setTitle("                                                                ****RADEE_TA****               ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,1000);
        setVisible(true);
        submit.addActionListener(this);
     
             
        // Adding the listeners to components..
       
       
        
	}
	

	

	  public void actionPerformed(ActionEvent ae) {
	        String userName = userName_text.getText();
	        String password = password_text.getText();
	     
	        if (userName.trim().equals("admin") && password.trim().equals("admin")) {

	try{  
		Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/radee_ta?autoReconnect=true&useSSL=false","root","root");  
		Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery("select * from Compteur");  
		while(rs.next()) {
			Vector vect=new Vector();
		vect.add(rs.getInt("Num_secteur"));
		vect.add(rs.getInt("Num_compteur"));
		vect.add(rs.getInt("index_compteur"));
		data.addElement(vect);
		}
		
				
		
		  
	
		columns.add("Num_secteur");
		columns.add("Num_compteur");
		columns.add("index_compteur");
		
	
		
		     
		    jt=new JTable(data,columns); 
		   jt.setBounds(300,300,900,900); 
		  
		  scrollPane=new JScrollPane(jt);
		  scrollPane.setPreferredSize(new Dimension(500, 200));
		   jt.setRowHeight(1000);
		   con.close();
		   message.setText(" hello.. ");
			pan.remove(panel); 
		   JButton b=new JButton("Refresh");
		   b.setPreferredSize(new Dimension(200, 80));
		   JButton a=new JButton("Log out");
		   a.setPreferredSize(new Dimension(200, 80));
		   pan.add(a);
		   pan.add(b);
			pan.add(scrollPane);
			   a.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
			logout();
				}
			   
			   });
		
		   b.addActionListener(new ActionListener() {
			   

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				 try{  	
					   pan.remove(scrollPane);
					  // vecte.removeAllElements();
					   //data1.removeAllElements();
						Class.forName("com.mysql.jdbc.Driver");  
							Connection con=DriverManager.getConnection(  
							"jdbc:mysql://localhost:3306/radee_ta?autoReconnect=true&useSSL=false","root","root");  
						Statement stmt=con.createStatement();  
						ResultSet rs=(ResultSet)stmt.executeQuery("select * from Compteur");  
						while(rs.next()) {
							Vector vecte=new Vector();
							vecte.add(rs.getInt("Num_secteur"));
							vecte.add(rs.getInt("Num_compteur"));
							vecte.add(rs.getInt("index_compteur"));
							data1.addElement(vecte);
						
						
						}
								
						con.close();
						  
					
						// DefaultTableModel model=new DefaultTableModel(data1,columns1);
						 scrollPane.remove(jt);
						    jt=new JTable(data1,columns);
						    jt.setBounds(300,300,500,500); 
						    jt.setRowHeight(100);
						    scrollPane=new JScrollPane(jt);
						    scrollPane.setPreferredSize(new Dimension(500, 100));
						remove(pan);
						   pan.add(scrollPane);
						  
						   setContentPane(pan);
						    
						  
				   }catch(Exception e){ System.out.println(e);}  
		       	
			        
		       }
		       

			    }
				
			
			   
			   
		   );
		    
			 
		
			
	  
		}catch(Exception e){ System.out.println(e);}  
		}
	        else {
	        	 message.setText("Error in User or password ");
	        	
	        }
	  }
	        public void logout() {
	    		// TODO Auto-generated method stub
				window main = new window();    

				    main.setVisible(true);
				    this.dispose();
	        }
	     
	       

	  
	  
		  
	   
	  
	  
	}


	

