package frc.robot.commands.shoot;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Shooter;

public class ShootCommand extends SequentialCommandGroup{
    Shooter shooter;
    double RPM;
    Feeder feeder;
    
    public ShootCommand (double RPM, Shooter shooter, Feeder feeder) {
        this.feeder = feeder;
        this.shooter = shooter;
        addRequirements(shooter);

        addCommands (
            new StartShooterCommand(RPM, shooter),
            new RunCommand(feeder::on, feeder).withTimeout(10),
            new InstantCommand(feeder::off. feeder),
            new StopShooterCommand(shooter)
        );
    }
}
