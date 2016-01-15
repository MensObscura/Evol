package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.Agent;
import model.SMA;

public class Vue extends JPanel implements Observer{

	private JButton[][] buttonTab;
	private SMA action;

	public Vue (SMA action){

		this.action = action;
		action.addObserver(this);

		initButton();

		this.setLayout(new GridLayout(this.buttonTab.length,this.buttonTab.length));
		
		System.out.println("On lance la fenêtre");
		JFrame f= new JFrame("Evol");
		f.add(this);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);


	}

	public void initButton(){
		int size = action.getEnvironnement().getEspace().length;
		this.buttonTab = new JButton[size][size];
		
		for(int i = 0 ; i < size; i++){
			for( int j = 0; j < size; j++){
				
				buttonTab[i][j] = new JButton();
				
				buttonTab[i][j].addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						//action.getEnvironnement().getEspace()[i][j].setAgent(new Agent(i,j,action.getEnvironnement().getEspace()));
					}

				});
				
				this.buttonTab[i][j].setPreferredSize(new Dimension(action.gettAgent(), action.gettAgent()));
				
				if(!action.isVisibleGrid()){
					Border emptyBorder = BorderFactory.createEmptyBorder();
					buttonTab[i][j].setBorder(emptyBorder);
				}
				
				
				this.add(buttonTab[i][j]);


			}

		}


		this.actualiseButton();


	}

	public void actualiseButton(){
		for(int i = 0 ; i < this.buttonTab.length; i++){
			for(int j = 0; j < this.buttonTab[i].length; j++){
				if(action.getEnvironnement().getEspace()[i][j].isEmpty()){
					buttonTab[i][j].setBackground(Color.WHITE);
				}else{
					buttonTab[i][j].setBackground(Color.BLACK);
				}


			}

		}
	}
	@Override
	public void update(Observable o, Object arg) {

		this.actualiseButton();
	}

}
