package fit.george.sp1.draft;

import java.io.IOException;
import java.io.InputStream;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
//import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.background.RepeatingSpriteBackground;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.AssetBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.util.GLState;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.andengine.util.debug.Debug;






import android.graphics.Color;
import android.graphics.Typeface;
import android.view.KeyEvent;

public class MainActivity extends BaseGameActivity
{
	public static MainActivity instance;
	public Font mFont;
	public static Font mFont_Game;
	
	private BuildableBitmapTextureAtlas mBitmapTextureAtlas;
	public RepeatingSpriteBackground mGrassBackground;
	public TiledTextureRegion dragon;
	public TiledTextureRegion octopus;
	public TiledTextureRegion npc;
	
	public ITexture mTextureRoad, mTextureCastle, mTextureTree1, mTextureTree2, mTextureTree3, mTextureGen ;
	public ITexture mTextureCool, mTextureWhy, mTexturePoker, mTextureTroll, mTextureBoss ;
	public ITextureRegion mTextureRegionCool, mTextureRegionWhy, mTextureRegionPoker, mTextureRegionTroll, mTextureRegionBoss;
	public ITextureRegion mTextureRegionRoad, mTextureRegionCastle, mTextureRegionTree1, mTextureRegionTree3, mTextureRegionTree2, mTextureRegionGen ;
	
	
	
	////////////////////////////////////////////////////
	final static int CAMERA_WIDTH = 800;
	final static int CAMERA_HEIGHT = 480;
//	final VertexBufferObjectManager vertexBufferObjectManager = this.getVertexBufferObjectManager();
	public static BoundCamera mBoundChaseCamera;
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
    
    public TiledTextureRegion creepLevel1Texture;
	public TiledTextureRegion creepLevel2Texture;
	private Object mFaceTextureRegion;

	
	@Override
	public EngineOptions onCreateEngineOptions() // creating standard engine options
	{
		instance = this;
		
		////////////////////
		//camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		this.mBoundChaseCamera = new BoundCamera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT, 0, CAMERA_HEIGHT*2, 0, CAMERA_WIDTH*2);
		EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), mBoundChaseCamera);
		return engineOptions;
	}

	@Override
	public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws Exception // loading splash screen while loading
	{
		this.mFont = FontFactory.create(this.getFontManager(), this.getTextureManager(), 256, 256, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32, Color.WHITE);
		this.mFont.load();
		mBoundChaseCamera.setBoundsEnabled(true);
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

		this.mBitmapTextureAtlas = new BuildableBitmapTextureAtlas(this.getTextureManager(), 512, 256, TextureOptions.NEAREST);

		this.mGrassBackground = new RepeatingSpriteBackground(800, 480, 
				this.getTextureManager(), AssetBitmapTextureAtlasSource.create(this.getAssets(), 
				"gfx/background_grass.png"), this.getVertexBufferObjectManager());
		
		this.dragon = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "dragon.png", 2, 2);
		this.octopus = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "all.png", 2, 2);
		this.npc    = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "player.png", 3, 4);
		this.creepLevel1Texture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "creepLevel1.png", 3, 4);
		this.creepLevel2Texture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "creepLevel2.png", 3, 4);
		try {
			this.mBitmapTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 1));
			this.mBitmapTextureAtlas.load();
		} catch (TextureAtlasBuilderException e) {
			Debug.e(e);
		}
		
		loadTextures();	
		
		/////////////////////////////////////////////
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
		
		
		mFont_Game = FontFactory.createFromAsset(this.getFontManager(), this.getTextureManager(), 512, 512, TextureOptions.BILINEAR, this.getAssets(), "Plok.ttf", 60, true, Color.WHITE);
		mFont_Game.load();	
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
	
	
	
	
	
	private void loadTextures() {
		try {
			this.mTextureRoad = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
				@Override
				public InputStream open() throws IOException {
					return getAssets().open("gfx/road.png");
				}
			});
			this.mTextureCastle = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
				@Override
				public InputStream open() throws IOException {
					return getAssets().open("gfx/castle.png");
				}
			});
			this.mTextureTree1 = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
				@Override
				public InputStream open() throws IOException {
					return getAssets().open("gfx/tree1.png");
				}
			});
			this.mTextureTree2 = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
				@Override
				public InputStream open() throws IOException {
					return getAssets().open("gfx/tree2.png");
				}
			});
			this.mTextureTree3 = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
				@Override
				public InputStream open() throws IOException {
					return getAssets().open("gfx/tree3.png");
				}
			});
			this.mTextureGen = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
				@Override
				public InputStream open() throws IOException {
					return getAssets().open("gfx/creep_gen.png");
				}
			});
			this.mTexturePoker = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
				@Override
				public InputStream open() throws IOException {
					return getAssets().open("gfx/poker.png");
				}
			});
			this.mTextureBoss = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
				@Override
				public InputStream open() throws IOException {
					return getAssets().open("gfx/boss.png");
				}
			});
			this.mTextureWhy = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
				@Override
				public InputStream open() throws IOException {
					return getAssets().open("gfx/why.png");
				}
			});
			this.mTextureTroll = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
				@Override
				public InputStream open() throws IOException {
					return getAssets().open("gfx/troll.png");
				}
			});
			this.mTextureCool = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
				@Override
				public InputStream open() throws IOException {
					return getAssets().open("gfx/cool.png");
				}
			});

			this.mTextureRoad.load();
			this.mTextureCastle.load();
			this.mTextureTree1.load();
			this.mTextureTree2.load();
			this.mTextureTree3.load();
			this.mTextureGen.load();
			this.mTexturePoker.load();
			this.mTextureWhy.load();
			this.mTextureBoss.load();
			this.mTextureCool.load();
			this.mTextureTroll.load();
			
			this.mTextureRegionRoad = TextureRegionFactory.extractFromTexture(this.mTextureRoad);			
			this.mTextureRegionCastle = TextureRegionFactory.extractFromTexture(this.mTextureCastle);
			this.mTextureRegionTree1 = TextureRegionFactory.extractFromTexture(this.mTextureTree1);
			this.mTextureRegionTree2 = TextureRegionFactory.extractFromTexture(this.mTextureTree2);
			this.mTextureRegionTree3 = TextureRegionFactory.extractFromTexture(this.mTextureTree3);
			this.mTextureRegionGen = TextureRegionFactory.extractFromTexture(this.mTextureGen);
			this.mTextureRegionPoker = TextureRegionFactory.extractFromTexture(this.mTexturePoker);
			this.mTextureRegionWhy = TextureRegionFactory.extractFromTexture(this.mTextureWhy);
			this.mTextureRegionBoss = TextureRegionFactory.extractFromTexture(this.mTextureBoss);
			this.mTextureRegionCool = TextureRegionFactory.extractFromTexture(this.mTextureCool);
			this.mTextureRegionTroll = TextureRegionFactory.extractFromTexture(this.mTextureTroll);
			
		} catch (IOException e) {
			Debug.e(e);
		}
	}
	
	
	


}
