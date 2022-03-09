package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commands.drive.*;
import frc.robot.commands.shoot.*;
import frc.robot.subsystems.*;

public class TopBallAuto extends SequentialCommandGroup
{
	public TopBallAuto(Limelight ll, Shooter shooter, Hood hood, Feeder feeder, Intake intake, Drivetrain dt)
	{
		addCommands
		(
			new DTDProfiled(2.274, dt),
			new RunCommand(intake::on, intake).withTimeout(2),
			new InstantCommand(intake::off, intake),
            // new DTDProfiled(-2.274, dt),
			new ShootUpperHub(ll, shooter, hood, feeder, dt),
			new RunCommand(feeder::on, feeder).withTimeout(2)
		);
	}
}
