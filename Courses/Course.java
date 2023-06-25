public class Course {
    private String coursename;
    private String department;
    private int code;
    private byte section;
    private String instructor;

    /**
     * constructs an object with a course name, department, class code, class section, and instructor name
     */
    public Course(String coursename, String department, int code, byte section, String instructor) {
        this.coursename = coursename;
        this.department = department;
        this.code = code;
        this.section = section;
        this.instructor = instructor;
    }

    /**
     * creates a cloned course with the same course name, department, course code, section, and instructor
     * @return cloned object
     */
    public Object clone() {
        Course clonedcourse = new Course(this.coursename, this.department, this.code, this.section, this.instructor);
        return clonedcourse;
    }

    /**
     * 
     * @param coursename takes in a course to compare
     * @return true if courses are the same 
     */
    public boolean equals(Object obj) {
        if (!(obj instanceof Course)) {
            return false;
        }
        Course c = (Course)obj;
        if (!(this.coursename.equals(c.getcoursename()))) {
            return false;
        }
        if (!(this.department.equals(c.getdepartment()))) {
            return false;
        }
        if (this.code != c.getcode()) {
            return false;
        }
        if (Byte.compare(this.section, c.getsection()) != 0) {
            return false;
        }
        if (!(this.instructor.equals(c.getinstructor()))) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @return course name
     */
    public String getcoursename() {
        return coursename;
    }

    /**
     * 
     * @return department
     */
    public String getdepartment() {
        return department;
    }

    /**
     * 
     * @return code for class
     */
    public int getcode() {
        return code;
    }

    /**
     * 
     * @return class section
     */
    public byte getsection() {
        return section;
    }

    /**
     * 
     * @return class instructor
     */
    public String getinstructor() {
        return instructor;
    }

    /**
     * 
     * @param coursename 
     */
    public void setcoursename(String coursename) {
        this.coursename = coursename;
    }

    /**
     * 
     * @param department
     */
    public void setdepartment(String department) {
        this.department = department;
    }

    /**
     * 
     * @param code
     */
    public void setcode(int code) {
        this.code = code;
    }

    /**
     * 
     * @param section
     */
    public void setsection(byte section) {
        this.section = section;
    }

    /**
     * 
     * @param instructor
     */
    public void setinstructor(String instructor) {
        this.instructor = instructor;
    }



}
