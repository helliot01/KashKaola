
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
 
import java.util.HashMap; 

public class login_form {
	
	
	static HashMap<String, String> login_info = new HashMap<String, String>();
	
	
	public static void create_login_popup() {
        final JFrame parent = new JFrame();
        String name = JOptionPane.showInputDialog(parent,
        "Please Enter your Username:", null);
        
        if (! login_info.containsKey(name)) {
        	while(! login_info.containsKey(name)) {
        		name = JOptionPane.showInputDialog(parent, 
        		"Username not Found! \nPlease Try Again:", null);
        	}
       }
        
        String pass = JOptionPane.showInputDialog(parent,
        "Please Enter your Password:", null);
        
        if(! login_info.get(name).equals(pass)) {
        	int count = 3;
        	while (! login_info.get(name).equals(pass) && count>1){
        		        		pass = JOptionPane.showInputDialog(parent,
        		 "Incorrect Password! \nPlease Try Again:", null);
        		count-=1;

        	}
        	if(count==1) {
    			JOptionPane.showMessageDialog(parent, 
    			"Too Many Invalid Attemps. Your Account Has Been Locked for 24 Hours.");
    		}

        }
        
    }
	
	
	public static void create_registration_popup() {
        final JFrame parent = new JFrame();
        String name = JOptionPane.showInputDialog(parent,
        "Please Enter your Desired Username:", null);
        
        if(login_info.containsKey(name)) {
        	while(login_info.containsKey(name)) {
        		name = JOptionPane.showInputDialog(parent, 
        		"Username Taken! \nPlease Try a New Username:", null);
        	}
       }
        
        
        String pass = JOptionPane.showInputDialog(parent,
        "Please Enter your Password:", null);
        
        String pass2 = JOptionPane.showInputDialog(parent,
        "Please Confirm your Password:", null);      
        
        
        while(! pass.equals(pass2)) {
        	pass2 = JOptionPane.showInputDialog(parent,
        	"Passwords Don't Match \nPlease Try Again:", null);      
        }
        
        
        
        login_info.put(name,pass);

	}


    public static void addComponentsToPane(Container pane) {
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
 
        JButton button = new JButton("Login");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(button);

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                create_login_popup();
            }
        });
        
        
        
        JButton button2 = new JButton("Register");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(button2);

        button2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                create_registration_popup();
                }
        });

        /*addAButton("Register", pane);
        addAButton("Button 3", pane);
        addAButton("Long-Named Button 4", pane);
        addAButton("5", pane);*/
    }
    /*
    private static void addAButton(String text, Container container) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(button);
    }
	*/
    
    
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Login Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        addComponentsToPane(frame.getContentPane());
 
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {


    	javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}