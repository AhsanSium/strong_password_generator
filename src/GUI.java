import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Base64;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


//class G {
//	  static String globalVar = "Global Value";
//	  static boolean userlogin = false;
//	  static LoginFrame frame = new LoginFrame();
//	  static Connection connection;
//	  static String id;
//}

public class GUI implements ActionListener{
	
	private int count = 0;
	private JLabel label;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JFrame frame;
	private JPanel panel;
	JTextField tf;
	JTextField tf2;
	JScrollPane sp;
	DefaultListModel<String> model = new DefaultListModel<>();
    JList<String> list = new JList<>(model);
    static PreparedStatement pst;
	
	
	// adding more characters to suggest
    // strong password
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
	
	
	public GUI() {
		
		JFrame frame = new JFrame("STRONG PASSWORD GENERATOR");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,800);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("FILE");
        JMenu m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Open");
        JMenuItem m22 = new JMenuItem("Save as");
        m1.add(m11);
        m1.add(m22);
        
        list = new JList(model);
        JScrollPane scroll = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        panel.setSize(1200, 800);
        JLabel label = new JLabel("Enter Password");
        tf = new JTextField(10); // accepts upto 10 characters
        tf2 = new JTextField();
        JScrollPane sp = new JScrollPane(tf2);
        tf2.setPreferredSize(new Dimension(650, 50));
        JButton send = new JButton("Submit");
        JButton reset = new JButton("Reset");
        label2 = new JLabel("Input Password: ");
        label3 = new JLabel("Encoded Password:                                       ");
        label4 = new JLabel("Suggested Password:");
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(send);
        panel.add(reset);
        panel.add(BorderLayout.WEST, scroll);
        //panel.add(label3);

        // Text Area at the Center
        JTextArea ta = new JTextArea();

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, label2);
        frame.getContentPane().add(BorderLayout.CENTER, label4);
        frame.getContentPane().add(BorderLayout.LINE_START, label3);
        frame.getContentPane().add(BorderLayout.LINE_END, sp);
        //frame.getContentPane().add( label3);
        frame.setVisible(true);
		
//		JPanel mainPanel = new JPanel();
//		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
//
//		frame = new JFrame();
//		panel = new JPanel();
//		
//		panel.setSize(800, 500);
//		frame.setSize(800,500);
		
		
//		JButton button = new JButton("Click Me");
//		
//		button.setPreferredSize(new Dimension(256, 256));
//		
		send.addActionListener(this);
		reset.addActionListener(e -> resetButtonPressed());
//		
		
		
		//panel.add(label);
		
//		JLabel textlabel = new JLabel ("Input: ");
//		
//		textlabel.setBounds(10, 20, 80, 25);
//		
//		panel.add(textlabel);
//		
//		JTextField textField = new JTextField();
//		
//		textField.setBounds(100, 20, 165, 25);
//		
//		panel.add(textField);
		
		
//		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
//		
//		panel.setLayout(new GridLayout(2 ,1));
		
//		panel.add(button);
		
//		panel.add(label);
		
		
		
		
		
		// Frame Stuff
		
//		mainPanel.add(panel);
//		
//		frame.add(mainPanel);
		
		
		
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		
//		frame.setTitle("Password Generator");
		
//		frame.pack();
		
//		frame.setVisible(true);
	}
	
	private Object resetButtonPressed() {
		// TODO Auto-generated method stub
		System.out.println("Hello World of Java!!" );
		label2.setText("Input String: ");
		label3.setText("Encoded Password:                                       ");
		tf2.setText("");
		model.clear();
		return null;
	}

//	public static void main (String[] args) {
//		
//		
//        G.frame.setTitle("Login Form");
//        G.frame.setVisible(true);
//        G.frame.setBounds(10, 10, 1200,800);
//        G.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        G.frame.setResizable(false);
//        try {
//        	String createTableSQL = "create table Users1(\r\n" + "  id  int(3) primary key,\r\n" +
//        	        "  name varchar(20),\r\n" + "  email varchar(20),\r\n" + "  country varchar(20),\r\n" +
//        	        "  password varchar(20)\r\n" + "  );";
//        	String getLoginData = "SELECT * FROM users1";
//        	G.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pasworddb", "root", "");  //Establishing connection
//        	System.out.println("Connected With the database successfully");
//        	Statement statement = G.connection.createStatement();
//        	// statement.execute(createTableSQL);
//        	Boolean loginData =  statement.execute(getLoginData);
//        	System.out.println("Login Data " + loginData);
//        	
//        	
//            
////            try {
////            	pst = connection.prepareStatement("SELECT * FROM users1");
////                ResultSet rs = pst.executeQuery();
////                System.out.println(rs);
////                int i = 0;
////                if (rs.next()) {
////                    String uname = rs.getString("name");
////                    String email = rs.getString("email");
////                    String pass = rs.getString("password");
////                    String cou = rs.getString("country");
////                    System.out.println(uname + email + pass + cou);
////                    i++;
////                }
////                if (i < 1) {
////                    JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
////                }
////                if (i == 1) {
////                    System.out.println(i + " Record Found");
////                } else {
////                    System.out.println(i + " Records Found");
////                }
////            } catch (Exception ex) {
////                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
////            }
//        	} catch (SQLException e) {
//        	System.out.println("Error while connecting to the database");
//        	}
//        System.out.println("Class1: "+G.globalVar);
//        System.out.println("G.userlogin : "+G.userlogin);
////		if(G.userlogin == true) {
////			frame.setVisible(false);
////			System.out.println("G.userlogin : "+G.userlogin);
////		}
//	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String current = tf.getText();
		label2.setText("Input String: " + current);
		String encodedString = Base64.getEncoder().encodeToString(current.getBytes());
		label3.setText("Encoded Password: " + encodedString + "                   ");
		// System.out.println("Hello World of Java!!" + current);   
		//label2.setText("Input Password: " + current);
		//panel.revalidate();
		StringBuilder input_String = new StringBuilder(current);
		StringBuilder suggest = generate_password(input_String.length(), input_String);
		//System.out.println("Hello World of Java!!" + suggest);
		String singleString = suggest.toString();
		String[] words = singleString.split("\\s+");
        tf2.setText("" +  suggest);
        for (String s: words)
        {
        	model.addElement(s);
        }
        
	}
	
}