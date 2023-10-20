package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "1 Button Test")//This line is saying that this is a TeleOp and is setting the name to "1 Button Test"
public class OneButtonClawTest extends LinearOpMode {
    int TimesPressed = 0; //Here we create a variable that is going to save the amount of times that the "a" button is pressed
    ElapsedTime ClawTime = new ElapsedTime(); //This creates a timer that will time how long it has been since the "a" button has been pressed

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
            //This is checking to see what the "TimesPressed" variable holds. Or rather how many times the "a" button has been pressed
            switch(TimesPressed){
                //If the answer is 0 it runs the code here
                case 0:
                    if(gamepad1.a){//This checks to see if the "a" button has been pressed on gamepad1
                        ClawTime.startTime();//This starts the timer since the "a" button was just pressed
                        claw1.setPosition(1);//this closes claw 1
                        TimesPressed++;//This adds one to the variable to keep track of times the "a" button has been pressed
                    }
                //If the answer is 1 it runs the code here
                case 1:
                    if(gamepad1.a && ClawTime.milliseconds() > 300){//This checks to see if it has been more than 300 milliseconds since the "a" button has been pressed
                        ClawTime.reset();//This resets the timer to 0
                        claw1.setPosition(0);//this opens claw 1
                        TimesPressed++;//This adds one to the variable to keep track of times the "a" button has been pressed
                    }
                //If the answer is 2 it runs the code here
                case 2:
                    if(gamepad1.a){//This checks to see if the "a" button has been pressed on gamepad1
                        ClawTime.startTime();//This starts the timer since the "a" button was just pressed
                        claw2.setPosition(1);//this closes claw 2
                        TimesPressed++;//This adds one to the variable to keep track of times the "a" button has been pressed
                    }
                //If the answer is 3 it runs the code here
                case 3:
                    if(gamepad1.a && ClawTime.milliseconds() > 300){//This checks to see if it has been more than 300 milliseconds since the "a" button has been pressed
                        ClawTime.reset();//This resets the timer to 0
                        claw2.setPosition(0);//this opens claw 2
                        TimesPressed++;//This adds one to the variable to keep track of times the "a" button has been pressed
                    }
                //If the answer is 4 it runs the code here
                case 4:
                    if(gamepad1.a){//This checks to see if the "a" button has been pressed on gamepad1
                        ClawTime.startTime();//This starts the timer since the "a" button was just pressed
                        claw1.setPosition(1);//this closes claw 1
                        claw2.setPosition(1);//this closes claw 2
                        TimesPressed++;//This adds one to the variable to keep track of times the "a" button has been pressed
                    }
                //If the answer is 5 it runs the code here
                case 5:
                    if(gamepad1.a && ClawTime.milliseconds() > 300){//This checks to see if it has been more than 300 milliseconds since the "a" button has been pressed
                        ClawTime.reset();//This resets the timer to 0
                        claw1.setPosition(0);//this opens claw 1
                        claw2.setPosition(0);//this opens claw 2
                        TimesPressed = 0;//This resets the counter back to 0 to start the cycle over again
                    }
                }

                telemetry.addData("Claw Coodown", ClawTime.milliseconds());
                telemetry.addData("Times Pressed", TimesPressed);
                telemetry.update();

            }
        }
    }
