package subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import constants.Const;
import edu.wpi.first.wpilibj.DoubleSolenoid;


public class arms{
	DoubleSolenoid pistonArm, pistonLU, pistonRU;
	public boolean release;
	public TalonSRX intakeLeftArm, intakeRightArm;

	public arms() {
		pistonArm = new DoubleSolenoid(Const.armPistonForward, Const.armLRev);
		intakeLeftArm = new TalonSRX(Const.armLIntake);
		intakeLeftArm.setInverted(true);
		intakeRightArm = new TalonSRX(Const.armRIntake);
	}

	/** Closes arms */
	public void grab() {
		pistonArm.set(DoubleSolenoid.Value.kForward);
		release = false;
	}
	/**Closes one side; true=right*/
	
	
	/** Opens arms */
	public void release() {
		pistonArm.set(DoubleSolenoid.Value.kReverse);
		release = true;
	}
	

	/** Makes intake take in */
	public void armSpeed(double spped) {
		intakeLeftArm.set(ControlMode.PercentOutput, spped);
		intakeRightArm.set(ControlMode.PercentOutput, spped);
	}

	/** Makes intake push out */
	

	/** Runs only the left intake motor */
	public void runL(double spped) {
		intakeLeftArm.set(ControlMode.PercentOutput, spped);
	}

	/** Runs only the right intake motor */
	public void runR(double spped) {
		intakeRightArm.set(ControlMode.PercentOutput, spped);
	}

	/** Stops all intake motors */
	public void stop() {
		intakeLeftArm.set(ControlMode.PercentOutput, 0);
		intakeRightArm.set(ControlMode.PercentOutput, 0);
	}

	
}
