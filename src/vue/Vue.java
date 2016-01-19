package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import model.Agent;
import model.SMA;

public class Vue extends JPanel implements Observer{

	private JButton[][] buttonTab;
	private SMA action;	
	private JPanel glob;

	public Vue (SMA action){

		this.action = action;
		action.addObserver(this);



		JPanel controle = new JPanel();
		initRuleButton(controle);


		System.out.println("On lance la fenêtre");
		JFrame f= new JFrame("Evol");
		glob = new JPanel();
		initButton();

		glob.setLayout(new BoxLayout(glob, BoxLayout.Y_AXIS));
		glob.add(this);
		glob.add(controle);
		f.add(glob);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);


	}

	public void initRuleButton(JPanel controle){

		JLabel labBille = new JLabel("Nombre de billes");
		JTextField billes = new JTextField("0");
		billes.setPreferredSize(new Dimension(50,20));
		billes.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(billes.getText().length() >0 )
					try{
						Integer.parseInt(billes.getText());
					}catch(Exception ex){
						billes.setText("0");
					}


			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		JLabel labVite = new JLabel("vitesse");
		JTextField vitesse = new JTextField("0");
		vitesse.setPreferredSize(new Dimension(50,20));
		vitesse.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(vitesse.getText().length() >0 )
					try{
						Integer.parseInt(vitesse.getText());
					}catch(Exception ex){
						vitesse.setText("0");
					}


			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});


		JLabel labSeed = new JLabel("Seed");
		JTextField seed = new JTextField("0");
		seed.setPreferredSize(new Dimension(50,20));
		seed.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(seed.getText().length() >0 )
					try{
						Integer.parseInt(seed.getText());
					}catch(Exception ex){
						seed.setText("0");
					}


			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		JCheckBox torique = new JCheckBox("Torique");
		torique.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (torique.isSelected()){
					action.setTorique(true);
				}else{
					action.setTorique(false);
				}
				
			}
		} );
		
		JCheckBox visibleGrid = new JCheckBox("Grille visible");
		visibleGrid.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (visibleGrid.isSelected()){
					action.setVisibleGrid(true);
					resetGrid();
				}else{
					action.setVisibleGrid(false);
					resetGrid();
				}
				
			}
		} );
		JCheckBox equitable = new JCheckBox("Equitable");
		equitable.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (equitable.isSelected()){
					action.setEquitable(true);
				}else{
					action.setEquitable(false);
				}
				
			}
		} );

		JButton start = new JButton("start");
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton source = ((JButton)	e.getSource());
				String text = source.getText();

				if( text.equals("start")){
					source.setText("stop");

					action.launch(Integer.parseInt(billes.getText()),Integer.parseInt(seed.getText()),Integer.parseInt(vitesse.getText()), torique.isSelected(), visibleGrid.isSelected(), equitable.isSelected());
					resetGrid();
					actualiseButton();
					action.changeRunning();




				}

				if( text.equals("stop")){
					source.setText("start");
					action.changeRunning();

				}



			}
		});

		JButton clean = new JButton("clear");

		clean.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {


				action.clearAgent();



			}
		});





		controle.setLayout(new FlowLayout());

		controle.add(labBille);
		controle.add(billes);
		controle.add(labVite);
		controle.add(vitesse);
		controle.add(labSeed);
		controle.add(seed);
		controle.add(torique);
		controle.add(visibleGrid);	
		controle.add(equitable);
		controle.add(start);
		controle.add(clean);







	}


	public void initButton(){



		int size = action.getEnvironnement().getEspace().length;
		this.buttonTab = new JButton[size][size];

		this.setLayout(new GridLayout(this.buttonTab.length,this.buttonTab.length));

		for(int i = 0 ; i < size; i++){
			for( int j = 0; j < size; j++){
				final int x = i;
				final int y = j;
				buttonTab[i][j] = new JButton();

				buttonTab[i][j].addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						Agent e = new Agent(x,y,action.getEnvironnement());
						action.getEnvironnement().getEspace()[x][y].setAgent(e);
						action.getEnvironnement().getAgents().add(e);
						buttonTab[x][y].setBackground(Color.BLACK);
						


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

	public void resetGrid(){
		for(int i = 0 ; i < this.buttonTab.length; i++){
			for(int j = 0; j < this.buttonTab[i].length; j++){
				if(!action.isVisibleGrid()){
					Border emptyBorder = BorderFactory.createEmptyBorder();
					buttonTab[i][j].setBorder(emptyBorder);
				}else{
					Border border = new LineBorder(Color.DARK_GRAY, 1);
					buttonTab[i][j].setBorder(border);
				}
			}
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {

		this.actualiseButton();
		repaint();
	}

}
