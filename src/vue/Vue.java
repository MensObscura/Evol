package vue;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

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

import agents.Agent;
import agents.Bille;
import model.AgentFactory;

import sma.SMAWator;

public class Vue extends JPanel implements Observer{

	private JButton[][] buttonTab;

	private SMAWator action;	
	private JPanel glob;

	public Vue(SMAWator action){

		this.action = action;
		action.addObserver(this);

		JPanel controle = new JPanel();
		initRuleButton(controle);


		System.out.println("On lance la fenêtre");
		JFrame f= new JFrame("Evol");
		glob = new JPanel();
		initButton();

		glob.setLayout( new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		glob.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		//natural height, maximum width
		c.fill = GridBagConstraints.BOTH;
		
		c.weightx =1.0;
		c.insets = new Insets(0,0,0,0); 
		c.weighty = 1.0;
		glob.add(this,c);
		
		c.weightx = 0;
		c.weighty = 0;
		glob.add(controle,c);
		
		f.add(glob);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);


	}

	public void initRuleButton(JPanel controle){

		JLabel labNemo = new JLabel("Nombre de nemos");
		JTextField nemo = new JTextField(this.action.getEnvironnement().getNbNemos()+"");
		nemo.setPreferredSize(new Dimension(50,20));
		nemo.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(nemo.getText().length() >0 )
					try{
						Integer.parseInt(nemo.getText());
					}catch(Exception ex){
						nemo.setText("0");
					}


			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		
		JLabel labNemoRepro = new JLabel("Maturité des nemos");
		JTextField nemoRepro = new JTextField(this.action.getEnvironnement().getReproductionNemo()+"");
		nemoRepro.setPreferredSize(new Dimension(50,20));
		nemoRepro.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(nemoRepro.getText().length() >0 )
					try{
						
					action.getEnvironnement().setReproductionNemo(Integer.parseInt(nemoRepro.getText()));
						
					}catch(Exception ex){
						nemoRepro.setText("0");
					}


			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		JLabel labRequin = new JLabel("Nombre de requins");
		JTextField requin = new JTextField(this.action.getEnvironnement().getNbRequins()+"");
		requin.setPreferredSize(new Dimension(50,20));
		requin.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(requin.getText().length() >0 )
					try{
						Integer.parseInt(requin.getText());
					}catch(Exception ex){
						requin.setText("0");
					}


			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		
		JLabel labRequinRepro = new JLabel("Maturité des requins");
		JTextField requinRepro = new JTextField(this.action.getEnvironnement().getReproductionRequin()+"");
		requinRepro.setPreferredSize(new Dimension(50,20));
		requinRepro.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(requinRepro.getText().length() >0 )
					try{
						
					action.getEnvironnement().setReproductionRequin(Integer.parseInt(requinRepro.getText()));
						
					}catch(Exception ex){
						requinRepro.setText("0");
					}


			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		
		
		JLabel labRequinFaim= new JLabel("Faim des requins");
		JTextField requinFaim = new JTextField(this.action.getEnvironnement().getFaimRequin()+"");
		requinFaim.setPreferredSize(new Dimension(50,20));
		requinFaim.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(requinFaim.getText().length() >0 )
					try{
						
					action.getEnvironnement().setFaimRequin(Integer.parseInt(requinFaim.getText()));
						
					}catch(Exception ex){
						requinFaim.setText("0");
					}


			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		

		JLabel labVite = new JLabel("vitesse");
		JTextField vitesse = new JTextField(this.action.getVitesse()+"");
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

						action.setVitesse(Integer.parseInt(vitesse.getText()));
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
		JTextField seed = new JTextField(this.action.getSeed()+"");
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
		torique.setSelected(this.action.getEnvironnement().isTorique());
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
		visibleGrid.setSelected(this.action.isVisibleGrid());
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
		equitable.setSelected(this.action.isEquit());
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

		JButton set = new JButton("set");
		set.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				action.launch(Integer.parseInt(nemo.getText()),Integer.parseInt(requin.getText()),Integer.parseInt(seed.getText()),Integer.parseInt(vitesse.getText()), torique.isSelected(), visibleGrid.isSelected(), equitable.isSelected(), Integer.parseInt(nemoRepro.getText()), Integer.parseInt(requinRepro.getText()), Integer.parseInt(requinFaim.getText()));
				resetGrid();
				actualiseButton();			
			}

		});

		JButton start = new JButton("start");
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton source = ((JButton)	e.getSource());
				String text = source.getText();

				if( text.equals("start")){
					source.setText("stop");


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





		controle.setLayout(new GridLayout(10,2));

		controle.add(labNemo);
		controle.add(nemo);
		controle.add(labNemoRepro);
		controle.add(nemoRepro);
		controle.add(labRequin);
		controle.add(requin);
		controle.add(labRequinRepro);
		controle.add(requinRepro);
		controle.add(labRequinFaim);
		controle.add(requinFaim);
		controle.add(labVite);
		controle.add(vitesse);
		controle.add(labSeed);
		controle.add(seed);
		controle.add(torique);
		controle.add(visibleGrid);	
		controle.add(equitable);
		controle.add(set);
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
						Agent e = AgentFactory.getInstance().getAgent("requin",action.getEnvironnement(),x,y,new String[] {action.getEnvironnement().getReproductionRequin()+"",action.getEnvironnement().getFaimRequin()+""});
						action.getEnvironnement().getEspace()[x][y].setAgent(e);
						action.getEnvironnement().getAgents().add(e);
						buttonTab[x][y].setBackground(e.getColor());



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
					buttonTab[i][j].setBackground(Color.cyan);
				}else{
					buttonTab[i][j].setBackground(action.getEnvironnement().getEspace()[i][j].getAgent().getColor());
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
					Border border = new LineBorder(Color.GRAY, 1);
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
