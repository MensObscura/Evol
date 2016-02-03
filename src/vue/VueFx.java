package vue;


import java.util.ArrayList;
import java.util.List;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import core.agents.Agent;
import core.agents.AgentFactory;
import core.model.Direction;
import core.sma.SMA;
import core.sma.SMASimulation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;
import projet.billes.SMABille;
import projet.pacman.SMAPacMan;
import projet.wator.SMAWator;

public class VueFx extends Application {

	public static List<Shape> circleAgent;

	public static ObservableList<Shape> circleObservable;
	public static Pane canvas;
	private VueController vue;

	public SMA action;
	private Timeline loop;
	public VueFx(){

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Parameters list =getParameters();
		Object args [] =list.getRaw().toArray();

		if(((String)args[0]).equals("-wator")){
			this.action = new SMAWator(Integer.parseInt((String)args[1]),Integer.parseInt((String)args[2]),Integer.parseInt((String)args[3]),Integer.parseInt((String)args[4]),Integer.parseInt((String)args[5]),Boolean.parseBoolean((String)args[6]),Boolean.parseBoolean((String)args[7]),Boolean.parseBoolean((String)args[8]),Integer.parseInt((String)args[9]),Integer.parseInt((String)args[10]),Integer.parseInt((String)args[11]),Integer.parseInt((String)args[12]));
		}else if(((String)args[0]).equals("-billes")){
			this.action =  new SMABille(Integer.parseInt((String)args[1]),Integer.parseInt((String)args[2]),Integer.parseInt((String)args[3]),Integer.parseInt((String)args[4]),Boolean.parseBoolean((String)args[5]),Boolean.parseBoolean((String)args[6]),Boolean.parseBoolean((String)args[7]),Integer.parseInt((String)args[8]));
		}else if(((String)args[0]).equals("-pacman")){
			this.action =  new SMAPacMan(Integer.parseInt((String)args[1]),Integer.parseInt((String)args[2]),Integer.parseInt((String)args[3]),Integer.parseInt((String)args[4]),Boolean.parseBoolean((String)args[5]), Boolean.parseBoolean((String)args[6]),Integer.parseInt((String)args[7]));

		}
		circleAgent = new ArrayList<Shape>();

		canvas = new Pane();
		if(((String)args[0]).equals("-pacman")){
			canvas.setStyle("-fx-background-color: #E0CDA9 ");
		}else if(((String)args[0]).equals("-wator")){
			canvas.setStyle("-fx-background-color: #B0F1FE ");
		}else if(((String)args[0]).equals("-billes")){
			canvas.setStyle("-fx-background-color: #A5DB92 ");
		}

		final Scene scene = new Scene(canvas, this.action.getEnvironnement().getTaille() *10, this.action.getEnvironnement().getTaille()*10 );

		if(((String)args[0]).equals("-wator")){
			primaryStage.setTitle("Battle !");
		}else if(((String)args[0]).equals("-billes")){
			primaryStage.setTitle("Chambre � particule");
		}else if(((String)args[0]).equals("-pacman")){
			primaryStage.setTitle("Run forest, run !");
		}
		primaryStage.setScene(scene);
		if(((String)args[0]).equals("-pacman"))
			this.addArrowsListener(scene);
		primaryStage.show();

		canvas.setOnMouseClicked(event -> {
			int x = (int) event.getX()/10; int y = (int) event.getY()/10;
			Agent e = null;
			if(((String)args[0]).equals("-wator")){
				e = AgentFactory.getInstance().getAgent("requin",action.getEnvironnement(),x,y,new String[] {((SMAWator)action).getReproductionRequin()+"",((SMAWator)action).getFaimRequin()+""});
			}else if(((String)args[0]).equals("-billes")){
				e = AgentFactory.getInstance().getAgent("bille",action.getEnvironnement(),x,y,new String[0]);
			}else if(((String)args[0]).equals("-pacman")){
				if(!action.isRunning() && event.getButton() == MouseButton.SECONDARY)
					e = AgentFactory.getInstance().getAgent("protecteur",action.getEnvironnement(),x,y,new String[0]);
			}


			if(e != null ){
				if((action.getEnvironnement().getEspace()[x][y].isEmpty() && action instanceof SMAPacMan)){
					action.getEnvironnement().getEspace()[x][y].setAgent(e);
					action.getEnvironnement().getAgents().add(e);
				}
				if(!( action instanceof SMAPacMan)){
					action.getEnvironnement().getEspace()[x][y].setAgent(e);
					action.getEnvironnement().getAgents().add(e);
				}
			}
		});


		vue = new VueController(this.action, true, ((String)args[0]));
		vue.setVueFx(this);
		if(((String)args[0]).equals("-pacman")){
			this.pacManLoop();
		}else{
			this.loop();

		}
	}

	public void pacManLoop() {
		if(loop != null)
			loop.stop();


		loop = new Timeline(new KeyFrame(Duration.millis(((SMAPacMan)action).getVitesse()), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				action.round();

				circleAgent.clear();
				canvas.getChildren().clear();
				//	Ajout des agents

				for (int i = 0; i < action.getAgents().size(); i++) {

					circleAgent.add(action.getAgents().get(i).getRepresentation());

				}

				circleObservable = FXCollections.observableArrayList(circleAgent);
				if(action.isVisibleGrid())
					drawGrid();
				canvas.getChildren().addAll(circleObservable);

			}


		}));




		loop.setCycleCount(Timeline.INDEFINITE);
		loop.play();

	}

	public void loop() {

		if(loop != null)
			loop.stop();

		loop = new Timeline(new KeyFrame(Duration.millis(((SMASimulation)action).getVitesse()), new EventHandler<ActionEvent>() {


			public void handle(final ActionEvent t) {
				action.round();

				circleAgent.clear();
				canvas.getChildren().clear();
				//	Ajout des agents
				for (int i = 0; i < action.getAgents().size(); i++) {

					circleAgent.add(action.getAgents().get(i).getRepresentation());

				}

				circleObservable = FXCollections.observableArrayList(circleAgent);
				if(action.isVisibleGrid())
					drawGrid();
				canvas.getChildren().addAll(circleObservable);

			}


		}));




		loop.setCycleCount(Timeline.INDEFINITE);
		loop.play();

	}


	private void addArrowsListener(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case UP:    ((SMAPacMan)action).getAvatar().changeDirection(Direction.OUEST);break;
				case DOWN:  ((SMAPacMan)action).getAvatar().changeDirection(Direction.EST); break;
				case LEFT: ((SMAPacMan)action).getAvatar().changeDirection(Direction.NORD); break;
				case RIGHT:((SMAPacMan)action).getAvatar().changeDirection(Direction.SUD); break;
				case SPACE: if(((SMAPacMan)action).addVitesse(100)){pacManLoop();}break;
				case S: if(((SMAPacMan)action).slowVitesse(100)){pacManLoop();}break;
				case Z: vue.changeRunning(); break;
				case CONTROL: vue.stopRunning(); vue.reset(); break;
				case F:if(((SMAPacMan)action).speedChasseurSpeed()){pacManLoop();}break;
				case D:if(((SMAPacMan)action).slowChasseurSpeed()){pacManLoop();}break;
				default: break;
				}
			}
		});



	}

	public void drawGrid(){
		List<Shape> lineAgent = new ArrayList<Shape>();
		for(int i = 0 ; i < this.action.getEnvironnement().getTaille();i++){
			Rectangle r = new Rectangle(this.action.getEnvironnement().getTaille()*10,1.5,javafx.scene.paint.Color.DARKGRAY);
			r.relocate( 0,i*10);
			lineAgent.add(r);
			r= new Rectangle(1.5,this.action.getEnvironnement().getTaille()*10,javafx.scene.paint.Color.DARKGRAY);
			r.relocate(i*10,0);
			lineAgent.add(r);

		}
		canvas.getChildren().addAll(lineAgent);
	}

	public static void run(String args[]){
		launch(args);
	}


}