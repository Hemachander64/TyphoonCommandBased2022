package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase
{
    WPI_TalonSRX intakeMotor = new WPI_TalonSRX(Constants.INTAKE_MOTOR_ID);
    DoubleSolenoid leftSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
    DoubleSolenoid rightSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 2, 3);
    Compressor compressor = new Compressor(PneumaticsModuleType.CTREPCM);

    private static final double speed = 1;
    
    public Intake()
    {
        leftSolenoid.set(Value.kForward);   //  set to enable toggling
        rightSolenoid.set(Value.kReverse);
    }

    public void off()
    {
        intakeMotor.set(0.0);
    }

    public void reverse()
    {
        intakeMotor.set(speed);
    }   

    public void on()
    {
        intakeMotor.set(-speed);
    }

    public void togglePneumatics()
    {
        leftSolenoid.toggle();
        rightSolenoid.toggle();
    }

    @Override
    public void periodic()
    {
        SmartDashboard.putBoolean("Pressure", compressor.getPressureSwitchValue());
    }
}

