import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JTable;
import java.sql.*;
import java.util.Base64;
import java.util.Random;
import java.awt.Button;
import java.awt.Cursor;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.JTextArea;



class G {
	  static String globalVar = "Global Value";
	  static boolean userlogin = false;
	  static LoginFrame frame = new LoginFrame();
	  static Connection connection;
	  static String id;
}

public class Main extends JFrame {
    public static JList<String> list_2 ;
	private JPanel contentPane;
	static PreparedStatement pst;
	static Connection connection;
	private JTextField textField;
	DefaultListModel<String> model = new DefaultListModel<>();
	DefaultListModel<String> model1 = new DefaultListModel<>();
    JList<String> list_generate = new JList<>(model1);
    private String singleString1;
 // Create an array of Button type Objects
    Button [] button = new Button[20];
    static int i;
    
    JPanel panel;

	/**
	 * Launch the application.
	 * @return 
	 */
	
	
	
//	public static void main() {
//		System.out.println("Main");
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				System.out.println("RUN");
//				try {
//					Main frame = new Main();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
    
    
    
    public void refresh_top() {}
    
    

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	
	
	 static StringBuilder add_more_char(
             StringBuilder str, int need)
	{
	int pos = 0;
	Random randm = new Random();
	
	// all 26 letters
	String low_case = "abcdefghijklmnopqrstuvwxyz";
	
	for (int i = 0; i < need; i++) {
	 pos = randm.nextInt(1000) % str.length();
	 str.setCharAt(pos,low_case.charAt(
	                 randm.nextInt(1000) % 26));
	}
	return str;
	}
	
	// make powerful String
	
	static StringBuilder suggester(int l, int u, int d,
	                   int s, StringBuilder str)
	{
	Random randm = new Random();
	
	// all digits
	String num = "0123456789";
	
	// all lower case, uppercase and special
	// characters
	String low_case = "abcdefghijklmnopqrstuvwxyz";
	String up_case = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	String spl_char = "*@#$_()!";
	
	// System.out.println("l, u, d, s, str  " + l + "  " + u + "  " + d + "  " + s + "  " + str  );
	
	// position at which place a character
	int pos = 0;
	
	// if there is no lowercase char in input
	// String, add it
	if (l == 0) {
	  
	 // generate random integer under
	 // String length()
	 pos = randm.nextInt(1000) % str.length();
	
	 // generate random integer under 26 for
	 // indexing of a to z
	 str.setCharAt(pos,low_case.charAt(randm.nextInt(1000)
	                             % 26));
	}
	
	// if there is no upper case char in input
	// String, add it
	if (u == 0) {
	 pos = randm.nextInt(1000) % str.length();
	 str.setCharAt(pos,up_case.charAt(randm.nextInt(1000)
	                             % 26));
	}
	
	// if there is no digit in input String, add it
	if (d == 0) {
	 pos = randm.nextInt(1000) % str.length();
	 str.setCharAt(pos,num.charAt(randm.nextInt(1000)
	                             % 10));
	}
	
	// if there is no special character in input
	// String, add it
	if (s == 0) {
	 pos = randm.nextInt(1000) % str.length();
	 str.setCharAt(pos,spl_char.charAt(randm.nextInt(1000)
	                             % 7));
	}
	
	return str;
	}
	
	/* make_password function :This function is used
	to check strongness and if input String is not
	strong, it will suggest*/
	static StringBuilder generate_password(int n, StringBuilder p)
	{
	
	// flag for lower case, upper case, special
	// characters and need of more characters
	int l = 0, u = 0, d = 0, s = 0, need = 0;
	
	// password suggestions.
	StringBuilder suggest = new StringBuilder();
	StringBuilder suggest2 = new StringBuilder();
	
	for (int i = 0; i < n; i++) {
	  
	 // password suggestions.
	 if (p.charAt(i) >= 97 && p.charAt(i) <= 122)
	     l = 1;
	 else if (p.charAt(i) >= 65 && p.charAt(i) <= 90)
	     u = 1;
	 else if (p.charAt(i) >= 48 && p.charAt(i) <= 57)
	     d = 1;
	 else if(p.charAt(i) == '*' || p.charAt(i) == '%' ||  p.charAt(i) == '$' || p.charAt(i) == '#' || p.charAt(i) == '@' || p.charAt(i) == '_' || p.charAt(i) == '(' || p.charAt(i) == ')' ||p.charAt(i) == '!')
	     s = 1;
	}
	
	// Check if input String is strong that
	// means all flag are active.
	if ((l + u + d + s) == 4) {
	 System.out.println("Your Password is Strong");
	 suggest.append("Your Password is Strong!  ");
	 return  suggest;
	}
	else
	 System.out.println("Suggested password:  ");
	
	/*suggest 10 strong Strings */
	for (int i = 0; i < 10; i++) {
	 suggest = suggester(l, u, d, s, p);
	 need = 8 - suggest.length();
	 if (need > 0)
	     suggest = add_more_char(suggest, need);
	 	suggest2.append(suggest + "        \n");
	 //System.out.println(suggest2);;
	}
	System.out.println(suggest2);
	return suggest2;
	}
	
	
	
	public Main() throws SQLException {
		
		
		
		
		JFrame frame = new JFrame();
		frame.setVisible(true);
//		JPanel main2 = new main2();
//		frame.add(main2);
		frame.setTitle("Strong Password Generator");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Ahsan\\Downloads\\icons8-lock-500.png"));
		frame.setPreferredSize(new Dimension(1200, 800));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(20, 374, 1154, 361);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Suggestion");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(561, 83, 95, 26);
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_4 = new JLabel("Password");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(561, 63, 74, 26);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_1 = new JLabel("Input Password:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(35, 63, 393, 26);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Encoded Password:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(35, 114, 431, 26);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Enter Password:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(35, 311, 126, 26);
		panel_1.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(174, 314, 292, 25);
		panel_1.add(textField);
		textField.setColumns(10);
		
		
		JList list_1 = new JList(model1);
		list_1.setBounds(659, 280, 495, -272);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(632, 35, 498, 274);
		panel_1.add(panel_2);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model1.clear();
				String current = textField.getText();
				lblNewLabel_1.setText("Input Password: " + current);
				String encodedString = Base64.getEncoder().encodeToString(current.getBytes());
				lblNewLabel_2.setText("Encoded Password: " + encodedString + "                   ");
				// System.out.println("Hello World of Java!!" + current);   
				//label2.setText("Input Password: " + current);
				//panel.revalidate();
				
				JList list_3 = new JList(model1);
	     		list_3.setToolTipText("Password");
	     		list_3.setVisibleRowCount(10);
	     		list_3.setLayoutOrientation(JList.VERTICAL_WRAP);
	     		list_3.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
	     		list_3.setBounds(30, 80, 1100, 300);
	     		list_3.setFixedCellHeight(25);
	     		list_3.setFixedCellWidth(200);
	     		list_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	     		panel_2.add(list_3);
	     		list_3.removeAll();
				
				
		        JScrollPane scroll = new JScrollPane(list_1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				
				StringBuilder input_String = new StringBuilder(current);
				StringBuilder suggest = generate_password(input_String.length(), input_String);
				System.out.println("Hello World of Java!!" + suggest);
				singleString1 = suggest.toString();
				String[] words = singleString1.split("\\s+");
				//tf2.setText("" +  suggest);
		        for (String s: words)
		        {
		        	model1.addElement(s);
		        }
		        panel_1.add(list_1);
		        panel_1.add(scroll);
		        
		        panel_1.revalidate();
		        frame.revalidate();
		        
//		        SwingUtilities.updateComponentTreeUI(panel_1);
//		        panel_1.invalidate();
//		        panel_1.validate();
//		        panel_1.repaint();
//		        
//		        
//		        SwingUtilities.updateComponentTreeUI(frame);
//		        frame.invalidate();
//		        frame.validate();
//		        frame.repaint();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(499, 312, 136, 26);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel_1.setText("Input Password: ");
				lblNewLabel_2.setText("Encoded Password:");
				textField.setText("");
				model1.clear();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBounds(659, 312, 118, 26);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Save");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
	                String sql = "insert into passwords(id, password) values(?, ?)";
	                pst = G.connection.prepareStatement(sql);
	                pst.setString(1, G.id);
	                pst.setString(2, singleString1);
	                pst.execute();
	               JOptionPane.showMessageDialog(null, "Password Saved successfully!");
	                
	                try {
	              	  //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pasworddb", "root", "");
	            		
	            		pst = G.connection.prepareStatement("SELECT * FROM passwords WHERE id = " + "'" +G.id + "'" + ';');
	                    ResultSet rs = pst.executeQuery();
	                    System.out.println(rs + " Password Result");
	                    model.clear();
	                    
	              	  i = 1;
	              	  while (rs.next()) {
	                  	System.out.println( "  Iteration ");
	                      JList<String> list = new JList<>(model);
	                       
	                      list = new JList(model);
	                      list_2 = new JList(model);
	               		list_2.setToolTipText("Password");
	               		list_2.setVisibleRowCount(11);
	               		list_2.setLayoutOrientation(JList.VERTICAL_WRAP);
	               		list_2.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
	               		list_2.setBounds(30, 80, 1100, 300);
	               		list_2.setFixedCellHeight(27);
	               		list_2.setFixedCellWidth(110);
	               		list_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	               		panel.add(list_2);
	                       
	                      JScrollPane scroll = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	                		
	                    	
	                    	String id = rs.getString("id");
	                        String password = rs.getString("password");
	                        String[] words = password.split("\\s+");
	                        model.addElement("P.Set " + i);
	                        for (String s: words)
	                        {
	                        	model.addElement(s);
	                        }
	                        
	                        System.out.println(id + " " + password + "    " + words);
	                        panel.add(scroll);
	                        
	                        button[i] = new Button("Delete: " + i );
	                        button[i].setBounds(10+115*(i-1), 0, 82, 23);
	                        panel_1.add(button[i]);
	                        
//	                        JButton btnNewButton_3 = new JButton("Delete");
//	                		btnNewButton_3.setBounds(10, 0, 82, 23);
//	                		panel_1.add(btnNewButton_3);
	          	      		button[i].addActionListener(new ActionListener() {
	          	      			public void actionPerformed(ActionEvent e) {
	          	      				System.out.println("E: " + e);
//	          	      				if (e.getSource() == "button") {
//	          	      					
//	          	      				}
	          	      				
	          	      				try{
	          	      					int k = i - 1;
	          	    	                String sql = "delete from passwords(pId) values(" + k + ")";
	          	    	                String sql2 = "DELETE FROM passwords where pId = '" + k + "'";
	          	    	                
	          	    	                pst = G.connection.prepareStatement(sql2);
	          	    	                pst.execute();
	          	    	                JOptionPane.showMessageDialog(null, "Password Deleted successfully!");
	          	    	                SwingUtilities.updateComponentTreeUI(frame);
	          	    	                model.clear();
	          	    	                
	          	    	            }catch(Exception exx){
	          	    	                JOptionPane.showMessageDialog(null, exx);
	          	    	            }
	          	      				
	          	      			}
	          	      		});
	                    
	                        i++;
	                     }
	                    
	                    
	                } 
	                catch (Exception ex) {
	                  //JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	              	JOptionPane.showMessageDialog(null, "No Record Found in DB", "Error", JOptionPane.ERROR_MESSAGE);
	              }
	                
	                
	                
	            }catch(Exception exx){
	                JOptionPane.showMessageDialog(null, exx);
	            }
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_2.setBounds(800, 312, 109, 27);
		panel_1.add(btnNewButton_2);
		
		
		
		panel = new JPanel();
		panel.setBounds(10, -19, 1164, 394);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Saved Passwords");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(491, 27, 189, 24);
		panel.add(lblNewLabel);
		
		
		
		
		
		
		
//		JList list_1 = new JList();
//		list_1.setToolTipText("Password");
//		list_1.setVisibleRowCount(10);
//		list_1.setLayoutOrientation(JList.HORIZONTAL_WRAP);
//		list_1.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
//		list_1.setBounds(10, 70, 114, 176);
//		panel.add(list_1);
		
		
		
		
		
		
//	  connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pasworddb", "root", "");
//	  pst = connection.prepareStatement("SELECT * FROM password WHERE id = " + "'" +G.id + "'" + ';');
//      ResultSet rs = pst.executeQuery();
//      System.out.println(rs + "  Result of Passwords");
      
      try {
    	  //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pasworddb", "root", "");
  		
  		pst = G.connection.prepareStatement("SELECT * FROM passwords WHERE id = " + "'" +G.id + "'" + ';');
          ResultSet rs = pst.executeQuery();
          System.out.println(rs + " Password Result");
          
    	  i = 1;
    	  while (rs.next()) {
        	System.out.println( "  Iteration ");
            JList<String> list = new JList<>(model);
             
            list = new JList(model);
            list_2 = new JList(model);
     		list_2.setToolTipText("Password");
     		list_2.setVisibleRowCount(11);
     		list_2.setLayoutOrientation(JList.VERTICAL_WRAP);
     		list_2.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
     		list_2.setBounds(30, 80, 1100, 300);
     		list_2.setFixedCellHeight(27);
     		list_2.setFixedCellWidth(110);
     		list_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
     		panel.add(list_2);
             
            JScrollPane scroll = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      		
          	
          	  String id = rs.getString("id");
          	  String pId = rs.getString("pId");
              String password = rs.getString("password");
              String[] words = password.split("\\s+");
              model.addElement("P.Set " + i);
              for (String s: words)
              {
              	model.addElement(s);
              }
              
              System.out.println(id + " " + password + "    " + words);
              panel.add(scroll);
              
              button[i] = new Button("Delete: " + i );
              button[i].setBounds(10+115*(i-1), 0, 82, 23);
              panel_1.add(button[i]);
              
//              JButton btnNewButton_3 = new JButton("Delete");
//      		btnNewButton_3.setBounds(10, 0, 82, 23);
//      		panel_1.add(btnNewButton_3);
	      		button[i].addActionListener(new ActionListener() {
	      			public void actionPerformed(ActionEvent e) {
	      				System.out.println("E: " + e);
//	      				if (e.getSource() == "button") {
//	      					
//	      				}
	      				
	      				try{
	      					int k = i - 1;
	    	                String sql = "delete from passwords(password) values(" + password + ")";
	    	                String sql2 = "DELETE FROM passwords where password = '" + password + "'";
	    	                
	    	                pst = G.connection.prepareStatement(sql2);
	    	                pst.execute();
	    	                JOptionPane.showMessageDialog(null, "Password Deleted successfully!");
	    	                SwingUtilities.updateComponentTreeUI(frame);
	    	                for( int j = 0 ;  j < 10 ; j++) {
	    	                	model.removeElementAt(j);
	    	                }
	    	                
	    	            }catch(Exception exx){
	    	                JOptionPane.showMessageDialog(null, exx);
	    	            }
	      				
	      			}
	      		});
          
              i++;
           }
          
          
      } 
      catch (Exception ex) {
        //JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    	JOptionPane.showMessageDialog(null, "No Record Found in DB", "Error", JOptionPane.ERROR_MESSAGE);
    }
     
		
	}
}
