import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CardCollection {

	
	List<MagicCard> cardList;
	
	public CardCollection() {
		cardList = new ArrayList<MagicCard>();
	}
	
	//Writing to our card collection.
	public void writeToCollection(String url) {
        try {
            FileWriter writer = new FileWriter("MagicCollection.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
 
            bufferedWriter.write(url);
            bufferedWriter.newLine();
 
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public List<MagicCard> readCollection() {
		List<MagicCard> cardList = new ArrayList<MagicCard>();
		try {
            FileReader reader = new FileReader("MagicCollection.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);            
            String line;
            
            while ((line = bufferedReader.readLine()) != null) {
            	MagicCard card = new MagicCard(line);
                cardList.add(card);
            }
            reader.close();
            
            
 
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return cardList;
	}

	public MagicCard getCard(int index) {
		return cardList.get(index);	
	}
	
	public int getSize(){
		if(cardList == null) {
			return 0;
		}
		return cardList.size();
	}
	
	public List<String> getCardNames() {
		List<String> cardNames = new ArrayList<String>();
		for(int i=0;i<cardList.size();i++) {
			cardNames.add(cardList.get(i).cardName);
		}		
		return cardNames;
	}
	
	public List<String> getCardPrices(){
		List<String> cardPrices = new ArrayList<String>();
		for(int i=0;i<cardList.size();i++) {
			cardPrices.add(cardList.get(i).price);
		}		
		return cardPrices;
	}
	
}
