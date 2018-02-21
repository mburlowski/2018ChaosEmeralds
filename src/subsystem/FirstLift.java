package subsystem;

import constants.Const;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class FirstLift {
	public long time=0;
	public boolean lifted=false;
	DoubleSolenoid lift=new DoubleSolenoid(Const.firstLiftdown,Const.firstLiftUP);
	
	public void liftUp() {
		time=System.currentTimeMillis();
		lift.set(DoubleSolenoid.Value.kForward);
		lifted=true;
	}
	public void liftDown() {
		time=System.currentTimeMillis();
		lift.set(DoubleSolenoid.Value.kReverse);
		lifted=false;
	}
}