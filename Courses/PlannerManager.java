import java.util.Scanner;

public class PlannerManager {

    public static Planner mainplanner = new Planner();
    public static Planner backupplanner = new Planner();
    public static void main(String[] args) {
        Scanner sk = new Scanner(System.in);
        boolean quit = false;
        String response;
        String coursename;
        String department;
        int coursecode;
        byte coursesection;
        String instructor;
        int position;
        while (!quit) {
            System.out.println("(A) Add Course\n" + 
                "(G) Get Course\n" + 
                "(R) Remove Course\n" +
                "(P) Print Courses in Planner\n" +
                "(F) Filter by Department Code\n" + 
                "(L) Look For Course\n" + 
                "(S) Size\n" +
                "(B) Backup\n" + 
                "(PB) Print Courses in Backup\n" +
                "(RB) Revert to Backup\n" +
                "(Q) Quit\n");
            
            System.out.println("Enter a selection: ");
            response = sk.nextLine();
            
            switch(response) {
                case "A":
                    System.out.println("Enter course name: ");
                    coursename = sk.nextLine();
                    System.out.println("Enter department: ");
                    department = sk.nextLine();
                    System.out.println("Enter course code: ");
                    coursecode = sk.nextInt();
                    sk.nextLine();
                    System.out.println("Enter course section: ");
                    coursesection = sk.nextByte();
                    sk.nextLine();
                    System.out.println("Enter instructor: ");
                    instructor = sk.nextLine();
                    System.out.println("Enter position: ");
                    position = sk.nextInt();
                    sk.nextLine();
                    try {
                        casea(coursename, department, coursecode, coursesection, instructor, position);
                    } catch (FullPlannerException e) {
                        System.out.println("FullPlannerException Error");
                    }
                    break;
                case "G":
                    System.out.println("Enter in a position \n");
                    position = sk.nextInt();
                    sk.nextLine();
                    caseg(position);
                    break;
                case "R":
                    System.out.println("Enter in a position \n");
                    position = sk.nextInt();
                    sk.nextLine();
                    caser(position);
                    break;
                case "P":
                    casep();
                    break;
                case "F":
                    System.out.println("Enter a department code: ");
                    department = sk.nextLine();
                    casef(department);
                    break;
                case "L":
                    System.out.println("Enter course name: ");
                    coursename = sk.nextLine();
                    System.out.println("Enter department: ");
                    department = sk.nextLine();
                    System.out.println("Enter course code: ");
                    coursecode = sk.nextInt();
                    sk.nextLine();
                    System.out.println("Enter course section: ");
                    coursesection = sk.nextByte();
                    sk.nextLine();
                    System.out.println("Enter instructor: ");
                    instructor = sk.nextLine();
                    casel(coursename, department, coursecode, coursesection, instructor);
                    break;
                case "S":
                    cases();
                    break;
                case "B":
                    try {
                        caseb();
                    } catch (FullPlannerException e) {
                        System.out.println("Full Planner Exception");
                    }
                    break;
                case "PB":
                    casepb();
                    break;
                case "RB":
                    caserb();
                    break;
                case "Q":
                    System.out.println("Program termininating successfully...");
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid input");
            } //switch loop
        } //while loop
        sk.close();
    }

    public static void casea(String coursename, String department, int coursecode, byte coursesection, String instructor, int position) throws FullPlannerException{
        Course courseToAdd = new Course(coursename, department, coursecode, coursesection, instructor);
        mainplanner.addCourse(courseToAdd, position);
        System.out.format("%s %d.%02d successfully added to planner \n\n", department, coursecode, coursesection);
    }

    public static void caseg(int position) {
        System.out.println("No. Course Name               Department Code Section Instructor");
        System.out.println("-------------------------------------------------------------------------------");
        Course selected = mainplanner.getCourse(position);
        System.out.printf("%3d %-26s%-12s%-9d%-3d%-15s \n\n", (position), selected.getcoursename(), selected.getdepartment(), selected.getcode(), selected.getsection(), selected.getinstructor());
    }

    public static void caser(int position) {
        Course selected = mainplanner.getCourse(position);
        System.out.format("%s %d.%02d successfully removed from planner \n\n", selected.getdepartment(), selected.getcode(), selected.getsection());
        mainplanner.removeCourse(position);
    }

    public static void casep() {
        System.out.println("No. Course Name               Department Code Section Instructor");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println(mainplanner.toString());
    }

    public static void casef(String department) {
        Planner.filter(mainplanner, department);
    }

    public static void casel(String coursename, String department, int coursecode, byte coursesection, String instructor) {
        Course selected = new Course(coursename, department, coursecode, coursesection, instructor);
        Course[] temp = mainplanner.getCourses();
        boolean exists = false;
        for (int i = 0; i < mainplanner.size(); i++) {
            if (temp[i] != null) {
                if (temp[i].equals(selected)) {
                    System.out.format("%s %d.%02d is found in the planner at position %d \n\n", department, coursecode, coursesection, (i+1));
                    exists = true;
                }
            }
            else {
                break;
            }
        }
        if (exists == false) System.out.format("%s %d.%02d is not found in the planner  \n\n", department, coursecode, coursesection, instructor);
    }

    public static void cases() {
        System.out.format("There are %d courses in the planner \n", mainplanner.size());
    }

    public static void caseb() throws FullPlannerException {
        backupplanner = mainplanner.clone();
        System.out.println("Created a backup of the current planner \n");
    }

    public static void casepb() {
        System.out.println("No. Course Name               Department Code Section Instructor");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println(backupplanner.toString());
    }

    public static void caserb() {
        System.out.println("Planner successfully reverted to the backup copy");
        mainplanner = backupplanner;
    }
}