package subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import constants.Const;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Solenoid;
import objects.Action;

public class arms extends Action {
	Solenoid pistonL,pistonR;
	TalonSRX intakeL,intakeR;
	public arms() {
		pistonL = new Solenoid(Const.armLPiston);
		pistonR = new Solenoid(Const.armRPiston);
		intakeL = new TalonSRX(Const.armLIntake);
		intakeR = new TalonSRX(Const.armRIntake);
	}
	public void grab() {
		pistonL.set(true);
		pistonR.set(true);
	}
	public void release() {
		
	}
	public void takeIn(double spped) {
		
	}
	public void takeOut(double spped) {
		
	}
	public void runL(double spped) {
		intakeL.set(ControlMode.PercentOutput, spped);
	}
	public void runR(double spped) {
		intakeR.set(ControlMode.PercentOutput, spped);
	}
	public void stop() {
		intakeL.set(ControlMode.PercentOutput, 0);
		intakeL.set(ControlMode.PercentOutput, 0);
	}
}
