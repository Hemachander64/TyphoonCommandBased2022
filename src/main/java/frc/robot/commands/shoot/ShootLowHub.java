package frc.robot.commands.shoot;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commands.drive.*;
import frc.robot.subsystems.*;

public class ShootLowHub extends SequentialCommandGroup { 
    
    private Shooter shooter;
    private Feeder feeder;
    private Hood hood;

    public ShootLowHub (Limelight ll, Shooter shooter, Hood hood, Feeder feeder, Drivetrain dt) {
        
        addRequirements(ll, shooter, hood, feeder, dt);

        addCommands(
			new StartShooterCommand(shooter, () -> 700).withTimeout(2)	//	according to desmos trajectory calc, 700rpm is perfect for lower hub
                .alongWith(new AimCommand(dt, ll::getTx)),
            new RunCommand(feeder::on, feeder).withTimeout(0.1),
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
