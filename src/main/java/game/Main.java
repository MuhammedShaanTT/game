package game;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;

public class Main extends SimpleApplication {

    private Geometry enemy;
    private float enemySpeed = 2f;
    private Geometry player;
    private boolean left, right, forward, backward;

    public static void main(String[] args) {
        Main app = new Main();
        AppSettings settings = new AppSettings(true);
        settings.setTitle("3D Game - Player Movement");
        settings.setResolution(1280, 720);
        app.setSettings(settings);
        app.start();
    }

    @Override
    public void simpleInitApp() {
        flyCam.setMoveSpeed(15);

        // Player cube
        Box box = new Box(1, 1, 1);
        player = new Geometry("Player", box);

        Material mat = new Material(assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);

        player.setMaterial(mat);
        rootNode.attachChild(player);

        initKeys();
        initLight();
        Box enemyBox = new Box(1, 1, 1);
        enemy = new Geometry("Enemy", enemyBox);
        Material enemyMat = new Material(assetManager,
        "Common/MatDefs/Misc/Unshaded.j3md");
        enemyMat.setColor("Color", ColorRGBA.Red);
        enemy.setMaterial(enemyMat);
        enemy.setLocalTranslation(5, 0, 0);
        rootNode.attachChild(enemy);

    }

    private void initKeys() {
        inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("Forward", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("Backward", new KeyTrigger(KeyInput.KEY_S));

        inputManager.addListener(actionListener,
                "Left", "Right", "Forward", "Backward");
    }

    private final ActionListener actionListener = (name, isPressed, tpf) -> {
        switch (name) {
            case "Left" -> left = isPressed;
            case "Right" -> right = isPressed;
            case "Forward" -> forward = isPressed;
            case "Backward" -> backward = isPressed;
        }
    };

    @Override
    public void simpleUpdate(float tpf) {
        Vector3f dir = new Vector3f();
        if (left) dir.addLocal(-1, 0, 0);
        if (right) dir.addLocal(1, 0, 0);
        if (forward) dir.addLocal(0, 0, -1);
        if (backward) dir.addLocal(0, 0, 1);

        player.move(dir.mult(tpf * 5));
        Vector3f direction = player.getLocalTranslation()
        .subtract(enemy.getLocalTranslation())
        .normalize();

enemy.move(direction.mult(tpf * enemySpeed));

    }

    private void initLight() {
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-1, -2, -1));
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);
    }
}
