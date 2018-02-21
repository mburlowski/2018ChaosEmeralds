package autoActions;

import org.usfirst.frc.team2472.robot.Robot;

import objects.Action;

public class pathFollower extends Action {
	double[] left,right;
	int length;
	double step=0;
	double math=1024/15.7;
	public pathFollower(int time,double[] leftSide,double[] rightSide,int totalLength) {
		left=leftSide;
		timeout=time;
		right=rightSide;
		length=totalLength;
		
		
	}
	public void startAction() {
		super.startAction();
		step=0;
		
	}
	public void periodic() {
	
		step=step+.2;
		if(length>=(int)(step)) {
			
			Robot.d.speed(math*left[(int)step],math*right[(int)step]);
		}else {
			endAction();
		}
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
