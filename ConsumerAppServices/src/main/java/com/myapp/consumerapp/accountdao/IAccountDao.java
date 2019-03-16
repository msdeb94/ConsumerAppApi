package com.myapp.consumerapp.accountdao;

import java.util.List;

import com.myapp.consumerapp.customeexception.MyAppException;
import com.myapp.consumerapp.uservo.InventoryDetails;
import com.myapp.consumerapp.uservo.UserProfileVO;

public interface IAccountDao {


	public void isCreateuser(UserProfileVO userProfile) throws MyAppException;

	public int orderRequest(InventoryDetails updateInfo) throws MyAppException;

	public List<InventoryDetails> getAllProduct( )throws MyAppException;

	

}
