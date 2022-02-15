package frc.robot.commands.shoot;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drive.*;
import frc.robot.subsystems.*;

public class ShootCommand extends SequentialCommandGroup { 
    
    public ShootCommand (Limelight ll, Shooter shooter, Hood hood, Feeder feeder, Drivetrain dt) {
        
        addRequirements(shooter, feeder, dt, hood, ll);

        addCommands(
            new ParallelCommandGroup(
                new AimCommand(dt, ll::getTx),
                new StartShooterCommand(shooter, ll::getTy)
            //  new RunCommand(() -> hood.setHoodAngle(hood.tyToHoodAngle(ll::getTy)), hood) -> this might run forever, so fix it when this is used
                ),
            new RunCommand(feeder::on, feeder).withTimeout(2),
            new InstantCommand(shooter::stop, shooter)
        );

    }
}
