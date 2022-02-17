package frc.robot.subsystems;

import java.util.function.DoubleSupplier;


import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Hood extends SubsystemBase {

    protected CANSparkMax hoodAngMotor = new CANSparkMax(Constants.HOOD_ANGLE_MOTOR_ID, MotorType.kBrushless); //adjusts hood of shooter
    SparkMaxPIDController pid = hoodAngMotor.getPIDController();
    RelativeEncoder encoder = hoodAngMotor.getEncoder();

    double tolerance = 2;
    double setpoint;



    public Hood()
    {
        //pid.setTolerance(3);
    }

    public void setHoodAngularPower(double power)
    {
        hoodAngMotor.set(power);
    }
    
    /* public boolean setHoodAngle(double angle)
    {
        setHoodAngularPower(pid.calculate(getHoodAngle(), angle));

        return pid.atSetpoint();
    }*/

    public void setHoodAngle(double angle)
	{
		this.setpoint = angle;
		pid.setReference(angle/*+200*/, ControlType.kPosition); // steady state err is 200, but I terms make it VIOLENT
		
		// math goes like:
		// ff = setpoint * ff_gain
		// output = pid + ff
	}

    //in degrees
    public double getHoodAngle()
    {
        return hoodAngMotor.getEncoder().getPosition();
    }

    public boolean atSetpoint()
    {
        return Math.abs(getHoodAngle() - setpoint) <= tolerance;
    }

    public double tyToHoodAngle(DoubleSupplier tyGetter)
    {
        return tyGetter.getAsDouble() * 4000; //TODO: fix it
    }

}
