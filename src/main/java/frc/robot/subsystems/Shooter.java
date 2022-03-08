
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.*;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase
{
    protected CRRFalcon500 masterShoot;
    protected CRRFalcon500 slaveShoot;
    protected CANSparkMax upperFlywheelMotor;

    // protected BangBangController bang = new BangBangController(Constants.SHOOT_TOLERANCE);
    protected PIDController bang = new PIDController(0, 0, 0);
    // protected PIDController bang = new PIDController(0.0075, 0, 0);
    protected SimpleMotorFeedforward flywheelFF = new SimpleMotorFeedforward(Constants.KS_SHOOT, Constants.KV_SHOOT);
    protected double setpoint;
    
    public Shooter()
    {
        masterShoot = new CRRFalcon500(Constants.RIGHT_SHOOTER_MOTOR_ID, true, NeutralMode.Coast);
        slaveShoot = new CRRFalcon500(Constants.LEFT_SHOOTER_MOTOR_ID, false, NeutralMode.Coast, masterShoot);
        upperFlywheelMotor = new CANSparkMax(Constants.HOOD_MOTOR_ID, MotorType.kBrushless);
    }
    
    /**
     * Returns true if it is at setpoint.
     * @param double tolerance the tolerance for targetSpeed
     * @return whether or not the shooter has reached the desired velocity.
     */
    public boolean atSetpoint()
    {
        return bang.atSetpoint();
    }

    public void setPower(double power)
    {
        masterShoot.set(power);
        upperFlywheelMotor.set(power);
    }

    public void setMotorVelocity(double setpoint)
    {
        this.setpoint = setpoint;
        
        double output = RobotController.getBatteryVoltage() * bang.calculate(getVelocity(), setpoint) + 
                        flywheelFF.calculate(setpoint);

        masterShoot.setVoltage(output);
        upperFlywheelMotor.set(setpoint / 6380);
    }

    /**
    * Stops both motors.
    */
    public void stop()
    {
        masterShoot.set(0);

        upperFlywheelMotor.set(0);
    }

    @Override
    public void periodic() 
    {
        SmartDashboard.putNumber("Shewter Speeeeeeeed", masterShoot.getVelocity());
    }

	public double getVelocity()
	{
		return masterShoot.getVelocity();
	}

    public double calcRPM(double distanceMeters)
    {
	    double flywheelRPM = 624.778 * distanceMeters + 6935.374; // done with the power of a ti84 😎
        double motorRPM = flywheelRPM / 2; // gear ratio;

        SmartDashboard.putNumber("RPM", motorRPM);
        return motorRPM;
    }

    public double calcDistanceMeters(double ty)
    {
        // https://docs.limelightvision.io/en/latest/cs_estimating_distance.html
        double h2 = 2.6416, h1 = 0.5116, a1 = Math.toRadians(30), a2 = Math.toRadians(ty);    //  TODO: measure a1, the limelight mounting angle from horizontal
        
        double d = (h2 - h1) / Math.tan(a1 + a2);

        SmartDashboard.putNumber("d", d);
        return d;
    }
}