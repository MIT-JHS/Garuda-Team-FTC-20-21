/*
Copyright 2014 

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a PushBot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Remove a @Disabled the on the next line or two (if present) to add this opmode to the Driver Station OpMode list,
 * or add a @Disabled annotation to prevent this OpMode from being added to the Driver Station
 */
@TeleOp

public class TestCollecting extends LinearOpMode {
    //private DcMotor frontRightMotor;
    private DcMotor lifter;
    //private DcMotor backRightMotor;
    private DcMotor lifter1;

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

        double lift = 0.0;
        double lift1 = 0.0;
        double speed = 0.0;

        lifter = hardwareMap.dcMotor.get("Lifter_drive");
        lifter1 = hardwareMap.dcMotor.get("Lifter1_drive");
        //backRightMotor = hardwareMap.dcMotor.get("Extender_drive");
        //frontRightMotor = hardwareMap.dcMotor.get("Collector_drive");


        //frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        //backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

// Set all motors to zero power
        lifter.setPower(0);
        //frontRightMotor.setPower(0);
        lifter1.setPower(0);
        //backRightMotor.setPower(0);

// Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver"); //
        telemetry.update();

// Wait for the game to start (driver presses PLAY)
        waitForStart();

// run until the end of the match (driver presses STOP)
            while (opModeIsActive()) {
// Run wheels in tank mode (note: The joystick goes negative when pushed forwards,)
                lift = gamepad1.right_stick_y;
                lift1 = gamepad1.left_stick_y;

                //frontLeftMotor.setPower(forwardbackward + leftright);
                //backLeftMotor.setPower(-forwardbackward + leftright);
                //frontRightMotor.setPower(forwardbackward + (-leftright));
                //backRightMotor.setPower(forwardbackward + (-leftright));

                lifter.setPower(lift);
                lifter1.setPower(lift1);

                //backRightMotor.setPower(-leftright);
                //frontRightMotor.setPower(-leftright);


// Send telemetry message to signify robot running;

                telemetry.addData("forwardbackward", "%.2f", lift);
                telemetry.addData("leftright", "%.2f", lift1);

                telemetry.update();
// Pause for metronome tick. 40 mS each cycle = update 25 times a second.
            }
        }

    public void Run() {

        lifter.setPower(gamepad1.left_stick_x * speed);
        //frontRightMotor.setPower(-(gamepad1.right_stick_x * speed));
        lifter1.setPower(gamepad1.left_stick_x * speed);
        //backRightMotor.setPower(-gamepad1.right_stick_x * speed);

        if (gamepad1.dpad_up) {
            lifter.setPower(0.5);
            lifter1.setPower(0.5);
            //frontRightMotor.setPower(speed);
            //backRightMotor.setPower(-speed);
        }
        if (gamepad1.dpad_down) {
            lifter.setPower(-0.5);
            lifter1.setPower(-0.5);
            //frontRightMotor.setPower(-speed);
            //backRightMotor.setPower(speed);
        }
        if (gamepad1.dpad_right) {
            lifter.setPower(-0.5);
            lifter1.setPower(0.5);
            //frontRightMotor.setPower(-speed);
            //backRightMotor.setPower(speed);
        }
        if (gamepad1.dpad_left) {
            lifter.setPower(0.5);
            lifter1.setPower(-0.5);
            //frontRightMotor.setPower(speed);
            //backRightMotor.setPower(-speed);
        } else {
            lifter.setPower(0);
            lifter1.setPower(0);
            //frontRightMotor.setPower(0);
            //backRightMotor.setPower(0);
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
