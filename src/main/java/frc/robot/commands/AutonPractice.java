package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drive.DriveToDistanceCommand;
import frc.robot.commands.drive.TurnToAngleCommand;
import frc.robot.subsystems.Drivetrain;
public class AutonPractice extends SequentialCommandGroup
{
    public AutonPractice(Drivetrain dt)
    {
        addCommands
        (
            new DriveToDistanceCommand(2, dt),
            new TurnToAngleCommand(90, dt),
            new DriveToDistanceCommand(1.8, dt),
            new TurnToAngleCommand(-90, dt),
            new DriveToDistanceCommand(8, dt),
            new TurnToAngleCommand(90, dt),
            new DriveToDistanceCommand(5, dt),
            new TurnToAngleCommand(-30, dt),
            new DriveToDistanceCommand(0.5, dt)

        );
    }
}
