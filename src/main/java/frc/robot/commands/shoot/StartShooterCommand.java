package frc.robot.commands.shoot;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class StartShooterCommand extends CommandBase {
    double RPM;
    Shooter shooter;

    public StartShooterCommand(double RPM, Shooter shooter) {
        this.shooter = shooter;
        this.RPM = RPM;
        addRequirements(shooter);
    }

    public void execute()
    {
        shooter.shoot(RPM);
    }
    
    public boolean isFinished()
    {
        return shooter.atSetpoint(RPM);
    }
}
