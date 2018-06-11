package endproject;

public class Pause {

	public static long pause_time;
	public Pause() {
		int i;
		MyLabel now;
		pause_time = System.currentTimeMillis();
		System.out.println("p: " + (pause_time - Main.begin_time));
		for(i = 0; i < Main.l.size(); i++) {
			now = Main.l.get(i);
			//if (now.b + 2500 < pause_time - Main.begin_time) {
			//}else {
				do {
					now.move_timer.cancel();
					now.show_timer.cancel();
					//now.remove_timer.cancel();
					now = now.nextL;
				} while (now != null);
			//}
		}
		
		Main.mp3.pause();
	}
}
