package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import com.qualcomm.robotcore.hardware.VoltageSensor; // Used for Battery Voltage
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.Telemetry;


@TeleOp
public class fieldCentric extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Declares motors
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeft");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeft");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRight");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRight");

        // Enables Encoder Drive
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Reverse right side motors.
        // If robot moves backwards when commanded to go forwards, reverse left side instead.
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        // Sets up settings for telemetry
        // Sets the display order for telemetry, in this case, has newest entries displayed first
        telemetry.log().setDisplayOrder(Telemetry.Log.DisplayOrder.NEWEST_FIRST);
        // Sets the amount of lines that are displayed by the driver hub
        telemetry.log().setCapacity(8);

        // Retrieves the IMU from the hardware map
        IMU imu = hardwareMap.get(IMU.class, "imu");
        // Adjust the orientation parameters to match your robot
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            // Uses joysticks to control x-y-z axis
            // blank_RSy currently unused
            double drive_LSy = gamepad1.left_stick_y;
            double strafe_LSx = gamepad1.left_stick_x;
            double blank_RSy = gamepad1.right_stick_y;
            double rotate_RSx = gamepad1.right_stick_x;

            // Displays data from joysticks
            telemetry.addLine("left joystick | ")
                    .addData("x", gamepad1.left_stick_x)
                    .addData("y", gamepad1.left_stick_y);
            telemetry.addLine("right joystick | ")
                    .addData("x", gamepad1.right_stick_x)
                    .addData("y", gamepad1.right_stick_y);

            // Precision Mode, uses dpad to control x / y axis
            // When dpad pressed, sets to 40% power or 0.4/1
            if (gamepad1.dpad_up) {
                drive_LSy = 0.4;
            }
            if (gamepad1.dpad_down) {
                drive_LSy = -0.4;
            }
            if (gamepad1.dpad_left) {
                strafe_LSx = -0.4;
            }
            if (gamepad1.dpad_right) {
                strafe_LSx = 0.4;
            }

            //Displays data from dpad
            telemetry.addLine("dpad | ")
                    .addData("up", gamepad1.dpad_up)
                    .addData("down", gamepad1.dpad_down)
                    .addData("left", gamepad1.dpad_left)
                    .addData("right", gamepad1.dpad_right);

            // Uses x button to reset heading to 0
            // readjusts orientation parameters to current robot orientation
            if (gamepad1.x) {
                imu.resetYaw();
            }

            // Gets current bot heading based on angle in RADIANS from field orientation
            double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            // Displays current bot heading
            telemetry.addData("Bot heading", botHeading);

            // Rotate the movement direction counter to the bot's rotation
            // Used to interpret
            // To calculate y rotation,
            // To calculate x rotation,
            double rotY = strafe_LSx * Math.sin(-botHeading) - drive_LSy * Math.cos(-botHeading);
            double rotX = strafe_LSx * Math.cos(-botHeading) + drive_LSy * Math.sin(-botHeading);


            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rotate_RSx), 1);
            double frontLeftPower = (rotY + rotX - rotate_RSx) / denominator;
            double backLeftPower = (rotY - rotX - rotate_RSx) / denominator;
            double frontRightPower = (rotY - rotX + rotate_RSx) / denominator;
            double backRightPower = (rotY + rotX + rotate_RSx) / denominator;

            // Displays data of motor powers
            telemetry.addData("frontLeftPower", frontLeftPower);
            telemetry.addData("backLeftPower", backLeftPower);
            telemetry.addData("frontRightPower", frontRightPower);
            telemetry.addData("backRightPower", backRightPower);

            // Updates telemetry with prior data
            telemetry.update();

            // Runs motors
            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);
        }
    }
}