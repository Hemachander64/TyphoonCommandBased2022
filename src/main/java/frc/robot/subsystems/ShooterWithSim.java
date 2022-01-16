package frc.robot.subsystems;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.BatterySim;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import edu.wpi.first.wpilibj.simulation.RoboRioSim;

public class ShooterWithSim extends Shooter
{
    private FlywheelSim shootSim = new FlywheelSim(DCMotor.getFalcon500(2), 1, 7);

	@Override
	public void simulationPeriodic()
	{
		shootSim.setInputVoltage(rightShooter.getMotorOutputVoltage());
        shootSim.update(0.02);
		rightShooter.setVelocity(shootSim.getAngularVelocityRPM());
        
		RoboRioSim.setVInVoltage(
			BatterySim.calculateDefaultBatteryLoadedVoltage(shootSim.getCurrentDrawAmps()));
	}
}