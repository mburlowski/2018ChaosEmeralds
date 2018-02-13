package autoActions;

import org.usfirst.frc.team2472.robot.Robot;

import objects.Action;

public class pathFollower extends Action {
	double[] left,right;
	int length;
	double step;
	public pathFollower(double[] leftSide,double[] rightSide,int totalLength) {
		left=leftSide;
		right=rightSide;
		length=totalLength;
	}
	public void startAction() {
		
	}
	public void periodic() {
		if(length>=(int)(step)) {
			
		}
	}
	public void endAction() {
		
	}
	public boolean isFinished() {
		return true;
	}
}
