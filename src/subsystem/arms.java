package subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import constants.Const;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Relay.Value;
import objects.Action;

public class arms extends Action {
	DoubleSolenoid pistonL, pistonR;
	Solenoid pistonLA, pistonRA;
	public TalonSRX intakeL, intakeR;

	public arms() {
		pistonL = new DoubleSolenoid(Const.armLFwd, Const.armLRev);
		pistonR = new DoubleSolenoid(Const.armRFwd, Const.armRRev);
		pistonLA = new Solenoid(Const.armLFwd);
		pistonRA = new Solenoid(Const.armRFwd);
		intakeL = new TalonSRX(Const.armLIntake);
		intakeR = new TalonSRX(Const.armRIntake);
	}

	/** Closes arms */
	public void grab() {
		pistonL.set(DoubleSolenoid.Value.kForward);
		pistonR.set(DoubleSolenoid.Value.kForward);
	}

	/** Opens arms */
	public void release() {
		pistonL.set(DoubleSolenoid.Value.kReverse);
		pistonR.set(DoubleSolenoid.Value.kReverse);
	}

	/** Makes intake take in */
	public void takeIn(double spped) {
		intakeL.set(ControlMode.PercentOutput, spped);
		intakeR.set(ControlMode.PercentOutput, spped);
	}

	/** Makes intake push out */
	public void takeOut(double spped) {
		intakeL.set(ControlMode.PercentOutput, -spped);
		intakeR.set(ControlMode.PercentOutput, -spped);
	}

	/** Runs only the left intake motor */
	public void runL(double spped) {
		intakeL.set(ControlMode.PercentOutput, spped);
	}

	/** Runs only the right intake motor */
	public void runR(double spped) {
		intakeR.set(ControlMode.PercentOutput, spped);
	}

	/** Stops all intake motors */
	public void stop() {
		intakeL.set(ControlMode.PercentOutput, 0);
		intakeR.set(ControlMode.PercentOutput, 0);
	}

	/** First is left(f) or right(t), second is off(0) rev(1) or fwd(2) */
	public void runPiston(boolean dir, int state) {
		switch (state) {
		case 0:
			if (dir)
				pistonR.set(DoubleSolenoid.Value.kOff);
			else
				pistonL.set(DoubleSolenoid.Value.kOff);
		case 1:
			if (dir)
				pistonR.set(DoubleSolenoid.Value.kReverse);
			else
				pistonL.set(DoubleSolenoid.Value.kReverse);
		case 2:
			if (dir)
				pistonR.set(DoubleSolenoid.Value.kForward);
			else
				pistonL.set(DoubleSolenoid.Value.kForward);
		}

	}

	/** First is left(f) or right(t), second is off(f) or on(t) */
	public void runPiston(boolean dir, boolean state) {
		if (dir)
			pistonRA.set(state);
		else
			pistonLA.set(state);
	}
}
