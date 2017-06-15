package superawesomepingpong;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		
		/*PF: All Swing code must be created
		 * and invoked in the Event Dispatch Thread
		 * (EDT). This is to prevent deadlock and
		 * random bugs from occuring.
		 */
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				createGUI();
				
			}
		});
	}
	
	//PF: Create GUI on Event Dispatch Thread
	private static void createGUI() {
		//JFrame
		JFrame main_frame = new JFrame();
		main_frame.setTitle("SuperAwesomePingPong");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main_frame.setLocation(150, 50);
		
		//Model, View, Controller
		Model model = new Model(main_frame);
		View view = new View(model);
		Controller controller = new Controller(model, view);
		
		view.setPreferredSize(new Dimension(1000, 600));
		view.setBackground(Color.BLACK);
		
		main_frame.setContentPane(view);
		main_frame.pack();
		main_frame.setVisible(true);
	}
	
}
