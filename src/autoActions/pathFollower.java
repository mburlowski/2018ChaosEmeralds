package autoActions;

import org.usfirst.frc.team2472.robot.Robot;

import objects.Action;

public class pathFollower extends Action {
	double[] left,right;
	int length;
	double step;
	double math=1024/15.7;
	public pathFollower(double[] leftSide,double[] rightSide,int totalLength) {
		left=leftSide;
		right=rightSide;
		length=totalLength;
		
	}
	public void startAction() {
		
	}
	public void periodic() {
		if(length>=(int)(step)) {
			step=step+.2;
			Robot.d.speed(math*left[(int)step],math*right[(int)step]);
		}else {
			endAction();
		}
	}
	public void endAction() {
		
	}
	public boolean isFinished() {
		return true;
	}
}
