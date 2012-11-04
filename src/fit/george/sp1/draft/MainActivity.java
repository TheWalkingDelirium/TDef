package fit.george.sp1.draft;

import java.io.IOException;
import java.io.InputStream;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
//import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.opengl.util.GLState;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.andengine.util.debug.Debug;




import android.graphics.Color;
import android.view.KeyEvent;

public class MainActivity extends BaseGameActivity
{
	final static int CAMERA_WIDTH = 800;
	final static int CAMERA_HEIGHT = 480;
//	final VertexBufferObjectManager vertexBufferObjectManager = this.getVertexBufferObjectManager();
	public static Camera camera;
	private Scene splashScene;
	public  mainState _MainState;
	public static Font mUbuntuFont;
	public static Font mUbuntuLFont;
	private boolean _GameLoaded = false;
	public static Engine _Engine;
	
    private BitmapTextureAtlas splashTextureAtlas;
    private ITextureRegion splashTextureRegion;
    public BitmapTextureAtlas menuTextureAtlas;
    public static ITextureRegion menu_aboutTextureRegion;
    public static ITextureRegion menu_logoTextureRegion;
    private Sprite splash;
    public static Sprite tower;
    public static Sprite creep;

	
	@Override
	public EngineOptions onCreateEngineOptions() // creating standard engine options
	{
		camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), camera);
		return engineOptions;
	}

	@Override
	public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws Exception // loading splash screen while loading
	{
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		FontFactory.setAssetBasePath("fonts/");
        splashTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
        splashTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(splashTextureAtlas, this, "splash.png", 0, 0);
        splashTextureAtlas.load();

        pOnCreateResourcesCallback.onCreateResourcesFinished();
	}

	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws Exception
	{
		initSplashScene();
        pOnCreateSceneCallback.onCreateSceneFinished(this.splashScene);
	}

	@Override
	public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception
	{
		mEngine.registerUpdateHandler(new TimerHandler(1f, new ITimerCallback() 
		{
            public void onTimePassed(final TimerHandler pTimerHandler) 
            {
                mEngine.unregisterUpdateHandler(pTimerHandler);
                loadResources();
                loadScenes();         
                splash.detachSelf();
                mEngine.setScene(_MainState); // starting the game --> mainState.java
                //currentScene = SceneType.MENU;
            }
		}));
  
		pOnPopulateSceneCallback.onPopulateSceneFinished();
	}
	
	public void loadResources() 
	{
		// Load your game resources here!
		
		menuTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(), 1024, 512, TextureOptions.BILINEAR);
        menu_logoTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(splashTextureAtlas, this, "logo_tower.png", 0, 0);
        menu_aboutTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(splashTextureAtlas, this, "creep.png", 201, 0);
        menuTextureAtlas.load();
        
        tower = new Sprite(0, 0, MainActivity.menu_logoTextureRegion, this.getVertexBufferObjectManager());
        creep = new Sprite(0, 0, MainActivity.menu_aboutTextureRegion, this.getVertexBufferObjectManager());
    	
        //tower.setScale(1.5f);
    	//tower.setPosition((MainActivity.CAMERA_WIDTH - tower.getWidth()), (MainActivity.CAMERA_HEIGHT - tower.getHeight()));
    	
        //Загружаю шрифт Ubuntu размером 22 и 48 пикселей
		final ITexture ubuntuFontTexture = new BitmapTextureAtlas(this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
		final ITexture ubuntuLFontTexture = new BitmapTextureAtlas(this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
		FontFactory.setAssetBasePath("fonts/");
		MainActivity.mUbuntuFont = FontFactory.createFromAsset(this.getFontManager(), ubuntuFontTexture, this.getAssets(), "Ubuntu-R.ttf", 22, true, Color.WHITE);
		MainActivity.mUbuntuFont.load();
		MainActivity.mUbuntuLFont = FontFactory.createFromAsset(this.getFontManager(), ubuntuLFontTexture, this.getAssets(), "Ubuntu-B.ttf", 48, true, Color.WHITE);
		MainActivity.mUbuntuLFont.load();		
		}
	
	private mainState loadScenes()
	{
		_MainState = new mainState();
		_GameLoaded = true;
		return _MainState;
	}
	
	// ===========================================================
	// INITIALIZIE  
	// ===========================================================
	
	private void initSplashScene()
	{
//		
//		final VertexBufferObjectManager vertexBufferObjectManager = this.getVertexBufferObjectManager();
    	
		splashScene = new Scene();
    	splashScene.setBackground(new Background(25/255f, 25/255f, 25/255f));
    	splash = new Sprite(0, 0, splashTextureRegion, this.getVertexBufferObjectManager())
    	{
    		@Override
            protected void preDraw(GLState pGLState, Camera pCamera) 
    		{
                super.preDraw(pGLState, pCamera);
                pGLState.enableDither();
            }
    	};
    	
    	//splash.setScale(1.5f);
    	splash.setPosition((CAMERA_WIDTH - splash.getWidth()) * 0.5f, (CAMERA_HEIGHT - splash.getHeight()) * 0.5f);
    	splashScene.attachChild(splash);
    	//splashScene.attachChild(new Text((CAMERA_WIDTH - splash.getWidth()) * 0.5f, (CAMERA_HEIGHT - splash.getHeight()) * 0.5f, this.mUbuntuFont, "Droid Font", vertexBufferObjectManager));
        //splashScene.attachChild(new Text((CAMERA_WIDTH - splash.getWidth()) * 0.5f, (CAMERA_HEIGHT - splash.getHeight()) * 0.5f, MainActivity.mUbuntuFont, "Ubuntu Font", vertexBufferObjectManager));
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) { // BACK_KEY_PRESS processing
		
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			if (!_GameLoaded ) return true;
			if (_MainState != null && _GameLoaded) {
				_MainState.onKeyDownPressed(keyCode, event);
				return true;
			}
			return true;
		}
	    return super.onKeyDown(keyCode, event);
	}
	
	
	


}
