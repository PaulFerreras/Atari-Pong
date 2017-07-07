package ataripong;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		
//		/*PF: All Swing code must be created
//		 * and invoked in the Event Dispatch Thread
//		 * (EDT). This is to prevent memory consistency 
//		 * errors and random bugs from occuring.
//		 */
//		SwingUtilities.invokeLater(new Runnable() {
//
//			@Override
//			public void run() {
//				
//			}
//		});
		
		createGUI();
		
	}
	
	//PF: Create GUI on Event Dispatch Thread
	private static void createGUI() {
		System.out.println(javax.swing.SwingUtilities.isEventDispatchThread());
		
		//JFrame
		JFrame main_frame = new JFrame();
		main_frame.setTitle("SuperAwesomePingPong");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main_frame.setLocation(150, 50);
		
		//Game Dimensions (screen)
		int screen_width = 1000;
		int screen_height = 600;
		
		//Model, View, Controller
		Model model = new Model(screen_width, screen_height);
		View view = new View(model, screen_width, screen_height);
		Controller controller = new Controller(model, view);
		
		main_frame.setResizable(false);
		main_frame.setContentPane(view);
		main_frame.pack();
		main_frame.setVisible(true);
		
		runGame(model, view, controller);
	}
	
	private static void runGame(Model model, View view, Controller controller) {
		//Game Loop
		boolean is_running = true;
		long start_time = System.nanoTime();
		
		//Locked at 60 frames per second
		double frames_per_sec = 60;
		
		while(is_running) {
			double elapsed_time = (System.nanoTime() - start_time) / 1000000000.0;
			
			if(elapsed_time >= (1/frames_per_sec)) {
				model.update();
				view.repaint();
				
				start_time = System.nanoTime();
			}
			
		}
	}
	
}
