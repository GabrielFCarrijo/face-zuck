package br.com.faceZuck;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@QuarkusMain
@ApplicationPath("/")
public class Main extends Application {

	public static void main(String... args) {
		Quarkus.run(MyApp.class);
	}
	public static class MyApp implements QuarkusApplication {

		@Override
		public int run(String... args) throws Exception {
			Quarkus.waitForExit();
			return 0;
		}
	}
}
