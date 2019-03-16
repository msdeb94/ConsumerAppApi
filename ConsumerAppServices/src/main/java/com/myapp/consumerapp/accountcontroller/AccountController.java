package com.myapp.consumerapp.accountcontroller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.consumerapp.accountservices.AccountServices;
import com.myapp.consumerapp.commonconstants.BaseResponse;
import com.myapp.consumerapp.commonconstants.ResponseConstant;
import com.myapp.consumerapp.customeexception.MyAppException;
import com.myapp.consumerapp.uservo.InventoryDetails;
import com.myapp.consumerapp.uservo.UserProfileVO;

@RestController
@RequestMapping("/account")
public class AccountController {
	private static final Logger logger = Logger.getLogger(AccountController.class);
	@Autowired
	private AccountServices accountServices;

	@RequestMapping(value = "/createuser", method = RequestMethod.POST, headers = "Accept=application/json")
	public BaseResponse createuser(@RequestBody UserProfileVO userProfileVO) {
		BaseResponse resgiterUserResponse = null;
		try {
			resgiterUserResponse = accountServices.createuser(userProfileVO);
		} catch (MyAppException e) {
			logger.error("Exception occuure at the time of creating user", e);
			resgiterUserResponse = new BaseResponse();
			resgiterUserResponse.setStatusCode(ResponseConstant.SERVER_ERROR);
			resgiterUserResponse.setStatusMessage(ResponseConstant.MESSAGE_SERVER_ERROR);

		}
		return resgiterUserResponse;
	}
	@RequestMapping(value = "/getallproduct", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<InventoryDetails> getAllProduct() {

		List<InventoryDetails>  inventoryDetails=null;
		try {
			inventoryDetails = accountServices.getAllProduct();
		} catch (MyAppException e) {
			inventoryDetails=new ArrayList<>();
			logger.error("Exception occurred in getallproduct", e);
			
		}
		return inventoryDetails;
	}
	@RequestMapping(value = "/orderrequest", method = RequestMethod.POST, headers = "Accept=application/json")
	public BaseResponse orderRequest(@RequestBody InventoryDetails inventory) {
		BaseResponse response = null;
		try {
			response = accountServices.orderRequest(inventory);
		} catch (MyAppException e) {
			logger.error("Exception occurred while requesting order", e);
			response = new BaseResponse();
			response.setStatusCode(ResponseConstant.SERVER_ERROR);
			response.setStatusMessage(ResponseConstant.MESSAGE_SERVER_ERROR);
		}
		return response;
	}



}
