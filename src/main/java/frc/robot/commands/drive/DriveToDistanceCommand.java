package frc.robot.commands.drive;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

public class DriveToDistanceCommand extends PIDCommand
{
    Drivetrain dt;
    
    
    public DriveToDistanceCommand(double distMeters, Drivetrain dt)
    {
        

        super(new PIDController(Constants.DTD_KP, 0, 0),
            dt::getDistanceMeters,    //  PID's process variable getter method
            distMeters + dt.getDistanceMeters(), //  PID setpoint
            output -> dt.arcadeDrive(output, 0),
            dt);    //  PID output method as a lambda, this w>?>,
        getController().setTolerance(Constants.DTD_TOLERANCE);  //  the tolerance with which the isFinished() method checks if the PV is within the setpoint
        
        this.dt = dt;
    }

    /*
    @Override
    public void initialize()
    {
      //dt.resetEncoders(); //  upon initialization, reset the encoders
      
      this.m_setpoint = distMeters + dt.getDistanceMeters();

    }
    */

    @Override
    public boolean isFinished()
    {
        return getController().atSetpoint();    //  This command will terminate once the desired distance has been reached.
    }
    @Override
    public void end(boolean interrupted) {
        dt.arcadeDrive(0, 0);
        
    }
}
