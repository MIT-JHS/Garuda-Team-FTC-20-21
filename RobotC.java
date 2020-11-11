package org.firstinspires.ftc.teamcode;

/**
 * Created by Ayesha on 11/12/18.
 */
        import android.app.Activity;
        import android.graphics.Color;
        import android.view.View;

        import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
        import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.hardware.ColorSensor;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.eventloop.opmode.Disabled;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.DcMotorSimple;
        import com.qualcomm.robotcore.hardware.Servo;
        import com.qualcomm.robotcore.util.ElapsedTime;

        import com.qualcomm.robotcore.hardware.DistanceSensor;
        import com.qualcomm.robotcore.hardware.DigitalChannel;
        import com.qualcomm.robotcore.hardware.TouchSensor;

        import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

        import java.util.Locale;


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

public class RobotC extends LinearOpMode {

    private DcMotor frontRightMotor;
    private DcMotor frontLeftMotor;
    private DcMotor backRightMotor;
    private DcMotor backLeftMotor;

    private Servo Scollecting;

    private DcMotor lifter;
    private DcMotor collector;
    private DcMotor turning;

    DistanceSensor sensorRange;
    ColorSensor sensorColor;
    DistanceSensor sensorDistance;
    //DigitalChannel digitalTouch;  // Hardware Device Object

    int a = 0;
    double speed = 0.0;
    double servoPosition = 0.0;
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
        double speed = 0.0;

        double scollect = 0.0;

        double lift = 0.0;
        double collect = 0.0;
        double turn = 0.0;

        backLeftMotor = hardwareMap.dcMotor.get("Bleft_drive");
        backRightMotor = hardwareMap.dcMotor.get("Bright_drive");
        frontLeftMotor = hardwareMap.dcMotor.get("Fleft_drive");
        frontRightMotor = hardwareMap.dcMotor.get("Fright_drive");

        Scollecting = hardwareMap.servo.get("Scollect_servo");

        lifter = hardwareMap.dcMotor.get("Lifter_drive");
        collector = hardwareMap.dcMotor.get("Collector_drive");
        turning = hardwareMap.dcMotor.get(("Turning_drive"));

        sensorColor = hardwareMap.get(ColorSensor.class, "sensor_color_distance");
        sensorDistance = hardwareMap.get(DistanceSensor.class, "sensor_color_distance");
        sensorRange = hardwareMap.get(DistanceSensor.class,"Range_sensor");
        //digitalTouch = hardwareMap.get(DigitalChannel.class,"Touch_sensor");

        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

// Set all motors to zero power
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);

        lifter.setPower(0);
        collector.setPower(0);
        turning.setPower(0);

        Scollecting.setPosition(servoPosition);

        float hsvValues[] = {0F, 0F, 0F};

        // values is a reference to the hsvValues array.
        final float values[] = hsvValues;

        // sometimes it helps to multiply the raw RGB values with a scale factor
        // to amplify/attentuate the measured values.
        final double SCALE_FACTOR = 255;

        // get a reference to the RelativeLayout so we can change the background
        // color of the Robot Controller app to match the hue detected by the RGB sensor.
        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);

        Rev2mDistanceSensor sensorTimeOfFlight = (Rev2mDistanceSensor)sensorRange;

        //digitalTouch = hardwareMap.get(DigitalChannel.class, "sensor_digital");

        // set the digital channel to input.
        //digitalTouch.setMode(DigitalChannel.Mode.INPUT);

// Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver"); //
        telemetry.update();

// Wait for the game to start (driver presses PLAY)
        waitForStart();

        //sensorColor.enableLed(true);

// run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            Color.RGBToHSV((int) (sensorColor.red() * SCALE_FACTOR),
                    (int) (sensorColor.green() * SCALE_FACTOR),
                    (int) (sensorColor.blue() * SCALE_FACTOR),
                   hsvValues);

// Run wheels in tank mode (note: The joystick goes negative when pushed forwards,)
            forwardbackward = -gamepad1.left_stick_y;
            leftright = -gamepad1.right_stick_x;

            lift = -gamepad2.left_stick_y;
            collect = gamepad2.right_stick_y;
            turn = gamepad2.right_stick_x;

            if (gamepad2.a){
                Scollecting.setPosition(scollect);
            }

            else {
                Scollecting.setPosition(0);
            }

            frontLeftMotor.setPower(forwardbackward * 0.7);
            backLeftMotor.setPower(forwardbackward * 0.7);
            frontRightMotor.setPower(forwardbackward * 0.7);
            backRightMotor.setPower(forwardbackward * 0.7);

            lifter.setPower(lift);
            collector.setPower(collect * 0.5);
            turning.setPower(turn * 0.5);

            frontLeftMotor.setPower(leftright * 0.7);
            backLeftMotor.setPower(leftright * 0.7);
            backRightMotor.setPower(-leftright * 0.7);
            frontRightMotor.setPower(-leftright * 0.7);


// Send telemetry message to signify robot running;

            telemetry.addData("forwardbackward", "%.2f", forwardbackward);
            telemetry.addData("leftright", "%.2f", leftright);

            //arm
            telemetry.addData("up", "%.2f", lift);
            telemetry.addData("collect", "%2f", collect);
            telemetry.addData("turn", "%2f", turn);

            //distance color
            telemetry.addData("Distance (cm)",
                    String.format(Locale.US, "%.02f", sensorDistance.getDistance(DistanceUnit.CM)));
            telemetry.addData("Alpha", sensorColor.alpha());
            telemetry.addData("Red  ", sensorColor.red());
            telemetry.addData("Green", sensorColor.green());
            telemetry.addData("Blue ", sensorColor.blue());
            telemetry.addData("Hue", hsvValues[0]);

            //servo
            telemetry.addData("Servo Position", Scollecting.getPosition());
            telemetry.addData("Status", "Running");

            // generic DistanceSensor methods. //2Mdistance
            telemetry.addData("deviceName",sensorRange.getDeviceName() );
            telemetry.addData("range", String.format("%.01f mm", sensorRange.getDistance(DistanceUnit.MM)));
            telemetry.addData("range", String.format("%.01f cm", sensorRange.getDistance(DistanceUnit.CM)));
            telemetry.addData("range", String.format("%.01f m", sensorRange.getDistance(DistanceUnit.METER)));
            telemetry.addData("range", String.format("%.01f in", sensorRange.getDistance(DistanceUnit.INCH)));

            // Rev2mDistanceSensor specific methods.
            telemetry.addData("ID", String.format("%x", sensorTimeOfFlight.getModelID()));
            telemetry.addData("did time out", Boolean.toString(sensorTimeOfFlight.didTimeoutOccur()));
            // change the background color to match the color detected by the RGB sensor.
            // pass a reference to the hue, saturation, and value array as an argument
            // to the HSVToColor method.

            // send the info back to driver station using telemetry function.
            // if the digital channel returns true it's HIGH and the button is unpressed.
            /*if (digitalTouch.getState() == true) {
                telemetry.addData("Digital Touch", "Is Not Pressed");
            } else {
                lifter.setPower(0);
                telemetry.addData("Digital Touch", "Is Pressed");
            }*/
            if (sensorRange.getDistance(DistanceUnit.CM) > 5)
            {
                telemetry.addData("Save To Drive", "CONTINUE");
            }
            else
            {
                frontLeftMotor.setPower(0);
                frontRightMotor.setPower(0);
                backLeftMotor.setPower(0);
                backRightMotor.setPower(0);
                telemetry.addData("Too Close", "STOP");

            }

            telemetry.update();
// Pause for metronome tick. 40 mS each cycle = update 25 times a second.


            // Set the panel back to the default color
        relativeLayout.post(new Runnable() {
                                public void run() {
                                    relativeLayout.setBackgroundColor(Color.WHITE);
                                    relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, values));
                                }
                            }
        );
        }
    }

    public void Run() {

        frontLeftMotor.setPower(gamepad1.right_stick_x * speed);
        frontRightMotor.setPower(-(gamepad1.right_stick_x * speed));
        backLeftMotor.setPower(gamepad1.right_stick_x * speed);
        backRightMotor.setPower(-gamepad1.right_stick_x * speed);

        lifter.setPower(gamepad2.left_stick_x * speed);
        collector.setPower(gamepad2.left_stick_x * 0.5);
        turning.setPower(gamepad2.left_stick_x * 0.5);

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


        switch (a) {
            //Combustion Plant Pos

            case 4:
                speed = 1;

                break;
            //Reaction Plant Pos
            case 3:
                speed = 0.8;

                break;

            //Intake Pos
            case 2:
                speed = 0.5;

                break;

            case 1:
                speed = 0.3;

                break;


        }

    }

}