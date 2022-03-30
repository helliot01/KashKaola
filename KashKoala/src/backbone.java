import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



public class backbone {
	
	static JFrame frame;
	JLabel label;
	static JPanel panel;
	static JPanel home;
	
	static HashMap<String, String> login_info = new HashMap<String, String>();
	static HashMap<String, String> user_to_name = new HashMap<String, String>();

	static boolean logged_in = false;
	static String logged_user = "";

	
	public backbone() {
		frame = new JFrame();
		
		JButton button = new JButton("Login");
		JButton button2 = new JButton("Register");
		JButton button3 = new JButton("Exit");
		
		button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                create_login_popup();
            }
        });
		
		button2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                create_registration_popup();
                }
        });
		
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
				
		});
		
		
		panel = new JPanel();
		
		panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
		panel.setLayout(new GridLayout(0,1));
		
		panel.add(button);
		panel.add(button2);
		panel.add(button3);


		
		frame.add(panel, BorderLayout.CENTER);
		
        panel.setBackground(Color.BLUE);
        frame.setLocationRelativeTo(null);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Login");
		frame.pack();
		frame.setVisible(true);
		
		
	}
	
	
	
	
	public static void create_login_popup() {
		//Creates a popup form to login when the button is pressed
        final JFrame parent = new JFrame();
        String pass = "";
         String name = JOptionPane.showInputDialog(parent,
        "Please Enter your Username:", null);
        
         if (name==null) {
        	parent.dispose();
        	         }
        
        
        //if the username given matches no records, try again
        if (! login_info.containsKey(name)) {
        	while(! login_info.containsKey(name)) {
        		if(name==null) 
        			break;
        		name = JOptionPane.showInputDialog(parent, 
        		"Username not Found! \nPlease Try Again:", null);
        	}
       }
        //if the user tries to close the login form it will close 
        if(!(name==null)) {
	        pass = JOptionPane.showInputDialog(parent,
	        "Please Enter your Password:", null);
	        if (login_info.containsKey(name) && login_info.get(name).equals(pass)) {
	        	logged_in = true;
	        	logged_user=name;
	        	frame.dispose();
	        	panel.setVisible(false);
	        	create_home_popup();
	        	}
        }
        //if the stored password does not equal the input, try again. After 3 attempts the user will be locked out of their acc.
        if(!(name==null)) {
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
        
    }
	
	public static void create_registration_popup() {
		//Creates a popup form to register when the button is pressed

		boolean information_valid = true;
        final JFrame parent = new JFrame();
        
        
        String first_name = JOptionPane.showInputDialog(parent,
        		"Please Enter your First Name:", null);
        
        String last_name = JOptionPane.showInputDialog(parent,
        		"Please Enter your Last Name:", null);
        
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
        
        //if the date entered has the user under the age of 18, they will not be able to use KashKoala
        if(Pattern.compile("../../2.0[5-9]").matcher(age).matches() || Pattern.compile("../../2.1.").matcher(age).matches() || Pattern.compile("../../2.2.").matcher(age).matches()) {
        	JOptionPane.showMessageDialog(parent, "You Must be 18 or Older to use KashKoala.");
        	information_valid=false;
        }
        
        //if the date is okay, the form continues
        if(information_valid==true) {
	        String cash = JOptionPane.showInputDialog(parent,
	        		"Please Enter your Estimated Current Savings:");
	        	
	        int money = Integer.parseInt(cash);
	        //if the user has less than $500, they cannot use KashKoala
	        if (money<500) {
	        	JOptionPane.showMessageDialog(parent, "You Must Have Atleast $500 to use KashKoala.");
	        	information_valid=false;
	        }
        }
        
        //if the info is valid, it will get added to the dictionary and sotre the user/pass and user/firstname
        
        if (information_valid==true){
        	login_info.put(name,pass);
        	user_to_name.put(name,first_name);
        }

	}
	
	public static void create_home_popup() {
		//creates homescreen popup on successful login
		
		
		home = new JPanel();

		JButton button = new JButton("Check Balance");
		String first_name = user_to_name.get(logged_user);
		JLabel label = new JLabel("Hi " + first_name + "!");
		label.setBorder(new EmptyBorder(0,175,0,0));
				
		button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("placeholder");
            }
        });
		
		JButton button2 = new JButton("Deposit Funds");
		
		button2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("placeholder");
            }
        });
		
		
		JButton button3 = new JButton("Withdraw Funds");
		
		button3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("placeholder");
            }
        });

		
		JButton button4 = new JButton("Logout");
		button4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(home);
				frame.dispose();
				new backbone();
				}
		});
		
		
        home.setBackground(Color.GREEN);
		home.setLayout(new GridLayout(0,1));

		home.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		home.add(label);
		home.add(button);
		home.add(button2);
		home.add(button3);
		home.add(button4);



		
		frame.add(home);
		

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Home");
		frame.setSize(400,400);
		///frame.pack();
		frame.setVisible(true);

	}
	
	
	
	public static void main(String[] args) {
    	new backbone();
	}
	
	
	
}
