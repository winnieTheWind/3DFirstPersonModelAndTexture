package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.UBJsonReader;

public class LocalAssetManager {

    //AssetManager : this is where we initialize all the assets (textures, fonts, models etc)
    //asset files with file locations

    //Textures
    private static final String BADLOGICSPLASH = "textures/badlogic.jpg";
    private static final String BUNNYTEXTURE = "textures/textureGrass.png";
    //Models
    private static final String BUNNYMODEL = "models/bunny.obj";
    //Shaders


    public AssetManager assets;

    //init texture, models, shaders
    public Model bunny;
    public Texture badlogicSplash;
    public Texture bunnyTexture;

    public void create() {

        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        assets = new AssetManager();
        assets.getLogger().setLevel(Logger.DEBUG);

        //load assets
        assets.load(BADLOGICSPLASH, Texture.class);
        assets.load(BUNNYTEXTURE, Texture.class);
        assets.load(BUNNYMODEL, Model.class);
        //blocks until all resources are loaded into memory
        assets.finishLoading();

        //get assets
        badlogicSplash = assets.get(BADLOGICSPLASH);
        bunny = assets.get(BUNNYMODEL);
        bunnyTexture = assets.get(BUNNYTEXTURE);

        //bindings


        //file location and texture combined

    }

    public void dispose() {

        assets.dispose();
    }

}
