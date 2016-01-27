package vue;




import java.util.ArrayList;
import java.util.List;

import agents.Agent;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.AgentFactory;
import sma.SMA;
import sma.SMABille;
import sma.SMAWator;

public class VueFx extends Application {




	public static List<Shape> circleAgent;

	public static ObservableList<Shape> circleObservable;
	public static Pane canvas;

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
		}
		circleAgent = new ArrayList<Shape>();






		canvas = new Pane();
		canvas.setStyle("-fx-background-color: #B0F1FE ");

		final Scene scene = new Scene(canvas, this.action.getEnvironnement().getTaille() *10, this.action.getEnvironnement().getTaille()*10 );
		primaryStage.setTitle("Battle");
		primaryStage.setScene(scene);
		primaryStage.show();

		canvas.setOnMouseClicked(event -> {
			int x = (int) event.getX()/10; int y = (int) event.getY()/10;
			Agent e = null;
			if(((String)args[0]).equals("-wator")){
				e = AgentFactory.getInstance().getAgent("requin",action.getEnvironnement(),x,y,new String[] {((SMAWator)action).getReproductionRequin()+"",((SMAWator)action).getFaimRequin()+""});
			}else if(((String)args[0]).equals("-billes")){
				e = AgentFactory.getInstance().getAgent("bille",action.getEnvironnement(),x,y,new String[0]);

			}

			if(e != null){
				action.getEnvironnement().getEspace()[x][y].setAgent(e);
				action.getEnvironnement().getAgents().add(e);
			}
		});


		//         Ajout des agents
		for (int i = 0; i < this.action.getAgents().size(); i++) {


			circleAgent.add(this.action.getAgents().get(i).getRepresentation());



		}



		circleObservable = FXCollections.observableArrayList(circleAgent);


		VueController vue = new VueController(this.action, true, ((String)args[0]));
		vue.setVueFx(this);

		this.loop();


	}

	public void loop() {
		if(loop != null)
			loop.stop();


		loop = new Timeline(new KeyFrame(Duration.millis(action.getVitesse()), new EventHandler<ActionEvent>() {


			public void handle(final ActionEvent t) {

				action.round();
				circleAgent.clear();
				canvas.getChildren().clear();
				//			         Ajout des agents
				for (int i = 0; i < action.getAgents().size(); i++) {

					circleAgent.add(action.getAgents().get(i).getRepresentation());



				}



				circleObservable = FXCollections.observableArrayList(circleAgent);

				canvas.getChildren().addAll(circleObservable);

			}


		}));




		loop.setCycleCount(Timeline.INDEFINITE);
		loop.play();

	}



	public static void run(String args[]){
		launch(args);
	}
}