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
        move(1.0,-1.0,1000);

        stopRobot();
        
        motorDD.setPower(0.5);
        sleep(2000);
        
        motorEE.setPower(-0.5);
        sleep(2000);
        
        motorEE.setPower(-1);
        sleep(2000);

        motorEE.setPower(0);
        motorDD.setPower(0);
        
        jari.setPosition(1);
        sleep(5000);
        jari.setPosition(0);
        sleep(1000);
        move(-1.0,1.0,1000);
        stopRobot();
        
        
        // // Berputar
        // motorKanan.setPower(0.5);
        // motorKiri.setPower (0.5);
        // sleep(3000);
        // motorKanan.setPower(0);
        // motorKiri.setPower (0);
        // sleep(500);
        
        // // Mundur
        // motorKanan.setPower(0.5);
        // motorKiri.setPower (-0.5);
        // sleep(1000);
        // motorKanan.setPower(0);
        // motorKiri.setPower (0);
        // sleep(500);
        
        // // Shooter
        // motorEE.setPower(0.5);
        // sleep(1000);
        // motorEE.setPower (0);
        // sleep(500);
        
        // // Menggerakan Jari
        // servoJari.setPosition(0);
        // sleep(1500);
        // servoJari.setPosition(1);
        // sleep(1500);
        // servoJari.setPosition(0);
        // sleep(500);


        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) { 
        
        telemetry.addData("Status", "Running"); 
        telemetry.update();
        
        }
        
    }
    
    public void move(double motorKananPower, double motorKiriPower, int sleepValue) {
        motorKanan.setPower(motorKananPower);
        motorKiri.setPower (motorKiriPower);
        sleep(sleepValue);
    }
    public void stopRobot() {
        motorKanan.setPower(0);
        motorKiri.setPower (0);
        sleep(500);
    }
}
          
