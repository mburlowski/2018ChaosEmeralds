package autoActions;

import objects.Action;

public class lift extends Action {
	boolean lift;
	int timeout;
	public lift(int time) {
		timeout=time;
	}
	public void startAction() {
		
	}
	public void periodic() {
		
	}
	public void endAction() {
		
	}
	public boolean isFinished() {
if(isTimedOut()){
			
			endAction();
			
			return true;
			
		}
		
		else{
		
			return false;
			
		}
		
	}
	}

