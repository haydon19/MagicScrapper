import java.awt.*;
import java.util.List;

public class MagicCard {
    String cardName;
    String description;
    String cardImage;
    String price;
    String url;

    public MagicCard(String name, String desc, String image, String price, String url) {
    	this.cardName = name;
    	this.description = desc;
    	this.cardImage = image;
    	this.price = price;
    	this.url = url;
    }
    
    public MagicCard(String url){
        this.url = url;
    }
    
    public MagicCard() {
    	this.cardName = "";
    	this.description = "";
    	this.cardImage = "";
    	this.price = "";
    	this.url = "";
    }
    
    public String getURL(){
        return this.url;
    }
}
