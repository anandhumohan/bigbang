package com.chaipoint.helperclasses;

import java.util.List;

public class OrderDetails {
	private List<OrderDetail> order_details;
	private PriceDetails price_details;
	private UserInfo user_info;
	private AddressInfo address_info;

	public List<OrderDetail> getOrder_details() {
		return order_details;
	}

	public void setOrder_details(List<OrderDetail> order_details) {
		this.order_details = order_details;
	}

	public PriceDetails getPrice_details() {
		return price_details;
	}

	public void setPrice_details(PriceDetails price_details) {
		this.price_details = price_details;
	}

	public UserInfo getUser_info() {
		return user_info;
	}

	public void setUser_info(UserInfo user_info) {
		this.user_info = user_info;
	}

	public AddressInfo getAddress_info() {
		return address_info;
	}

	public void setAddress_info(AddressInfo address_info) {
		this.address_info = address_info;
	}

}
