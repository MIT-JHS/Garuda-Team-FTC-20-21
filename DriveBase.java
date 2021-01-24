// PEMROGRAMAN AUTONOMOUS DRIVEBASE

package org.firstinspires.ftc.teamcode;

import java.util.Locale;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@Autonomous(name=" DriveBase ", group="Autonomous")

public class DriveBase extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor motorKanan;
    private DcMotor motorKiri; 
    private DcMotor motorEE; 
    private Servo servoJari; 
    private DcMotor motorDD = null;
    private Servo jari = null;

    @Override
    public void runOpMode() {
        
        motorKanan = hardwareMap.get(DcMotor.class,"motorA");
        motorKiri = hardwareMap.get(DcMotor.class,"motorB");
        motorEE = hardwareMap.get(DcMotor.class,"motorE");
        servoJari = hardwareMap.get(Servo.class, "jari");
        motorDD = hardwareMap.get(DcMotor.class, "motorD");
        jari = hardwareMap.get(Servo.class, "jari");


        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        
        // Drive base
        move("Forward",4, "Very Fast");
        stopRobot();
        
        buka_tutup_jari("Open", 1000);
        buka_tutup_jari("Close", 1000);

        move("Backward",1, "Very Fast");
        stopRobot();
        

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) { 
        
        telemetry.addData("Status", "Running"); 
        telemetry.update();
        
        }
        
    }
    
    // 325 sleep 1 floor 
    public int setFloor(int floorValues) {
      int totalSleep = 0;
              
      for (int i = 1; i <= floorValues; i++) {
        totalSleep += 310;
      }
      
      return totalSleep;
      
    }
    
    public void manualMove(double motorKananPower,
                     double motorKiriPower, 
                     int floorValues) {

        motorKanan.setPower(motorKananPower);
        motorKiri.setPower (motorKiriPower);
        int sleepValue = setFloor(floorValues);
        sleep(sleepValue);
    }

    public void move(String statement,
                          int floorValues, String motorPower) {
      moveSwitch(statement, motorPower);
      int sleepValue = setFloor(floorValues);
      sleep(sleepValue);

    }
    
    public void moveSwitch(String statement, String power) {
      System.out.println(power);
      
      double motorPower = 
        statement == "Backward" ? checkPower(power) * -1.0 :  checkPower(power);
      
      
      switch (statement) {
        case "Forward":
          motorKanan.setPower(motorPower);
          motorKiri.setPower(-motorPower);

          break;
        case "Backward":
          motorKanan.setPower(motorPower);
          motorKiri.setPower(motorPower * -1.0);
        default:
          System.out.println("ERR ON STATEMENT"); 
          break;
      }
    }
    
    public double checkPower(String power) {
      switch(power) {
        case "Very Slow":
          return 0.1;
        case "Slow":
          return 0.25;
        case "Medium":
          return 0.50;
        case "Fast":
          return 0.75;
        case "Very Fast":
          return 1.0;
        default:
          System.out.println("WRONG POWER!");
          return 0.5;
      }
    }


    public void stopRobot() {
        motorKanan.setPower(0);
        motorKiri.setPower (0);
        sleep(100);
    }

    public void buka_tutup_jari(String statement, int sleepValue) {
      switch (statement) {
        case "Open":
          jari.setPosition(1); 
          break;
        case "Close":
          jari.setPosition(0); 
          break;
        default: 
          System.out.println("Error at the first parameter!");
          break;
      }

      sleep(sleepValue);

  }
    
}
