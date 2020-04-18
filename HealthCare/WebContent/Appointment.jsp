<%@page import="com.controller.Appointment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<%
	//Save user---------------------------------
	if (request.getParameter("appointmentDate") != null) {
		Appointment appointmentObj = new Appointment();
		String stsMsg = "";

		if (request.getParameter("hidAppointmentIDSave") == "") {
			stsMsg = appointmentObj.insertAppointment(request.getParameter("appointmentDate"), request.getParameter("appointmentTime"),
					request.getParameter("appointmentDoctor"), request.getParameter("appointmentHospital"));
		} else //Update
		{
			stsMsg = appointmentObj.updateAppointment(request.getParameter("hidAppointmentIDSave"), request.getParameter("appointmentDate"),
					request.getParameter("appointmentTime"),
					request.getParameter("appointmentDoctor"), request.getParameter("appointmentHospital"));
		}

		session.setAttribute("statusMsg", stsMsg);
	}
	//Delete user---------------------------------
	if (request.getParameter("hidAppointmentIDDelete") != null) {
		Appointment appointmentObj = new Appointment();
		String stsMsg = appointmentObj.deleteAppointment(request.getParameter("hidAppointmentIDDelete"));
		session.setAttribute("statusMsg", stsMsg);
	}
%>
    
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Appointment Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.5.0.min.js"></script>
<script src="Components/User.js"></script>
</head>
<body>

 <div class="container">
		<div class="row">
			<div class="col-8">
				<h1>Appointment Management</h1>
				<form id="formAppoinment" name="formAppoinment" method="post" action="Appoinment.jsp">
					Appointment date : <input id="appointmentDate" name="date" type="text"
						class="form-control form-control-sm"><br> time :
					<input id="appointmentTime" name="time" type="text"
						class="form-control form-control-sm"><br> doctor : <input
						id="appointmentDoctor" name="doctor" type="text"
						class="form-control form-control-sm"><br> hospital : <input
						id="appointmentHospital" name="hospital" type="text"
						class="form-control form-control-sm"><br> <input
						id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidAppointmentIDSave" name="hidAppointmentIDSave" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success">
					<%
						out.print(session.getAttribute("statusMsg"));
					%>
				</div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<%
					Appointment appointmentObj = new Appointment();
					out.print(appointmentObj.readAppointment());
				%>
			</div>
		</div>
	</div>
</body>
</html>