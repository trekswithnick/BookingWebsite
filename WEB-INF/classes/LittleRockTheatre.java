import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

/** 
 * Author: Nicholas Browning (c3302779)
 * File Name: LittleRockTheatre.java
 */

/**
 * To access this file type in the search engine (Microsoft Edge)
 * 'http://localhost:8080/c3302779/LittleRockTheatre'
 */

/**
 * References:
 * Y. Daniel Liang. (2011). Introduction To Java Programming Comphrensive Version (10th). USA: Pearson.
 * Felke-Morris. T. (2015). Web Development and Design Foundations with HTML5 (7th). USA: Pearson.
 */
@WebServlet(urlPatterns = {"/LittleRockTheatre"})
public class LittleRockTheatre extends HttpServlet {

    /**
     * The variable 'DATE_TIME_FORMAT' creates the of the date being displayed.
     */
    private static final String DATE_TIME_FORMAT = "dd-MM-yy hh-mm-ss";
    
    /**
     * Instead of a multi-dimensional array. I experimented with a Hashmap which will store items as a collection. 
     * Using a hashmap has made it easier to create a URL.
     */
    private static HashMap<String, Seat> seats = new HashMap<String, Seat>();

    /**
     * An instance String field to store the generated unique security code.
     */
    private String securityCode; // 

    /**
     * Generate a stream of pseudorandom numbers and letters.
     */
    private static Random random = new Random();
    

    /**
     * Creates the HTML header. Called by the process method.
     */
    public static String htmlHead() 
    {
        String docType =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n"
                + "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" "
                + "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">\n";

        String head = 
                "   <head>\n"
                + "       <title>Little Rock Theatre - Home</title>\n"
                + "       <meta name=\"description\" content=\"Seat Booking System\" />\n"
                + "       <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\" media=\"screen\" />\n"
                + "       <script type=\"text/javascript\" src=\"js/valid.js\"></script>\n"
                + "	</head>\n"
                + "  <body>\n"
                + "     <h1>Little Rock Theatre - Home</h1>\n";

        return (docType + "\n" + head);
    }

    /**
     * Creates the HTML footer and address. Called by the process method.
     */
    public static String pageFooter() 
    {
        return ("					</div>\n"
                + "					<footer>\n"

                + "                         <p class=\"copy\">&copy; Copyright 2019, All Rights Reserved.</p>\n "                
                					
                + "				</footer>\n"

                + "             <address>"
                + "             <p>Nicholas Browning</p> <br/>"
                +           "c3302779 <br/>"
                +           getCurrentTime()
                + "             </address>\n"
                + "	</body>\n"
                + "</html>\n");
    }

    /**
     * returns current time as a string
     */
    private static String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
        return sdf.format(cal.getTime());
    }

    /**
     * The getSecurityCode method is used to create a unique security code which is used to 
     * validate a booking. 6 characters are used.
     */
    private static String getSecurityCode()
    {
		String characters = "1234567890QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";
		String out = "" + characters.charAt(random.nextInt(10));	
        for (int i = 0; i < 5; i++)
        {
			out += characters.charAt(random.nextInt(characters.length()));
		}
		return out;
    }

    /**
     * Stores the object in the securityCode variable
     */
    private void generateSecurityCode()
    {
        this.securityCode = getSecurityCode();
    }

    /**
     * The receives seat object as a string, a form html page is display, information is validated.
     */
    private String bookingForm(String id) {
        this.generateSecurityCode();

        StringBuilder builder = new StringBuilder();
        builder.append("	<div>Seat ").append(id).append(" Booking Request</div>\n");

        builder.append("<h2>Booking Form</h2>");
        builder.append("<form id=\"booking\" action=\"LittleRockTheatre\" onsubmit=\"return checkBooking(this);\" method=\"post\">\n");
        builder.append("<p>\n<label for=\"username\">User ID *:</label>\n<input type=\"text\" id=\"username\" name=\"username\" size=\"50\" />\n</p>\n");
        builder.append("<p>\n<label for=\"name\">Name *:</label>\n<input type=\"text\" id=\"name\" name=\"name\" size=\"50\" />\n</p>\n");
        builder.append("<p>\n<label for=\"phone\">Phone:</label>\n<input type=\"text\" id=\"phone\" name=\"phone\" size=\"50\" />\n</p>\n");
        builder.append("<p>\n<label for=\"address\">Address:</label>\n<input type=\"text\" id=\"address\" name=\"address\" size=\"50\" />\n</p>\n");
        builder.append("<p>\n<label for=\"email\">Email *:</label>\n<input type=\"text\" id=\"email\" name=\"email\" size=\"50\" />\n</p>\n");
        builder.append("<p>\n<label for=\"email\">Security Code *:</label>\n<input type=\"text\" id=\"security_code\" name=\"security_code\" size=\"50\" /> ").append(this.securityCode).append("\n</p>\n");
        builder.append("<div style=\"margin-left: 150px;\">\n");
        builder.append("<input type=\"submit\" value=\"Submit\" /> <input type=\"reset\" value=\"Clear\" />\n</div>\n");
        builder.append("<input type=\"hidden\" name=\"id\" value=\"").append(id).append("\" />");
        builder.append("<input type=\"hidden\" id=\"code\" name=\"code\" value=\"").append(this.securityCode).append("\" /> ");
        builder.append("</form>\n");

        return builder.toString();
    }

    /**
     * The viewSeat method displays seat with information
     */
    private String viewSeat(Seat seat) {
        StringBuilder builder = new StringBuilder();

        builder.append("<div>Name:</div><div>&nbsp;").append(seat.getName()).append("</div>");

        // phone number is displayed if the seat contains a phone number
        if (!seat.getPhone().equals("")) 
        {
            builder.append("<div>Phone:</div><div>&nbsp;").append(seat.getPhone()).append("</div>");
        }

        // address is displayed if the seat contains an address
        if (!seat.getAddress().equals("")) 
        {
            builder.append("<div>Address:</div><div>&nbsp;").append(seat.getAddress()).append("</div>");
        }
        builder.append("<div>Email:</div><div>&nbsp;").append(seat.getEmail()).append("</div>");
        builder.append("<div>Booking Time:</div><div>&nbsp;").append(seat.getBookingTime()).append("</div>");

        builder.append("</div>");
        return builder.toString();
    }

    /**
     * The getSeats method creates the table of seats 
     */
    private String getSeats() {
        StringBuilder builder = new StringBuilder();
        builder.append("<h2>Seat Layout</h2>");
        // Table is created
        builder.append("<div>\n");
            builder.append("<table style=\"text-align:center; table-align:center;\">\n");                        					

        for (char l = 'A'; l <= 'H'; l++) 
        {
            builder.append("\n<tr>\n");
                for (int i = 1; i <= 8; i++) 
                { 
                    String id = l + "" + i; // Creates the seat ID.
                    
                    // creates the link
                    builder.append("<td class=\"seat\">");
                    builder.append("<a href=\"LittleRockTheatre?seat=").append(id).append("\"><div class=\"seat\">");
                    if (seats.containsKey(id)) {
                        builder.append("<div class=\"booked\">").append(id).append("</div>");
                    } else {
                        builder.append(id);
                    }
                    builder.append("</a></td>\n");
                }
            builder.append("</tr>\n");
        }

        builder.append("</table>\n");

        builder.append("</div>\n");

        return builder.toString(); // Returns as a String.
    }
    
    /**
     * The canBookMore method counts the iteration of a certain username
     */
    private boolean canBookMore(String username) {
        // count is set to 0. if count is 3. The booking is cancelled.
        int count = 0;
        for (Seat seat : seats.values()) 
        {
            if (username.equalsIgnoreCase(seat.getUsername()))
            {
                count++;
            }
        }
        return (count < 3);
    }

    /**
     * Loads the textfile
     */
    private boolean load(){
        try
        {
			Scanner file = new Scanner(new File("Seat.txt"));
            for (Seat seat : seats.values())
            {
				String line = file.nextLine();
				String[] parameters = line.split(" ");				
			}
		}
		catch(Exception e){
			return false;
		}
		return true;
	}

    /**
     * The receives, processes and outputs booking submission into both the Hashmap and text file
     */
    private void addBooking(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if (request.getParameter("id") != null) 
        { 
            if (this.canBookMore(request.getParameter("username"))) {
                Seat seat = new Seat();
                seat.setUsername(request.getParameter("username"));
                seat.setName(request.getParameter("name"));
                seat.setPhone(request.getParameter("phone"));
                seat.setAddress(request.getParameter("address"));
                seat.setEmail(request.getParameter("email"));
                seat.setBookingTime(getCurrentTime());
                // Places the booked seat into the HashMap.
                seats.put(request.getParameter("id"), seat);

                // writes into a text file
                try
                {
                    PrintWriter output = new PrintWriter("Seat.txt", "UTF-8");
                    
                    output.println(request.getParameter("username"));
                    output.println(request.getParameter("name"));
                    output.println(request.getParameter("phone"));
                    output.println(request.getParameter("address"));
                    output.println(request.getParameter("email"));
                    output.println(getCurrentTime());

                    output.close();
                }
                catch(Exception e){}
            } 
            else 
            {
                out.println("<div><p>As a result of user " + request.getParameter("username") + " attempting book more than 3 seats. Booking is disabled for that user</p></div><br />");
            }


        }
    }

    /**
     * The main function of the java page as well as creating the html pages
     */
    protected void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();

                // loads text file
                load();

                // creates HTML content
                out.println(htmlHead());
                String seat = request.getParameter("seat");
                if (seat != null && (!seats.containsKey(seat))) 
                {
                    out.println(this.bookingForm(seat));
                } 
                else 
                {
                    if (seat != null && seats.containsKey(seat)) 
                    {
                        out.println(this.viewSeat(seats.get(seat)));

                        out.println(this.bookingForm(seat));
                    } 
                    else 
                    {
                        addBooking(request, response);
                    }
                    out.println(this.getSeats());
                }

                out.println(pageFooter());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        // Use "request" to read incoming HTTP headers
		//(e.g.cookies) and HTML form data (e.g. data the user
		//entered and submitted)
		
		// Use "response" to specify the HTTP response line
		//and headers (e.g. specifying the content type,
		//setting cookies).
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        // Use "request" to read incoming HTTP headers
		//(e.g.cookies) and HTML form data (e.g. data the user
		//entered and submitted)
		
		// Use "response" to specify the HTTP response line
		//and headers (e.g. specifying the content type,
		//setting cookies).iuui
        process(request, response);
    }
}