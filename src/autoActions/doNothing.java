package autoActions;

import objects.Action;

public class doNothing extends Action {
	int timeout;
	public doNothing(int time) {
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
