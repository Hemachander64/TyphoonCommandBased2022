package frc.robot.commands.shoot;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

public class StartShooterCommand extends CommandBase {
    double RPM;
    Shooter shooter;
    DoubleSupplier tyGetter;

    public StartShooterCommand(Shooter shooter, DoubleSupplier tyGetter) {
        this.shooter = shooter;
        this.tyGetter = tyGetter;
        RPM = calcRPM(tyGetter.getAsDouble());
        addRequirements(shooter);
    }

    public void execute()
    {
        shooter.setVelocity(RPM);
    }
    
    public boolean isFinished()
    {
        return shooter.atSetpoint();
    }

    public double calcRPM(double tY) 
    {
		return 1940.435 - 30.356*tY; //TODO: TUNE DIS!!!!
    }
}
