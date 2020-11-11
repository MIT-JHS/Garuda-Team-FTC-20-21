package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * Created by Ayesha on 9/14/18.
 */

@TeleOp(name="ManualDrive", group="Practice-Bot ")
public class ManualDrive extends LinearOpMode {

    private DcMotor frontRightMotor;
    private DcMotor frontLeftMotor;
    private DcMotor backRightMotor;
    private DcMotor backLeftMotor;

    int a = 0;
    double speed = 0.0;
    boolean isbeingpressed = true;
    boolean press = false;
    boolean toggle = false;

    private ElapsedTime period = new ElapsedTime();

    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome
     * with a regular periodic tick. This is used to compensate for varying
     * processing times for each cycle. The function looks at the elapsed cycle time,
     * and sleeps for the remaining time interval.
     *
     * @param periodMs Length of wait cycle in mSec.
     */
    private void waitForTick(long periodMs) throws java.lang.InterruptedException {
        long remaining = periodMs - (long) period.milliseconds();
// sleep for the remaining portion of the regular cycle period.
        if (remaining > 0) {
            Thread.sleep(remaining);
        }
        // Reset the cycle clock for the next pass.
        period.reset();
    }

    @Override
    public void runOpMode() {

        double forwardbackward = 0.0;
        double leftright = 0.0;
        double slide = 0.0;

        backLeftMotor = hardwareMap.dcMotor.get("Bleft_drive");
        backRightMotor = hardwareMap.dcMotor.get("Bright_drive");
        frontLeftMotor = hardwareMap.dcMotor.get("Fleft_drive");
        frontRightMotor = hardwareMap.dcMotor.get("Fright_drive");


        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

// Set all motors to zero power
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);

// Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Fatih"); //
        telemetry.update();

// Wait for the game to start (driver presses PLAY)
        waitForStart();

// run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
// Run wheels in tank mode (note: The joystick goes negative when pushed forwards,)
            forwardbackward = gamepad1.left_stick_y;
            leftright = gamepad1.left_stick_x;
            slide = gamepad1.right_stick_x;

            frontLeftMotor.setPower(-forwardbackward);
            backLeftMotor.setPower(forwardbackward);
            frontRightMotor.setPower(-forwardbackward);
            backRightMotor.setPower(forwardbackward);

            frontLeftMotor.setPower(leftright);
            backLeftMotor.setPower(leftright);
            backRightMotor.setPower(-leftright);
            frontRightMotor.setPower(-leftright);

            frontLeftMotor.setPower(slide);
            backLeftMotor.setPower(-slide);
            backRightMotor.setPower(slide);
            frontRightMotor.setPower(-slide);

// Send telemetry message to signify robot running;

            telemetry.addData("forwardbackward", "%.2f", forwardbackward);
            telemetry.addData("leftright", "%.2f", leftright);
            telemetry.addData("slide", "%.2f", slide);

            telemetry.update();
// Pause for metronome tick. 40 mS each cycle = update 25 times a second.
        }
    }

    public void Run() {

        frontLeftMotor.setPower(gamepad1.right_stick_x * speed);
        frontRightMotor.setPower(-(gamepad1.right_stick_x * speed));
        backLeftMotor.setPower(gamepad1.right_stick_x * speed);
        backRightMotor.setPower(-gamepad1.right_stick_x * speed);

        if (gamepad1.dpad_up) {
            frontLeftMotor.setPower(speed);
            backLeftMotor.setPower(-speed);
            frontRightMotor.setPower(speed);
            backRightMotor.setPower(-speed);
        }
        if (gamepad1.dpad_down) {
            frontLeftMotor.setPower(-speed);
            backLeftMotor.setPower(speed);
            frontRightMotor.setPower(-speed);
            backRightMotor.setPower(speed);
        }
        if (gamepad1.dpad_right) {
            frontLeftMotor.setPower(speed);
            backLeftMotor.setPower(-speed);
            frontRightMotor.setPower(-speed);
            backRightMotor.setPower(speed);
        }
        if (gamepad1.dpad_left) {
            frontLeftMotor.setPower(-speed);
            backLeftMotor.setPower(speed);
            frontRightMotor.setPower(speed);
            backRightMotor.setPower(-speed);
        } else {
            frontLeftMotor.setPower(0);
            backLeftMotor.setPower(0);
            frontRightMotor.setPower(0);
            backRightMotor.setPower(0);
        }


        if (!isbeingpressed && (gamepad1.right_bumper)) {
            a++;
            if (a > 4.0) {
                a = 0;
            }
            isbeingpressed = true;

        }
        if (isbeingpressed && (!gamepad1.right_bumper)) {
            isbeingpressed = false;
        }

        if (!isbeingpressed && (gamepad1.a)) {
            a++;
            if (a > 4) {
                a = 1;
            }
            isbeingpressed = true;

        }
        if (isbeingpressed && (!gamepad1.a)) {
            isbeingpressed = false;
        }

        switch (a) {
            //Combustion Plant Pos

            case 3:

                speed = 0.8;


                break;
            //Reaction Plant Pos
            case 2:

                speed = 0.5;


                break;

            //Intake Pos
            case 1:

                speed = 0.3;


                break;

        }

    }

}