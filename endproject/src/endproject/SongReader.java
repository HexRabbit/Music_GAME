package endproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;



public class SongReader {
	static Song readFile(String path) {
		//File file = new File(path);
		Song sng = new Song();
		/*Scanner sc = null;
		try {
			sc = new Scanner(file);
			//sc.nextLine();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		FileReader file ;
		
		JSONParser jsps = new JSONParser();
		JSONObject jsob;
		Object obj;
		try {
			//String json = sc.nextLine();
			file = new FileReader(path);
			obj =  jsps.parse(file);
			jsob = (JSONObject)obj;
			JSONArray hitnotes = (JSONArray) jsob.get("hitnotes");
			sng.title = (String)  jsob.get("title");
			//System.out.println(sng.title);
			sng.audiofile = (String)  jsob.get("audiofile");
			ArrayList<LinkedList<Note>> track = new ArrayList<LinkedList<Note>>();
			for	(int i = 0; i < 4; i++) {
				track.add(new LinkedList<Note>());
			}
			for (int i = 0; i < hitnotes.size(); ++i) {
			  JSONArray note = (JSONArray) hitnotes.get(i);
			  //System.out.println(note);
			  Integer col = (int) (long) note.get(0);
			  Integer start = (int) (long) note.get(1);
			  Integer end = (int) (long) note.get(2);
			  Integer hold = (int) (long) note.get(3);

			  track.get(col.intValue()).add(new Note(start, end, hold));
			}
			sng.track = track;
			
			return sng;
		} catch ( ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

}
