package entity;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	private Vector3f position = new Vector3f(0,0,0);
	private float pitch = 1.0f;
	private float yaw = 1.0f;
	private float roll = 1.0f;

	private float positionModificationAmount = 0.04f;

	private float angleModificationAmount = 0.6f;

	public Camera(){}

	public void move() {
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			position.z -= positionModificationAmount;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			position.z += positionModificationAmount;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			position.x += positionModificationAmount;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			position.x -= positionModificationAmount;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
			position.y += positionModificationAmount;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_G)) {
			position.y -= positionModificationAmount;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			yaw -= angleModificationAmount;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			yaw += angleModificationAmount;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			pitch -= angleModificationAmount;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			pitch += angleModificationAmount;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			roll -= angleModificationAmount;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
			roll += angleModificationAmount;
		}
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}
}
