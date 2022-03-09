package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commands.drive.*;
import frc.robot.commands.shoot.*;
import frc.robot.subsystems.*;

public class LowBallTerminal extends SequentialCommandGroup {

    public LowBallTerminal(Limelight ll, Shooter shooter, Hood hood, Feeder feeder, Intake intake, Drivetrain dt) {

        int driveAngle = 20; // TODO: Tune this Magicn't number
        int driveDistance = 20; // TODO: Tune this Magicn't number

        addCommands(

            new LowBallAuto(ll, shooter, hood, feeder, intake, dt),
            new InstantCommand(intake::on, intake).withTimeout(2),
            new TTAProfiled(driveAngle, dt),
            new DTDProfiled(driveDistance, dt),
			new InstantCommand(intake::off, intake),
			new ShootUpperHub(ll, shooter, hood, feeder, dt)

        );
    }

}