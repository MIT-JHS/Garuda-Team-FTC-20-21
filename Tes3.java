package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name = "Tes3 (Blocks to Java)", group = "")
public class Tes3 extends LinearOpMode {

  private DigitalChannel digitalTouch;
  private Servo servo0;
  private DcMotor motor1;
  private DcMotor motor2;
  private DcMotor motor3;
  private DcMotor motor4;
  private ColorSensor sensorColorRange;

  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    double tgtPower;
    double ugtPower;

    digitalTouch = hardwareMap.digitalChannel.get("digitalTouch");
    servo0 = hardwareMap.servo.get("servo 0");
    motor1 = hardwareMap.dcMotor.get("motor 1");
    motor2 = hardwareMap.dcMotor.get("motor 2");
    motor3 = hardwareMap.dcMotor.get("motor 3");
    motor4 = hardwareMap.dcMotor.get("motor 4");
    sensorColorRange = hardwareMap.colorSensor.get("sensorColorRange");

    // Put initialization blocks here.
    digitalTouch.setMode(DigitalChannel.Mode.INPUT);
    servo0.setPosition(0.5);
    waitForStart();
    if (opModeIsActive()) {
      // Put run blocks here.
      while (opModeIsActive()) {
        // Put loop blocks here.
        if (gamepad1.y) {
          servo0.setPosition(0);
        } else if (gamepad1.x || gamepad1.b) {
          servo0.setPosition(0.5);
        } else if (gamepad1.a) {
          servo0.setPosition(0);
        }
        tgtPower = -gamepad1.left_stick_y;
        ugtPower = gamepad1.right_stick_y;
        motor1.setPower(tgtPower);
        motor2.setPower(tgtPower);
        motor3.setPower(ugtPower);
        motor4.setPower(ugtPower);
        telemetry.addData("Target Power", tgtPower);
        telemetry.addData("Motor Power", motor1.getPower());
        telemetry.addData("Uarget Power", ugtPower);
        telemetry.addData("Motor Power", motor3.getPower());
        telemetry.addData("servo position", servo0.getPosition());
        telemetry.addData("Distance (cm)", ((DistanceSensor) sensorColorRange).getDistance(DistanceUnit.CM));
        if (digitalTouch.getState()) {
          telemetry.addData("digitalTouch", "NOT pressed (HIGHHH)");
        } else {
          telemetry.addData("digitalTouch", "Pressed (LOWWW)");
        }
        telemetry.update();
      }
    }
  }
}
