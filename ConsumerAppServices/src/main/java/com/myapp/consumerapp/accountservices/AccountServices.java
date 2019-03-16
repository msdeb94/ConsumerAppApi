package com.myapp.consumerapp.accountservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.consumerapp.accountdao.IAccountDao;
import com.myapp.consumerapp.commonconstants.BaseResponse;
import com.myapp.consumerapp.commonconstants.ResponseConstant;
import com.myapp.consumerapp.customeexception.MyAppException;
import com.myapp.consumerapp.uservo.InventoryDetails;
import com.myapp.consumerapp.uservo.UserProfileVO;

@Service("accountServices")
public class AccountServices {
	@Autowired
	private IAccountDao iAccountDao;

	public BaseResponse createuser(UserProfileVO userProfile) throws MyAppException {
		BaseResponse resgiterUserResponse = null;
		try {
			iAccountDao.isCreateuser(userProfile);
			resgiterUserResponse = new BaseResponse();
			resgiterUserResponse.setStatusCode(ResponseConstant.CREATED);
			resgiterUserResponse.setStatusMessage(ResponseConstant.MESSAGE_CREATED);

		} catch (Exception e) {
			throw new MyAppException("Exception occure at  the time of creating new user", e);
		}
		return resgiterUserResponse;
	}

	public BaseResponse orderRequest(InventoryDetails updateInfo) throws MyAppException {
		BaseResponse resgiterUserResponse = null;
		try {
			int n = iAccountDao.orderRequest(updateInfo);
			if (n > 0) {
				resgiterUserResponse = new BaseResponse();
				resgiterUserResponse.setStatusCode(ResponseConstant.OK);
				resgiterUserResponse.setStatusMessage(ResponseConstant.MESSAGE_UPDATE);
			}
			if (n < 0) {
				resgiterUserResponse = new BaseResponse();
				resgiterUserResponse.setStatusCode(ResponseConstant.CREATED);
				resgiterUserResponse.setStatusMessage(ResponseConstant.OUT_OF_STOCK);
			} else {
				resgiterUserResponse = new BaseResponse();
				resgiterUserResponse.setStatusCode(ResponseConstant.NOT_FOUND);
				resgiterUserResponse.setStatusMessage(ResponseConstant.MESSAGE_UPDATE);

			}
		} catch (Exception e) {
			throw new MyAppException("exception occure at the time of geting getallproduct");
		}
		return resgiterUserResponse;
	}

	public List<InventoryDetails> getAllProduct() throws MyAppException {
		return iAccountDao.getAllProduct();
	}

}
