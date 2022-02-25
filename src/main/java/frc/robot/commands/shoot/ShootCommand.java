package frc.robot.commands.shoot;
//hehehehehe
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drive.*;
import frc.robot.subsystems.*;

public class ShootCommand extends SequentialCommandGroup { 
    
    public ShootCommand (Limelight ll, Shooter shooter, Hood hood, Feeder feeder){ //, Drivetrain dt) {
        
        // addRequirements(shooter, feeder, dt, hood, ll);
        addRequirements(shooter, feeder, hood, ll);

        addCommands(
            new ParallelCommandGroup(
                // new AimCommand(dt, ll::getTx),/
                // new StartShooterCommand(shooter, ll::getTy),
                new RunCommand(() -> shooter.setPower(1), shooter).withTimeout(2),
                new SetHoodAngleCommand(hood.tyToHoodAngle(ll::getTy), hood)
                ),
            new RunCommand(feeder::on, feeder).withTimeout(2),
            new InstantCommand(shooter::stop, shooter)
        );

    }
}
