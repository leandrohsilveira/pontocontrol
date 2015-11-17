package br.com.lhs.pontocontrol.fx.main;

import java.net.URL;

import br.com.lhs.pontocontrol.jetty.ApplicationService;
import br.com.pontocontrol.controleponto.PathsManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControlePontoFX extends Application {

	@Override
	public void start(final Stage stage) throws Exception {
		final ApplicationService applicationService = new ApplicationService(8441, ControlePontoFX.class);

		applicationService.run();

		final String projectRoot = PathsManager.getInstance().projectRootPath();
		final URL sceneUrl = PathsManager.getInstance().getFileResource(projectRoot, "/fxml/Scene.fxml").toURI().toURL();
		final String cssPath = PathsManager.getInstance().getFileResource(projectRoot, "/styles/Styles.css").toURI().getPath();

		final Parent root = FXMLLoader.load(sceneUrl);

		final Scene scene = new Scene(root);

		scene.getStylesheets().add("file:///" + cssPath);

		stage.setTitle("Controle Ponto [PontoControl FX]");
		stage.setScene(scene);
		stage.setOnCloseRequest((event) -> {
			applicationService.stop();
		});
		stage.show();
	}

	/**
	 * The main() method is ignored in correctly deployed JavaFX application. main() serves only as fallback in case the application can not be launched through deployment artifacts, e.g., in IDEs with limited FX support. NetBeans ignores main().
	 *
	 * @param args
	 *           the command line arguments
	 */
	public static void main(final String[] args) {
		Application.launch(args);
	}

}