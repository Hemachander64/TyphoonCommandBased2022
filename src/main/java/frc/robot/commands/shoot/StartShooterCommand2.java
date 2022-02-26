package frc.robot.commands.shoot;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class StartShooterCommand2 extends CommandBase {
    double power;
    Shooter shooter;
    DoubleSupplier tyGetter;

    public StartShooterCommand2(Shooter shooter, DoubleSupplier tyGetter) {
        this.shooter = shooter;
        this.tyGetter = tyGetter;
        power = calcPower(tyGetter.getAsDouble());
        addRequirements(shooter);
    }

    public void execute()
    {
        shooter.setPower(power);
    }
    
    public boolean isFinished()
    {
        double setpoint = 1000, tolerance = 50;

        return Math.abs(setpoint - shooter.getVelocity()) <= tolerance;
    }

    public double calcPower(double tY) 
    {
		return 1940.435 - 30.356*tY; //TODO: TUNE DIS!!!!
    }
}
