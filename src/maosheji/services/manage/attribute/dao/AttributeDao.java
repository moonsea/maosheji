package maosheji.services.manage.attribute.dao;import maosheji.core.DaoManager;import maosheji.services.manage.attribute.bean.Attribute;public interface AttributeDao extends DaoManager<Attribute> {	/**	 * @param e	 */	void deleteByPid(int pid);	/**	 * @param id	 */	void deleteAllById(int id);	int selectCount(Attribute ee);}