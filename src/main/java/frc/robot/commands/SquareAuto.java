package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drive.DTDProfiled;
import frc.robot.commands.drive.TTAProfiled;
import frc.robot.subsystems.Drivetrain;

public class SquareAuto extends SequentialCommandGroup
{
	public SquareAuto(Drivetrain dt)
	{
		addCommands
		(
			new DTDProfiled(1, dt),
			new TTAProfiled(90, dt),
			new DTDProfiled(1, dt),
			new TTAProfiled(90, dt),
			new DTDProfiled(1, dt),
			new TTAProfiled(90, dt),
			new DTDProfiled(1, dt),
			new TTAProfiled(90, dt)
		);
	}
}
