C1 (a) – User Registration Form with Validation 

<!DOCTYPE html>
<html>
<head>
    <title>User Registration</title>
    <script>
        function validateForm() {

            let name = document.forms["regForm"]["name"].value;
            let email = document.forms["regForm"]["email"].value;
            let password = document.forms["regForm"]["password"].value;

            let emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

            if(name == "" || email == "" || password == "") {
                alert("All fields are required");
                return false;
            }

            if(!emailPattern.test(email)) {
                alert("Enter valid email address");
                return false;
            }

            if(password.length < 6) {
                alert("Password must contain at least 6 characters");
                return false;
            }

            alert("Registration Successful");
            return true;
        }
    </script>
</head>

<body>

<h2>User Registration Form</h2>

<form name="regForm" onsubmit="return validateForm()">

    Name:
    <input type="text" name="name"><br><br>

    Email:
    <input type="text" name="email"><br><br>

    Password:
    <input type="password" name="password"><br><br>

    <input type="submit" value="Register">

</form>

</body>
</html>

Output

Displays registration form.

Shows error message for empty fields.

Validates email format.

Checks password length.

Displays success message after valid submission.



---

C2 (a) – JDBC Student Record Management 

Java Program

import java.sql.*;

public class StudentDB {

    public static void main(String args[]) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/college",
                    "root",
                    "root");

            // Insert Record
            String insertQuery =
                    "insert into Student values(?,?,?)";

            PreparedStatement ps = con.prepareStatement(insertQuery);

            ps.setInt(1, 101);
            ps.setString(2, "Arun");
            ps.setInt(3, 85);

            ps.executeUpdate();

            // Update Record
            String updateQuery =
                    "update Student set marks=? where id=?";

            PreparedStatement ps1 = con.prepareStatement(updateQuery);

            ps1.setInt(1, 90);
            ps1.setInt(2, 101);

            ps1.executeUpdate();

            // Display Records
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select * from Student");

            System.out.println("ID\tNAME\tMARKS");

            while(rs.next()) {
                System.out.println(
                        rs.getInt(1) + "\t" +
                        rs.getString(2) + "\t" +
                        rs.getInt(3));
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }
}

Output

ID    NAME    MARKS
101   Arun    90


---

C3 (a) – Servlet Based Student Login System 

login.html

<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>

<h2>Student Login</h2>

<form action="LoginServlet" method="post">

Username:
<input type="text" name="uname"><br><br>

Password:
<input type="password" name="pass"><br><br>

<input type="submit" value="Login">

</form>

</body>
</html>

LoginServlet.java

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest req,
                       HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");

        PrintWriter out = res.getWriter();

        String uname = req.getParameter("uname");
        String pass = req.getParameter("pass");

        if(uname.equals("admin") && pass.equals("12345")) {
            out.println("<h2>Login Successful</h2>");
        }
        else {
            out.println("<h2>Invalid Username or Password</h2>");
        }
    }
}

Output

Correct username/password → “Login Successful”

Wrong credentials → “Invalid Username or Password”



---

C4 (a) – JSP Electricity Bill Calculator 

index.html

<html>
<body>

<h2>Electricity Bill</h2>

<form action="bill.jsp">

Consumer Name:
<input type="text" name="name"><br><br>

Units Consumed:
<input type="text" name="units"><br><br>

Rate Per Unit:
<input type="text" name="rate"><br><br>

<input type="submit" value="Calculate">

</form>

</body>
</html>

BillBean.java

public class BillBean {

    private String name;
    private int units;
    private double rate;

    public void setName(String name) {
        this.name = name;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public double getTotal() {
        return units * rate;
    }
}

bill.jsp

<jsp:useBean id="b" class="BillBean" />

<jsp:setProperty name="b" property="*" />

<html>
<body>

<h2>Electricity Bill</h2>

Consumer Name:
<jsp:getProperty name="b" property="name" />
<br><br>

Total Amount:
<%= b.getTotal() %>

</body>
</html>

Output

Electricity Bill
Consumer Name : Arun
Total Amount : 500.0
