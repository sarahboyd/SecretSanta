
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

class View extends JPanel implements IView {
	
	private SantaModel model;

	private JLabel info;
	private JLabel info2;
	private JLabel info3;
	private JButton ok_button;
	private JTextField text_field;
	private JTextField text_field2;
	
	View(SantaModel model_) {
		
		this.setLayout(new GridLayout(2,3));
		
		model = model_;
		
		info = new JLabel();
		info.setText("Number of Participants:");
		
		info2 = new JLabel();
		info3 = new JLabel();
		
		ok_button = new JButton();
		ok_button.setText("OK");
		
		text_field = new JTextField(25);
		text_field2 = new JTextField();
		text_field2.setEditable(false);
		
		this.add(info);
		this.add(text_field);
		this.add(info3);
		this.add(info2);
		this.add(text_field2);
		this.add(ok_button);
		
		ok_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ok_button.getText()=="Send"){
					model.sendEmails();
					System.out.println("sent");
					info3.setText("Emails Sent!!");
				}else if (model.getStateNum()){
					model.addName(text_field.getText(), text_field2.getText());
					text_field.setText("");
					text_field2.setText("");
				}else {
					
					model.setStateNum();
					model.setNum(Integer.parseInt(text_field.getText()));
					text_field.setText("");
				}
			}
		});	
		
	}
	
	public void getNames(){
		info.setText("Enter name "+(model.getNameCount()+1)+" :");
		info2.setText("Enter email "+(model.getNameCount()+1)+" :");
		text_field2.setEditable(true);
		System.out.println("nametime");
	}
	
	public void disable(){
		text_field.setText("");
		text_field2.setText("");
		text_field.setEditable(false);
		text_field2.setEditable(false);
	}
	
	public void updateView() {
		System.out.println("View: updateView");
		if (model.getStateNum()){
			System.out.println("now here");
			if (model.getNameCount() < model.getNum()){
				getNames();
			}else {
				disable();
				info3.setText("Click to send");
				ok_button.setText("Send");
				model.printNames();
				model.match();
				model.printMatch();
			}
		}else{
		}
	}
}