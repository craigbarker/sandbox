package org.sgodden.tom.model;

/**
 * A line on a customer order.
 * 
 * @author Simon Godden
 */
@SuppressWarnings("serial")
public class CustomerOrderLine extends AbstractIdentity {

	private String packageType;
	private String descriptionOfGoods;

	public String getPackageType() {
		return packageType;
	}
	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}
	public String getDescriptionOfGoods() {
		return descriptionOfGoods;
	}
	public void setDescriptionOfGoods(String descriptionOfGoods) {
		this.descriptionOfGoods = descriptionOfGoods;
	}

}