package maosheji.services.manage.area.dao;import java.util.List;import maosheji.core.DaoManager;import maosheji.services.manage.area.bean.Area;public interface AreaDao extends DaoManager<Area> {	void deleteAll();	List<Area> selectListByPcode(String pcode);	int getCount(Area area);}