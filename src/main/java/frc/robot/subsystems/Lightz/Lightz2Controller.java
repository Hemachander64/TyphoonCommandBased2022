package frc.robot.subsystems.Lightz;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.*;

public class Lightz2Controller extends SubsystemBase
{
	Lightz2 lightz = new Lightz2();

	Drivetrain dt;

	public Lightz2Controller(Drivetrain dt)
	{
		this.dt = dt;

		register();
	}

	/**
	 * Updates robot status to control LEDs.
	 * @param tryingToShoot the shooter button
	 * @param tryingToAim the aiming button
	 */
	public void updateMode(boolean tryingToAim, boolean tryingToShoot)
	{		
		//if (atPressure)
		//	lightz.mode = Lightz2.Mode.kGreen;
		//else if (isCompressing())
		//	lightz.mode = Lightz2.Mode.kRed;
		//else
		//	lightz.mode = Lightz2.Mode.kBlue;

	}

	public void disabled()
	{
		lightz.mode = Lightz2.Mode.kPlano;
	}
}
