Answers for Advanced Java Programming CAT-II

Based on the uploaded question paper 


---

C1 (a) User Registration Form with Validation

<!DOCTYPE html>
<html>
<head>
<title>Registration Form</title>
<script>
function validateForm() {
    let name = document.forms["regForm"]["name"].value;
    let email = document.forms["regForm"]["email"].value;
    let password = document.forms["regForm"]["password"].value;

    let emailPattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;

    if(name == "" || email == "" || password == "") {
        alert("All fields are required");
        return false;
    }

    if(!email.match(emailPattern)) {
        alert("Invalid Email Format");
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
    Name: <input type="text" name="name"><br><br>

    Email: <input type="text" name="email"><br><br>

    Password: <input type="password" name="password"><br><br>

    <input type="submit" value="Register">
</form>

</body>
</html>


---

C2 (a) JDBC Student Record Management

import java.sql.*;

public class StudentJDBC {
    public static void main(String args[]) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/college",
                    "root",
                    "root");

            Statement stmt = con.createStatement();

            // Insert Record
            stmt.executeUpdate("insert into student values(1,'Arun',20,'CSE')");

            // Update Record
            stmt.executeUpdate("update student set age=21 where id=1");

            // Display Records
            ResultSet rs = stmt.executeQuery("select * from student");

            while(rs.next()) {
                System.out.println(
                        rs.getInt(1) + " " +
                        rs.getString(2) + " " +
                        rs.getInt(3) + " " +
                        rs.getString(4));
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }
}


---

C3 (a) Servlet Student Login System

login.html

<!DOCTYPE html>
<html>
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


---

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

        if(uname.equals("admin") && pass.equals("1234")) {
            out.println("<h2>Login Successful</h2>");
            out.println("Welcome " + uname);
        }
        else {
            out.println("<h2>Invalid Credentials</h2>");
        }
    }
}


---

C4 (a) JSP Electricity Bill Calculation

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


---

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


---

bill.jsp

<jsp:useBean id="b" class="BillBean" />

<jsp:setProperty name="b" property="name" param="name"/>
<jsp:setProperty name="b" property="units" param="units"/>
<jsp:setProperty name="b" property="rate" param="rate"/>

<html>
<body>

<h2>Electricity Bill</h2>

Consumer Name:
<jsp:getProperty name="b" property="name"/>

<br><br>

Total Amount:
<%= b.getTotal() %>

</body>
</html>
