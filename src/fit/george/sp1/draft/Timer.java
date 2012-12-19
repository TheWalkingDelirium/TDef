package fit.george.sp1.draft;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;

public class Timer extends TimerHandler {
 
	private boolean mPause = true;
	  
	public Timer(float pTimerSeconds, boolean pAutoReset, ITimerCallback pTimerCallback) {
		super(pTimerSeconds, pAutoReset, pTimerCallback);
	}
	  
	
	public boolean isPause() {
		return mPause;
	}
	
	 
	public void pause() {
		this.mPause = true;
	}
	 
	  
	public void resume() {
		this.mPause = false;
	}
	
	
	 
	@Override
	public void onUpdate(float pSecondsElapsed) {
		 if(!this.mPause) {
			 super.onUpdate(pSecondsElapsed);
		 }
	}
	 
	 
}


