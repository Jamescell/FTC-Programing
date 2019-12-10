package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Driver_Controled", group="Linear Opmode")

public class Driving extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor back_left = null;
    private DcMotor back_right = null;
    private DcMotor front_left = null;
    private DcMotor front_right = null;
    private Servo rightServo = null;
    private Servo leftServo = null;
    
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        back_left  = hardwareMap.get(DcMotor.class, "back_left");
        back_right = hardwareMap.get(DcMotor.class, "back_right");
        front_left  = hardwareMap.get(DcMotor.class, "back_left");
        front_right = hardwareMap.get(DcMotor.class, "back_right");
        rightServo = hardwareMap.get(Servo.class, "rightServo");
        leftServo = hardwareMap.get(Servo.class, "leftServo");
        
        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        back_left.setDirection(DcMotor.Direction.FORWARD);
        front_left.setDirection(DcMotor.Direction.FORWARD);
        back_right.setDirection(DcMotor.Direction.REVERSE);
        front_right.setDirection(DcMotor.Direction.REVERSE);
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double leftPower;
            double rightPower;
            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            double right = gamepad1.left_stick_y;
            double left  = gamepad1.right_stick_x;
            leftPower    = Range.clip(right + left, -1.0, 1.0) ;
            rightPower   = Range.clip(right - left, -1.0, 1.0) ;
            
           
           
           
       
            back_left.setPower(leftPower);
            back_right.setPower(rightPower);
            front_left.setPower(leftPower);
            front_right.setPower(rightPower);
            
 // check to see if we need to move the servo.
      if(gamepad1.dpad_down) {
 // move to 0 degrees.
    rightServo.setPosition(0);
    leftServo.setPositon(0);
     } else if (gamepad1.dpad_right) {
 // move to 90 degrees.
    rightServo.setPosition(0.5);
    leftServo.setPosition(0.5);
    } else if (gamepad1.dpad_up) {
 // move to 180 degrees.
     rightServo.setPosition(1);
     lefttServo.setPosition(1);
     }
            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();
            
            
        }
    }
}
