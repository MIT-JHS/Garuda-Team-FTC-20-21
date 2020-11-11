package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * Created by Ayesha on 9/14/18.
 */

@TeleOp
public class RobotD extends LinearOpMode {

    private DcMotor frontRightMotor;
    private DcMotor frontLeftMotor;
    private DcMotor backRightMotor;
    private DcMotor backLeftMotor;

    private DcMotor latcher;
    private DcMotor leftLifter;
    private DcMotor rightLifter;
    private DcMotor Extender;

    private Servo servoCollector;
    //private Servo servoDivider;

    //public DigitalChannel digitalTouch;

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

        double latch = 0.0;
        double leftlift = 0.0;
        double rightlift = 0.0;
        double extend = 0.0;

        double servoCollect = 0.0;
        //double servoDivide = 0.0;

        backLeftMotor = hardwareMap.dcMotor.get("Bleft_drive");
        backRightMotor = hardwareMap.dcMotor.get("Bright_drive");
        frontLeftMotor = hardwareMap.dcMotor.get("Fleft_drive");
        frontRightMotor = hardwareMap.dcMotor.get("Fright_drive");

        latcher = hardwareMap.dcMotor.get("Latching");
        leftLifter = hardwareMap.dcMotor.get("LeftLifter");
        rightLifter = hardwareMap.dcMotor.get("RightLifter");
        Extender = hardwareMap.dcMotor.get("Extender");

        servoCollector = hardwareMap.servo.get("Servo_Collect");
        //servoDivider = hardwareMap.servo.get("Servo_Divide");

        //digitalTouch = hardwareMap.get(DigitalChannel.class,"Touch_sensor");

        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

// Set all motors to zero power
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);

        latcher.setPower(0);
        leftLifter.setPower(0);
        rightLifter.setPower(0);
        Extender.setPower(0);

        servoCollector.setPosition(0);
        //servoDivider.setPosition(0.5);

        //digitalTouch = hardwareMap.get(DigitalChannel.class, "sensor_digital");

        // set the digital channel to input.
        //digitalTouch.setMode(DigitalChannel.Mode.INPUT);

// Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver"); //
        telemetry.update();

// Wait for the game to start (driver presses PLAY)
        waitForStart();

// run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
// Run wheels in tank mode (note: The joystick goes negative when pushed forwards,)
            forwardbackward = -gamepad1.left_stick_y;
            leftright = gamepad1.right_stick_x;
            slide = gamepad1.left_stick_x;


            if (gamepad2.x) {
                latcher.setPower(1);
            }


            if (gamepad2.y){
                latcher.setPower(-1);
            }


            if (gamepad2.b){
                leftLifter.setPower(-0.5);
                rightLifter.setPower(0.5);
            }


            if (gamepad2.a){
                leftLifter.setPower(0.5);
                rightLifter.setPower(-0.5);
            }

            if (gamepad2.left_bumper){
                Extender.setPower(1);
            }
            if (gamepad2.right_bumper){
                Extender.setPower(-1);
            }

            frontLeftMotor.setPower(forwardbackward);
            frontRightMotor.setPower(forwardbackward);
            backLeftMotor.setPower(forwardbackward);
            backRightMotor.setPower(forwardbackward);

            frontLeftMotor.setPower(leftright);
            backLeftMotor.setPower(leftright);
            backRightMotor.setPower(-leftright);
            frontRightMotor.setPower(-leftright);

            frontLeftMotor.setPower(slide);
            backLeftMotor.setPower(-slide);
            backRightMotor.setPower(slide);
            frontRightMotor.setPower(-slide);

            latcher.setPower(latch);
            leftLifter.setPower(leftlift);
            rightLifter.setPower(rightlift);
            Extender.setPower(extend);

            servoCollector.setPosition(servoCollect);
            //servoDivider.setPosition(servoDivide);

// Send telemetry message to signify robot running;

            telemetry.addData("forwardbackward", "%.2f", forwardbackward);
            telemetry.addData("leftright", "%.2f", leftright);
            telemetry.addData("slide", "%.2f", slide);

            telemetry.addData("latch", "%.2f", latch);
            telemetry.addData("uplift", "%.2f", leftlift);
            telemetry.addData("downlift", "%.2f", rightlift);
            telemetry.addData("extend", "%.2f", extend);

            // send the info back to driver station using telemetry function.
            // if the digital channel returns true it's HIGH and the button is unpressed.
            /*if (digitalTouch.getState() == true) {
                telemetry.addData("Digital Touch", "Is Not Pressed");
            } else {
                latcher.setPower(0);
                telemetry.addData("Digital Touch", "Is Pressed");
            }*/
            telemetry.update();
// Pause for metronome tick. 40 mS each cycle = update 25 times a second.
        }
    }

    public void Run() {

        frontLeftMotor.setPower(gamepad1.right_stick_x * speed);
        frontRightMotor.setPower(-(gamepad1.right_stick_x * speed));
        backLeftMotor.setPower(gamepad1.right_stick_x * speed);
        backRightMotor.setPower(-gamepad1.right_stick_x * speed);

        latcher.setPower(gamepad2.right_stick_x * speed);

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


        if (!isbeingpressed && (gamepad2.a)) {
            a++;
            if (a > 4) {
                a = 1;
            }
            isbeingpressed = true;

        }
        if (isbeingpressed && (!gamepad2.a)) {
            isbeingpressed = false;
        }


        if (!isbeingpressed && (gamepad2.x)) {
                a++;
                if (a > 4) {
                    a = 1;
                }
                isbeingpressed = true;

            }
        if (isbeingpressed && (!gamepad2.x)) {
                isbeingpressed = false;
            }

        if (!isbeingpressed && (gamepad2.y)) {
            a++;
            if (a > 4) {
                a = 1;
            }
            isbeingpressed = true;

        }
        if (isbeingpressed && (!gamepad2.y)) {
            isbeingpressed = false;
        }

            switch (a) {

                case 4:
                    leftLifter.setPower(0);
                    rightLifter.setPower(0);
                    latcher.setPower(0);
                    Extender.setPower(0);

                    break;

                case 3:
                    leftLifter.setPower(-1);
                    rightLifter.setPower(1);
                    latcher.setPower(-1);
                    Extender.setPower(-1);

                    break;

                case 2:
                    leftLifter.setPower(0);
                    rightLifter.setPower(0);
                    latcher.setPower(0);
                    Extender.setPower(0);

                    break;

                case 1:
                    leftLifter.setPower(1);
                    rightLifter.setPower(-1);
                    latcher.setPower(1);
                    Extender.setPower(1);

                    break;

            }
        }

    }
