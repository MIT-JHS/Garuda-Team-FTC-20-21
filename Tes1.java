package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Tes1 (Blocks to Java)", group = "")
public class Tes1 extends LinearOpMode {

  private DcMotor motor1;

  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    motor1 = hardwareMap.dcMotor.get("motor 1");

    // Reverse one of the drive motors.
  }
}
