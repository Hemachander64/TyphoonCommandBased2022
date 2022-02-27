package frc.robot.commands.shoot;
//hehehehehe
// hahhahaha very funny zoheb :|
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commands.drive.*;
import frc.robot.subsystems.*;

public class ShootUpperHub extends SequentialCommandGroup { 
    
    private Shooter shooter;
    private Feeder feeder;
    private Hood hood;

    public ShootUpperHub (Limelight ll, Shooter shooter, Hood hood, Feeder feeder, Drivetrain dt) {
        
        addRequirements(ll, shooter, hood, feeder, dt);

        addCommands(
			new StartShooterCommand(shooter, () -> shooter.calcRPM(shooter.calcDistanceMeters(ll.getTy()))).withTimeout(2)
				.alongWith(new AimCommand(dt, ll::getTx)),	//	According to ReCalc, the shooter needs 1.17 sec to wind up
            new RunCommand(feeder::on, feeder).withTimeout(0.1),
			new StartShooterCommand(shooter, () -> shooter.calcRPM(shooter.calcDistanceMeters(ll.getTy()))).withTimeout(2),
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
