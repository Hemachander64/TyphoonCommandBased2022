package frc.robot.commands.drive;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

public class AimCommand extends CommandBase
{
 
    Drivetrain dt;
    PIDController txController = new PIDController(0.05, 0, 0);
    DoubleSupplier txGetter;
    //PIDController tyController = new PIDController(0.1, 0, 0);

    public AimCommand(Drivetrain dt, DoubleSupplier txGetter)
    {
        this.dt = dt;
        this.txGetter = txGetter;
        txController.setTolerance(2);
        //tyController.setTolerance(1);
        addRequirements(dt);
    }

    @Override
    public void initialize()
    {
        dt.resetEncoders();
    }

    @Override
    public void execute()
    {
        dt.arcadeDrive(0, txController.calculate(-txGetter.getAsDouble()));
        // dt.drive(0, tyController.calculate(ll.getTy(), 20), txController.calculate(-ll.getTx(), 0));
    }

    @Override
    public boolean isFinished()
    {
        return txController.atSetpoint();
    }
}