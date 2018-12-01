package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Op Mode for testing", group="Linear Opmode")
//@Disabled
public class TestingOpMode_Linear extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private HardwareRobot Shachar = new HardwareRobot();

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables.
        Shachar.init(hardwareMap);
        //Shachar.RUN_WITHOUT_ENCODERS();






        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();
        Shachar.Forward(100, 0.5);
        //Shachar.Foward(100,0.3);
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry




            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());

            telemetry.update();
        }
    }

}
