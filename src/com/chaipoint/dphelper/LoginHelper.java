package com.chaipoint.dphelper;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.HibernateIterator;

import com.chaipoint.dppojos.StaffMaster;
import com.chaipoint.helperclasses.DpStatus;
import com.chaipoint.helperclasses.OrderStatus;
import com.chaipoint.hibernatehelper.HibernateTemplate;

public class LoginHelper {
	public static Map<String, Queue<String>> DPQueues = new HashMap<String, Queue<String>>();
	public static Map<String, DpStatus> dpStatus = new HashMap<String, DpStatus>();

	public static Map<String, OrderStatus> orderStatus = new HashMap<String, OrderStatus>();
	public static Map<String, String> storeToNinja = new HashMap<String, String>();

	HibernateTemplate template = null;
	DpStatus status = null;
	Queue<String> queue = null;

	public String login(ArrayList<String> storeId, String mtfId, String password, String roleId) {

		String msg = "";
		boolean validate;
		if (roleId == "DP" && mtfId != null && roleId != null) {
			validate = userValidate(mtfId, password);
			String DPId = "";
			// create maps and queue
			if (validate == true) {
				queue = new LinkedList<String>();
				queue.add(DPId);
				DPQueues.put(storeId.get(0), queue);
				status = new DpStatus();
				status.setDpId(DPId);
				status.setStatus("AVAILABLE");
				dpStatus.put(DPId, status);

				Random random = new SecureRandom();
				String token = new BigInteger(130, random).toString(32);
				return token;
			} else {
				msg = " username or password is incorrect";
			}

		} else {
			msg = "username or password format error";
		}

		if (roleId == "Ninja" && mtfId != null && roleId != null) {
			validate = userValidate(mtfId, password);

			if (validate == true) {
				// storeToNinja.put(storeId, mtfId);
				Random random = new SecureRandom();
				String token = new BigInteger(130, random).toString(32);
				return token;
			}

			msg = "username or password format error";
		}

		if (roleId == "Supervisor" && mtfId != null && roleId != null) {
			validate = userValidate(mtfId, password);
			if (validate == true) {
				Random random = new SecureRandom();
				String token = new BigInteger(130, random).toString(32);
				return token;
			}

		} else {
			msg = "username or password format error";
		}

		return msg;
	}

	public boolean userValidate(String mtfId, String password) {
		String storedPassword = "";

		// checking mtfId is in correct format
		Criteria criteria = template.getSession().createCriteria(StaffMaster.class);
		criteria.add(Restrictions.eq("name", mtfId));
		criteria.setProjection(Projections.property("password"));
		ArrayList<String> DPList = (ArrayList<String>) template.get(criteria);
		storedPassword = DPList.get(0);

		if (storedPassword.equals(password)) {
			return true;
		} else {
			return false;
		}

	}

	public HibernateTemplate getTemplate() {
		if (template == null) {
			template = new HibernateTemplate();
		}
		return template;
	}

}
