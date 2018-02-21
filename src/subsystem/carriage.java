package subsystem;

import constants.Const;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class carriage {

	public boolean out;
	public boolean mid;
	public boolean in;
	DoubleSolenoid Carriage1 =new DoubleSolenoid(Const.carriage1Out,Const.carriage1In);
	DoubleSolenoid Carriage2 =new DoubleSolenoid(Const.carriage2Out,Const.carriage2In);
	public void Out() {
<<<<<<< HEAD
		out=true;
		mid=false;
=======
	
>>>>>>> parent of 1c3930b... more!!!
		in=false;
		Carriage1.set(DoubleSolenoid.Value.kForward);
		Carriage2.set(DoubleSolenoid.Value.kForward);
	}
	public void In() {
<<<<<<< HEAD
		out=false;
		mid=false;
=======
	
>>>>>>> parent of 1c3930b... more!!!
		in=true;
		Carriage1.set(DoubleSolenoid.Value.kReverse);
		Carriage2.set(DoubleSolenoid.Value.kReverse);
	}
	public void middle() {
		out=false;
		mid=true;
		in=false;
		Carriage1.set(DoubleSolenoid.Value.kReverse);
		Carriage2.set(DoubleSolenoid.Value.kForward);
	}

}
