package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase
{
    WPI_TalonSRX intakeMotor = new WPI_TalonSRX(Constants.INTAKE_MOTOR_ID);
    double speed = 0.65;
    
    public void off()
    {
        intakeMotor.set(0.0);
    }
    public void reverse()
    {
        intakeMotor.set(-speed);
    }   
    public void on()
    {
        intakeMotor.set(speed);
    }
}

