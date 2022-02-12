package frc.robot.commands.shoot;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drive.*;
import frc.robot.subsystems.*;

public class ShootCommand extends SequentialCommandGroup { 
    Limelight ll;
    Shooter shooter;
    Feeder feeder;
    Drivetrain dt;
    
    public ShootCommand (Limelight ll, Shooter shooter, Feeder feeder, Drivetrain dt) {
        this.ll = ll;
        this.feeder = feeder;
        this.shooter = shooter;
        this.dt = dt;
        
        addRequirements(shooter);

        addCommands(
            new ParallelCommandGroup(
                new AimCommand(dt, ll),
                new StartShooterCommand(shooter, ll),
                new SetHoodAngle(shooter, ll)
                ),
            new RunCommand(feeder::on, feeder).withTimeout(10),
            new StopShooterCommand(shooter)
        );

        // addCommands (
        //     new StartShooterCommand(RPM, shooter),
        //     new RunCommand(feeder::on, feeder).withTimeout(10),
        //     new InstantCommand(feeder::off, feeder),
        //     new StopShooterCommand(shooter)
        // );
    }
}
