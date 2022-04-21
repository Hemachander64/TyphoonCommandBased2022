package frc.robot;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Gun extends SubsystemBase
{
    Solenoid solenoid = new Solenoid(Constants.GUN_SOLENOID_ID, PneumaticsModuleType.CTREPCM, 0);
    
    public Gun()
    {
        set(false);
    }

    public void set(boolean on)
    {
        solenoid.set(on);
    }
}
