package salesbl;

import java.util.ArrayList;

import PO.MemberPO;
import enums.MemberType;

public interface MemberInfo {

	public ArrayList<MemberPO> findByMemberType(MemberType type);
}
