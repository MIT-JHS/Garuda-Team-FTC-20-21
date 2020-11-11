package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import java.util.Locale;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

@Autonomous(name="A2", group="Autonomous")

public class A2 extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive;
    private DcMotor rightDrive;
    private DcMotor arm;
    private Servo servoTest;
    private DigitalChannel leftDigitalTouch;
    private DigitalChannel rightDigitalTouch;
    //private ColorSensor sensorColorRange;
    private DistanceSensor sensorDistance;    
    
    @Override
    public void runOpMode() {
        
        leftDrive = hardwareMap.get(DcMotor.class,"leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class,"rightDrive");
        arm = hardwareMap.get(DcMotor.class,"arm");
        servoTest = hardwareMap.get(Servo.class, "servoTest");
        leftDigitalTouch = hardwareMap.get(DigitalChannel.class, "leftDigitalTouch");
        rightDigitalTouch = hardwareMap.get(DigitalChannel.class, "rightDigitalTouch");

        // set digital channel to input mode. 
        leftDigitalTouch.setMode(DigitalChannel.Mode.INPUT);
        rightDigitalTouch.setMode(DigitalChannel.Mode.INPUT);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        
        //Mundur
        //leftDrive.setPower(-0.5);
        //rightDrive.setPower(0.5);
        //sleep(1000);
        
        //Arm
        arm.setPower(-0.2);
        sleep(500);
        arm.setPower(0);
        sleep(200);
        arm.setPower(0.2);
        sleep(200);
        arm.setPower(0);
        sleep(200);
        
        /*
        //Servo
        servoTest.setPosition(0);
        sleep(5000);
        servoTest.setPosition(1);
        sleep(5000);
        */
        
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            //servoTest.setPosition(0);
            //sleep(5000);
            //servoTest.setPosition(1);
            //sleep(5000);
        
        /*    
        // is button pressed?
        if (rightDigitalTouch.getState() == false) {
        // button is pressed.
        telemetry.addData("Button1", "PRESSED");
        servoTest.setPosition(0);
        } else {
        // button is not pressed.
        telemetry.addData("Button1", "NOT PRESSED");
        servoTest.setPosition(1);
        }
        */
        
        // /*
        if (leftDigitalTouch.getState() == false) {
        // button is pressed.
        telemetry.addData("Button1", "PRESSED");
        servoTest.setPosition(0);
        } 
        if (leftDigitalTouch.getState() == true) {
        // button is not pressed.
        telemetry.addData("Button1", "NOT PRESSED");
        servoTest.setPosition(1);
        }        

        // */
        
        sensorDistance = hardwareMap.get(DistanceSensor.class, "sensorDistance");
        telemetry.addData("Distance (cm)", sensorDistance.getDistance(DistanceUnit.CM));           
        telemetry.addData("Status", "Running"); 
        telemetry.update();
        
        }
        
    }
}
          