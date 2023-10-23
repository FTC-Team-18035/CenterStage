    package org.firstinspires.ftc.teamcode;

    import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
    import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
    import com.qualcomm.robotcore.hardware.DcMotor;
    import com.qualcomm.robotcore.hardware.DcMotorSimple;
    import com.qualcomm.robotcore.hardware.Servo;
    import com.qualcomm.robotcore.util.ElapsedTime;

    @TeleOp(name = "DRIVE_LIFT")
    public class OnlyDrive extends LinearOpMode {
        // variables
        static final double     COUNTS_PER_MOTOR_REV    = 288 ;    // eg: TETRIX Motor Encoder
        static final double     DRIVE_GEAR_REDUCTION    = 1 ;     // This is < 1.0 if geared UP
        static final double     WHEEL_DIAMETER_INCHES   = .5 ;     // For figuring circumference
        static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
        private ElapsedTime     runtime = new ElapsedTime();    // sets up a timer function
        private ElapsedTime     runtime2 = new ElapsedTime();    // sets up a timer function
        private double          frontLeftPower = 0;     // declare motor power variable
        private double          backLeftPower = 0;      // declare motor power variable
        private double          frontRightPower = 0;    // declare motor power variable
        private double          backRightPower = 0;     // declare motor power variable
        private double          denominator = 1;        // declare motor power calculation variable
        private int             precision = 1;          // chassis motor power reduction factor 1 = full 2 = half power 3 = third power 4 = quarter power
                                                        // ** 231023 set default speed to FULL power *******

        private double          liftPower = 0;     // declare lift motor power variable *******

        private boolean         isClosed = false;


        @Override
        public void runOpMode() throws InterruptedException {
            // Declare our motors
            // Make sure your ID's match your configuration

            DcMotor Fleft = hardwareMap.dcMotor.get("Fleft");
            DcMotor Bleft = hardwareMap.dcMotor.get("Bleft");
            DcMotor Fright = hardwareMap.dcMotor.get("Fright");
            DcMotor Bright = hardwareMap.dcMotor.get("Bright");
            Servo Claw = hardwareMap.servo.get("Claw");
            DcMotor "******" = hardwareMap.dcMotor.get("*****");    // Lift Motors *******
            DcMotor "******" = hardwareMap.dcMotor.get("*****");    // Lift Motors *******



            // Reverse the right side motors
            // Reverse left motors if you are using NeveRests

           // Fleft.setDirection(DcMotorSimple.Direction.REVERSE);
            Fright.setDirection(DcMotorSimple.Direction.REVERSE);
            Bright.setDirection(DcMotorSimple.Direction.REVERSE);

            // Lift Motors direction?? *******
            
            Fleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            Fright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            Bleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            Bright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            // Lift Motors behavior *******

            Claw.setPosition(0);


// Beginning of code to interpret operator controlled movements


            waitForStart();

            //if (isStopRequested()) return;

            while (opModeIsActive()) {

                // check for driving input

                double y = gamepad1.left_stick_y; // Remember, this is reversed!
                double x = -gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
                double rx = -gamepad1.right_stick_x;

                telemetry.update();

                // calculate motor movement math and adjust according to lift height or manual precision mode selection

                denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
                precision = 2;            // reset default speed to half power

                denominator = denominator * precision;
                frontLeftPower = (y + x + rx) / denominator;
                backLeftPower = (y - x + rx) / denominator;
                frontRightPower = (y - x - rx) / denominator;
                backRightPower = (y + x - rx) / denominator;

	// check for claw movement input and 

                if(gamepad1.a && !isClosed){
                    Claw.setPosition(1);
                    isClosed = true;
                    sleep(500);    // we should be using Elapsed time instead  *******
                }
                else if(gamepad1.a && isClosed){
                    Claw.setPosition(0);
                    isClosed = false;
                    sleep(500);       // we should be using Elapsed time instead *******
                }

	// check for lift movement input





	// issue lift power for movement





                // issue chassis power for movement

                Fleft.setPower(frontLeftPower);
                Bleft.setPower(backLeftPower);
                Fright.setPower(frontRightPower);
                Bright.setPower(backRightPower);

            }

        }
    }
