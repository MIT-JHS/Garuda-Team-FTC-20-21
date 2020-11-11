package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="AutonomousA", group="Autonomous")

public class AutonomousA extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor FrontLeft;
    private DcMotor FrontRight;
    private DcMotor BackLeft;
    private DcMotor BackRight;

    @Override
    public void runOpMode() {
        
        FrontLeft = hardwareMap.get(DcMotor.class,"FrontLeft");
        FrontRight = hardwareMap.get(DcMotor.class,"FrontRight");
        BackLeft = hardwareMap.get(DcMotor.class,"BackLeft");
        BackRight= hardwareMap.get(DcMotor.class,"BackRight");
        
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        
        //Mundur
        FrontLeft.setPower(-0.5);
        FrontRight.setPower(0.5);
        BackLeft.setPower(-0.5);
        BackRight.setPower(0.5);
        sleep(1120);
        
        //Berhenti
        FrontLeft.setPower(0);
        FrontRight.setPower(0);
        BackLeft.setPower(0);
        BackRight.setPower(0);
        sleep(500);
        
        //putar 90 derajat
        FrontLeft.setPower(-0.5);
        FrontRight.setPower(-0.5);
        BackLeft.setPower(-0.5);
        BackRight.setPower(-0.5);
        sleep(920);
        
        //Berhenti
        FrontLeft.setPower(0);
        FrontRight.setPower(0);
        BackLeft.setPower(0);
        BackRight.setPower(0);
        sleep(500);
        
        //Maju
        FrontLeft.setPower(0.5);
        FrontRight.setPower(-0.5);
        BackLeft.setPower(0.5);
        BackRight.setPower(-0.5);
        sleep(500);
        
        //Berhenti
        FrontLeft.setPower(0);
        FrontRight.setPower(0);
        BackLeft.setPower(0);
        BackRight.setPower(0);
        sleep(500);
        
        //Mundur
        FrontLeft.setPower(-0.5);
        FrontRight.setPower(0.5);
        BackLeft.setPower(-0.5);
        BackRight.setPower(0.5);
        sleep(200);
        
        //Berhenti
        FrontLeft.setPower(0);
        FrontRight.setPower(0);
        BackLeft.setPower(0);
        BackRight.setPower(0);
        sleep(500);
        
        //putar 45 derajat
        FrontLeft.setPower(-0.5);
        FrontRight.setPower(-0.5);
        BackLeft.setPower(-0.5);
        BackRight.setPower(-0.5);
        sleep(500);
        
        //Berhenti
        FrontLeft.setPower(0);
        FrontRight.setPower(0);
        BackLeft.setPower(0);
        BackRight.setPower(0);
        sleep(500);
        
        //Maju
        FrontLeft.setPower(0.5);
        FrontRight.setPower(-0.5);
        BackLeft.setPower(0.5);
        BackRight.setPower(-0.5);
        sleep(1250);
        
        //Berhenti
        FrontLeft.setPower(0);
        FrontRight.setPower(0);
        BackLeft.setPower(0);
        BackRight.setPower(0);
        sleep(500);
        
        FrontLeft.setPower(-1);
        FrontRight.setPower(1);
        BackLeft.setPower(-1);
        BackRight.setPower(1);
        sleep(2000);
        
        telemetry.addData("Path" , "Complete");
        telemetry.update();
        sleep(1000);

    }
    
}
