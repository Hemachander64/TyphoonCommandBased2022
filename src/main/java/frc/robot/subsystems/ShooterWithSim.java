//heheheheheheheheheh
package frc.robot.subsystems;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.simulation.BatterySim;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import edu.wpi.first.wpilibj.simulation.RoboRioSim;

public class ShooterWithSim extends Shooter
{
    private FlywheelSim shootSim = new FlywheelSim(
		DCMotor.getFalcon500(2),
		0.75,
		0.5 * Units.lbsToKilograms(0.74 * 2) * Math.pow(Units.inchesToMeters(4/2), 2)
	);

	@Override
	public void simulationPeriodic()
	{
		shootSim.setInputVoltage(rightShooter.getSimCollection().getMotorOutputLeadVoltage());
        shootSim.update(0.02);
		rightShooter.getSimCollection().setIntegratedSensorVelocity(
			(int) CRRFalcon500.RPMtoTalonUnits(shootSim.getAngularVelocityRPM()));
        
		RoboRioSim.setVInVoltage(
			BatterySim.calculateDefaultBatteryLoadedVoltage(shootSim.getCurrentDrawAmps()));
	}
}