package subsystem;

import constants.Const;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class carriage {

	public boolean out;
	public boolean mid;
	public boolean in;
	DoubleSolenoid Carriage1 =new DoubleSolenoid(Const.carriage1Out,Const.carriage1In);
	
	public void Out() {
		out=true;
		mid=false;
		in=false;
		Carriage1.set(DoubleSolenoid.Value.kForward);
		
	}
	public void In() {
		out=false;
		mid=false;
		in=true;
		Carriage1.set(DoubleSolenoid.Value.kReverse);
	
	}
	public void middle() {
		out=false;
		mid=true;
		in=false;
		Carriage1.set(DoubleSolenoid.Value.kReverse);
		
	}

}
