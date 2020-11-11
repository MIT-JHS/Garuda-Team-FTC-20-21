package org.firstinspires.ftc.teamcode;

import java.util.Locale;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name=" DrivebaseA ", group="Autonomous")

public class DrivebaseA extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor motorKanan;
    private DcMotor motorKiri; 
    private DcMotor motor5;
    
    @Override
    public void runOpMode() {
        
        motorKanan = hardwareMap.get(DcMotor.class,"motorKanan");
        motorKiri = hardwareMap.get(DcMotor.class,"motorKiri");
        motor5 = hardwareMap.get(DcMotor.class,"motor5");

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        
        
        // Drive base 
        
        motorKiri.setPower(-0.5);
        sleep(2000);
        motorKiri.setPower(0.5);
        sleep(2000);
        motor5.setPower (-0.5);
        sleep(2000);
        motor5.setPower (0.5);
        sleep(2000);
        
        /*
        // Maju
        motorKanan.setPower(-0.5);
        motorKiri.setPower (0.5);
        sleep(1000);
        motorKanan.setPower(0);
        motorKiri.setPower (0);
        sleep(500);
        
        // Belok
        motorKanan.setPower(0.5);
        motorKiri.setPower (0.5);
        sleep(500);
        motorKanan.setPower(0);
        motorKiri.setPower (0);
        sleep(500);
        
        // Maju
        motorKanan.setPower(-0.5);
        motorKiri.setPower (0.5);
        sleep(1000);
        motorKanan.setPower(0);
        motorKiri.setPower (0);
        sleep(500);
        
        // Belok
        motorKanan.setPower(0.5);
        motorKiri.setPower (0.5);
        sleep(500);
        motorKanan.setPower(0);
        motorKiri.setPower (0);
        sleep(500);
        
        // Maju
        motorKanan.setPower(-0.5);
        motorKiri.setPower (0.5);
        sleep(1000);
        motorKanan.setPower(0);
        motorKiri.setPower (0);
        sleep(500);
        
        // belok
        motorKanan.setPower(-0.5);
        motorKiri.setPower (-0.5);
        sleep(500);
        motorKanan.setPower(0);
        motorKiri.setPower (0);
        sleep(500);
        
        // Maju
        motorKanan.setPower(-0.5);
        motorKiri.setPower (0.5);
        sleep(1000);
        motorKanan.setPower(0);
        motorKiri.setPower (0);
        sleep(500);
        
        // belok
        motorKanan.setPower(-0.5);
        motorKiri.setPower (-0.5);
        sleep(500);
        motorKanan.setPower(0);
        motorKiri.setPower (0);
        sleep(500);
        
        // Maju
        motorKanan.setPower(-0.5);
        motorKiri.setPower (0.5);
        sleep(1000);
        motorKanan.setPower(0);
        motorKiri.setPower (0);
        sleep(500);
        
        // belok
        motorKanan.setPower(-0.5);
        motorKiri.setPower (-0.5);
        sleep(500);
        motorKanan.setPower(0);
        motorKiri.setPower (0);
        sleep(500);
        
        // Mundur
        motorKanan.setPower(0.5);
        motorKiri.setPower (-0.5);
        sleep(1000);
        motorKanan.setPower(0);
        motorKiri.setPower (0);
        sleep(500);
        
        // Muter
        motorKanan.setPower(-0.5);
        motorKiri.setPower (-0.5);
        sleep(3000);
        motorKanan.setPower(0);
        motorKiri.setPower (0);
        sleep(500);
        
        */
         
        
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) { 
        
        telemetry.addData("Status", "Running"); 
        telemetry.update();
        
        }
        
    }
}
          
