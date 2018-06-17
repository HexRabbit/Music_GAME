package endproject;

import java.util.LinkedList;
import java.util.Timer;

public class Resume {
	public static long real_run_time;
	public static long r;
	public Resume(long begin_time, LinkedList<MyLabel> l) {
		int i;
		MyLabel now;
		if(r == 0) {
			real_run_time = Pause.pause_time - begin_time;
		} else {
			real_run_time += Pause.pause_time - r;
		}
		r = System.currentTimeMillis(); //resume time
		
		
		for(i = 0; i < l.size(); i++) {
			now = l.get(i);
			
			Timer move_timer = new Timer();
			Timer show_timer = new Timer();
			Timer remove_timer = new Timer();
			Move move = new Move(now, now.parent);
			Show show = new Show(now, now.parent);
			Remove remove = new Remove(now, now.parent);
			if (now.b - 1000 > real_run_time) { // 還沒出現
				now.move_timer = move_timer;
				now.show_timer = show_timer;
				now.remove_timer = remove_timer;
				now.move_timer.scheduleAtFixedRate(move, now.b - real_run_time - 1000+100, 2);
				now.show_timer.schedule(show, now.b - real_run_time - 1000+100);
				now.remove_timer.scheduleAtFixedRate(remove, now.b - real_run_time - 1000+100, 2);
				
			} else { // 已經出現
				now.move_timer = move_timer;
				now.remove_timer = remove_timer;
				now.move_timer.scheduleAtFixedRate(move, 100, 2);
				now.remove_timer.scheduleAtFixedRate(remove, 100, 2);

			}

			// remove_timer.schedule(remove, 0, 2);
			
		}
		
		Selection.frame1.mp3.play();
	}
}
