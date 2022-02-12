package frc.robot.commands.shoot;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

public class StartShooterCommand extends CommandBase {
    double RPM;
    Shooter shooter;
    Limelight ll;

    public StartShooterCommand(Shooter shooter, Limelight ll) {
        this.shooter = shooter;
        this.ll = ll;
        RPM = calcRPM(ll.getTy());
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
		return 1940.435 - 30.356*tY;
    }
}
