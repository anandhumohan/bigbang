package com.chaipoint.deliveryappapis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.chaipoint.helperclasses.OrderDetails;
import com.chaipoint.supervisor.SuperVisorOperations;

@Path("/supervisor")
public class SuperVisorAPI {
	@Path("/status")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response orderStatus(ArrayList<String> storeList) {
		Map<String, ArrayList<OrderDetails>> orderDetails = new HashMap<String, ArrayList<OrderDetails>>();
		orderDetails = new SuperVisorOperations().getOrderStatus(storeList, orderDetails);
		return Response.ok(orderDetails, MediaType.APPLICATION_JSON).build();

	}
}
