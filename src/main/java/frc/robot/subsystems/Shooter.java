
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase
{
    private CRRFalcon500 rightShooter;
    private CRRFalcon500 leftShooter;
    
    private FlywheelSim shootSim;

    double setpoint;
    
    public Shooter()
    {
        rightShooter = new CRRFalcon500(Constants.RIGHT_SHOOTER_MOTOR_ID);
        leftShooter = new CRRFalcon500(Constants.LEFT_SHOOTER_MOTOR_ID, true, NeutralMode.Coast, rightShooter);
    }
    
    /**
     * Returns true if it is at setpoint.
     * @param double tolerance the tolerance for targetSpeed
     * @return whether or not the shooter has reached the desired velocity.
     */
    public boolean atSetpoint( double tolerance)
    {
        return rightShooter.atSetpoint(tolerance);
    }

    public void shoot(double setpoint) 
    {
        this.setpoint = setpoint;
        leftShooter.setVelocity(setpoint);
        rightShooter.setVelocity(setpoint);
    }

    /**
    * Stops both motors.
    */
    public void stop()
    {
        shoot(0);
    }

    @Override
    public void periodic() 
    {
        SmartDashboard.putNumber("Shtter Speeeeeeeed", rightShooter.getVelocity());
    }
}


