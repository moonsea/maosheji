package maosheji.services.manage.orderdetail;import java.util.List;import maosheji.core.Services;import maosheji.services.manage.order.bean.Order;import maosheji.services.manage.orderdetail.bean.Orderdetail;import maosheji.web.action.manage.report.ReportInfo;public interface OrderdetailService extends Services<Orderdetail> {	/**	 * 产品销售排行榜统计：图表的形式展示指定时间范围内的商品的销售情况，包括数量、金额等。	 * @param orderdetail	 * @return	 */	List<ReportInfo> reportProductSales(Orderdetail orderdetail);}