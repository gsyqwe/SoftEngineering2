package promotionstrategydata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

import PO.PromotionByCombinationListPO;
import PO.PromotionByLevelPO;
import PO.PromotionBySumPO;
import persistence.txt.implement.IOStrategyUseTxt;
import persistence.txt.service.IOStrategy;

public class PromotionStrategyDataUseTxt extends PromotionStrategyData {
	private static Set<PromotionByLevelPO> promotionByLevelPOs = new HashSet<>();
	private static Set<PromotionByCombinationListPO> promotionByCombinationListPOs = new HashSet<>();
	private static Set<PromotionBySumPO> promotionBySumPOs = new HashSet<>();
	private static final String txtNameOfLevel = "PromotionByLevelPO";
	private static final String txtNameOfSum = "PromotionBySumPO";
	private static final String txtNameOfCombination = "PromotionByCombinationPO";

	public PromotionStrategyDataUseTxt() {
		setIoStrategyOfCombination(new IOStrategyUseTxt<>(txtNameOfCombination));
		setIoStrategyOfLevel(new IOStrategyUseTxt<>(txtNameOfLevel));
		setIoStrategyOfSum(new IOStrategyUseTxt<>(txtNameOfSum));
		backUpFromFile(promotionByLevelPOs, getIoStrategyOfLevel(), PromotionByLevelPO.class);
		backUpFromFile(promotionByCombinationListPOs, getIoStrategyOfCombination(), PromotionByCombinationListPO.class);
		backUpFromFile(promotionBySumPOs, getIoStrategyOfSum(), PromotionBySumPO.class);
	}

	private <T> void backUpFromFile(Collection<T> data, IOStrategy<T> ioStrategy, Class clazz) {
		Iterator<T> iterator = ioStrategy.outAll(clazz);
		while (iterator.hasNext()) {
			T po = iterator.next();
			data.add(po);
		}
	}

	private <T> Collection<T> selectPromotionType(T obj) {
		if (obj instanceof PromotionBySumPO)
			return (Collection<T>) promotionBySumPOs;
		else if (obj instanceof PromotionByCombinationListPO)
			return (Collection<T>) promotionByCombinationListPOs;
		else if (obj instanceof PromotionByLevelPO)
			return (Collection<T>) promotionByLevelPOs;

		throw new IllegalArgumentException();
	}

	@Override
	protected <T> Boolean addPromotion(IOStrategy<T> ioStrategy, T obj) {
		Collection<T> collection = selectPromotionType(obj);
		if (collection.contains(obj))
			return false; // existed
		collection.add((T) obj);
		return ioStrategy.inOne(obj);
	}

	@Override
	protected <T> Boolean deletePromotion(IOStrategy<T> ioStrategy, T obj) {
		Collection<T> collection = selectPromotionType(obj);
		if (!collection.contains(obj))
			return false; // not exist
		collection.remove(obj);
		return ioStrategy.replaceAll(collection.iterator());
	}

	@Override
	protected ArrayList<PromotionByCombinationListPO> getAllCombination() {
		return promotionByCombinationListPOs.parallelStream().collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	protected ArrayList<PromotionByLevelPO> getAllLevel() {
		return promotionByLevelPOs.parallelStream().collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	protected ArrayList<PromotionBySumPO> getAllSum() {
		return promotionBySumPOs.parallelStream().collect(Collectors.toCollection(ArrayList::new));
	}

}
