
import java.util.*;

import src.MainGui;

public class Main {

    public static void main (String args[]) {

    	//Create a list of magic cards.
        CardCollection collection = new CardCollection();
        //Instantiate the scraper.
        Scraper scraper = new Scraper();
        
        collection.cardList.add(scraper.getCard("charcoal"));
        collection.cardList.add(scraper.getCard("ghalta"));
        collection.cardList.add(scraper.getCard("polyraptor"));
        collection.cardList.add(scraper.getCard("wakening avatar"));

		try {
			Gui frame = new Gui(collection);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
 
    }
}
