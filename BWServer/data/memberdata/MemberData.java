package memberdata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

import PO.MemberPO;
import enums.MemberType;
import enums.MemberVipLevel;
import enums.ResultMessage;
import idhelper.IDHelper;
import memberdataservice.MemberDataService;
import persistence.txt.service.IOStrategy;

public abstract class MemberData implements MemberDataService {
	private IOStrategy<MemberPO> ioStrategy;

	protected abstract ResultMessage deleteHooked(String memberID);

	protected abstract ResultMessage updateHooked(String memberID, MemberPO replacement);

	protected abstract ResultMessage insertHooked(MemberPO po);

	@Override
	public ResultMessage delete(String memberID) {
		isValidIfNotThrowIllegalArgumentException(memberID);
		return deleteHooked(memberID);
	}

	@Override
	public MemberPO findByID(String id) {
		isValidIfNotThrowIllegalArgumentException(id);
		ArrayList<MemberPO> findResult = this.getAll();
		if (findResult == null || findResult.isEmpty())
			return null;
		findResult = findResult.parallelStream().filter(po -> po.getId().equals(id))
				.collect(Collectors.toCollection(ArrayList::new));
		return findResult.isEmpty() ? null : findResult.get(0);
	}

	@Override
	public ArrayList<MemberPO> findByLevel(MemberVipLevel level) {
		isValidIfNotThrowIllegalArgumentException(level);
		ArrayList<MemberPO> findList = this.getAll();
		if (findList == null || findList.isEmpty())
			return null;
		findList = findList.parallelStream().filter(po -> po.getMemberVipLevel().equals(level))
				.collect(Collectors.toCollection(ArrayList::new));
		return findList.isEmpty() ? null : findList;
	}

	@Override
	public ArrayList<MemberPO> findByMemberType(MemberType type) {
		isValidIfNotThrowIllegalArgumentException(type);
		ArrayList<MemberPO> list = this.getAll();
		if (list == null || list.isEmpty())
			return null;
		list = list.parallelStream().filter(po -> po.getMemberType().equals(type))
				.collect(Collectors.toCollection(ArrayList::new));
		return list.isEmpty() ? null : list;
	}

	@Override
	public ArrayList<MemberPO> findByName(String name) {
		isValidIfNotThrowIllegalArgumentException(name);
		ArrayList<MemberPO> list = this.getAll();
		if (list == null || list.isEmpty())
			return null;
		list = list.parallelStream().filter(po -> po.getName().contains(name))
				.collect(Collectors.toCollection(ArrayList::new));
		return list.isEmpty() ? null : list;
	}

	@Override
	public ArrayList<MemberPO> findByPostcode(String postcode) {
		isValidIfNotThrowIllegalArgumentException(postcode);
		ArrayList<MemberPO> list = this.getAll();
		if (list == null || list.isEmpty())
			return null;
		list = list.parallelStream().filter(po -> po.getPostcode().equals(postcode))
				.collect(Collectors.toCollection(ArrayList::new));
		return list.isEmpty() ? null : list;
	}

	@Override
	public ArrayList<MemberPO> findBySalesman(String salesmanId) {
		isValidIfNotThrowIllegalArgumentException(salesmanId);
		ArrayList<MemberPO> list = this.getAll();
		if (list == null || list.isEmpty())
			return null;
		list = list.parallelStream().filter(po -> po.getDefaultSalesman().equals(salesmanId))
				.collect(Collectors.toCollection(ArrayList::new));
		return list.isEmpty() ? null : list;
	}

	@Override
	public ArrayList<MemberPO> findByVoucher(String voucherID) {
		isValidIfNotThrowIllegalArgumentException(voucherID);
		ArrayList<MemberPO> list = this.getAll();
		if (list == null || list.isEmpty())
			return null;
		list = list.parallelStream().filter(po -> po.getVouchersAsList().contains(voucherID))
				.collect(Collectors.toCollection(ArrayList::new));
		return list.isEmpty() ? null : list;
	}

	@Override
	public abstract ArrayList<MemberPO> getAll();

	@Override
	public ResultMessage insert(MemberPO po) {
		isValidIfNotThrowIllegalArgumentException(po);
		return insertHooked(po);
	}

	private void isValidIfNotThrowIllegalArgumentException(Object obj) {
		if (null == obj)
			throw new IllegalArgumentException();
	}

	private void isValidIfNotThrowIllegalArgumentException(String str) {
		if (null == str || str.isEmpty())
			throw new IllegalArgumentException();
	}

	@Override
	public ResultMessage update(String targetID, MemberPO replacement) {
		isValidIfNotThrowIllegalArgumentException(targetID);
		isValidIfNotThrowIllegalArgumentException(replacement);
		/*
		 * 此處應該檢查targetId == replacements.getID
		 */
		if (!replacement.getId().equals(targetID))
			throw new IllegalArgumentException("目標ID與覆蓋ID不同");
		return updateHooked(targetID, replacement);
	}

	protected void setIoStrategy(IOStrategy<MemberPO> ioStrategy) {
		this.ioStrategy = ioStrategy;
	}

	protected IOStrategy<MemberPO> getIoStrategy() {
		return ioStrategy;
	}

	@Override
	public String getMemberID(MemberType type) {
		isValidIfNotThrowIllegalArgumentException(type);
		Iterator<MemberPO> iterator = ioStrategy.outAll(MemberPO.class);
		int count = 0;
		while (iterator.hasNext()) {
			if (iterator.next().getMemberType().equals(type)) {
				count++;
			}
		}
		return IDHelper.toKBitString(count + 1, 5);
	}

}
