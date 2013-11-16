
import javax.swing.*;
import java.awt.*;	

public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Secret Santa");
		
		SantaModel model = new SantaModel();
		
		View2 view2 = new View2(model);
		View view = new View(model);
		View3 view3 = new View3(model);
		model.addView(view2);
		model.addView(view);
		model.addView(view3);
		
		
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));		
		
		frame.getContentPane().add(p);
		p.add(view2);
		p.add(view);
		p.add(view3);
		
		frame.setPreferredSize(new Dimension(450,300));
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
