package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.teamcode.DriveBase;

@TeleOp(name=" TesGamepadSteam", group="Linear Opmode")

public class TesGamepadSteam extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor motorCC = null;
    private DcMotor motorDD = null;
    private DcMotor motorEE = null;
    private Servo jari = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftDrive  = hardwareMap.get(DcMotor.class, "motorA");
        rightDrive = hardwareMap.get(DcMotor.class, "motorB");
        motorCC = hardwareMap.get(DcMotor.class, "motorC");
        motorDD = hardwareMap.get(DcMotor.class, "motorD");
        motorEE = hardwareMap.get(DcMotor.class, "motorE");
        jari = hardwareMap.get(Servo.class, "jari");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        motorCC.setDirection(DcMotor.Direction.FORWARD);
        motorDD.setDirection(DcMotor.Direction.FORWARD);
        motorEE.setDirection(DcMotor.Direction.FORWARD);
        jari.setDirection(Servo.Direction.FORWARD);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double leftPower;
            double rightPower;
            //double motorCC;
            //double motorDD;

            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            double drive = -gamepad1.left_stick_y;
            double turn  =  gamepad1.right_stick_x;
            leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
            rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;

            // Tank Mode uses one stick to control each wheel.
            // - This requires no math, but it is hard to drive forward slowly and keep straight.
            // leftPower  = -gamepad1.left_stick_y ;
            // rightPower = -gamepad1.right_stick_y ;

            // Send calculated power to wheels
            leftDrive.setPower(leftPower);
            rightDrive.setPower(rightPower);
            
            if (gamepad1.right_bumper == true) {
                jari.setPosition(-1);
            }
            if (gamepad1.left_bumper == true) {
                jari.setPosition(1);
            }
            
            
            if (gamepad1.y == true) {
                motorCC.setPower(1);
            }
            if (gamepad1.a == true) {
                motorCC.setPower(-1);
            } else {
                motorCC.setPower(0);
            }
            if (gamepad1.x == true) {
                motorDD.setPower(1);
            }
            if (gamepad1.b == true) {
                motorDD.setPower(-1);
            } else {
                motorDD.setPower(0);
            }
            
            if (gamepad1.dpad_left == true) {
                motorEE.setPower(1);
            }
            if (gamepad1.dpad_right == true) {
                motorEE.setPower(-1);
            } else {
                motorEE.setPower(0);
            }       
            
            // Script Uji Coba
            
            // if (gamepad1.right_bumper == true && gamepad1.left_bumper == true) {
            // }

            // Show the elapsed game time and wheel power
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();
        }
    }
}
