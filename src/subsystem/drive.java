package subsystem;

import objects.Action;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import constants.Const;
import edu.wpi.first.wpilibj.Joystick;

public class drive extends Action {
	TalonSRX l1, l2, r1, r2;
	/**requires ports for front left motor, front right motor, back left motor, back right motor*/
	public drive(int flm, int frm, int blm, int brm) {
		l1 = new TalonSRX(blm);
		l2 = new TalonSRX(flm);
		r1 = new TalonSRX(brm);
		r2 = new TalonSRX(frm);
		r1.setInverted(true);
		r2.setInverted(true);
		//setInverted causes positive numbers to move the robot forward
	}
	/**Runs a side of the drivetrain forward at desired speed*/
	public void runMotor(int motor, double spped) {
		if (motor == Const.motorBL || motor == Const.motorFL) {
			l1.set(ControlMode.PercentOutput, spped);
			l2.set(ControlMode.PercentOutput,spped);
		} else if (motor == Const.motorBR || motor == Const.motorFR) {
			r1.set(ControlMode.PercentOutput,spped);
			r2.set(ControlMode.PercentOutput,spped);
		}
	}
	/**Runs a side of the drivetrain backwards at desired speed.*/
	public void runMotorReverse(int motor, double spped) {
		if (motor == Const.motorBL || motor == Const.motorFL) {
			l1.set(ControlMode.PercentOutput,spped);
			l2.set(ControlMode.PercentOutput,spped);
		} else if (motor == Const.motorBR || motor == Const.motorFR) {
			r1.set(ControlMode.PercentOutput,spped);
			r2.set(ControlMode.PercentOutput,spped);
		}
	}
	/**Stops all motors*/
	public void stopMotors() {
		l1.set(ControlMode.PercentOutput,0.0);
		l2.set(ControlMode.PercentOutput,0.0);
		r1.set(ControlMode.PercentOutput,0.0);
		r2.set(ControlMode.PercentOutput,0.0);
	}
	/**Moves the robot based on joystick movement*/
	public void tankDrive(Joystick ljoy, Joystick rjoy) {
		l1.set(ControlMode.PercentOutput,-ljoy.getY());
		l2.set(ControlMode.PercentOutput,-ljoy.getY());
		r1.set(ControlMode.PercentOutput,-rjoy.getY());
		r2.set(ControlMode.PercentOutput,-rjoy.getY());
	}
	/**Moves the robot at desired power*/
	public void setAllMotors(double spped) {

		l1.set(ControlMode.PercentOutput,spped);
		l2.set(ControlMode.PercentOutput,spped);
		r1.set(ControlMode.PercentOutput,spped);
		r2.set(ControlMode.PercentOutput,spped);

	}
	/**Runs both sides of the drivetrain at desired power individually*/
	public void turn(double Lspeed, double Rspeed) {

		l1.set(ControlMode.PercentOutput,Lspeed);
		l2.set(ControlMode.PercentOutput,Lspeed);
		r1.set(ControlMode.PercentOutput,Rspeed);
		r2.set(ControlMode.PercentOutput,Rspeed);

	}
	/**Causes the robot to spin by running both sides of the drive train in opposition*/
	public void spin() {

		l1.set(ControlMode.PercentOutput,-1.0);
		l2.set(ControlMode.PercentOutput,-1.0);
		r1.set(ControlMode.PercentOutput,1.0);
		r2.set(ControlMode.PercentOutput,1.0);

	}
	/**Runs front right motor at full power*/
	public void runFR() {
		r2.set(ControlMode.PercentOutput,1.0);
		
	}
	/**Runs back right motor at full power*/
	public void runBR() {
		r1.set(ControlMode.PercentOutput,1.0);

	}
	/**Runs front left motor at full power*/ 
	public void runFL() {
		l2.set(ControlMode.PercentOutput,1.0);

	}
	/**Runs back left motor at full power*/
	public void runBL() {
		l1.set(ControlMode.PercentOutput,1.0);

	}
}
