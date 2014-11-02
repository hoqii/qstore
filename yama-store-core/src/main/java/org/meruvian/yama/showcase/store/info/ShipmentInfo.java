package org.meruvian.yama.showcase.store.info;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.meruvian.yama.core.DefaultJpaPersistence;
import org.meruvian.yama.core.user.JpaUser;

@Entity
@Table(name = "yama_user_shipment_info")
public class ShipmentInfo extends DefaultJpaPersistence {

	public enum ShipmentType {
		BILLTO, SHIPTO
	}

	private JpaUser user;
	private ShipmentType shipmentType;
	private String contactName;
	private String country;
	private String address;
	private String zip;
	private String phoneNumber;

	
	@ManyToOne
	@JoinColumn(name = "user_id")
	public JpaUser getUser() {
		return user;
	}

	public void setUser(JpaUser user) {
		this.user = user;
	}

	public ShipmentType getShipmentType() {
		return shipmentType;
	}

	public void setShipmentType(ShipmentType shipmentType) {
		this.shipmentType = shipmentType;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
