public class Planner {
    Course[] courses;
    final int MAX_COURSES = 50;
    public int courseCount = 0;

    /**
     * creates a plan object with the max number of courses
     */
    public Planner() {
        courses = new Course[MAX_COURSES];
    }

    public Course[] getCourses() {
        return courses;
    }

    /**
     * 
     * @param listofCourses
     * @return number of courses in listofCourses
     */
    public int size() {
        return courseCount;
    }

    /**
     * Checks to see if position in Planner will be valid and if Planner is full.
     * If it is, then shift all the positions of the courses in the list are depending on where the preference is
     * @param newCourse the new course to add to list
     * @param position the position of the course on the list
     */
    public void addCourse(Course newCourse, int position) throws FullPlannerException {
        if (courses[MAX_COURSES-1] != null) {
            throw new FullPlannerException("The planner is full");
        }
        if ((position < 1) || (position > courses.length)) {
            throw new IllegalArgumentException("The position is not in the valid range"); 
        }

        for (int i = courses.length; i > position; i--) {
            courses[i-1] = courses[i-2];
        }
        courses[position-1] = newCourse;
        courseCount+=1;
    }

    /**
     * adds course to end of listofCourses
     * @param newCourse new course
     */
    public void addCourse(Course newCourse) throws FullPlannerException{
        addCourse(newCourse, size()+1);
        courseCount+=1;
    }

    /**
     * removes the course at position then moves courses appropriately
     * @param position of course
     */
    public void removeCourse(int position) {
        if ((position > courses.length) || (position <= 0 || position > 50)) {
            throw new IllegalArgumentException("The position isn't a valid range");
        }
        for (int i = position-1; i < courses.length; i++) {
            if (i != 49) courses[i] = courses[i+1];
        }
        courseCount-=1;
    }

    /**
     * returns course at a certain position
     * @param position of course you want to retrieve
     * @return the course at the specified location
     */
    public Course getCourse(int position) {
        if ((position > courses.length) || (position <= 0 || position > 50)) {
            throw new IllegalArgumentException("The position is not within the valid range");
        }
        return courses[position-1];
    }

    /**
     * Displays a formatted table of the courses in the correct preference order
     * @param planner list of courses you are searching through
     * @param department the 3 letter department code for the course
     */
    public static void filter(Planner planner, String department) {
        System.out.println("No. Course Name               Department Code Section Instructor");
        System.out.println("-------------------------------------------------------------------------------");
        Course[] courses = planner.getCourses();
        for (int i = 0; i < courses.length; i++) {
            if (courses[i] == null) break;
            if (courses[i].getdepartment().equals(department)) {
                System.out.printf("%3d %-26s%-12s%-9d%-3d%-15s \n", (i+1), courses[i].getcoursename(), courses[i].getdepartment(), courses[i].getcode(), courses[i].getsection(), courses[i].getinstructor());
            }
            
        }
    }

    /**
     * Checks to see if a course exists
     * @param course
     * @return false if course doesn't exist and true if it does
     */
    public boolean exists(Course course) {
        for (int i = 0; i < courses.length; i++) {
            if (courses[i].equals(course)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @return new cloned planner
     */
    public Planner clone() {
        Planner newplanner = new Planner();
        Course[] newcourse = newplanner.getCourses();
        for (int i = 0; i < MAX_COURSES; i++) {
            if (courses[i] == null) break;
            newcourse[i] = courses[i];
        }
        return newplanner;
    }

    /**
     * prints the new string
     */
    public void printAllCourses() {
        System.out.println(toString());
    }

    /**
     * 
     * @return the formmated string
     */
    public String toString() {
        String news = "";
        for (int i = 0; i < courses.length; i++) {
            if (courses[i] == null) break;
            news += String.format("%3d %-26s%-12s%-9d%-3d%-15s \n", (i+1), courses[i].getcoursename(), courses[i].getdepartment(), courses[i].getcode(), courses[i].getsection(), courses[i].getinstructor());
        }
        return news;
    }
}
