
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
 
import java.util.HashMap; 
import java.util.regex.Pattern;


public class login_form {
	
	
	static HashMap<String, String> login_info = new HashMap<String, String>();
	static boolean logged_in = false;
	
	public static void create_login_popup() {
		//Creates a popup form to login when the button is pressed
        final JFrame parent = new JFrame();
         String name = JOptionPane.showInputDialog(parent,
        "Please Enter your Username:", null);
        
        
        
        //if the username given matches no records, try again
        if (! login_info.containsKey(name)) {
        	while(! login_info.containsKey(name)) {
        		name = JOptionPane.showInputDialog(parent, 
        		"Username not Found! \nPlease Try Again:", null);
        	}
       }
        
        String pass = JOptionPane.showInputDialog(parent,
        "Please Enter your Password:", null);
        if (login_info.containsKey(name) && login_info.get(name).equals(pass)) {
        	logged_in = true;
        	///createAndShowHomeGUI();
        	}
        
        //if the stored password does not equal the input, try again. After 3 attempts the user will be locked out of their acc.
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
		//Creates a popup form to register when the button is pressed

		boolean information_valid = true;
        final JFrame parent = new JFrame();
        String name = JOptionPane.showInputDialog(parent,
        "Please Enter your Desired Username:", null);
        
        //if the username has already been taken, try again
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
        
        
        //if the two passwords inputted do not match, try again
        while(! pass.equals(pass2)) {
        	pass2 = JOptionPane.showInputDialog(parent,
        	"Passwords Don't Match \nPlease Try Again:", null);      
        }
        
        String age = JOptionPane.showInputDialog(parent, 
        "Please Enter your Date of Birth:", "mm/dd/yyyy");
        
        ///matches the pattern of the entered date to valid formats
        if (! (Pattern.compile("0[1-9]/[0-3][0-9]/[12][0-9][0-9][0-9]").matcher(age).matches() || 
        					Pattern.compile("1[12]/[0-3][0-9]/[12][0-9][0-9][0-9]").matcher(age).matches())) {
        	while(! (Pattern.compile("0[1-9]/[0-3][0-9]/[12][0-9][0-9][0-9]").matcher(age).matches() || 
        					Pattern.compile("1[12]/[0-3][0-9]/[12][0-9][0-9][0-9]").matcher(age).matches())) {
        		age = JOptionPane.showInputDialog(parent,
        				"Invalid Date Entered.\nPlease Try Again:");
        	}
        }
        
        if(Pattern.compile("../../2.0[5-9]").matcher(age).matches() || Pattern.compile("../../2.1.").matcher(age).matches()) {
        	JOptionPane.showMessageDialog(parent, "You Must be 18 or Older to use KashKoala.");
        	information_valid=false;
        }
        
        String cash = JOptionPane.showInputDialog(parent,
        		"Please Enter your Estimated Current Savings:");
        	
        int money = Integer.parseInt(cash);
        if (money<500) {
        	JOptionPane.showMessageDialog(parent, "You Must Have Atleast $500 to use KashKoala.");
        	information_valid=false;
        }
        
        
        if (information_valid==true){
        	login_info.put(name,pass);
        }

	}


    public static void addComponentsToPane(Container pane) {
    	//adds the buttons to the frame
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
    	//creates the frame used for the form
        final JFrame frame = new JFrame("Login Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentsToPane(frame.getContentPane());
 
        frame.setSize(200, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    	
    }
    
    private static void createAndShowHomeGUI() {
    	JFrame frame2 = new JFrame("Kash Koala Home");
    	frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	
    	frame2.setSize(600, 600);
        frame2.setLocationRelativeTo(null);
    	frame2.setVisible(true);
    }
 
    public static void main(String[] args) {
    	javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}