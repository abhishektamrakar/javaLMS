import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

// Define roles for users
enum Role {
    STUDENT, INSTRUCTOR
}

// User class to represent users
class User {
    private String email;
    private String password;
    private String fullName;
    private String phoneNumber;
    private Role role;
    private List<Course> enrolledCourses = new ArrayList<>();

    public User(String email, String password, String fullName, String phoneNumber, Role role) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    // Getter methods
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    // Method to enroll in a course
    public void enrollInCourse(Course course) {
        enrolledCourses.add(course);
    }

    // Method to get enrolled courses
    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }
}

// Course class to represent courses
class Course {
    private String courseName;
    private String description;
    private String duration;
    private Date startDate;
    private User instructor;

    public Course(String courseName, String description, String duration, Date startDate, User instructor) {
        this.courseName = courseName;
        this.description = description;
        this.duration = duration;
        this.startDate = startDate;
        this.instructor = instructor;
    }

    // Getter methods
    public String getCourseName() {
        return courseName;
    }

    public String getDescription() {
        return description;
    }

    public String getDuration() {
        return duration;
    }

    public Date getStartDate() {
        return startDate;
    }

    public User getInstructor() {
        return instructor;
    }
}

public class LearningManagementSystem {
    private static Map<String, User> users = new HashMap<>();
    private static Map<String, Course> courses = new HashMap<>();
    private static User currentUser = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (currentUser == null) {
                // Main menu when not logged in
                System.out.println("1. Register as Student");
                System.out.println("2. Register as Instructor");
                System.out.println("3. Login");
                System.out.println("4. Exit");
            } else {
                // Main menu when logged in
                System.out.println("1. Create Course (Instructor Only)");
                System.out.println("2. Enroll in a Course (Student Only)");
                System.out.println("3. View All Courses");
                System.out.println("4. View My Courses");
                System.out.println("5. Logout");
            }

            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    if (currentUser == null) {
                        // Registration menu for students and instructors
                        registerUser(scanner, Role.STUDENT);
                    } else if (currentUser.getRole() == Role.INSTRUCTOR) {
                        createCourse(scanner);
                    } else {
                        System.out.println("You do not have permission to create a course.");
                    }
                    break;
                case 2:
                    if (currentUser == null) {
                        // Registration menu for students and instructors
                        registerUser(scanner, Role.INSTRUCTOR);
                    } else if (currentUser.getRole() == Role.STUDENT) {
                        enrollInCourse(scanner);
                    } else {
                        System.out.println("You do not have permission to enroll in a course.");
                    }
                    break;
                case 3:
                    if (currentUser != null) {
                        viewAllCourses();
                    } else {
                        login(scanner);
                    }
                    break;
                case 4:
                    if (currentUser != null) {
                        if (currentUser.getRole() == Role.INSTRUCTOR) {
                            viewInstructorCourses();
                        } else {
                            viewStudentCourses();
                        }
                    } else {
                        System.out.println("You need to log in to view your courses.");
                    }
                    break;
                case 5:
                    logout();
                    break;
                case 6:
                    System.out.println("Exiting the program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void registerUser(Scanner scanner, Role defaultRole) {
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        if (users.containsKey(email)) {
            System.out.println("User with this email already exists. Please log in.");
            return;
        }

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        System.out.print("Enter your full name: ");
        String fullName = scanner.nextLine();

        System.out.print("Enter your phone number: ");
        String phoneNumber = scanner.nextLine();

        Role role = defaultRole;

        User user = new User(email, password, fullName, phoneNumber, role);
        users.put(email, user);
        System.out.println("Registration successful!");
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (users.containsKey(email) && users.get(email).getPassword().equals(password)) {
            currentUser = users.get(email);
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid email or password. Please try again.");
        }
    }

    private static void logout() {
        currentUser = null;
        System.out.println("Logged out successfully.");
    }

    private static void createCourse(Scanner scanner) {
        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();

        System.out.print("Enter course description: ");
        String description = scanner.nextLine();

        System.out.print("Enter course duration: ");
        String duration = scanner.nextLine();

        System.out.print("Enter course start date (dd-MM-yyyy): ");
        String startDateStr = scanner.nextLine();

        Date startDate;
        try {
            startDate = new SimpleDateFormat("dd-MM-yyyy").parse(startDateStr);
        } catch (Exception e) {
            System.out.println("Invalid date format. Please use dd-MM-yyyy.");
            return;
        }

        Course course = new Course(courseName, description, duration, startDate, currentUser);
        courses.put(courseName, course);
        System.out.println("Course created successfully!");
    }

    private static void enrollInCourse(Scanner scanner) {
        System.out.print("Enter the name of the course you want to enroll in: ");
        String courseName = scanner.nextLine();

        if (courses.containsKey(courseName)) {
            Course course = courses.get(courseName);

            if (currentUser.getRole() == Role.STUDENT) {
                currentUser.enrollInCourse(course);
                System.out.println("Enrollment successful!");

                // Display instructor details after successful enrollment
                User instructor = course.getInstructor();
                System.out.println("Instructor Name: " + instructor.getFullName());
                System.out.println("Instructor Email: " + instructor.getEmail());
                System.out.println("Instructor Contact Number: " + instructor.getPhoneNumber());
            } else {
                System.out.println("Only students can enroll in courses.");
            }
        } else {
            System.out.println("Course not found.");
        }
    }

    private static void viewAllCourses() {
        System.out.println("List of all courses:");
        courses.values().forEach(course -> {
            System.out.println("Course Name: " + course.getCourseName());
            System.out.println("Instructor: " + course.getInstructor().getFullName());
            System.out.println("Description: " + course.getDescription());
            System.out.println("Duration: " + course.getDuration());
            System.out.println("Start Date: " + new SimpleDateFormat("dd-MM-yyyy").format(course.getStartDate()));
            System.out.println("--------------------");
        });
    }

    private static void viewStudentCourses() {
        System.out.println("List of your enrolled courses:");
        currentUser.getEnrolledCourses().forEach(course -> {
            System.out.println("Course Name: " + course.getCourseName());
            System.out.println("Instructor: " + course.getInstructor().getFullName());
            System.out.println("Description: " + course.getDescription());
            System.out.println("Duration: " + course.getDuration());
            System.out.println("Start Date: " + new SimpleDateFormat("dd-MM-yyyy").format(course.getStartDate()));
            System.out.println("--------------------");
        });
    }

    private static void viewInstructorCourses() {
        System.out.println("List of courses you are instructing:");
        List<Course> instructorCourses = courses.values().stream()
                .filter(course -> course.getInstructor() == currentUser)
                .collect(Collectors.toList());

        instructorCourses.forEach(course -> {
            System.out.println("Course Name: " + course.getCourseName());
            System.out.println("Description: " + course.getDescription());
            System.out.println("Duration: " + course.getDuration());
            System.out.println("Start Date: " + new SimpleDateFormat("dd-MM-yyyy").format(course.getStartDate()));
            System.out.println("--------------------");
        });
    }
}
