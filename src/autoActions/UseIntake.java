package autoActions;

import org.usfirst.frc.team2472.robot.Robot;

public class UseIntake extends Action {
	double armSpeed;
	int timeout;
	/**
	 * Give Speed and time for autonomous
	 * @param speed
	 * @param time
	 */
	public UseIntake(double speed,int time) {
		armSpeed=speed;
		timeout=time;
	}
	public void startAction() {
		super.startAction();
	}
	public void periodic() {
		Robot.a.armSpeed(armSpeed);
	}
	public void endAction() {
		
	}
public boolean isFinished(){
		
		if(isTimedOut()){
			
			endAction();
			
			return true;
			
		}
		
		else{
		
			return false;
			
		}
		
	}
}
