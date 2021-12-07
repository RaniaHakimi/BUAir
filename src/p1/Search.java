package p1;

import java.io.IOException;

import java.io.PrintWriter;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.*;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import p1.Flight;

/**
 * 
 * Servlet implementation class Search
 * 
 */

@WebServlet("/Search")

public class Search extends HttpServlet {

String source;

String destination;

String flight_date;

String class_flight;

//setting the url for mysql server connection
String url = "jdbc:mysql://localhost:3306/airline?useSSL=false";
//setting root variable
String root = "root";
// setting password
String password = "rootadmin";

// function that returns an arraylist of Flight object

public ArrayList<Flight> search_flights(String type, String from, String to, String date, String clas,
HttpServletRequest request) {

ArrayList<Flight> flights = new ArrayList<Flight>();

try {

HttpSession session = request.getSession();
Date d = null;
d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
SimpleDateFormat s = new SimpleDateFormat("E");
String day = s.format(d);
day = day.toLowerCase();
session.setAttribute("day", day);


// selecting based on the day

String sql = "select flight.id,company.company,route.distance,flight.start_time,flight.dest_time,flight.mon_seats,flight.mon_seats_bus,flight.price,flight.price2 from flight inner join company on company.id=flight.company_id inner join route on route.id=flight.route_id where route.from1=? and route.to1=? and mon=1";

if (day.equals("mon"))

sql = "select  flight.id,company.company,route.distance,flight.start_time,flight.dest_time,flight.mon_seats,flight.mon_seats_eco,flight.price,flight.price2 from flight inner join company on company.id=flight.company_id inner join route on route.id=flight.route_id where route.from1=? and route.to1=? and mon=1";

else if (day.equals("tue"))

sql = "select  flight.id,company.company,route.distance,flight.start_time,flight.dest_time,flight.tue_seats,flight.tue_seats_bus,flight.price,flight.price2 from flight inner join company on company.id=flight.company_id inner join route on route.id=flight.route_id where route.from1=? and route.to1=? and tue=1";

else if (day.equals("wed"))

sql = "select  flight.id,company.company,route.distance,flight.start_time,flight.dest_time,flight.wed_seats,flight.wed_seats_bus,flight.price,flight.price2 from flight inner join company on company.id=flight.company_id inner join route on route.id=flight.route_id where route.from1=? and route.to1=? and wed=1";

else if (day.equals("thu"))

sql = "select  flight.id,company.company,route.distance,flight.start_time,flight.dest_time,flight.thu_seats,flight.thu_seats_bus,flight.price,flight.price2 from flight inner join company on company.id=flight.company_id inner join route on route.id=flight.route_id where route.from1=? and route.to1=? and thu=1";

else if (day.equals("fri"))

sql = "select  flight.id,company.company,route.distance,flight.start_time,flight.dest_time,flight.fri_seats,flight.fri_seats_bus,flight.price,flight.price2 from flight inner join company on company.id=flight.company_id inner join route on route.id=flight.route_id where route.from1=? and route.to1=? and fri=1";

else if (day.equals("sat"))

sql = "select  flight.id,company.company,route.distance,flight.start_time,flight.dest_time,flight.sat_seats,flight.sat_seats_bus,flight.price,flight.price2 from flight inner join company on company.id=flight.company_id inner join route on route.id=flight.route_id where route.from1=? and route.to1=? and sat=1";

else if (day.equals("sun"))

sql = "select  flight.id,company.company,route.distance,flight.start_time,flight.dest_time,flight.sun_seats,flight.sun_seats_bus,flight.price,flight.price2 from flight inner join company on company.id=flight.company_id inner join route on route.id=flight.route_id where route.from1=? and route.to1=? and sun=1";


Class.forName("com.mysql.jdbc.Driver");

Connection connection = DriverManager.getConnection(url, root, password);

PreparedStatement st = connection.prepareStatement(sql);

st.setString(1, from);

st.setString(2, to);

ResultSet rs = st.executeQuery();

while (rs.next()) {

Flight a = new Flight(rs.getInt(1), rs.getString(2), from, to, rs.getString(4),
rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getFloat(8), rs.getFloat(9));

flights.add(a);
}
}
catch (Exception e) {
e.printStackTrace();
}
return flights;
}
private static final long serialVersionUID = 1L;




protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {

response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");

PrintWriter out = response.getWriter();

HttpSession ses = request.getSession();

String from = request.getParameter("from").toString();

String to = request.getParameter("to").toString();

// handling case where source == destination

if (from.equals(to)) {

out.print("<html><script>alert('Your destination may not be the same as your departure city')</script>");

out.print("<script>location.href='search_flight.jsp'</script></html>");

}

String date = request.getParameter("date").toString();

String clas = request.getParameter("clas").toString();

Date date2 = new Date();

DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

String strDate = dateFormat.format(date2);

try {

SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

Date d1 = sdf.parse(date); // input date

System.out.println(d1);

Date d2 = sdf.parse(strDate);

System.out.println(d2);

Date f1 = new Date(); // todays date

Calendar c = Calendar.getInstance();

c.setTime(f1);

c.add(Calendar.DATE, 7);

Date c1 = c.getTime(); // date after 7 days

if (d1.compareTo(d2) < 0) {
out.print("<html><script>alert('Cannot select a date before today')</script>");
out.print("<script>location.href='search_flight.jsp'</script></html>");

}

else if (f1.compareTo(d1) * d1.compareTo(c1) < 0) {
out.print("<html><script>alert('Flights are only available 7 days in advance')</script>");
out.print("<script>location.href='search_flight.jsp'</script></html>");
}
}
catch (Exception e) {
}
int i = 0;

ses.setAttribute("i", i);

ses.setAttribute("class", clas);

out = response.getWriter();

String val = null;

val = "international";

ArrayList<Flight> a = search_flights(val, from, to, date, clas, request);

if (a.size() == 0) {
out.print("<html><script>alert('Flight not available')</script>");
out.print("<script>location.href='search_flight.jsp'</script></html>");
}

else {
out.print("<html><head>" + "<link rel=\"stylesheet\"\r\n"
+ " href=\"https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css\"\r\n"
+ " integrity=\"sha384-r4NyP46KrjDleawBgD5tp8Y7UzmLA05oM1iAEQ17CSuDqnUK2+k9luXQOfXJCJ4I\"\r\n"
+ " crossorigin=\"anonymous\">\r\n" + "<script\r\n"
+ " src=\"https://cdn.jsdelivr.net/npm/bs-custom-file-input/dist/bs-custom-file-input.js\"></script>\r\n"
+ "\r\n" + "<script src='https://api.mapbox.com/mapbox-gl-js/v1.12.0/mapbox-gl.js'></script>\r\n"
+ "<link href='https://api.mapbox.com/mapbox-gl-js/v1.12.0/mapbox-gl.css'\r\n"
+ " rel='stylesheet' />\r\n" + "<link rel=\"stylesheet\" href=\"css/app.css?version=1\"></head>"
+ "<body class=\"d-flex flex-column vh-100\" style = 'background-image: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)),\r\n"
+ " url(\"https://source.unsplash.com/ANAa-P_e2lE\");'>");

out.print("<nav class=\"navbar sticky-top navbar-expand-lg navbar-dark bg-dark\">\r\n"
+ " <div class=\"container-fluid\">\r\n"
+ " <a class=\"navbar-brand\" href=\"#\">BUAir</a>\r\n"
+ " <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\"\r\n"
+ " data-target=\"#navbarNavAltMarkup\" aria-controls=\"navbarNavAltMarkup\"\r\n"
+ " aria-expanded=\"false\" aria-label=\"Toggle navigation\">\r\n"
+ " <span class=\"navbar-toggler-icon\"></span>\r\n" + " </button>\r\n"
+ " <div class=\"collapse navbar-collapse\" id=\"navbarNavAltMarkup\">\r\n"
+ " <div class=\"navbar-nav\">\r\n"
+ " <a class=\"nav-link\" href=\"home.jsp\">Home</a>\r\n"
+ " </div>\r\n" + "<div class=\"navbar-nav ml-auto\">\r\n"
+ " <form action=\"Customer\" method=\"post\">\r\n"
+ " <button class=\"btn btn-danger btn-block\" name=\"s1\" value=\"logout\">Log\r\n"
+ " Out</button>\r\n" + " </form>\r\n"
+ " </div>\r\n" + " </div>\r\n" + " </div>\r\n" + " </nav>");
out.print("<main class=\"container mt-4 mb-4\">\r\n" + "\r\n" + " <div\r\n"
+ " class=\"container d-flex justify-content-center align-items-center mt-2 mb-2\">\r\n"
+ " <div class=\"card text-center\">\r\n" + " <div class=\"card p-3\">");

Iterator<Flight> itr = a.iterator();

while (itr.hasNext()) {

Flight flight = (Flight) itr.next();

if (i == 0) {

out.print("<html>"
+ "<head>"
+ "<style>"
+ "table {\r\n" +
"  font-family: arial, sans-serif;\r\n" +
"  border-collapse: collapse;\r\n" +
"  width: 100%;\r\n" +
"}\r\n" +
"\r\n" +
"td, th {\r\n" +
"  border: 1px solid #dddddd;\r\n" +
"  text-align: left;\r\n" +
"  padding: 8px;\r\n" +
"}\r\n" +
"\r\n" +
"tr:nth-child(even) {\r\n" +
"  background-color: #dddddd;\r\n" +
"}"
+ "</style>"
+ "</head>"
+ "</html>");
out.print("<table>"
+ "<tr>"
+ "<th>Flight ID</th>"
+ "<th>From</th>"
+ "<th>To</th>"
+ "<th>Company</th>"
+ "<th>Departure Time</th>"
+ "<th>Economy Seat Price</th>"
+ "<th>Business Seat Price</th>"
+ "<th>Available Economy Seats</th>"
+ "<th>Available Business Seats</th>"
+ "</tr>"
);

i = 1;

}

int eco = flight.seat_eco;

int bus = flight.seat_bus;

out.print("<html>"
+ "<head>"
+ "<style>"
+ "table {\r\n" +
"  font-family: arial, sans-serif;\r\n" +
"  border-collapse: collapse;\r\n" +
"  width: 100%;\r\n" +
"}\r\n" +
"\r\n" +
"td, th {\r\n" +
"  border: 1px solid #dddddd;\r\n" +
"  text-align: left;\r\n" +
"  padding: 8px;\r\n" +
"}\r\n" +
"\r\n" +
"tr:nth-child(even) {\r\n" +
"  background-color: #dddddd;\r\n" +
"}"
+ "#one{"
+ "color:green;}"
+ "#two{"
+ "color:red;}"
+ "</style>"
+ "</head>"
+ "</html>");
out.print("<tr>"
+ "<td>" + flight.id + "</td>"
+ "<td>" + from + "</td>"
+ "<td>" + to + "</td>"
+ "<td>" + flight.company + "</td>"
+ "<td>" + flight.departure_time + "</td>"
+ "<td>" + flight.price_eco + "</td>"
+ "<td>" + flight.price_bus + "</td>"

);

ses.setAttribute("from", from);

ses.setAttribute("to", to);

ses.setAttribute("date", date);

ses.setAttribute("eco1", eco);

ses.setAttribute("bus1", bus);

if (eco > 0) {
out.print("<td id='one'><b><big>"
+ eco + " seats available</big></b></td>"

);

} else {
out.print("<td id='two'><b><big>Seats Unavailable</big></b></td>");

}

if (bus > 0) {
out.print("<td id='one'><b><big>"
+ bus + " seats available</big></b></td></tr>");
}
else {
out.print("<td id='two'><b><big>Seats Unavailable</big></b></td></tr>");
}
}
if (clas.equals("economy")) {
out.println("<center><tr><form action='add.jsp' method='post'>"
+ "<b><big style='margin-left:30px'>Flight ID:</big></b>"
+ "<input class='form-select mt-1'type='number' name='id' style='padding:5px 10px;margin-right:15px;' placeholder='Enter Flight ID'><br><br>");
out.print("<b><big style='margin-left:30px' id='1'>Enter Number of Economy Class Seats:</big></b>"
+ "<input class='form-select mt-1 mb-2' type='number' name='num' style='padding:5px 10px;margin-right:15px;' placeholder='Enter Num Seats'>");
out.print(
"<input class='mt-3 btn btn-success' type='submit' style='padding:5px 10px' value='Book'></form><br><br></tr></center></table>");

}

else {

out.println("<center><tr><form action='add.jsp' method='post'>"
+ "<b><big style='margin-left:30px'>Flight Id:</big></b>"
+ "<input class='form-select mt-1' type='number' name='id' style='padding:5px 10px;margin-right:15px;' placeholder='Enter Flight ID'><br><br>");
out.print("<b><big id='2'>Enter Number of Business Class Seats:</big></b>"
+ "<input class='form-select mt-1 mb-2' type='number' name='num' style='padding:5px 10px;margin-right:15px;' placeholder='Enter Num Seats'>");
out.print(
"<input class='mt-3 btn btn-success' type='submit' style='padding:5px 10px' value='Book'></form><br><br></tr></center></table>");
}
out.print(" </div>\r\n" + " </div>\r\n" + " </div>\r\n" + "\r\n"
+ " </main> <footer class=\"footer bg-dark py-3 mt-auto\">\r\n"
+ " <div class=\"container\">\r\n"
+ " <span class=\"text-white\">&copy; BUAir 2021</span>\r\n" + " </div>\r\n"
+ " </footer>\r\n" + " <script\r\n"
+ " src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js\"\r\n"
+ " integrity=\"sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo\"\r\n"
+ " crossorigin=\"anonymous\"></script>\r\n" + " <script\r\n"
+ " src=\"https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js\"\r\n"
+ " integrity=\"sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/\"\r\n"
+ " crossorigin=\"anonymous\"></script>\r\n" + "\r\n"
+ " <script src='js/validateForms.js'></script>\r\n" + "</body>\r\n" + "\r\n" + "</html>");

out = response.getWriter();
}
}
}