# Learning Management System

This Learning Management System (LMS) is a Java application built using the Core Java and Collection Framework. It provides a basic framework for managing users, courses, and course enrollments. Users can register as either students or instructors, create and enroll in courses, and view their course details.

## Table of Contents
1. [Features](#features)
2. [Prerequisites](#prerequisites)
3. [Getting Started](#getting-started)
4. [Usage](#usage)
5. [Screenshots](#screenshots)
6. [Contributing](#contributing)
7. [License](#license)

## Features<a name="features"></a>

- User registration as a student or instructor.
- Course creation (instructor only).
- Course enrollment (student only).
- Viewing all available courses.
- Viewing courses you are enrolled in (student) or instructing (instructor).
- Secure user authentication.

## Prerequisites<a name="prerequisites"></a>

To run this application, you'll need:

- Java Development Kit (JDK) installed on your system.
- A Java IDE or text editor (e.g., Eclipse, IntelliJ IDEA, or Visual Studio Code).

## Getting Started<a name="getting-started"></a>

1. Clone this repository to your local machine using the following command:

   ```bash
   git clone https://github.com/your-username/learning-management-system.git
   ```

2. Open the project in your Java IDE or text editor.

3. Build and run the `LearningManagementSystem` class.

## Usage<a name="usage"></a>

Upon running the application, you will see a menu that allows you to perform various actions based on your role (student or instructor).

### Main Menu (Not Logged In)
- Register as a Student
- Register as an Instructor
- Login
- Exit

### Main Menu (Logged In)
- Create Course (Instructor Only)
- Enroll in a Course (Student Only)
- View All Courses
- View My Courses
- Logout

#### Registering as a Student or Instructor
You can register as either a student or an instructor by providing your email, password, full name, and phone number.

#### Logging In
To access the features, you need to log in using your registered email and password.

#### Creating a Course (Instructor Only)
Instructors can create courses by providing course details, including name, description, duration, and start date. Only instructors have permission to create courses.

#### Enrolling in a Course (Student Only)
Students can enroll in available courses by providing the course name. After successful enrollment, you will see details about the course instructor.

#### Viewing All Courses
You can view a list of all available courses, including their names, instructors, descriptions, durations, and start dates.

#### Viewing My Courses (Student)
Students can view a list of courses they are enrolled in, including course names, instructors, descriptions, durations, and start dates.

#### Viewing Courses I'm Instructing (Instructor)
Instructors can view a list of courses they are instructing, including course names, descriptions, durations, and start dates.

### Screenshots<a name="screenshots"></a>

Insert screenshots of the application's user interface and different screens here.

### Contributing<a name="contributing"></a>

Contributions to this project are welcome. You can fork the repository, make changes, and submit a pull request.

### License<a name="license"></a>

This project is licensed under the [MIT License](LICENSE.md).

---
