import javax.swing.*;
import java.awt.event.*;
import java.util.*;

class Calculator extends JFrame {
	
	private JPanel mypanel = new JPanel();
	private JTextField myfield = new JTextField();
	private JButton[] button = new JButton[16];
	private List<String> stores = new ArrayList<>();
	private static String nil = "";
	
	public Calculator() {
		super("Calculator");
		setSize(225,300);
		mypanel.setLayout(null);
		mypanel.setSize(225,300);
		myfield.setLocation(5,5);
		myfield.setSize(130,25);
		myfield.setEditable(false);
		myfield.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		String[] operator = {"0","+","X","=","1","2","3","-","4","5","6","/","7","8","9","C"};
		for(int i=0;i<operator.length;i++) {
			button[i] = new JButton(operator[i]);
		}
		mypanel.add(myfield);
		for(int j=0;j<16;j++) {
			button[j].addActionListener(new TombolHandler());
			mypanel.add(button[j]);
		}
		myfield.setBounds(5,5,195,35);
		int z=5;
		for(int k=0;k<16;k++) {
			if(k>=0 && k<=3) {
				button[k].setBounds(z,205,45,40);
			} else if(k>=4 && k<=7) {
				if(k==4) z=5;
				button[k].setBounds(z,155,45,40);
			} else if(k>=8 && k<=11) {
				if(k==8) z=5;
				button[k].setBounds(z,105,45,40);
			} else if(k>=12 && k<=15) {
				if(k==12) z=5;
				button[k].setBounds(z,55,45,40);
			}
			z+=50;
		}
		getContentPane().add(mypanel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	private class TombolHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			JButton tombol = (JButton) e.getSource();
			int answers;
			String[] store;
			
			for(int i=0;i<10;i++) {
				if(tombol.getText().equals(String.valueOf(i))) {
					nil = nil + String.valueOf(i);
					myfield.setText(nil);
				}
			}
			
			if(tombol.getText().equals("X") || tombol.getText().equals("+")
				|| tombol.getText().equals("-") || tombol.getText().equals("/")) {
				stores.add(nil);
				stores.add(tombol.getText());
				nil = "";
			} else if(tombol.getText().equals("C")) {
				store = null;
				stores = new ArrayList<>();
				myfield.setText("");
			} else if(tombol.getText().equals("=")) {
				stores.add(nil);
				nil = "";
				store = stores.toArray(new String[stores.size()]);
				if(store[1] == "+") {
					answers = Integer.parseInt(store[0]) + Integer.parseInt(store[2]);
					myfield.setText(String.valueOf(answers));					
				} else if(store[1] == "-") {
					answers = Integer.parseInt(store[0]) - Integer.parseInt(store[2]);
					myfield.setText(String.valueOf(answers));					
				} else if(store[1] == "X") {
					answers = Integer.parseInt(store[0]) * Integer.parseInt(store[2]);
					myfield.setText(String.valueOf(answers));					
				} else if(store[1] == "/") {
					double answer;
					answer = Double.parseDouble(store[0]) / Double.parseDouble(store[2]);
					myfield.setText(Double.toString(answer));					
				}
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		Calculator calc = new Calculator();
	}
	
}