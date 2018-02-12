package subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import constants.Const;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import objects.Action;

public class arms extends Action {
	DoubleSolenoid pistonLF, pistonRF, pistonLU, pistonRU;
	Solenoid pistonLFA, pistonRFA, pistonLUA, pistonRUA;
	public TalonSRX intakeL, intakeR;

	public arms() {
		pistonLF = new DoubleSolenoid(Const.armLFwd, Const.armLRev);
		pistonRF = new DoubleSolenoid(Const.armRFwd, Const.armRRev);
		pistonLU = new DoubleSolenoid(Const.armLUpFwd, Const.armLUpRev);
		pistonRU = new DoubleSolenoid(Const.armRUpFwd, Const.armRUpRev);
		pistonLFA = new Solenoid(Const.armL);
		pistonRFA = new Solenoid(Const.armR);
		intakeL = new TalonSRX(Const.armLIntake);
		intakeR = new TalonSRX(Const.armRIntake);
	}

	/** Closes arms */
	public void grab() {
		pistonLF.set(DoubleSolenoid.Value.kForward);
		pistonRF.set(DoubleSolenoid.Value.kForward);
	}

	/** Opens arms */
	public void release() {
		pistonLF.set(DoubleSolenoid.Value.kReverse);
		pistonRF.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void rise() {
		
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

	/** First is left-forward(0), right-forward(1), left-up(2), right-up(3); second is off(0) rev(1) or fwd(2) */
	public void runPiston(int which, int state) {
		
		switch (state) {
		case 0:
			switch(which) {
			case 0:
				pistonLF.set(DoubleSolenoid.Value.kOff);
				break;
			case 1:
				pistonRF.set(DoubleSolenoid.Value.kOff);
				break;
			case 2:
				pistonLU.set(DoubleSolenoid.Value.kOff);
				break;
			case 3:
				pistonRU.set(DoubleSolenoid.Value.kOff);
				break;
			}
		case 1:
			switch(which) {
			case 0:
				pistonLF.set(DoubleSolenoid.Value.kReverse);
				break;
			case 1:
				pistonRF.set(DoubleSolenoid.Value.kReverse);
				break;
			case 2:
				pistonLU.set(DoubleSolenoid.Value.kReverse);
				break;
			case 3:
				pistonRU.set(DoubleSolenoid.Value.kReverse);
				break;
			}
		case 2:
			switch(which) {
			case 0:
				pistonLF.set(DoubleSolenoid.Value.kForward);
				break;
			case 1:
				pistonRF.set(DoubleSolenoid.Value.kForward);
				break;
			case 2:
				pistonLU.set(DoubleSolenoid.Value.kForward);
				break;
			case 3:
				pistonRU.set(DoubleSolenoid.Value.kForward);
				break;
			}
		}

	}

	/** First is left-forward(0), right-forward(1), left-up(2), right-up(3); second is off(f) or on(t) */
	public void runPiston(int which, boolean state) {
		switch (which) {
		case 0:
			pistonLFA.set(state);
			break;
		case 1:
			pistonRFA.set(state);
			break;
		case 2:
			pistonLUA.set(state);
			break;
		case 3:
			pistonRUA.set(state);
			break;
	}
	}
}
