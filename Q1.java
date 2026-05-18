C1(a) – JavaScript Event Handling Web Page

<!DOCTYPE html>
<html>
<head>
    <title>JavaScript Event Handling</title>

    <script>
        // Function for button click
        function showMessage() {
            alert("Button Clicked Successfully!");
        }

        // Function for displaying entered text
        function displayText() {
            var text = document.getElementById("name").value;
            document.getElementById("result").innerHTML =
                "You entered: " + text;
        }
    </script>
</head>

<body>

    <h2>User Interaction using JavaScript</h2>

    <!-- Button Event -->
    <button onclick="showMessage()">Click Me</button>

    <br><br>

    <!-- Text Input Event -->
    Enter Your Name:
    <input type="text" id="name" onkeyup="displayText()">

    <p id="result"></p>

</body>
</html>

Output

When the button is clicked, a message box appears.

When the user types in the text field, the entered text is displayed instantly.



---

C1(b) – User Registration Form Validation

<!DOCTYPE html>
<html>
<head>
    <title>Registration Form Validation</title>

    <script>
        function validateForm() {

            let name = document.forms["regForm"]["name"].value;
            let email = document.forms["regForm"]["email"].value;
            let password = document.forms["regForm"]["password"].value;

            // Name validation
            if (name == "") {
                alert("Name cannot be empty");
                return false;
            }

            // Email validation
            let emailPattern =
                /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;

            if (!email.match(emailPattern)) {
                alert("Enter valid email");
                return false;
            }

            // Password validation
            if (password.length < 6) {
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


---

C2(a) – Servlet Program for Student Welcome Message

HTML File (index.html)

<!DOCTYPE html>
<html>
<head>
    <title>Student Form</title>
</head>
<body>

<h2>Student Registration</h2>

<form action="WelcomeServlet" method="post">

    Enter Student Name:
    <input type="text" name="sname">

    <input type="submit" value="Submit">

</form>

</body>
</html>


---

Servlet Program (WelcomeServlet.java)

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class WelcomeServlet extends HttpServlet {

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
                       throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        String name = request.getParameter("sname");

        out.println("<html><body>");
        out.println("<h2>Welcome, " + name + "</h2>");
        out.println("</body></html>");
    }
}


---

web.xml

<web-app>

    <servlet>
        <servlet-name>WelcomeServlet</servlet-name>
        <servlet-class>WelcomeServlet</servlet-class>
    </servlet>

    <servlet-mapping>
        <servlet-name>WelcomeServlet</servlet-name>
        <url-pattern>/WelcomeServlet</url-pattern>
    </servlet-mapping>

</web-app>

Output

If the student enters “Arun”, the servlet displays:

Welcome, Arun


---

C2(b)(i) – JDBC Programs for Retrieve, Update and Delete

Retrieve All Records

import java.sql.*;

public class RetrieveEmployee {

    public static void main(String args[]) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/company",
                "root",
                "root");

            Statement st = con.createStatement();

            ResultSet rs =
                st.executeQuery("SELECT * FROM employee");

            while(rs.next()) {

                System.out.println(
                    rs.getInt(1) + " " +
                    rs.getString(2));
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }
}


---

Update Employee Name

import java.sql.*;

public class UpdateEmployee {

    public static void main(String args[]) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/company",
                "root",
                "root");

            PreparedStatement ps =
                con.prepareStatement(
                "UPDATE employee SET name=? WHERE id=?");

            ps.setString(1, "Rahul");
            ps.setInt(2, 101);

            int i = ps.executeUpdate();

            System.out.println(i + " record updated");

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }
}


---

Delete Employee Record

import java.sql.*;

public class DeleteEmployee {

    public static void main(String args[]) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/company",
                "root",
                "root");

            PreparedStatement ps =
                con.prepareStatement(
                "DELETE FROM employee WHERE id=?");

            ps.setInt(1, 101);

            int i = ps.executeUpdate();

            System.out.println(i + " record deleted");

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }
}


---

C2(b)(ii) – JDBC Insert Using PreparedStatement

import java.sql.*;

public class InsertEmployee {

    public static void main(String args[]) {

        try {

            // Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create Connection
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/company",
                "root",
                "root");

            // PreparedStatement
            PreparedStatement ps =
                con.prepareStatement(
                "INSERT INTO employee VALUES(?, ?)");

            // Set Values
            ps.setInt(1, 101);
            ps.setString(2, "Arun");

            // Execute Query
            int i = ps.executeUpdate();

            System.out.println(i + " record inserted");

            // Close Connection
            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }
}


---

C3(a)(i) – JSP Application to Display Student Details

HTML Form (student.html)

<!DOCTYPE html>
<html>
<head>
    <title>Student Form</title>
</head>
<body>

<h2>Student Details Form</h2>

<form action="display.jsp" method="post">

    Name:
    <input type="text" name="sname"><br><br>

    Course:
    <input type="text" name="course"><br><br>

    <input type="submit" value="Submit">

</form>

</body>
</html>


---

JSP Page (display.jsp)

<%@ page language="java" %>

<html>
<body>

<h2>Student Details</h2>

<%
    String name = request.getParameter("sname");
    String course = request.getParameter("course");
%>

<p>
    Student Name : <%= name %>
</p>

<p>
    Course : <%= course %>
</p>

</body>
</html>


---

C3(a)(ii) – Advanced JSP Features

header.jsp

<h1>College Management System</h1>
<hr>


---

display.jsp

<%@ page import="java.util.Date" %>

<%@ include file="header.jsp" %>

<html>
<body>

<%
    String name = request.getParameter("sname");
    String course = request.getParameter("course");
%>

<h2>Student Information</h2>

<p>
    Name : <%= name %>
</p>

<p>
    Course : <%= course %>
</p>

<p>
    Current Date and Time :
    <%= new Date() %>
</p>

</body>
</html>


---

C3(b) – XML Document for Bookstore

books.xml

<?xml version="1.0"?>

<bookstore>

    <book>
        <bookid>101</bookid>
        <title>Java Programming</title>
        <author>James Gosling</author>
    </book>

    <book>
        <bookid>102</bookid>
        <title>Web Technology</title>
        <author>Robert</author>
    </book>

    <book>
        <bookid>103</bookid>
        <title>Database Systems</title>
        <author>Navathe</author>
    </book>

    <book>
        <bookid>104</bookid>
        <title>Python Basics</title>
        <author>Guido</author>
    </book>

    <book>
        <bookid>105</bookid>
        <title>Data Structures</title>
        <author>Mark Allen</author>
    </book>

</bookstore>


---

DTD File (books.dtd)

<!ELEMENT bookstore (book+)>

<!ELEMENT book (bookid, title, author)>

<!ELEMENT bookid (#PCDATA)>
<!ELEMENT title (#PCDATA)>
<!ELEMENT author (#PCDATA)>


---

XML with DTD Connection

<?xml version="1.0"?>

<!DOCTYPE bookstore SYSTEM "books.dtd">

<bookstore>

    <book>
        <bookid>101</bookid>
        <title>Java Programming</title>
        <author>James Gosling</author>
    </book>

</bookstore>
