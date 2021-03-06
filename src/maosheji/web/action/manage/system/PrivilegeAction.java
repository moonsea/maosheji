package maosheji.web.action.manage.system;

import java.util.List;

import maosheji.core.BaseAction;
import maosheji.core.Services;
import maosheji.core.system.bean.Privilege;
import maosheji.services.manage.system.impl.PrivilegeService;


/**
 * 权限管理
 * @author Administrator
 *
 */
public class PrivilegeAction extends BaseAction<Privilege> {//implements ModelDriven<Role>{
	private static final long serialVersionUID = 1L;
	private PrivilegeService privilegeService;
	private List privilegeList;

	
	private Privilege privilege = new Privilege();

	public String delete() throws Exception {
		this.privilegeService.delete(privilege);
		return SUCCESS;
	}

	public PrivilegeService getPrivilegeService() {
		return privilegeService;
	}

	public void setPrivilegeService(PrivilegeService privilegeService) {
		this.privilegeService = privilegeService;
	}

	public List getPrivilegeList() {
		return privilegeList;
	}

	public void setPrivilegeList(List privilegeList) {
		this.privilegeList = privilegeList;
	}

	public Privilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	@Override
	public Privilege getE() {
		// TODO Auto-generated method stub
		return this.privilege;
	}

	@Override
	public Services<Privilege> getServer() {
		// TODO Auto-generated method stub
		return this.privilegeService;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertAfter(Privilege e) {
		// TODO Auto-generated method stub
		e.clear();
	}
	@Override
	protected void selectListAfter() {
		pager.setPagerUrl("privilege!selectList.action");
	}
}
