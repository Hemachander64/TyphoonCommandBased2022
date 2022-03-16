package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commands.drive.*;
import frc.robot.commands.shoot.*;
import frc.robot.subsystems.*;

public class LowBallAuto extends SequentialCommandGroup
{
	public LowBallAuto(Shooter shooter, Hood hood, Feeder feeder, Intake intake, Drivetrain dt)
	{
		addCommands
		(
			new ShootUpperHub(shooter, hood, feeder),
			new DTDProfiled(1.658, dt)
	//		new RunCommand(intake::on, intake).withTimeout(2),
		//	new InstantCommand(intake::off, intake),
            // new DTDProfiled(-1.658, dt)
		//	new ShootUpperHub(shooter, hood, feeder),
		//	new RunCommand(feeder::on, feeder).withTimeout(2)
		);
	}
}
