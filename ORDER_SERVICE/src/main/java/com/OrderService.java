package com;

import model.Order;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Order")
public class OrderService
{
Order orderObj = new Order();
@GET
@Path("/")
@Produces(MediaType.TEXT_HTML)
public String readOrder()
{
	return orderObj.readOrder();
}
@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertOrder(@FormParam("buyerId") String buyerId,
@FormParam("buyerName") String buyerName,
@FormParam("paymentdetails") String paymentdetails)
{
String output = orderObj.insertOrder(buyerId, buyerName, paymentdetails);
return output;
}

@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updateOrder(String orderData)
{
//Convert the input string to a JSON object
JsonObject orderObject = new JsonParser().parse(orderData).getAsJsonObject();
//Read the values from the JSON object
String orderId = orderObject.get("orderId").getAsString();
String buyerId = orderObject.get("buyerId").getAsString();
String buyerName = orderObject.get("buyerName").getAsString();
String paymentdetails = orderObject.get("paymentdetails").getAsString();


String output = orderObj.updateOrder( orderId, buyerId, buyerName , paymentdetails );
return output;
}

@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deleteOrder(String orderData)
{
//Convert the input string to an XML document
Document doc = Jsoup.parse(orderData, "", Parser.xmlParser());

String orderId = doc.select("OrderId").text();
String output = orderObj.deleteOrder(orderData);
return output;
}
}