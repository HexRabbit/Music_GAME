package endproject;

import java.util.Timer;

public class Resume {
	public static long real_run_time;
	public static long r;
	public Resume() {
		int i;
		MyLabel now;
		if(r == 0) {
			real_run_time = Pause.pause_time - Main.begin_time;
		} else {
			real_run_time += Pause.pause_time - r;
		}
		r = System.currentTimeMillis(); //resume time
		
		
		for(i = 0; i < Main.l.size(); i++) {
			now = Main.l.get(i);
			do {
				Timer move_timer = new Timer();
				Timer show_timer = new Timer();
				Move move = new Move(now, now.parent);
				Show show = new Show(now, now.parent);
				if(now.b -2500 > real_run_time) {  //還沒出現
					now.move_timer = move_timer;
					now.show_timer = show_timer;
					now.move_timer.schedule(move, now.b - real_run_time - 1000, 40);
					now.show_timer.schedule(show, now.b - real_run_time - 1000);
					
				} else { //已經出現
					now.move_timer = move_timer;
					now.move_timer.schedule(move, 0, 40);
					
				}
				
				//remove_timer.schedule(remove, 0, 40);
				now = now.nextL;
			} while(now != null);
			
		}
		
		Main.mp3.play();
	}
}
