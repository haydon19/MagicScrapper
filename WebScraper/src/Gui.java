import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.*;
import java.util.List;
import java.awt.image.*;
import java.net.URL;
import javax.imageio.*;
import java.io.*;

public class Gui extends JFrame{

    //public String cardSearch;
    public List<String> theList;
    public JLabel imageLabel;
    public List<MagicCard> queryCardSearchList;
    public List<String> queryCardList;
	private JPanel contentPane;
	private JTextField querySearchBar;

    public Gui(final CardCollection collection){
	
    	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//Center Image Panel
		JPanel CardImagePanel = new JPanel();
		contentPane.add(CardImagePanel, BorderLayout.CENTER);
		CardImagePanel.setLayout(null);
		
		JLabel cardImage = new JLabel("");
		cardImage.setBounds(240, 15, 335, 400);
		CardImagePanel.add(cardImage);
		//End of Center Image Panel
		
		//Query Panel
		JPanel queryCardPanel = new JPanel();
		queryCardPanel.setBounds(570, 2, 230, 350);
		CardImagePanel.add(queryCardPanel);
		queryCardPanel.setLayout(null);
		
		JLabel queryCardLabel = new JLabel("Card Search");
		queryCardLabel.setBounds(80, 2, 75, 14);
		queryCardPanel.add(queryCardLabel);
		
		querySearchBar = new JTextField();
		querySearchBar.setBounds(5, 19, 225, 20);
		queryCardPanel.add(querySearchBar);
		querySearchBar.setColumns(10);
		
		//Query Temp list
		queryCardList = new ArrayList<String>();
		queryCardSearchList = new ArrayList<MagicCard>();
		
		JList<String> qL = new JList<String>();
		qL.setBounds(5, 44, 230, 350);
		qL.setModel(new AbstractListModel<String>() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		queryCardPanel.add(qL);

		
		//End of Query Panel
		
		//Deck List Panel
		JPanel deckListPanel = new JPanel();
		deckListPanel.setBounds(5, 2, 230, 350);
		CardImagePanel.add(deckListPanel);
		deckListPanel.setLayout(null);
		
		JLabel deckListLabel = new JLabel("Deck List");
		deckListLabel.setHorizontalAlignment(SwingConstants.CENTER);
		deckListLabel.setBounds(80, 2, 59, 14);
		deckListPanel.add(deckListLabel);

		//Card List
		JList<String> deckCardsList = new JList<String>();
		deckCardsList.setBounds(0, 20, 230, 350);
		deckListPanel.add(deckCardsList);
		//Model of List
		theList = new ArrayList<String>();
		addMagicCardsToList(collection);
		deckCardsList.setModel(new AbstractListModel<String>() {
			String[] values = theList.toArray(new String[0]);
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		
    	//End of Deck List Panel
    	
	    querySearchBar.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if(querySearchBar.getText().isEmpty()){
	                queryCardList.clear();
	                queryCardSearchList.clear();
	            }else {
		            queryCardList.clear();
		            queryCardSearchList.clear();
		            Scraper scraper = new Scraper();
		            queryCardSearchList = scraper.queryCards(querySearchBar.getText());
		            addQueriedMagicCardsToQueryList(queryCardSearchList);
	            	qL.setModel(new AbstractListModel<String>() {
	            		String[] values = queryCardList.toArray(new String[0]);
	            		public int getSize() {
	            			return values.length;
	            		}
	            		public String getElementAt(int index) {
	            			return values[index];
	            		}
	            	});
	            }
	        }
	    });
	
	    qL.addListSelectionListener(new ListSelectionListener() {
	    	
	    	@Override
	    	public void valueChanged(ListSelectionEvent e) {
	    		if(!e.getValueIsAdjusting()) {
	    			int index = qL.getSelectedIndex();
	    			if(index > -1) {
	    				try {
	    					cardImage.setIcon(new ImageIcon(resize(new URL(queryCardSearchList.get(index).cardImage), new Dimension(330,400))));
	    				}catch(Exception err){
	    					throw new RuntimeException(err);
	    				}
	    			}
	    		}
	    	}
	    });
	
	    deckCardsList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
	            if (!e.getValueIsAdjusting()) {
	            	int index = deckCardsList.getSelectedIndex();
	            	if(index > -1) {
	                    try {
	                        cardImage.setIcon(new ImageIcon(resize(new URL(collection.cardList.get(index).cardImage), new Dimension(330,400))));
	                    } catch (IOException e1) {
	                        throw new RuntimeException(e1);
	                    }
	            	}
	            }
			}
		});
    }


	public void addMagicCardsToList(CardCollection collection){
        for(int i=0;i<collection.cardList.size();i++){
            theList.add(collection.getCard(i).cardName + ": " + collection.getCard(i).price);
        }
    }
	
	public void addQueriedMagicCardsToQueryList(List<MagicCard> magicCards) {
		for(int i=0;i<magicCards.size();i++) {
			queryCardList.add(magicCards.get(i).cardName + ": " + magicCards.get(i).price);
		}
	}

    public BufferedImage resize(final URL url, final Dimension size) throws IOException{
        final BufferedImage image = ImageIO.read(url);
        final BufferedImage resized = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g = resized.createGraphics();
        g.drawImage(image, 0, 0, size.width, size.height, null);
        g.dispose();
        return resized;
    }

}
