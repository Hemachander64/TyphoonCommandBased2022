package frc.robot.commands;

import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drive.DriveToDistanceCommand;
import frc.robot.commands.drive.TurnToAngleCommand;
import frc.robot.subsystems.Drivetrain;

public class AutonomousCommand extends SequentialCommandGroup
{
    public AutonomousCommand(Drivetrain dt)
    {
        addCommands
        (
            new DriveToDistanceCommand(Units.feetToMeters(2), dt),
            new PrintCommand("step 1 done"),
            // new WaitCommand(1),
            // // Drives in a 1x1 meter square, by executing the following commands in sequence.
             new DriveToDistanceCommand(Units.feetToMeters(2), dt),
             new PrintCommand("step 2 done")
            // new TurnToAngleCommand(90, dt),
            // new DriveToDistanceCommand(1, dt),
            // new TurnToAngleCommand(90, dt),
            // new DriveToDistanceCommand(1, dt),
            // new TurnToAngleCommand(90, dt),
            // new DriveToDistanceCommand(1, dt),
            // new TurnToAngleCommand(90, dt)

            //  new DriveToDistanceCommand(1, dt),
            //   new TurnToAngleCommand(90, dt),
            //   new DriveToDistanceCommand(1, dt),
            //   new TurnToAngleCommand(90, dt),
            //   new DriveToDistanceCommand(1, dt),
            //   new TurnToAngleCommand(90, dt),
            //   new DriveToDistanceCommand(1, dt),
            //   new TurnToAngleCommand(90, dt)
          //   new TurnToAngleCommand(90, dt)
            // new DriveToDistanceCommand(2.5, dt),
            // new TurnToAngleCommand(90, dt),
            // new DriveToDistanceCommand(2, dt),//
            // new DriveToDistanceCommand(-2, dt),
            // new TurnToAngleCommand(270, dt),
            // new DriveToDistanceCommand(2.5, dt),
            // new TurnToAngleCommand(90, dt),
            // new DriveToDistanceCommand(5, dt)

<<<<<<< HEAD
            new DriveToDistanceCommand(5, dt), 
            new TurnToAngleCommand(90, dt),
            new DriveToDistanceCommand(5, dt),
            new TurnToAngleCommand(90, dt),
            new DriveToDistanceCommand(5, dt),
            new TurnToAngleCommand(90, dt),
            new DriveToDistanceCommand(5, dt),
            new TurnToAngleCommand(90, dt)
=======
            // new DriveToDistanceCommand(5, dt),
            // new TurnToAngleCommand(90, dt),
            // new DriveToDistanceCommand(5, dt),
            // new TurnToAngleCommand(90, dt),
            // new DriveToDistanceCommand(5, dt),
            // new TurnToAngleCommand(90, dt),
            // new DriveToDistanceCommand(5, dt),
            // new TurnToAngleCommand(90, dt)
>>>>>>> de94a17ccab4ec85bab7cfa16ab17acbf3732bc9
        );
    }
}
