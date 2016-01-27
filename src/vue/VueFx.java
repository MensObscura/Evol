package vue;




import java.util.ArrayList;
import java.util.List;

import agents.Agent;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.AgentFactory;
import sma.SMAWator;

public class VueFx extends Application {




	public static List<Shape> circleAgent;

	public static ObservableList<Shape> circleObservable;
	public static Pane canvas;

	public SMAWator action;
	private Timeline loop;
	public VueFx(){

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Parameters list =getParameters();
		Object args [] =list.getRaw().toArray();
		this.action = new SMAWator(Integer.parseInt((String)args[0]),Integer.parseInt((String)args[1]),Integer.parseInt((String)args[2]),Integer.parseInt((String)args[3]),Integer.parseInt((String)args[4]),Boolean.parseBoolean((String)args[5]),Boolean.parseBoolean((String)args[6]),Boolean.parseBoolean((String)args[7]),Integer.parseInt((String)args[8]),Integer.parseInt((String)args[9]),Integer.parseInt((String)args[10]),Integer.parseInt((String)args[11]));

		circleAgent = new ArrayList<Shape>();






		canvas = new Pane();
		canvas.setStyle("-fx-background-color: #B0F1FE ");

		final Scene scene = new Scene(canvas, this.action.getEnvironnement().getTaille() , this.action.getEnvironnement().getTaille() );
		primaryStage.setTitle("Battle");
		primaryStage.setScene(scene);
		primaryStage.show();

		canvas.setOnMouseClicked(event -> {
			int x = (int) event.getX(); int y = (int) event.getY();
			Agent e = AgentFactory.getInstance().getAgent("requin",action.getEnvironnement(),x,y,new String[] {action.getEnvironnement().getReproductionRequin()+"",action.getEnvironnement().getFaimRequin()+""});
			action.getEnvironnement().getEspace()[x][y].setAgent(e);
			action.getEnvironnement().getAgents().add(e);
		});


		//         Ajout des agents
		for (int i = 0; i < this.action.getAgents().size(); i++) {


			circleAgent.add(this.action.getAgents().get(i).getRepresentation());



		}



		circleObservable = FXCollections.observableArrayList(circleAgent);




		this.loop();


		Vue vue = new Vue(action, true);
		vue.setVueFx(this);
//		Thread t = new Thread(){
//
//			public void run(){
//				while(true){
//					action.round();
//					Platform.runLater(new Runnable(){
//						@Override public void run() {
//						
//							circleAgent.clear();
//							canvas.getChildren().clear();
//							//			         Ajout des agents
//							for (int i = 0; i < action.getAgents().size(); i++) {
//
//
//								circleAgent.add(action.getAgents().get(i).getRepresentation());
//
//
//
//							}
//
//
//
//							circleObservable = FXCollections.observableArrayList(circleAgent);
//
//							canvas.getChildren().addAll(circleObservable);
//						}
//					});
//					System.out.println("fin loop fx");
//				}
//			}
//
//		};
//		t.start();
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
						
						System.out.println("fin loop fx");
					}
		
		
				}));
		
		
		
		
				loop.setCycleCount(Timeline.INDEFINITE);
				loop.play();

	}

	public static void run(String args[]){
		launch(args);
	}
}