public class Student {
    
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String course;
    private String email;
    private String phone;
    private String address;
    private int studentNumber;

    // Constructor for inserting
    public Student(String firstName, String lastName, int age, String gender,
                   String course, String email, String phone,
                   String address, int studentNumber) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.course = course;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.studentNumber = studentNumber;
    }

    // Constructor for retrieving
    public Student(int id, String firstName, String lastName, int age, String gender,
                   String course, String email, String phone,
                   String address, int studentNumber) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.course = course;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.studentNumber = studentNumber;
    }

    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getCourse() { return course; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public int getStudentNumber() { return studentNumber; }

    @Override
    public String toString() {
        return id + " | " + firstName + " " + lastName +
                " | Age: " + age +
                " | " + gender +
                " | " + course +
                " | " + email +
                " | " + phone +
                " | " + address +
                " | " + studentNumber;
    }
}