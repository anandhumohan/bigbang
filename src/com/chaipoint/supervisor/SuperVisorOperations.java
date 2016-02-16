package com.chaipoint.supervisor;

import java.util.ArrayList;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.chaipoint.dppojos.StaffMaster;
import com.chaipoint.helperclasses.OrderDetails;
import com.chaipoint.hibernatehelper.HibernateTemplate;

public class SuperVisorOperations {
	HibernateTemplate template = null;

	public Map<String, ArrayList<OrderDetails>> getOrderStatus(ArrayList<String> storeList,
			Map<String, ArrayList<OrderDetails>> orderDetails) {
		ArrayList<OrderDetails> newOrderList = getOrderList(storeList, "NEW");
		orderDetails.put("NEW", newOrderList);
		ArrayList<OrderDetails> confirmOrderList = getOrderList(storeList, "CONFIRM");
		orderDetails.put("CONFIRM", confirmOrderList);
		ArrayList<OrderDetails> readyOrderList = getOrderList(storeList, "READY");
		orderDetails.put("READY", readyOrderList);
		ArrayList<OrderDetails> dispatchedOrderList = getOrderList(storeList, "DISPATCHED");
		orderDetails.put("DISPATCHED", dispatchedOrderList);
		ArrayList<OrderDetails> deliveredOrderList = getOrderList(storeList, "DELIVERED");
		orderDetails.put("DELIVERED", deliveredOrderList);
		return orderDetails;
	}

	private ArrayList<OrderDetails> getOrderList(ArrayList<String> storeList, String status) {
		
		for(String store : storeList){
		ArrayList<OrderDetails> orderList = new ArrayList<OrderDetails>();
		Criteria criteria = template.getSession().createCriteria(OrderDetails.class);
		criteria.add(Restrictions.eq("status", status));
		 orderList = (ArrayList<OrderDetails>) template.get(criteria);
		orderList.addAll(orderList);
		}
		return getOrderList(null, null);
	}

	public HibernateTemplate getHibernatetemplate() {
		if (template == null) {
			template = new HibernateTemplate();
		}
		return template;
	}

}
