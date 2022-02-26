
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase
{
    protected CRRFalcon500 masterShoot;
    protected CRRFalcon500 slaveShoot;
    protected CANSparkMax upperFlywheelMotor;

    protected PIDController flywheelPID = new PIDController(Constants.KP_SHOOT, 0, Constants.KD_SHOOT);;
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
        // return masterShoot.atSetpoint(Constants.SHOOT_TOLERANCE);
        return Math.abs(setpoint - masterShoot.getVelocity()) <= Constants.SHOOT_TOLERANCE;
    }

    public void setPower(double power)
    {
        masterShoot.set(power);
        upperFlywheelMotor.set(power);
    }

    public void setVelocity(double setpoint) 
    {
        // leftShooter.setVelocity(setpoint);
        // rightShooter.setVelocity(setpoint);
        // upperFlywheelMotor.getPIDController().setReference(setpoint, ControlType.kVelocity);
        this.setpoint = setpoint;
        
        double output = flywheelPID.calculate(getVelocity(), setpoint) + 
                        flywheelFF.calculate(setpoint);

        masterShoot.setVoltage(output);
        upperFlywheelMotor.setVoltage(output);
    }

    /**
    * Stops both motors.
    */
    public void stop()
    {
        masterShoot.set(0);
        slaveShoot.set(0);

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
}