package frc.robot.commands.shoot;
//hehehehehe
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drive.*;
import frc.robot.subsystems.*;

public class ShootCommand2 extends SequentialCommandGroup { 
    
    public ShootCommand2 (Limelight ll, Shooter shooter, Hood hood, Feeder feeder, Drivetrain dt){ //, Drivetrain dt) {
        
        addRequirements(shooter, feeder, dt, hood, ll);
        // addRequirements(shooter, feeder, hood, ll);

        addCommands(
            new ParallelCommandGroup(
                new AimCommand(dt, ll::getTx),
                new StartShooterCommand2(shooter, ll::getTy),
                new SetHoodAngleCommand(hood.tyToHoodAngle(ll::getTy), hood)
                ),
            new RunCommand(feeder::on, feeder).withTimeout(2),
            new InstantCommand(shooter::stop, shooter)
        );

    }
}
