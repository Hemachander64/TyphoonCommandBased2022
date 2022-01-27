package frc.robot.commands.drive;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

public class DTDProfiled extends ProfiledPIDCommand
{
    Drivetrain dt;
    double distanceMeters;

    public DTDProfiled(double distanceMeters, Drivetrain dt)
    {
        super(new ProfiledPIDController(Constants.DTDP_KP, 0, 0,
                new TrapezoidProfile.Constraints(Constants.DTDP_MV, Constants.DTDP_MA)),
            () -> dt.getAverageDistanceMeters(),
            new TrapezoidProfile.State(distanceMeters, 0),
            (output, setpoint) -> dt.arcadeDrive(output, 0),
            dt);
        
        this.dt = dt;
        this.distanceMeters = distanceMeters;

        getController().setTolerance(Constants.DTDP_TOLERANCE);
        // getController().setTolerance(0.01);
        System.out.println("Hello World");
    }
    
    
    @Override
    public void initialize()
    {
        super.initialize();

        var goal = new TrapezoidProfile.State(distanceMeters + dt.getAverageDistanceMeters(), 0);
        this.m_goal = () -> goal;
    }

    @Override
    public boolean isFinished()
    {
        return getController().atGoal();
    }

    
}