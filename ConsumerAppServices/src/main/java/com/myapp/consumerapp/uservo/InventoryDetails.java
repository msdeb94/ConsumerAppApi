package com.myapp.consumerapp.uservo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.myapp.consumerapp.commonconstants.BaseResponse;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@JsonIgnoreProperties(ignoreUnknown = true)
public class InventoryDetails extends BaseResponse {
	private static final long serialVersionUID = 7334049059139091298L;
	private long profileId;
	private long productId;
	private String ProductName;
	private String genericName;
	private String brandName;
	private String manufacturerName;
	private String genericId;
	private long availableQuantity;
	@JsonInclude(JsonInclude.Include.ALWAYS)
	private long buyingQuantity;

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public String getGenericName() {
		return genericName;
	}

	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public String getGenericId() {
		return genericId;
	}

	public void setGenericId(String genericId) {
		this.genericId = genericId;
	}

	public long getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(long availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public long getBuyingQuantity() {
		return buyingQuantity;
	}

	public void setBuyingQuantity(long buyingQuantity) {
		this.buyingQuantity = buyingQuantity;
	}

	public long getProfileId() {
		return profileId;
	}

	public void setProfileId(long profileId) {
		this.profileId = profileId;
	}

}
