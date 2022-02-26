package frc.robot.commands.shoot;
//hehehehehe
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drive.*;
import frc.robot.subsystems.*;

public class ShootCommand extends SequentialCommandGroup { 
    
    private Shooter shooter;
    private Feeder feeder;
    private Hood hood;

    public ShootCommand (Limelight ll, Shooter shooter, Hood hood, Feeder feeder){ //, Drivetrain dt) {
        
        // addRequirements(shooter, feeder, dt, hood, ll);
        addRequirements(shooter, feeder, hood, ll);

        addCommands(
            // new ParallelCommandGroup(
                // new AimCommand(dt, ll::getTx),
                // new StartShooterCommand(shooter, ll::getTy),
                // new SetHoodAngleCommand(hood.tyToHoodAngle(ll::getTy), hood)
                // ),
            new StartShooterCommand(shooter, ll::getTy),
            new RunCommand(feeder::on, feeder).withTimeout(0.25),
            new RunCommand(feeder::off, feeder).alongWith(new RunCommand(() -> shooter.atSetpoint())),
            new RunCommand(feeder::on, feeder).withTimeout(0.25),
            new InstantCommand(shooter::stop, shooter),
            new InstantCommand(feeder::off, feeder)
        );

        this.shooter = shooter;
        this.feeder = feeder;
        this.hood = hood;
    }

    @Override
    public void end(boolean interrupted)
    {
        super.end(interrupted);

        shooter.stop();
        feeder.off();
        hood.setHoodAngularPower(0);
    }
}
