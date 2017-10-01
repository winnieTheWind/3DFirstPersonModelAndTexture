package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.DefaultTextureBinder;
import com.badlogic.gdx.graphics.g3d.utils.FirstPersonCameraController;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;

public class Main extends ApplicationAdapter {

    //main asset manager
    LocalAssetManager localAssetManager;

    //batches
	SpriteBatch batch;
    ModelBatch modelBatch;

    //instances
    ModelInstance bunnyInstance;

    //camera stuff
	PerspectiveCamera camera;
	FirstPersonCameraController cameraController;

	//enviroment(shaders)
    Environment env;
    TestShader shader;
    RenderContext renderContext;

	@Override
	public void create () {

        localAssetManager = new LocalAssetManager();
        localAssetManager.create();

        //camera stuff
        camera = new PerspectiveCamera(75, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cameraController = new FirstPersonCameraController(camera);
        Gdx.input.setInputProcessor(cameraController);
        camera.position.set(0f,0f,0f);
        camera.lookAt(0f,0f,0f);
        camera.near = 0.1f;
        camera.far = 300.0f;

        //batches
        modelBatch = new ModelBatch();
        batch = new SpriteBatch();

        //instances
        bunnyInstance = new ModelInstance(localAssetManager.bunny, 0, -0.3f, -0.6f);
        bunnyInstance.transform.scale(3f, 3f, 3f);

        //shaders
        shader = new TestShader();
        shader.init();
	}

	@Override
	public void render () {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        cameraController.setVelocity(0.9f);
        cameraController.update();
        camera.update();

        localAssetManager.bunnyTexture.bind();
        localAssetManager.bunnyTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        modelBatch.begin(camera);
        modelBatch.render(bunnyInstance, shader);
        modelBatch.end();

        batch.begin();
        batch.draw(localAssetManager.badlogicSplash, 0, 0);
        batch.end();
	}
	
	@Override
	public void dispose () {
        modelBatch.dispose();
        batch.dispose();
        localAssetManager.dispose();
	}
}
