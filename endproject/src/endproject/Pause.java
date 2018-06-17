package endproject;

import java.util.LinkedList;

public class Pause {

	public static long pause_time;
	public Pause(long begin_time, LinkedList<MyLabel> l) {
		int i;
		MyLabel now;
		pause_time = System.currentTimeMillis();
		System.out.println("p: " + (pause_time - begin_time));
		for(i = 0; i < l.size(); i++) {
			now = l.get(i);
			now.move_timer.cancel();
			now.show_timer.cancel();
			now.remove_timer.cancel();
			

		}
		
		Selection.frame1.mp3.pause();
	}
}
