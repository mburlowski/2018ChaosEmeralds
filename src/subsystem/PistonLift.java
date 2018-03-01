package subsystem;

import constants.Const;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class PistonLift {
	public boolean lifted=false;
	DoubleSolenoid lift=new DoubleSolenoid(Const.firstLiftdown,Const.firstLiftUP);
	
	public void liftUp() {
		lift.set(DoubleSolenoid.Value.kForward);
		lifted=true;
	}
	public void liftDown() {
		lift.set(DoubleSolenoid.Value.kReverse);
		lifted=false;
	}
}