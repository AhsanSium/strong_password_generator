import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class homee extends JFrame {

	private JPanel contentPane;
	private static JFrame homeFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					homeFrame = new homee();
					homeFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public homee() {
		setBounds(new Rectangle(0, 0, 1200, 800));
		setMinimumSize(new Dimension(1200, 800));
		setAlwaysOnTop(true);
		setForeground(new Color(255, 239, 213));
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(new Color(238, 232, 170));
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Ahsan\\Pictures\\Saved Pictures\\cute-sloth-sleeping-with-coffee-cup-cartoon-vector-icon-illustration-animal-drink-icon-concept-isolated-premium-vector-flat-cartoon-style\\3800_4_02.jpg"));
		setTitle("Strong Password Generator");
		setBounds(100, 100, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setMinimumSize(new Dimension(1200, 800));
		contentPane.setBounds(new Rectangle(0, 0, 1200, 800));
		contentPane.setSize(1200, 800);
		contentPane.setForeground(new Color(173, 216, 230));
		contentPane.setBorder(new MatteBorder(1, 1, 2, 1, (Color) new Color(0, 0, 0)));

		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Enter");
		btnNewButton.setFocusTraversalKeysEnabled(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setDefaultCapable(false);
		btnNewButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		btnNewButton.setBounds(211, 648, 221, 44);
		btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewButton.setToolTipText("Enter The Application");
		btnNewButton.setBackground(new Color(128, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homeFrame.setVisible(false);
				G.frame.setTitle("Login Form");
		        G.frame.setVisible(true);
		        G.frame.setBounds(10, 10, 1200,800);
		        G.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        G.frame.setResizable(false);
		        try {
//		        	String createTableSQL = "create table Users1(\r\n" + "  id  int(3) primary key,\r\n" +
//		        	        "  name varchar(20),\r\n" + "  email varchar(20),\r\n" + "  country varchar(20),\r\n" +
//		        	        "  password varchar(20)\r\n" + "  );";
		        	String getLoginData = "SELECT * FROM users1";
		        	G.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pasworddb", "root", "");  //Establishing connection
		        	System.out.println("Connected With the database successfully");
		        	Statement statement = G.connection.createStatement();
		        	// statement.execute(createTableSQL);
		        	Boolean loginData =  statement.execute(getLoginData);
		        	System.out.println("Login Data " + loginData);
		        	
		        	
		            
//		            try {
//		            	pst = connection.prepareStatement("SELECT * FROM users1");
//		                ResultSet rs = pst.executeQuery();
//		                System.out.println(rs);
//		                int i = 0;
//		                if (rs.next()) {
//		                    String uname = rs.getString("name");
//		                    String email = rs.getString("email");
//		                    String pass = rs.getString("password");
//		                    String cou = rs.getString("country");
//		                    System.out.println(uname + email + pass + cou);
//		                    i++;
//		                }
//		                if (i < 1) {
//		                    JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
//		                }
//		                if (i == 1) {
//		                    System.out.println(i + " Record Found");
//		                } else {
//		                    System.out.println(i + " Records Found");
//		                }
//		            } catch (Exception ex) {
//		                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//		            }
		        	
		        	} catch (SQLException error) {
		        	System.out.println("Error while connecting to the database");
		        	}
		        System.out.println("Class1: "+G.globalVar);
		        System.out.println("G.userlogin : "+G.userlogin);
//				if(G.userlogin == true) {
//					frame.setVisible(false);
//					System.out.println("G.userlogin : "+G.userlogin);
//				}
			}
		});
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Strong Password Generator");
		lblNewLabel_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 28));
		lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel_1.setBounds(448, 65, 318, 32);
		contentPane.add(lblNewLabel_1);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1.setBounds(834, 648, 187, 44);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Ahsan\\Pictures\\7070629_3293465.jpg"));
		lblNewLabel.setBounds(269, 37, 700, 700);
		contentPane.add(lblNewLabel);
	}
}
