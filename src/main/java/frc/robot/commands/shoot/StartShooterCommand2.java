package frc.robot.commands.shoot;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class StartShooterCommand2 extends CommandBase {
    double power;
    Shooter shooter;
    DoubleSupplier tyGetter;
    Timer timer = new Timer();

    public StartShooterCommand2(Shooter shooter, DoubleSupplier tyGetter) {
        this.shooter = shooter;
        this.tyGetter = tyGetter;
        power = calcPower(tyGetter.getAsDouble());
        addRequirements(shooter);
    }

    @Override
    public void initialize()
    {
        timer.reset();
        timer.start();
    }
    public void execute()
    {
        shooter.setPower(power);
    }
    
    public boolean isFinished()
    {
        return timer.get() > 2;
    }

    public double calcPower(double tY) 
    {
		return 1940.435 - 30.356*tY; //TODO: TUNE DIS!!!!
    }
}
