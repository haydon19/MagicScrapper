package src;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.SwingConstants;

public class MainGui extends JFrame {

	private JPanel contentPane;
	private JTextField querySearchBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGui frame = new MainGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel CardImagePanel = new JPanel();
		CardImagePanel.setBounds(5, 5, 424, 251);
		contentPane.add(CardImagePanel);
		CardImagePanel.setLayout(null);
		
		JLabel cardImage = new JLabel("");
		cardImage.setBounds(114, 0, 200, 251);
		CardImagePanel.add(cardImage);
		
		JPanel queryCardPanel = new JPanel();
		queryCardPanel.setBounds(314, 0, 110, 251);
		CardImagePanel.add(queryCardPanel);
		queryCardPanel.setLayout(null);
		
		JLabel queryCardLabel = new JLabel("Card Search");
		queryCardLabel.setBounds(27, 2, 59, 14);
		queryCardPanel.add(queryCardLabel);
		
		querySearchBar = new JTextField();
		querySearchBar.setBounds(7, 19, 96, 20);
		queryCardPanel.add(querySearchBar);
		querySearchBar.setColumns(10);
		
		JList queryCardList = new JList();
		queryCardList.setBounds(7, 44, 96, 207);
		queryCardList.setModel(new AbstractListModel() {
			String[] values = new String[] {"Test", "Test", "Test"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		queryCardPanel.add(queryCardList);
		
		JPanel deckListPanel = new JPanel();
		deckListPanel.setBounds(0, 0, 110, 251);
		CardImagePanel.add(deckListPanel);
		deckListPanel.setLayout(null);
		
		JLabel deckListLabel = new JLabel("Deck List");
		deckListLabel.setHorizontalAlignment(SwingConstants.CENTER);
		deckListLabel.setBounds(22, 2, 59, 14);
		deckListPanel.add(deckListLabel);
		
		JList deckListList = new JList();
		deckListList.setBounds(2, 22, 105, 229);
		deckListPanel.add(deckListList);
	}

}
