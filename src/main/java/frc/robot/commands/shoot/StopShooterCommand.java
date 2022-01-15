package frc.robot.commands.shoot;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class StopShooterCommand extends CommandBase {
    Shooter shooter;

    public StopShooterCommand (Shooter shooter) {
        this.shooter = shooter;
        addRequirements(shooter);
    }

    public void execute() {
        shooter.stop();
    }
    
    public boolean isFinished() {
        return shooter.atSetpoint(1.0);
    }
}
