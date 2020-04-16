package com.Service;

import com.Model.AppointmentModel;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Appointment")
public class AppointmentService {
	AppointmentModel appoinmentObj = new AppointmentModel();
	
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readAppointment() {
		return appoinmentObj.readAppointment();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(
			@FormParam("appointmentDate") String appointmentDate, 
			@FormParam("appointmentTime") String appointmentTime
			) {
		String output = appoinmentObj.insertAppointment(appointmentDate,appointmentTime);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String appointmentData) {
		// Convert the input string to a JSON object
		JsonObject appoinmentObject = new JsonParser().parse(appointmentData).getAsJsonObject();
		// Read the values from the JSON object
		String appointmentID = appoinmentObject.get("appointmentID").getAsString();
		String appointmentDate = appoinmentObject.get("appointmentDate").getAsString();
		String appointmentTime = appoinmentObject.get("appointmentTime").getAsString();
	
		String output = appoinmentObj.updateAppointment(appointmentID, appointmentDate,appointmentTime);
		return output;
	}
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String appointmentData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(appointmentData, "", Parser.xmlParser());
		// Read the value from the element <itemID>
		String appointmentID = doc.select("appointmentID").text();
		String output = appoinmentObj.deleteItem(appointmentID);
		return output;
	}
}

