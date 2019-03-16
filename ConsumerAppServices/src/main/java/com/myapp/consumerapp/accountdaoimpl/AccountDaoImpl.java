package com.myapp.consumerapp.accountdaoimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jongo.Jongo;
import org.springframework.stereotype.Repository;

import com.mongodb.WriteResult;
import com.myapp.consumerapp.accountdao.IAccountDao;
import com.myapp.consumerapp.customeexception.MyAppException;
import com.myapp.consumerapp.mongoserverconfig.MongoCollectionConstants;
import com.myapp.consumerapp.mongoserverconfig.MongoDBUtil;
import com.myapp.consumerapp.uservo.InventoryDetails;
import com.myapp.consumerapp.uservo.UserProfileVO;

@Repository("iAccountDao")
public class AccountDaoImpl implements IAccountDao {
	@Override
	public void isCreateuser(UserProfileVO userProfile) throws MyAppException {
		try {
			new Jongo(MongoDBUtil.getDB()).getCollection(MongoCollectionConstants.REGISTRATION).insert(userProfile);
		} catch (Exception e) {
			throw new MyAppException("Exception occure at the time of insering user details in db", e);
		}
	}

	@Override
	public int orderRequest(InventoryDetails updateInfo) throws MyAppException {
		WriteResult result = null;
		int res = 0;
		try {
			if (updateInfo.getAvailableQuantity() < updateInfo.getBuyingQuantity()) {
				res = -1;
			} else {
				updateInfo.setAvailableQuantity(updateInfo.getAvailableQuantity() - updateInfo.getBuyingQuantity());
				result = new Jongo(MongoDBUtil.getDB()).getCollection(MongoCollectionConstants.INVENTORY_STOCKS)
						.update("{productId:#,genericId:#}", updateInfo.getProductId(), updateInfo.getGenericId())
						.upsert().with("{$set:{'availableQuantity':#}}", updateInfo.getAvailableQuantity());
				res = result.getN();
				try {
					new Jongo(MongoDBUtil.getDB()).getCollection(MongoCollectionConstants.ORDER_DETAILS).insert(
							"{'profileId':#,'genericId':#,'quantity':#,'date':#}", updateInfo.getProfileId(),
							updateInfo.getGenericId(), updateInfo.getBuyingQuantity(), System.currentTimeMillis());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			throw new MyAppException("Exception occure at the time of insering details in db", e);
		}
		return res;
	}

	@Override
	public List<InventoryDetails> getAllProduct() throws MyAppException {
		List<InventoryDetails> list = null;
		try {
			list = new ArrayList<InventoryDetails>();
			Iterator<InventoryDetails> itr = new Jongo(MongoDBUtil.getDB())
					.getCollection(MongoCollectionConstants.INVENTORY_STOCKS).find().as(InventoryDetails.class);
			while (itr.hasNext()) {
				list.add(itr.next());
			}
		}

		catch (Exception e) {
			throw new MyAppException("Exception occure at the time of fetching inventorydetails details in db", e);
		}
		return list;

	}
}