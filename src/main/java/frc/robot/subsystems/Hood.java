package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Hood extends SubsystemBase {

    protected CANSparkMax hoodAngMotor; //adjusts hood of shooter
    PIDController pid = new PIDController(Constants.HOOD_KP, 0, 0);

    public Hood()
    {
        hoodAngMotor = new CANSparkMax(Constants.HOOD_ANGLE_MOTOR_ID, MotorType.kBrushless);
        pid.setTolerance(3);
    }

    public void setHoodAngularPower(double power)
    {
        hoodAngMotor.set(power);
    }
    
    public boolean setHoodAngle(double angle)
    {
        setHoodAngularPower(pid.calculate(getHoodAngle(), angle));

        return pid.atSetpoint();
    }

    //in degrees
    public double getHoodAngle()
    {
        return hoodAngMotor.getEncoder().getPosition();
    }

    

    public double tyToHoodAngle(DoubleSupplier tyGetter)
    {
        return tyGetter.getAsDouble() * 4000; //TODO: fix it
    }
}
