import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class View2 extends JPanel implements IView {
	
	private SantaModel model;
	
	private JLabel info;
	
	View2(SantaModel model_) {
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		model = model_;
		
		info = new JLabel();
		info.setText("Secret Santa Mailer app");
		
		this.add(info);
	
		
	}

	public void updateView() {
		System.out.println("View2: updateView");
	}
}
