package ieci.tdw.ispac.ispacweb.manager;

import ieci.tdw.ispac.api.ISPACEntities;
import ieci.tdw.ispac.api.errors.ISPACException;
import ieci.tdw.ispac.api.item.ITask;
import ieci.tdw.ispac.ispaclib.bean.BeanFormatter;
import ieci.tdw.ispac.ispaclib.utils.StringUtils;

import java.io.InputStream;

import org.apache.log4j.Logger;

public class ClosedTaskListFormatFactory extends TaskListFormatFactory {

	protected Logger logger = Logger.getLogger(ClosedTaskListFormatFactory.class);

    static final String FILE_FMTNAME="closedtasklistformat.xml";
    static final String DIR_FMTPROCESSLIST="closedtasklistformat";

    static final String FILE_FMTNAME_SUBPROCESS="closedsubprocesslistformat.xml";
    static final String DIR_FMTPROCESSLIST_SUBPROCESS="closedsubprocesslistformat";


    public ClosedTaskListFormatFactory(String basepath, int taskType) throws ISPACException {
		super(basepath, getFileName(taskType), getSubDir(taskType));
	}

	// Obtener el formateador a partir del c�digo del tr�mite
    public BeanFormatter getBeanFormatter(String taskCode, int taskType) throws ISPACException {
		return getBeanFormatter(ISPACEntities.ENTITY_NULLREGKEYID, null, taskCode, taskType);
	}

    public BeanFormatter getBeanFormatter(int procedureId, String procedureCode, String taskCode, int taskType) throws ISPACException {
		InputStream istream = getTaskListFormat(procedureId, procedureCode, taskCode, taskType);
		return getBeanFormatter(istream);
	}

	// Obtener el formateador a partir del c�digo del tr�mite
	public InputStream getTaskListFormat(String taskCode, int taskType) throws ISPACException {
		return getTaskListFormat(ISPACEntities.ENTITY_NULLREGKEYID, null, taskCode, taskType);
	}

	public InputStream getTaskListFormat(int procedureId, String procedureCode, String taskCode, int taskType) throws ISPACException {

        String procedureDir = null;
        if (procedureId != ISPACEntities.ENTITY_NULLREGKEYID) {
			procedureDir = DIR_PCD + procedureId;
        }

        String procedureCodeDir = null;
        if (StringUtils.isNotBlank(procedureCode)) {
			procedureCodeDir = DIR_PCD + "_" + StringUtils.escapeFile(procedureCode);
        }

        String taskdir = getMainDir(taskType) + "_" + StringUtils.escapeFile(taskCode);

		return findFormatResource(procedureDir, procedureCodeDir, taskdir);
    }

	private static String getMainDir(int taskType) {
    	if (taskType == ITask.SIMPLE_TASK_TYPE){
    		return DIR_TASK;
    	}
		return SubProcessListFormatFactory.DIR_SUBPCD;
	}

	private static String getSubDir(int taskType) {
    	if (taskType == ITask.SIMPLE_TASK_TYPE){
    		return DIR_FMTPROCESSLIST;
    	}
		return DIR_FMTPROCESSLIST_SUBPROCESS;
	}

	private static String getFileName(int taskType) {
    	if (taskType == ITask.SIMPLE_TASK_TYPE){
    		return FILE_FMTNAME;
    	}
    	return FILE_FMTNAME_SUBPROCESS;
	}
}