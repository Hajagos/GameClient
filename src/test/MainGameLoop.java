package test;

import engine.DisplayManager;
import engine.parser.ObjLoader;
import entity.Camera;
import entity.Entity;
import model.TexturedModel;
import org.lwjgl.opengl.Display;
import engine.Loader;
import model.RawModel;
import engine.Renderer;
import org.lwjgl.util.vector.Vector3f;
import shaders.StaticShader;
import texture.ModelTexture;

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
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);

		float[] vertices = {
				-0.5f,0.5f,0,
				-0.5f,-0.5f,0,
				0.5f,-0.5f,0,
				0.5f,0.5f,0,

				-0.5f,0.5f,1,
				-0.5f,-0.5f,1,
				0.5f,-0.5f,1,
				0.5f,0.5f,1,

				0.5f,0.5f,0,
				0.5f,-0.5f,0,
				0.5f,-0.5f,1,
				0.5f,0.5f,1,

				-0.5f,0.5f,0,
				-0.5f,-0.5f,0,
				-0.5f,-0.5f,1,
				-0.5f,0.5f,1,

				-0.5f,0.5f,1,
				-0.5f,0.5f,0,
				0.5f,0.5f,0,
				0.5f,0.5f,1,

				-0.5f,-0.5f,1,
				-0.5f,-0.5f,0,
				0.5f,-0.5f,0,
				0.5f,-0.5f,1

		};

		float[] textureCoords = {

				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0


		};

		int[] indices = {
				0,1,3,
				3,1,2,
				4,5,7,
				7,5,6,
				8,9,11,
				11,9,10,
				12,13,15,
				15,13,14,
				16,17,19,
				19,17,18,
				20,21,23,
				23,21,22

		};

		ObjLoader objLoader = new ObjLoader();

		RawModel model = objLoader.loadObjModel("Porsche_911_GT2", loader);

		TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("0000")));

		Entity entity = new Entity(staticModel, new Vector3f(0,0,-5),0,0,0,1);

		Camera camera = new Camera();
		while (!Display.isCloseRequested()) {
			// game logic
			long startTime = System.currentTimeMillis();
			long elapsedTime = 0L;

			//RawModel m = loader.loadToVAO(vertices, indices);
			modifyVertices(vertices);
			renderer.prepare();

			entity.increaseRotation(0, 1, 0);
			camera.move();
			renderer.prepare();
			shader.start();

			shader.loadViewMatrix(camera);

			renderer.render(entity, shader);

			shader.stop();

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

		shader.cleanUp();
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
