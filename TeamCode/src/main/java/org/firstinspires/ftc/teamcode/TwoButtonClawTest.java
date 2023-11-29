package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
@Disabled
@TeleOp(name = "2 Button Claw")//This is where we declare that this file is a TeleOp and set the name to "2 Button Claw"
public class TwoButtonClawTest extends LinearOpMode {

    //We declare variables here
    boolean isClosed1 = false;//Here we create a boolean to keep track of whether claw1 is closed. For example it would be true if the claw was closed or vice versa
    boolean isClosed2 = false;//Here we create a boolean to keep track of whether claw2 is closed. For example it would be true if the claw was closed or vice versa
    ElapsedTime close1 = new ElapsedTime();//This creates a timer that will time how long it has been since the "a" button has been pressed
    ElapsedTime close2 = new ElapsedTime();//This creates a timer that will time how long it has been since the "b" button has been pressed

    @Override
    public void runOpMode(){

        //We declare motors/servos here. We also insert the names of the motors that are configured in the driver station
        Servo claw1 = hardwareMap.servo.get("Claw1");
        Servo claw2 = hardwareMap.servo.get("Claw2");

        //This opens both claws at the start to make sure they are open
        claw1.setPosition(0);
        claw2.setPosition(0);
        waitForStart();

        //Everything that is in this while loop runs until you hit stop on the driver station
        while(opModeIsActive()){
            if(gamepad1.a && !isClosed1){//This checks to see if the "a" button is pressed and if claw1 is open
                claw1.setPosition(1);//This closes claw1
                isClosed1 = true;//This sets this to true to keep stored that claw1 is now closed
                close1.reset();//This starts the timer since the "a" button was just pressed
            }
            else if(gamepad1.a && isClosed1 && close1.milliseconds() >= 300){//This checks to see if it has been 300 milliseconds since the "a" button was pressed and that claw1 is closed and if the "a" button is pressed
                claw1.setPosition(0);//This opens claw1
                isClosed1 = false;//This sets this to false to keep stored that claw1 is now opened
                close1.reset();//This resets the timer since the "a" button was pressed
            }
            if(gamepad1.b && !isClosed2){//This checks to see if the "b" button is pressed and if claw2 is open
                claw2.setPosition(1);//This closes claw2
                isClosed2 = true;//This sets this to true to keep stored that claw2 is now closed
                close2.reset();//This starts the timer since the "b" button was just pressed
            }
            else if(gamepad1.b && isClosed2 && close2.milliseconds() >= 300){//This checks to see if it has been 300 milliseconds since the "b" button was pressed and that claw2 is closed and if the "b" button is pressed
                claw2.setPosition(0);//This opens claw2
                isClosed2 = false;//This sets this to false to keep stored that claw2 is now opened
                close2.reset();//This resets the timer since the "b" button was pressed
            }
            telemetry.addData("Close time 1", close1.milliseconds());
            telemetry.addData("Close time 2", close2.milliseconds());
            telemetry.update();
        }
    }
}
