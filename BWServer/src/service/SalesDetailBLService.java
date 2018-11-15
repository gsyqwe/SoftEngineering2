package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import VO.ReceiptVO;
import VO.SalesDetailVO;

/**
 *
 * @author JiYuzhe
 * 查看销售明细表，以商品为查找单位。要能根据时间区间，商品名，客户，业务员，仓库来查找符合的商品的销售记录
 * 以列表形式显示，列表中包含如下信息：时间（精确到天），商品名，型号，数量，单价，总额
 * 实现方法：先根据时间区间，客户，业务员和仓库的筛选条件找到符合条件的SalesReceiptPO的ArrayList
 * 注意：销售明细表的查找范围应该只是在销售单，不包括进货单等单据
 * 用SalesDetailVO来包装lineItem
 * 如果没有根据商品名筛选，则返回这个list里面的所有的lineItem，对每个lineItem找到对应的商品名，然后直接显示在列表里面
 * 如果有按照商品名筛选，调用dataService里面的方法，根据商品名找到对应的商品编号，然后再lineItem里面筛选，然后返回符合的lineItem
 * 传入的Finder的dataService只能有SalesDataService，不能有别的
 *
 *
 * 增加了按照各项属性排序的方法，目前只增加了升序版本的
 */
public interface SalesDetailBLService extends Remote{
	public ArrayList<SalesDetailVO> getEffectiveLineItemWithoutSpecificName(ArrayList<ReceiptVO> receipts) throws RemoteException;

	public ArrayList<SalesDetailVO> getEffectiveLineItemWithCommodityName(String commodityName,
			ArrayList<ReceiptVO> findReceiptList) throws RemoteException;//按照商品名筛选

	public ArrayList<SalesDetailVO> getInitLineItem() throws RemoteException;//得到一个初始化的列表，在刚加载页面的时候使用

	public ArrayList<SalesDetailVO> sortByDate(ArrayList<SalesDetailVO> detailList) throws RemoteException;

	public ArrayList<SalesDetailVO> sortByCommodityName(ArrayList<SalesDetailVO> detailList) throws RemoteException;

	public ArrayList<SalesDetailVO> sortByAmount(ArrayList<SalesDetailVO> detailList) throws RemoteException;

	public ArrayList<SalesDetailVO> sortByPriceEach(ArrayList<SalesDetailVO> detailList) throws RemoteException;

	public ArrayList<SalesDetailVO> sortByQuantity(ArrayList<SalesDetailVO> detailList) throws RemoteException;
}
