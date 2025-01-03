# Strafing Code
## Introduction
- Initial code used is from: https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html
- There are **TWO** different types of drive programmed in `strafe-drive`. See the table in #strafing-info for more details.
## Initialization
**NOTE: ALL MOTORS ARE KNOWN AS "GoBILDA 5202/3/4 series"**

### Robot Centric
| **Port** | **Device Type**   | **Name in Config** |
|----------|-------------------|--------------------|
| 0        | Front Left Motor  | `frontLeft`        |
| 1        | Front Right Motor | `frontRight`       |
| 2        | Back Left Motor   | `backLeft`         |
| 3        | Back Right Motor  | `backRight`        |

### Field Centric
| **Port**  | **Device Type**   | **Name in Config** |
|-----------|-------------------|--------------------|
| 0         | Front Left Motor  | `frontLeft`        |
| 1         | Front Right Motor | `frontRight`       |
| 2         | Back Left Motor   | `backLeft`         |
| 3         | Back Right Motor  | `backRight`        |
| N/A       | Rev Internal IMU  | `imu`              |

*IMU is in `I2C Bus 0`*

## Controls
### Robot Centric
| Gamepad input   | Output             |
|-----------------|--------------------|
| `left_stick_y`  | Forward-Backward   |
| `left_stick_x`  | Left-Right         |
| `right_stick_y` | ______________     |
| `right_stick_x` | Rotation           |
| `dpad_up`       | Forward 40% power  |
| `dpad_down`     | Backward 40% power |
| `dpad_left`     | Left 40% power     |
| `dpad_right`    | Right 40% power    |

### Field Centric
| Gamepad input    | Output             |
|------------------|--------------------| 
| `left_stick_y`   | Forward-Backward   |
| `left_stick_x`   | Left-Right         |
| `right_stick_y`  | ______________     |
| `right_stick_x`  | Rotation           |
| `dpad_up`        | Forward 40% power  |
| `dpad_down`      | Backward 40% power |
| `dpad_left`      | Left 40% power     |
| `dpad_right`     | Right 40% power    |
| `x`              | Reset Orientation  |

## Telemetry
- Definition: Collection/measurement, transmission and display of data.
- Telemetry collects data from code or sensors and displays on FTC driver hub.
- Telemetry displays newest entries first.
- When initialized, telemetry set to have max amount of lines displayed.
- The number of lines each part of telemetry uses is recorded below.
**Ensure that number of lines generated does not exceed maximum set**
### Robot Centric Implementation
- Data from joysticks x2
- Data from dpad x1
- Current motor power x4
### Field Centric Implementation
- Data from joysticks x2
- Data from dpad x1
- Current bot heading in IMU x1
- Current motor power x4
### Features to be implemented
- Voltage sensor
- Runtime data
- Current draw

## Strafing Info
### Robot Centric
- Definition: In this system, the robot interprets all movement commands relative to its own orientation (local frame).
- The robot's "forward" direction is always aligned with its front.
- Driving in this mode is akin to driving a car.

### Field Centric
- Definition: In this system, movement commands are interpreted relative to the field (global frame) rather than the robot’s orientation.
- Directions like "forward" are always relative to a fixed point on the field (e.g., North).
- Field is set upon initialization of `Field Centric`.
- Field can be reset using `X` on the gamepad.

### Comparison
| Aspect                | Robot-Centric                        | Field-Centric                         |
|-----------------------|--------------------------------------|---------------------------------------|
| **Reference Frame**   | Robot’s local orientation            | Global (field) orientation            |
| **"Forward" Motion**  | Based on where the robot is facing   | Fixed direction on the field          |
| **Ease of Control**   | Harder for drivers during rotations  | Easier and more intuitive for drivers |
| **Implementation**    | Simple; no external sensors required | Requires gyros/IMUs for orientation   |
