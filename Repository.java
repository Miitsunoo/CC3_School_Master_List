import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private static final String URL = "jdbc:mysql://localhost:3306/masterlist";
    private static final String USER = "root";
    private static final String PASS = "aldrei123!";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL driver not found: " + e.getMessage());
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public boolean addStudent(Student s) {
        String sql = "INSERT INTO students (stud_fname, stud_lname, stud_age, stud_gender, stud_course, stud_email, stud_phonenum, stud_address, stud_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getFirstName());
            ps.setString(2, s.getLastName());
            ps.setInt(3, s.getAge());
            ps.setString(4, s.getGender());
            ps.setString(5, s.getCourse());
            ps.setString(6, s.getEmail());
            ps.setString(7, s.getPhone());
            ps.setString(8, s.getAddress());
            ps.setInt(9, s.getStudentNumber());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error adding student: " + e.getMessage());
            return false;
        }
    }

    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching students: " + e.getMessage());
        }
        return list;
    }

    public Student getStudentById(int id) {
        String sql = "SELECT * FROM students WHERE stud_ID = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching student: " + e.getMessage());
        }
        return null;
    }

    public boolean updateStudent(Student s) {
        if (s.getId() == 0) return false;
        String sql = "UPDATE students SET stud_fname = ?, stud_lname = ?, stud_age = ?, stud_gender = ?, stud_course = ?, stud_email = ?, stud_phonenum = ?, stud_address = ?, stud_number = ? WHERE stud_ID = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getFirstName());
            ps.setString(2, s.getLastName());
            ps.setInt(3, s.getAge());
            ps.setString(4, s.getGender());
            ps.setString(5, s.getCourse());
            ps.setString(6, s.getEmail());
            ps.setString(7, s.getPhone());
            ps.setString(8, s.getAddress());
            ps.setInt(9, s.getStudentNumber());
            ps.setInt(10, s.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating student: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE stud_ID = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting student: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteAllStudents() {
        String sql = "DELETE FROM students";
        try (Connection conn = getConnection(); Statement st = conn.createStatement()) {
            st.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            System.err.println("Error deleting all students: " + e.getMessage());
            return false;
        }
    }

    private Student mapRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("stud_ID");
        String fname = rs.getString("stud_fname");
        String lname = rs.getString("stud_lname");
        int age = rs.getInt("stud_age");
        String gender = rs.getString("stud_gender");
        String course = rs.getString("stud_course");
        String email = rs.getString("stud_email");
        String phone = rs.getString("stud_phonenum");
        String address = rs.getString("stud_address");
        int studNum = rs.getInt("stud_number");
        return new Student(id, fname, lname, age, gender, course, email, phone, address, studNum);
    }
}
