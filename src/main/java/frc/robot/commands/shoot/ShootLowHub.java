package frc.robot.commands.shoot;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commands.drive.*;
import frc.robot.subsystems.*;

public class ShootLowHub extends SequentialCommandGroup { 
    
    private Shooter shooter;
    private Feeder feeder;
    private Hood hood;

    public ShootLowHub (Limelight ll, Shooter shooter, Hood hood, Feeder feeder) {
        
        addRequirements(ll, shooter, hood /*feeder, */);

        addCommands(
			new StartShooterCommand(shooter, () -> 1400)	//	according to desmos trajectory calc, 700rpm is perfect for lower hub
            // new RunCommand(feeder::on, feeder).withTimeout(0.1),
            // new InstantCommand(shooter::stop, shooter)
            // new InstantCommand(feeder::off, feeder)
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
        // feeder.off();
        hood.setHoodAngularPower(0);
    }
}
