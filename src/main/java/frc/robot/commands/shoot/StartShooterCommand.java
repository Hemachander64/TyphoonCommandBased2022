package frc.robot.commands.shoot;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class StartShooterCommand extends CommandBase {
    double RPM;
    Shooter shooter;
    DoubleSupplier rpmGetter;

    public StartShooterCommand(Shooter shooter, DoubleSupplier rpmGetter) {
        this.shooter = shooter;
        this.rpmGetter = rpmGetter;
        addRequirements(shooter);
    }

    @Override
    public void initialize()
    {
        RPM = rpmGetter.getAsDouble();
    }

    public void execute()
    {
        shooter.setMotorVelocity(RPM);
    }
    
    public boolean isFinished()
    {
        return shooter.atSetpoint();
    }
}
