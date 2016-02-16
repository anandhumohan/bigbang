package com.chaipoint.ninja;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

//import com.chaipoint.deliverypartner.DPselector;
import com.chaipoint.helperclasses.DpStatus;
import com.chaipoint.helperclasses.OrderDetails;
import com.chaipoint.helperclasses.OrderStatus;

//import com.chaipoint.deliverypartner.DPselector;

public class NinjaOperations {
	
	public static Map<String, Queue<String>> DPQueues = new HashMap<String, Queue<String>>();
	public static Map<String, DpStatus> dpStatus = new HashMap<String, DpStatus>();

	public static Map<String, OrderStatus> orderStatus = new HashMap<String, OrderStatus>();
	public static Map<String, String> storeToNinja = new HashMap<String, String>();

	DpStatus status = null;
	Queue<String> queue = null;
	public OrderDetails newState(String orderId, String state) {

		String ordeDetails = "";
		OrderDetails details = new OrderDetails();
		if (!orderId.equals(null) && state.equals("NEW")) {
			// get order details
			
		}

		return details;
	}

	public String confirmState(String orderId, String state) {

		String status = "";
		if (!orderId.equals(null) && state.equals("CONFIRMED")) {

			// send order status for tracking
			status = "OK";
		}
		return status;
	}

	public String readyState(String orderId, String state) {

		String storeId = "";
		String status = "";
		if (!orderId.equals(null) && state.equals("READY")) {
			String dpId = getAtStoreDP(storeId);
			if (dpId == null) {
				ArrayList<String> availDp = new ArrayList<String>();
				availDp = getAvailableDp(storeId);
				// push notification to all available availDp
				status = "OK";
			} else {
				// give order details to DpId
			}

			// send order status

		}
		return status;
	}

	public String reassignState(String orderId, String state, String oldDPId, String newDPId) {

		String status = "";
		if (!orderId.equals(null) && state.equals("REASSIGN")) {

			// Send order status for tracking, Generate alert for stakeholders
			status = "OK";
		}
		return status;
	}

	public String transferState(String orderId, String state, String newStoreId, String transferReason) {

		String status = "";
		if (!orderId.equals(null) && state.equals("TRANSFER")) {
			// Send order status for tracking, Generate alert for stakeholders
			status = "OK";
		}
		return status;
	}

	public String cancelState(String orderId, String state, String cancelReason) {

		String status = "";
		if (!orderId.equals(null) && state.equals("CANCELLED")) {
			// Send order status for tracking, Generate alert for stakeholders
			status = "OK";
		}
		return status;
	}
	
	
	
	public String getAtStoreDP(String storeId) {

		String dpId = "";
		queue = DPQueues.get(storeId);
		dpId = queue.poll();
		return dpId;

	}
	
	public ArrayList<String> getAvailableDp(String storeId) {

		ArrayList<String> dps = new ArrayList<>(DPQueues.get(storeId));
		ArrayList<String> availDp = new ArrayList<>();
		for (String status : dps) {
			if (dpStatus.get(status).getStatus() == "AVAILABLE") {
				availDp.add(status);

			}
		}

		return availDp;
	}

}
