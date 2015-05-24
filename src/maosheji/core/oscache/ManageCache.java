package maosheji.core.oscache;

import java.util.List;

import maosheji.core.TaskManager;
import maosheji.core.front.SystemManager;
import maosheji.services.front.area.AreaService;
import maosheji.services.manage.comment.CommentService;
import maosheji.services.manage.order.OrderService;
import maosheji.services.manage.order.bean.OrdersReport;
//import maosheji.services.manage.oss.OssService;
//import maosheji.services.manage.oss.bean.AliyunOSS;
//import maosheji.services.manage.oss.bean.Oss;
import maosheji.services.manage.product.ProductService;
import maosheji.services.manage.task.TaskService;
import maosheji.services.manage.task.bean.Task;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * 缓存管理器。 后台项目可以通过接口程序通知该类重新加载部分或全部的缓存
 * 
 * @author huangf
 * 
 */
public class ManageCache {
	private static final Logger logger = LoggerFactory.getLogger(ManageCache.class);
	
	/**
	 * manage后台
	 */
	private OrderService orderService;
	private ProductService productService;
	private CommentService commentService;
	private AreaService areaService;
	private TaskService taskService;

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public CommentService getCommentService() {
		return commentService;
	}

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	/**
	 * 加载订单报表
	 */
	public void loadOrdersReport(){
		System.out.println("success");
		SystemManager.ordersReport = orderService.loadOrdersReport();
		if(SystemManager.ordersReport==null){
			SystemManager.ordersReport = new OrdersReport();
		}
		//加载缺货商品数
		SystemManager.ordersReport.setOutOfStockProductCount(productService.selectOutOfStockProductCount());

		//加载吐槽评论数
		SystemManager.ordersReport.setNotReplyCommentCount(commentService.selectNotReplyCount());
		
		logger.error("SystemManager.ordersReport = " + SystemManager.ordersReport.toString());
	}

	
	/**
	 * 加载定时任务列表
	 */
	public void loadTask(){
		List<Task> list = taskService.selectList(new Task());
		if(list!=null){
			TaskManager.taskPool.clear();
			for(int i=0;i<list.size();i++){
				Task item = list.get(i);
				TaskManager.taskPool.put(item.getCode(),item);
			}
		}
	}
	
	/**
	 * 加载全部的缓存数据
	 * @throws Exception 
	 */
	public void loadAllCache() throws Exception {
		logger.error("ManageCache.loadAllCache...");
		loadOrdersReport();
		loadTask();
		logger.error("后台缓存加载完毕!");
	}

	public static void main(String[] args) {
		String str = "10280|10281|10282";
		String[] arr = str.split("\\|");
		for(int i=0;i<arr.length;i++){
			System.out.println(arr[i]);
		}
	}
}
