package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drive.DTDProfiled;
import frc.robot.commands.drive.TTAProfiled;
import frc.robot.subsystems.Drivetrain;

public class TopBallAuto extends SequentialCommandGroup
{
	public TopBallAuto(Drivetrain dt)
	{
		addCommands
		(
			new DTDProfiled(2.274, dt),
            new DTDProfiled(-2.274, dt)
		);
	}
}
