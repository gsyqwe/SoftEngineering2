package promotionstrategydata;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.stream.Collectors;

import PO.PromotionByCombinationListPO;
import PO.PromotionByLevelPO;
import PO.PromotionBySumPO;
import PromotionStrategydataService.PromotionStrategydataService;
import enums.PromotionType;
import enums.ResultMessage;
import idhelper.IDHelper;
import persistence.txt.service.IOStrategy;

public abstract class PromotionStrategyData implements PromotionStrategydataService {
	private IOStrategy<PromotionByLevelPO> ioStrategyOfLevel;
	private IOStrategy<PromotionByCombinationListPO> ioStrategyOfCombination;
	private IOStrategy<PromotionBySumPO> ioStrategyOfSum;

	private void isValidIfNotThrowIllegalArgumentException(Object obj) {
		if (null == obj) {
			throw new IllegalArgumentException();
		}
	}

	protected abstract <T> Boolean addPromotion(IOStrategy<T> ioStrategy, T obj);

	protected abstract <T> Boolean deletePromotion(IOStrategy<T> ioStrategy, T obj);

	protected abstract ArrayList<PromotionByCombinationListPO> getAllCombination();

	protected abstract ArrayList<PromotionByLevelPO> getAllLevel();

	protected abstract ArrayList<PromotionBySumPO> getAllSum();

	@Override
	public ResultMessage addPromotionByLevelPO(PromotionByLevelPO po) {
		isValidIfNotThrowIllegalArgumentException(po);
		if (!this.addPromotion(ioStrategyOfLevel, po))
			return ResultMessage.PROMOTION_ADD_FALL;
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage addPromotionByCombinationList(PromotionByCombinationListPO po) {
		isValidIfNotThrowIllegalArgumentException(po);
		if (!this.addPromotion(ioStrategyOfCombination, po))
			return ResultMessage.PROMOTION_ADD_FALL;
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage addPromotionBySum(PromotionBySumPO po) {
		isValidIfNotThrowIllegalArgumentException(po);
		if (!this.addPromotion(ioStrategyOfSum, po))
			return ResultMessage.PROMOTION_ADD_FALL;
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage deletePromotionByLevel(PromotionByLevelPO po) {
		isValidIfNotThrowIllegalArgumentException(po);
		if (!this.deletePromotion(ioStrategyOfLevel, po))
			return ResultMessage.PROMOTION_DELETE_FAIL;
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage deletePromotionByCombination(PromotionByCombinationListPO po) {
		isValidIfNotThrowIllegalArgumentException(po);
		if (!this.deletePromotion(ioStrategyOfCombination, po))
			return ResultMessage.PROMOTION_DELETE_FAIL;
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage deletePromotionBySum(PromotionBySumPO po) {
		isValidIfNotThrowIllegalArgumentException(po);
		if (!this.deletePromotion(ioStrategyOfSum, po))
			return ResultMessage.PROMOTION_DELETE_FAIL;
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<PromotionByLevelPO> findPromotionByLevelByTime(Date nowTime) {
		isValidIfNotThrowIllegalArgumentException(nowTime);

		ArrayList<PromotionByLevelPO> findResult = getAllLevel();
		if (findResult == null || findResult.isEmpty())
			return null;
		findResult = findResult.parallelStream()
				.filter(po -> po.getBeginDate().before(nowTime) && po.getEndDate().after(nowTime))
				.collect(Collectors.toCollection(ArrayList::new));
		return findResult.isEmpty() ? null : findResult;
	}

	@Override
	public ArrayList<PromotionByCombinationListPO> findPromotionByCombinationByTime(Date nowTime) {
		isValidIfNotThrowIllegalArgumentException(nowTime);
		ArrayList<PromotionByCombinationListPO> findResult = getAllCombination();
		if (findResult == null || findResult.isEmpty())
			return null;
		findResult = findResult.parallelStream()
				.filter(po -> po.getBeginTime().before(nowTime) && po.getEndTime().after(nowTime))
				.collect(Collectors.toCollection(ArrayList::new));
		return findResult.isEmpty() ? null : findResult;
	}

	@Override
	public ArrayList<PromotionBySumPO> findPromotionBySumByTime(Date nowTime) {
		isValidIfNotThrowIllegalArgumentException(nowTime);
		ArrayList<PromotionBySumPO> resultList = getAllSum();
		if (resultList == null || resultList.isEmpty())
			return null;
		resultList = resultList.parallelStream()
				.filter(po -> po.getStartTime().before(nowTime) && po.getEndTime().after(nowTime))
				.collect(Collectors.toCollection(ArrayList::new));
		return resultList.isEmpty() ? null : resultList;
	}

	protected IOStrategy<PromotionBySumPO> getIoStrategyOfSum() {
		return ioStrategyOfSum;
	}

	protected IOStrategy<PromotionByLevelPO> getIoStrategyOfLevel() {
		return ioStrategyOfLevel;
	}

	protected IOStrategy<PromotionByCombinationListPO> getIoStrategyOfCombination() {
		return ioStrategyOfCombination;
	}

	protected void setIoStrategyOfLevel(IOStrategy<PromotionByLevelPO> ioStrategyOfLevel) {
		this.ioStrategyOfLevel = ioStrategyOfLevel;
	}

	protected void setIoStrategyOfCombination(IOStrategy<PromotionByCombinationListPO> ioStrategyOfCombination) {
		this.ioStrategyOfCombination = ioStrategyOfCombination;
	}

	protected void setIoStrategyOfSum(IOStrategy<PromotionBySumPO> ioStrategyOfSum) {
		this.ioStrategyOfSum = ioStrategyOfSum;
	}

	/*
	 * promotionType = PBL,PBC,PBS
	 */
	@Override
	public String getSuffix(PromotionType promotionType) {
		isValidIfNotThrowIllegalArgumentException(promotionType);
		int idLength = 0;
		if (promotionType.equals(PromotionType.PromotionByLevel)) {
			Iterator<PromotionByLevelPO> iterator = ioStrategyOfLevel.outAll(PromotionByLevelPO.class);
			while (iterator.hasNext()) {
				iterator.next();
				idLength++;
			}
		} else if (promotionType.equals(PromotionType.PromotionByCombination)) {
			Iterator<PromotionByCombinationListPO> iterator = ioStrategyOfCombination
					.outAll(PromotionByCombinationListPO.class);
			while (iterator.hasNext()) {
				iterator.next();
				idLength++;
			}
		} else if (promotionType.equals(PromotionType.PromotionBySum)) {
			Iterator<PromotionBySumPO> iterator = ioStrategyOfSum.outAll(PromotionBySumPO.class);
			while (iterator.hasNext()) {
				iterator.next();
				idLength++;
			}
		} else
			throw new IllegalArgumentException("PromotionType can only be PRL or PBC or PBS");
		return IDHelper.toKBitString(idLength + 1, 5); // k = 5
	}

}
