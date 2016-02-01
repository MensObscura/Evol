package vue;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import agents.Agent;
import model.AgentFactory;
import model.Direction;
import sma.SMAWator;
import sma.SMA;
import sma.SMAPacMan;
import sma.SMABille;
import sma.SMASimulation;

public class VueController extends JPanel implements Observer{

	private JButton[][] buttonTab;

	private SMA action;	
	private JPanel glob;
	private boolean isFx;
	private JFrame f;
	private VueFx vueFx;
	private JButton set;
	private JTextField vitesse;
	private JTextField seed;
	private JCheckBox torique;
	private JCheckBox visibleGrid;
	private JCheckBox equitable;
	private String mode;
	private JButton start;
	private JLabel score ;

	public VueController(SMA action, boolean isFx,String mode){

		this.mode=mode;
		this.isFx=isFx;
		this.action = action;
		action.addObserver(this);

		JPanel controle = new JPanel();
		initRuleButton(controle);


		this.f= new JFrame("Evol");
		glob = new JPanel();

		if(!this.isFx)
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


		this.f.add(glob);
		this.f.pack();
		this.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.f.setVisible(true);


	}


	public void setVueFx(VueFx vue){

		this.vueFx=vue;

	}

	public void initRuleButton(JPanel controle){

		set = new JButton("set");

		if(this.mode.equals("-wator"))
			initButtonWator(controle);

		if(this.mode.equals("-billes"))
			initButtonBilles(controle);

		if(this.mode.equals("-pacman"))
			initButtonPacMan(controle);

		JLabel labVite = new JLabel("vitesse");
		if(!this.mode.equals("-pacman")){
			this.vitesse = new JTextField(((SMASimulation)this.action).getVitesse()+"");
		}else{
			this.vitesse = new JTextField("");
		}
		this.vitesse.setPreferredSize(new Dimension(50,20));
		this.vitesse.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(vitesse.getText().length() >0 )
					try{

						int nbVitesse = Integer.parseInt(vitesse.getText());

						((SMASimulation)action).setVitesse(nbVitesse);
						if(isFx && vueFx != null){
							vueFx.loop();
						}
					}catch(Exception ex){
						System.out.println(ex);
						vitesse.setText("0");
					}



			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});


		JLabel labSeed = new JLabel("Seed");
		this.seed = new JTextField(this.action.getSeed()+"");
		this.seed.setPreferredSize(new Dimension(50,20));
		this.seed.addKeyListener(new KeyListener() {

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
		this.torique = new JCheckBox("Torique");
		this.torique.setSelected(this.action.getEnvironnement().isTorique());
		this.torique.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (torique.isSelected()){
					action.setTorique(true);
				}else{
					action.setTorique(false);
				}

			}
		} );

		this.visibleGrid = new JCheckBox("Grille visible");

		this.visibleGrid.setSelected(this.action.isVisibleGrid());

		this.visibleGrid.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (visibleGrid.isSelected()){
					action.setVisibleGrid(true);

				}else{
					action.setVisibleGrid(false);

				}
				if(!isFx){
					resetGrid();
				}
			}
		} );
		this.equitable = new JCheckBox("Equitable");
		if(!this.mode.equals("-pacman")){
			this.equitable.setSelected(((SMASimulation)this.action).isEquit());
		}else{
			this.equitable.setSelected(false);
		}
		this.equitable.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (equitable.isSelected()){
					((SMASimulation)action).setEquitable(true);
				}else{
					((SMASimulation)action).setEquitable(false);
				}

			}
		} );




		start = new JButton("start");
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				changeRunning();
			}
		});

		JButton clean = new JButton("clear");

		clean.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {


				action.clearAgent();



			}
		});




		if(this.mode.equals("-wator"))
			controle.setLayout(new GridLayout(10,2));

		if(this.mode.equals("-billes"))
			controle.setLayout(new GridLayout(6,2));

		if(this.mode.equals("-pacman"))
			controle.setLayout(new GridLayout(13,2));
	


		if(!this.mode.equals("-pacman")){
			controle.add(labVite);
			controle.add(this.vitesse);
		}

		controle.add(labSeed);
		controle.add(this.seed);
		controle.add(this.torique);
		controle.add(this.visibleGrid);	
		if(!this.mode.equals("-pacman")){
			controle.add(this.equitable);
		}
		controle.add(this.set);
		controle.add(start);
		controle.add(clean);
		if(this.mode.equals("-pacman")){
			JLabel rule = new JLabel("Reach the white case");
			JLabel arrow = new JLabel("ARROWS : ");
			JLabel ctrl = new JLabel("CTRL :");
			JLabel space = new JLabel("SPACE :");
			JLabel b = new JLabel("KEY_B : ");
			JLabel leftClick = new JLabel("RIGHT_CLICK :");
			
			//dirty trick
			this.score = new JLabel(((SMAPacMan)action).getState());
			controle.add(this.score);
			controle.add(rule);
			controle.add(new JLabel(" whitout be catching be the hunters (reds one)"));
			controle.add(arrow);
			controle.add(new JLabel("to move the avatar (green one)"));
			controle.add(ctrl);
			controle.add(new JLabel(" to start and pause"));
			controle.add(space);
			controle.add(new JLabel("speed the game"));
			controle.add(b);
			controle.add(new JLabel("slow the game"));
			controle.add(leftClick);
			controle.add(new JLabel(" to add a protector (violets one)"));
			controle.add(new JLabel("KEY_S : "));
			controle.add(new JLabel(" Reset the game"));
		}





	}


	private void initButtonPacMan(JPanel controle) {
		JLabel labChasseur = new JLabel("Nombre de Chasseurs");
		JTextField chasseur = new JTextField(((SMAPacMan)this.action).getNbChasseurs()+"");
		chasseur.setPreferredSize(new Dimension(50,20));
		chasseur.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(chasseur.getText().length() >0 )
					try{
						Integer.parseInt(chasseur.getText());
					}catch(Exception ex){
						chasseur.setText("0");
					}


			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});


		JLabel labMur = new JLabel("Nombre de murs");
		JTextField mur = new JTextField(((SMAPacMan)this.action).getNbMurs()+"");
		mur.setPreferredSize(new Dimension(50,20));
		mur.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if( mur.getText().length() >0 )
					try{
						Integer.parseInt( mur.getText());
					}catch(Exception ex){
						mur.setText("0");
					}


			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		set.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				((SMAPacMan)action).launch(Integer.parseInt(chasseur.getText()),Integer.parseInt(mur.getText()), torique.isSelected(), Integer.parseInt(seed.getText()), visibleGrid.isSelected());
				if(!isFx){
					resetGrid();
					actualiseButton();			
				}
			}

		});

		controle.add(labChasseur);
		controle.add(chasseur);
		controle.add(labMur);
		controle.add(mur);
		
	}

	private void initButtonBilles(JPanel controle){
		JLabel labBille = new JLabel("Nombre de billes");
		JTextField billes = new JTextField(((SMABille)this.action).getNbBilles()+"");
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
						int nbBille=	Integer.parseInt(billes.getText());
						if(nbBille > 2000){
							billes.setText("2000");
						}
					}catch(Exception ex){
						billes.setText("0");
					}


			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		set.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				((SMABille)action).launch(Integer.parseInt(billes.getText()),Integer.parseInt(seed.getText()),Integer.parseInt(vitesse.getText()), torique.isSelected(), visibleGrid.isSelected(), equitable.isSelected());
				if(!isFx){
					resetGrid();
					actualiseButton();			
				}
			}

		});

		controle.add(labBille);
		controle.add(billes);
	}

	private void initButtonWator(JPanel controle) {

		JLabel labNemo = new JLabel("Nombre de nemos");
		JTextField nemo = new JTextField(((SMAWator)this.action).getNbNemos()+"");
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
		JTextField nemoRepro = new JTextField(((SMAWator)this.action).getReproductionNemo()+"");
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

						((SMAWator)action).setReproductionNemo(Integer.parseInt(nemoRepro.getText()));

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
		JTextField requin = new JTextField(((SMAWator)this.action).getNbRequins()+"");
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
		JTextField requinRepro = new JTextField(((SMAWator)this.action).getReproductionRequin()+"");
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

						((SMAWator)action).setReproductionRequin(Integer.parseInt(requinRepro.getText()));

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
		JTextField requinFaim = new JTextField(((SMAWator)this.action).getFaimRequin()+"");
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

						((SMAWator)action).setFaimRequin(Integer.parseInt(requinFaim.getText()));

					}catch(Exception ex){
						requinFaim.setText("0");
					}


			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});


		set.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				((SMAWator)action).launch(Integer.parseInt(nemo.getText()),Integer.parseInt(requin.getText()),Integer.parseInt(seed.getText()),Integer.parseInt(vitesse.getText()), torique.isSelected(), visibleGrid.isSelected(), equitable.isSelected(), Integer.parseInt(nemoRepro.getText()), Integer.parseInt(requinRepro.getText()), Integer.parseInt(requinFaim.getText()));
				if(!isFx){
					resetGrid();
					actualiseButton();			
				}
			}

		});

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

						Agent e = null;
						if(mode.equals("-wator"))
							e = AgentFactory.getInstance().getAgent("requin",action.getEnvironnement(),x,y,new String[] {((SMAWator)action).getReproductionRequin()+"",((SMAWator)action).getFaimRequin()+""});
						if(mode.equals("-billes"))
							e= AgentFactory.getInstance().getAgent("bille",action.getEnvironnement(),x,y,new String[0]);
						if(mode.equals("-pacman"))
							e= AgentFactory.getInstance().getAgent("protecteur",action.getEnvironnement(),x,y,new String[0]);

						if(e!= null){
							action.getEnvironnement().getEspace()[x][y].setAgent(e);
							action.getEnvironnement().getAgents().add(e);
							buttonTab[x][y].setBackground(e.getColor());
						}



					}

				});

				this.buttonTab[i][j].setPreferredSize(new Dimension(action.gettAgent(), action.gettAgent()));

				if(!((SMASimulation)action).isVisibleGrid()){
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
				if(!((SMASimulation)action).isVisibleGrid()){
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

		this.score.setText(((SMAPacMan)action).getState());
		if(!this.isFx)
			this.actualiseButton();
			

		if(this.mode.equals("-pacman")){
			vueFx.pacManLoop();
		}
		repaint();
	}


	public void changeRunning() {
		String text = start.getText();

		if( text.equals("start")){
			start.setText("stop");

			action.changeRunning();


		}

		if( text.equals("stop")){
			start.setText("start");

			action.changeRunning();

		}

		


	}
	public void reset(){
		set.doClick();
	}


	public void stopRunning() {
		start.setText("start");
		action.stopRunning();
		
	}



}
