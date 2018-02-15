package subsystem;

import constants.Const;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class carriage {
	DoubleSolenoid Carriage1 =new DoubleSolenoid(Const.carriage1Out,Const.carriage1In);
	DoubleSolenoid Carriage2 =new DoubleSolenoid(Const.carriage2Out,Const.carriage2In);
	public void Out() {
		Carriage1.set(DoubleSolenoid.Value.kForward);
		Carriage2.set(DoubleSolenoid.Value.kForward);
	}
	public void In() {
		Carriage1.set(DoubleSolenoid.Value.kReverse);
		Carriage2.set(DoubleSolenoid.Value.kReverse);
	}

}
