
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.PIDGains;

public class Shooter extends SubsystemBase
{
    protected CRRFalcon500 rightShooter;
    protected CRRFalcon500 leftShooter;

    protected CANSparkMax upperFlywheelMotor;
    
    public Shooter()
    {
        rightShooter = new CRRFalcon500(Constants.RIGHT_SHOOTER_MOTOR_ID, false, NeutralMode.Coast, new PIDGains(10000, 0, 0));
        leftShooter = new CRRFalcon500(Constants.LEFT_SHOOTER_MOTOR_ID, true, NeutralMode.Coast, rightShooter);

        upperFlywheelMotor = new CANSparkMax(Constants.HOOD_MOTOR_ID, MotorType.kBrushless);

        // upperFlywheelMotor.getPIDController().setP(1);
        upperFlywheelMotor.getPIDController().setFF(1);
        upperFlywheelMotor.burnFlash();
    }
    
    /**
     * Returns true if it is at setpoint.
     * @param double tolerance the tolerance for targetSpeed
     * @return whether or not the shooter has reached the desired velocity.
     */
    public boolean atSetpoint()
    {
        double tolerance = 50;
        return rightShooter.atSetpoint(tolerance);
    }

    public void setPower(double power)
    {
        rightShooter.set(power);
        leftShooter.set(power);
        upperFlywheelMotor.set(power);
    }

    public void setVelocity(double setpoint) 
    {
        leftShooter.setVelocity(setpoint);
        rightShooter.setVelocity(setpoint);
        upperFlywheelMotor.getPIDController().setReference(setpoint, ControlType.kVelocity);
    }

    /**
    * Stops both motors.
    */
    public void stop()
    {
        rightShooter.set(0);
        leftShooter.set(0);

        upperFlywheelMotor.set(0);
    }

    @Override
    public void periodic() 
    {
        SmartDashboard.putNumber("Shewter Speeeeeeeed", rightShooter.getVelocity());
    }
}