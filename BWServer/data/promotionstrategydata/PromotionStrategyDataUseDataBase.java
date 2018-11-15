package promotionstrategydata;

import java.util.ArrayList;
import java.util.Iterator;

import PO.PromotionByCombinationListPO;
import PO.PromotionByLevelPO;
import PO.PromotionBySumPO;
import persistence.hibernate.implement.IOStrategyUseHibernate;
import persistence.hibernate.service.IODataBaseExtendion;
import persistence.txt.service.IOStrategy;

public class PromotionStrategyDataUseDataBase extends PromotionStrategyData {

	// public static void main(String[] args) {
	// PromotionStrategyDataUseDataBase tester = new
	// PromotionStrategyDataUseDataBase();
	// tester.getAllSum().forEach(po -> System.out.println(po));
	// tester.getAllLevel().forEach(po -> System.out.println(po));
	// tester.getAllCombination().forEach(po -> System.out.println(po));
	// }

	public PromotionStrategyDataUseDataBase() {
		setIoStrategyOfCombination(new IOStrategyUseHibernate<>());
		setIoStrategyOfLevel(new IOStrategyUseHibernate<>());
		setIoStrategyOfSum(new IOStrategyUseHibernate<>());
	}

	@Override
	protected <T> Boolean addPromotion(IOStrategy<T> ioStrategy, T obj) {
		return ioStrategy.inOne(obj);
	}

	@Override
	protected <T> Boolean deletePromotion(IOStrategy<T> ioStrategy, T obj) {
		return (((IODataBaseExtendion<T>) ioStrategy).deleteOne(obj));
	}

	@Override
	protected ArrayList<PromotionByCombinationListPO> getAllCombination() {
		ArrayList<PromotionByCombinationListPO> rePromotionByCombinationListPOs = new ArrayList<>();
		Iterator<PromotionByCombinationListPO> iterator = getIoStrategyOfCombination()
				.outAll(PromotionByCombinationListPO.class);
		while (iterator.hasNext()) {
			rePromotionByCombinationListPOs.add(iterator.next());
		}
		return rePromotionByCombinationListPOs.isEmpty() ? null : rePromotionByCombinationListPOs;
	}

	@Override
	protected ArrayList<PromotionByLevelPO> getAllLevel() {
		ArrayList<PromotionByLevelPO> rePromotionByLevelPOs = new ArrayList<>();
		Iterator<PromotionByLevelPO> iterator = getIoStrategyOfLevel().outAll(PromotionByLevelPO.class);
		while (iterator.hasNext())
			rePromotionByLevelPOs.add(iterator.next());
		return rePromotionByLevelPOs.isEmpty() ? null : rePromotionByLevelPOs;
	}

	@Override
	protected ArrayList<PromotionBySumPO> getAllSum() {
		ArrayList<PromotionBySumPO> rePromotionBySumPOs = new ArrayList<>();
		Iterator<PromotionBySumPO> iterator = getIoStrategyOfSum().outAll(PromotionBySumPO.class);
		while (iterator.hasNext())
			rePromotionBySumPOs.add(iterator.next());
		return rePromotionBySumPOs.isEmpty() ? null : rePromotionBySumPOs;
	}

}
