package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.VoltageSensor; // Used for Battery Voltage
import com.qualcomm.robotcore.util.ElapsedTime; // Used to measure time
import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp
public class robotCentric extends LinearOpMode {
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
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        // Sets up settings for telemetry
        // Sets the display order for telemetry, in this case, has newest entries displayed first
        telemetry.log().setDisplayOrder(Telemetry.Log.DisplayOrder.NEWEST_FIRST);
        // Sets the amount of lines that can be displayed by the driver hub
        telemetry.log().setCapacity(7);

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
            // When dpad pressed, sets to 30% power or 0.3/1
            if (gamepad1.dpad_up) {
                drive_LSy = 0.3;
            }
            if (gamepad1.dpad_down) {
                drive_LSy = -0.3;
            }
            if (gamepad1.dpad_left) {
                strafe_LSx = -0.3;
            }
            if (gamepad1.dpad_right) {
                strafe_LSx = 0.3;
            }

            //Displays data from dpad
            telemetry.addLine("dpad | ")
                    .addData("up", gamepad1.dpad_up)
                    .addData("down", gamepad1.dpad_down)
                    .addData("left", gamepad1.dpad_left)
                    .addData("right", gamepad1.dpad_right);

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(drive_LSy) + Math.abs(strafe_LSx) + Math.abs(rotate_RSx), 1);
            double frontLeftPower = (-drive_LSy + strafe_LSx + rotate_RSx) / denominator;
            double backLeftPower = (-drive_LSy - strafe_LSx + rotate_RSx) / denominator;
            double frontRightPower = (-drive_LSy - strafe_LSx - rotate_RSx) / denominator;
            double backRightPower = (-drive_LSy + strafe_LSx - rotate_RSx) / denominator;

            // Displays data of motor powers
            telemetry.addData("frontLeftPower", frontLeftPower);
            telemetry.addData("backLeftPower", backLeftPower);
            telemetry.addData("frontRightPower", frontRightPower);
            telemetry.addData("backRightPower", backRightPower);

            // Updates telemetry log
            telemetry.update();

            // Runs motors
            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);
        }
    }
}