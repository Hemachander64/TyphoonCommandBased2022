package frc.robot;

import java.security.PublicKey;

import com.ctre.phoenix.CANifier.PinValues;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Winch extends SubsystemBase
{
    WPI_TalonSRX winchMotor = new WPI_TalonSRX(Constants.WINCH_MOTOR_ID);

    public Winch()
    {
        winchMotor.setInverted(false);
    }
    
}
