import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


class LoginFrame extends JFrame implements ActionListener {

    Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel emailLabel = new JLabel("EMAIL");
    
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JTextField emailTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton createButton = new JButton("CREATE");
    JButton resetButton = new JButton("RESET");
    JCheckBox showPassword = new JCheckBox("Show Password");
    JCheckBox newUser = new JCheckBox("New User");
    static PreparedStatement pst;

    LoginFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

    }

    public void setLayoutManager() {
        container.setLayout(null);
        emailLabel.setVisible(false);
    	emailTextField.setVisible(false);
    	createButton.setVisible(false);
    }

    public void setLocationAndSize() {
        userLabel.setBounds(50, 150, 100, 30);
        
        
        emailLabel.setBounds(50, 80, 100, 30); // NEW
        
        
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        
        
        emailTextField.setBounds(150, 80, 200, 30); // NEW
        
        
        
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        newUser.setBounds(300, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        createButton.setBounds(50, 300, 100, 30);
        resetButton.setBounds(200, 300, 100, 30);


    }

    public void addComponentsToContainer() {
        container.add(userLabel);                    
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(createButton);
        container.add(resetButton);
        container.add(newUser);
        
        
        container.add(emailLabel);
        container.add(emailTextField);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        createButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
        newUser.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = passwordField.getText();
            
            try {
            	pst = G.connection.prepareStatement("SELECT * FROM users1 WHERE name = " + "'" +userText + "'" + ';');
                ResultSet rs = pst.executeQuery();
                System.out.println(rs);
                int i = 0;
                if (rs.next()) {
                	G.id = rs.getString("id");
                    String uname = rs.getString("name");
                    String email = rs.getString("email");
                    String pass = rs.getString("password");
                    String cou = rs.getString("country");
                    
                    System.out.println(userText + " " + pwdText);
                    
                    if (userText.equals(uname) && pwdText.equals(pass)) {
                        JOptionPane.showMessageDialog(this, "Login Successful");
                        
                        //new GUI();
                        G.frame.setVisible(false);
                        new Main();
                    } 
                    
                    else {
                        JOptionPane.showMessageDialog(this, "Invalid Username or Password");
                    }
                    
                    System.out.println(uname + email + pass + cou);
                    i++;
                }
//                if (i < 1) {
//                    JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
//                }
//                if (i == 1) {
//                    System.out.println(i + " Record Found");
//                } else {
//                    System.out.println(i + " Records Found");
//                }
            } catch (Exception ex) {
                //JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            	JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
        
        
        
        if (e.getSource() == createButton) {
        	String userEmail;
            String userText;
            String pwdText;
            
            userEmail = emailTextField.getText();
            userText = userTextField.getText();
            pwdText = passwordField.getText();
            
//            try {
//            	String createTableSQL = " INSERT INTO  `Users1` (`name`, `email`, `password`)  VALUES(" + userText + ", " + userEmail + " ," + pwdText + " ) ;";
//            	Statement statement = G.connection.createStatement();
//            	statement.execute(createTableSQL);
//                
//            } catch (Exception ex) {
//                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            }
            
            try{
                String sql = "insert into Users1(name, email, password) values(?, ?, ?)";
                pst = G.connection.prepareStatement(sql);
                pst.setString(1, userText);
                pst.setString(2, userEmail);
                pst.setString(3, pwdText);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Account Created successfully!");
                
                try {
                	pst = G.connection.prepareStatement("SELECT * FROM users1 WHERE name = " + "'" +userText + "'" + ';');
                    ResultSet rs = pst.executeQuery();
                    System.out.println(rs);
                    int i = 0;
                    if (rs.next()) {
                    	G.id = rs.getString("id");
                        
                    }
//                    if (i < 1) {
//                        JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
//                    }
//                    if (i == 1) {
//                        System.out.println(i + " Record Found");
//                    } else {
//                        System.out.println(i + " Records Found");
//                    }
                } catch (Exception ex) {
                    //JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                	JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
                }

                
                G.frame.setVisible(false);
                // new GUI();
                new Main();
                
                
            }catch(Exception exx){
                JOptionPane.showMessageDialog(null, exx);
            }


        }
        
        
        
        
        
        if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }


        }
        if (e.getSource() == newUser) {
            if (newUser.isSelected()) {
            	System.out.println(" New User Clicked");
            	createButton.setVisible(true);
            	loginButton.setVisible(false);
            	emailLabel.setVisible(true);
            	emailTextField.setVisible(true);
            } else {
            	createButton.setVisible(false);
            	loginButton.setVisible(true);
            	emailLabel.setVisible(false);
            	emailTextField.setVisible(false);
            }


        }
    }

}

