package test;

import engine.DisplayManager;
import org.lwjgl.opengl.Display;
import engine.Loader;
import engine.RawModel;
import engine.Renderer;

import java.util.Date;

/**
 * This class contains the main method and is used to test the engine.
 * 
 * @author Karl
 *
 */
public class MainGameLoop {


	/**
	 * Loads up the position data for two triangles (which together make a quad)
	 * into a VAO. This VAO is then rendered to the screen every frame.
	 * 
	 * @param args
	 */
	private static long updateTimeSum = 0L;
	private static int currentFps = 0;

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		
		float[] vertices = {
				-0.5f, 0.5f, 0f,//v0
				-0.5f, -0.5f, 0f,//v1
				0.5f, -0.5f, 0f,//v2
				0.5f, 0.5f, 0f,//v3
		};
		
		int[] indices = {
				0,1,3,//top left triangle (v0, v1, v3)
				3,1,2//bottom right triangle (v3, v1, v2)
		};
		
		RawModel model = loader.loadToVAO(vertices, indices);

		while (!Display.isCloseRequested()) {
			// game logic
			long startTime = System.currentTimeMillis();
			long elapsedTime = 0L;

			//RawModel m = loader.loadToVAO(vertices, indices);
			modifyVertices(vertices);
			renderer.prepare();
			renderer.render(model);
			DisplayManager.updateDisplay();

			elapsedTime = (new Date()).getTime() - startTime;

			if (updateTimeSum / 1000 >= 1L) {
				updateTimeSum = 0;
				System.out.println("fps: " + currentFps);
				currentFps = 0;
			} else {
				updateTimeSum += elapsedTime;
				//System.out.println("updateTimeSum: " + updateTimeSum);
				currentFps++;
			}
			//System.out.println("time: " + elapsedTime);
		}

		loader.cleanUp();
		DisplayManager.closeDisplay();
	}
	/**
	 * Messing around with vertices...
	 * @param vertices
	 */
	private static void modifyVertices(float[] vertices) {
		for (int i = 0; i < vertices.length; ++i) {
			//System.out.println("before: " + vertices[i]);
			vertices[i] *= 1.0005;
			//System.out.println("after: " + vertices[i]);
		}
	}

}
