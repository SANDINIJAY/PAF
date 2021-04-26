package com;

//For REST Service
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//For XML
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

//For JSON
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Fund;

@Path("/Fund")
public class FundService {
	Fund fundObj = new Fund();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readFund() {
		return fundObj.readFund();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertFund(@FormParam("amount") String amount, @FormParam("description") String description) {
		String output = fundObj.insertFund(amount, description);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateFund(String fundData) {
//Convert the input string to a JSON object
		JsonObject fundObject = new JsonParser().parse(fundData).getAsJsonObject();
//Read the values from the JSON object
		String id = fundObject.get("id").getAsString();
		String amount = fundObject.get("amount").getAsString();
		String description = fundObject.get("description").getAsString();

		String output = fundObj.updateFund(id, amount, description);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFund(String fundData) {
//Convert the input string to an XML document
		Document doc = Jsoup.parse(fundData, "", Parser.xmlParser());

		String fundID = doc.select("id").text();
		String output = fundObj.deleteFund(fundID);
		return output;
	}
}
