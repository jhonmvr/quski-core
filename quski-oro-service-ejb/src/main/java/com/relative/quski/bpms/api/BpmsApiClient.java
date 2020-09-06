package com.relative.quski.bpms.api;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.quski.enums.EstadoBpmsEnum;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.wrapper.EnvioWrapper;
import com.relative.quski.wrapper.ProcessInputWrapper;
import com.relative.quski.wrapper.RestClientWrapper;
import com.relative.quski.wrapper.TaskItemWrapper;
import com.relative.quski.wrapper.TaskSummaryWrapper;
import com.relative.quski.wrapper.TokenWrapper;

public class BpmsApiClient {
	
	private static final Log log = LogFactory.getLog(BpmsApiClient.class);
	
	private static final String urlInstanceProcess="kie-server/services/rest/server/containers/--containerid--/processes/--bpmprocessid--/instances";
	private static final String urlAbortProcess="kie-server/services/rest/server/containers/--containerid--/processes/instances/--processid--";
	
    private static final String urlTaskState="kie-server/services/rest/server/containers/--containerid--/tasks/--taskid--/states/--state--";
    //private static final String urlProcessInstaceTask="kie-server/services/rest/server/queries/processes/instances/--idprocessinstace--";
    private static final String urlTasksInProcessInstances="kie-server/services/rest/server/queries/tasks/instances/process/--idprocessinstace--";
    private static final String urlPotencialOwnerTask="kie-server/services/rest/server/queries/tasks/instances/pot-owners";
    private static final String urlTaskVariables="kie-server/services/rest/server/containers/--containerid--/tasks/--taskid--?withInputData=true&withOutputData=true";
    private static final String urlQueryVariablesByInstanceId="kie-server/services/rest/server/queries/processes/instances/--instanceid--/variables/instances";
    private static final String urlToken = "token/";
    
    private static final String empty="{}";
    
	
	
	public static void main(String[] args) {
		try {
			/*TaskSummaryWrapper sw= BpmsApiClient.callBpmsFindPotencialOwnerTask(MidasOroConstantes.BPMS_URL_SERVICE, 
					"Basic a2llc2VydmVyOmtpZXNlcnZlcjEh" , "AdministradorInterno", "");
			if( sw != null ) {
				sw.getTaskSummary().stream().forEach( t-> System.out.println("tarea: " + t.getTaskId() )  );
			}*/
			/*TaskSummaryWrapper sw= BpmsApiClient.callBpmsFindTasksByProcessInstance(
					MidasOroConstantes.BPMS_URL_SERVICE, 
					"Basic a2llc2VydmVyOmtpZXNlcnZlcjEh" , (long)52);
			if( sw != null ) {
				sw.getTaskSummary().stream().forEach( t-> System.out.println("tarea: " + t.getTaskId() )  );
			}*/
			
			/*TaskItemWrapper iw= BpmsApiClient.callBpmsTaskById(MidasOroConstantes.BPMS_URL_SERVICE, 
					"Basic a2llc2VydmVyOmtpZXNlcnZlcjEh",MidasOroConstantes.BPMS_PROJECT,MidasOroConstantes.BPMS_PROJECT_VERSION,
					"146");
			if( iw != null ) {
				System.out.println("tarea: " + iw.getTaskId() );
			}*/
			/*ProcessInputWrapper piw = new ProcessInputWrapper(123,"343434343434343",EstadoSiniestroAgricolaEnum.REGISTRADO.toString());
		 System.out.println("retornoooooo " + BpmsApiClient.callBpmsInitProcesss(MidasOroConstantes.BPMS_URL_SERVICE, 
					"Basic a2llc2VydmVyOmtpZXNlcnZlcjEh", MidasOroConstantes.BPMS_PROJECT, 
					MidasOroConstantes.BPMS_PROJECT_VERSION, 
					MidasOroConstantes.BPMS_PROJECT_MAIN_BPM, MidasOroConstantes.BPMS_PROJECT_MAIN_BPM_VERSION, 
					piw) );*/
			
			/*TokenWrapper sw= BpmsApiClient.getToken(
					"https://172.16.101.60:8243/", 
					"Basic NmJkQU5TVVNNZF9ScW8xZnJralhxWGNOekxBYTplTHZwR2NvQlRLVDZjRk9FXzJpTEtqc05XcjBh");
			if( sw != null ) {
				 System.out.println("token: " + sw.getAccessToken() );
			}*/
			ArrayList<HashMap<String, Object>> ls=BpmsApiClient.getBpmsProcesssVariable("http://192.168.100.182:8780/", "Basic a2llc2VydmVyOmtpZXNlcnZlcjEh", "34");
			if( ls != null ) {
				 for( Map<String, Object> x:ls ) {
					 System.out.println("===>datos has variable iterando " +x); 
				 }
			 }
			
		} catch (RelativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Metodo que inicia el proceso en el BPMS
	 * @param authorization
	 * @param idProyecto
	 * @param versionProyecto
	 * @param bpmProcessId
	 * @param versionProcesoBpm
	 * @param piw
	 * @return Numero de proceso
	 * @throws RelativeException
	 */
	public static String callBpmsInitProcesss(String bpmsUrlService, String authorization,String idProyecto, 
			String versionProyecto, String bpmProcessId, String versionProcesoBpm, ProcessInputWrapper piw)  throws RelativeException{
        log.info("===> callBpmsInitProcesss con idProyecto " + idProyecto);
        log.info("===> callBpmsInitProcesss con versionProyecto " + versionProyecto);
        log.info("===> callBpmsInitProcesss con bpmProcessId " + bpmProcessId);
        Gson gson = new Gson();
        String content = gson.toJson(piw);
        log.info("=====> PIW "+ content);
        String containerId=idProyecto + "_" + versionProyecto;
        String service = bpmsUrlService + urlInstanceProcess.replace("--containerid--",containerId)
        .replace("--bpmprocessid--",bpmProcessId);
        log.info("===> callBpmsInitProcesss con servcio " + service);
        Map<String,Object> response= ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,RestClientWrapper.CONTENT_TYPE_JSON,authorization , content, RestClientWrapper.METHOD_POST, 
    		   null, null, null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, 
    		   Boolean.FALSE,Boolean.TRUE, service,  String.class );
            log.info("===> REspuesta de servicio "  + response);
        return String.valueOf(response.get(ReRestClient.RETURN_OBJECT));    
    }
	
	/**
	 * Metodo que llena genera la llamda al servicio rest de BPMS.
	 * @param bpmsUrlService 
	 * @param authorization
	 * @param idProyecto
	 * @param versionProyecto
	 * @param taskId
	 * @param state
	 * @param actor
	 * @param ew
	 * @throws RelativeException
	 */
	public static String callBpmsStateTask(String bpmsUrlService, String authorization, String idProyecto, 
			String versionProyecto, String taskId, 
			String state, String actor, EnvioWrapper ew, ProcessInputWrapper piw)  throws RelativeException{
        log.info("===> callBpmsStateTask con idProyecto " + idProyecto);
        log.info("===> callBpmsStateTask con versionProyecto " + versionProyecto);
        log.info("===> callBpmsStateTask con taskId " + taskId);
        log.info("===> callBpmsStateTask con state " + state);
        log.info("===> callBpmsStateTask con actor " + actor);
        log.info("===> callBpmsStateTask con envio " + ew);
        log.info("===> callBpmsStateTask con piw " + piw);
        String containerId=idProyecto + "_" + versionProyecto;
        String service = bpmsUrlService + urlTaskState.replace("--containerid--",containerId)
        .replace("--taskid--",taskId).replace("--state--",state) + "?user="+ actor;
        String content=null;
        if( ew != null ) {
        	Gson gson = new Gson();
        	content = gson.toJson(ew);
        } else {
        	content="{}";
        	if( piw != null ) {
            	Gson gson = new Gson();
            	content = gson.toJson(piw);
            } else {
            	content="{}";
            }
        }
        
        log.info("===> callBpmsStateTask con servcio " + service);
        log.info("===> callBpmsStateTask con CONTENT " + content);
        Map<String,Object> response= ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,RestClientWrapper.CONTENT_TYPE_JSON,
        		authorization , content, RestClientWrapper.METHOD_PUT, 
     		   null, null, null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, 
     		   QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, 
     		   Boolean.FALSE,Boolean.TRUE, service,  String.class );
             log.info("===> REspuesta de servicio "  + response);
       return String.valueOf( response.get(ReRestClient.RETURN_STATUS) );     
    }
	
	/**
	 * 
	 * @param bpmsUrlService
	 * @param authorization
	 * @param group
	 * @param user
	 * @return
	 * @throws RelativeException
	 */
	public static TaskSummaryWrapper callBpmsFindPotencialOwnerTask(String bpmsUrlService, String authorization,String group, String user) 
			throws RelativeException{
        log.info("===> callBpmsFindPotencialOwnerTask con idProyecto " + group);
        log.info("===> callBpmsFindPotencialOwnerTask con versionProyecto " + user);
        StringBuilder param = new StringBuilder();
        param.append( "status" ).append("=").append("Ready")
        .append( "&groups" ).append("=").append(group)
        .append( "&page" ).append("=").append(0)
        .append( "&pageSize" ).append("=").append(1000)
        .append( "&sortOrder" ).append("=").append(Boolean.TRUE)
        .append( "&user" ).append("=").append(user);
        
        String service = bpmsUrlService + urlPotencialOwnerTask +"?" +param.toString();
        log.info("===> callBpmsFindPotencialOwnerTask con servcio " + service);
        Map<String,Object> response= ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,RestClientWrapper.CONTENT_TYPE_JSON,authorization , 
        		empty, RestClientWrapper.METHOD_GET, 
      		   null, null, null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, 
      		   QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, 
      		   Boolean.FALSE, Boolean.TRUE,service,  TaskSummaryWrapper.class );
        return (TaskSummaryWrapper)response.get(ReRestClient.RETURN_OBJECT);
    }
	
	/**
	 * 
	 * @param bpmsUrlService
	 * @param authorization
	 * @param group
	 * @param user
	 * @param processInstanceId
	 * @param status
	 * @return
	 * @throws RelativeException
	 */
	public static TaskItemWrapper callBpmsFindPotencialOwnerTaskByProcessInstanceAndStatus(
			String bpmsUrlService, String authorization,String group, String user,
			Long processInstanceId, EstadoBpmsEnum status) 
			throws RelativeException{
        log.info("===> callBpmsFindPotencialOwnerTaskByProcessInstanceAndStatus con idProyecto " + group);
        log.info("===> callBpmsFindPotencialOwnerTaskByProcessInstanceAndStatus con versionProyecto " + user);
        StringBuilder param = new StringBuilder();
        param.append( "status" ).append("=").append(status.toString())
        .append( "&groups" ).append("=").append(group)
        .append( "&page" ).append("=").append(0)
        .append( "&pageSize" ).append("=").append(1000)
        .append( "&sortOrder" ).append("=").append(Boolean.TRUE)
        .append( "&user" ).append("=").append(user);
        String service = bpmsUrlService + urlPotencialOwnerTask +"?" +param.toString();
        log.info("===> callBpmsFindPotencialOwnerTaskByProcessInstanceAndStatus con servcio " + service);
        Map<String,Object> response= ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,RestClientWrapper.CONTENT_TYPE_JSON,authorization , 
        		empty, RestClientWrapper.METHOD_GET, 
      		   null, null, null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, 
      		   QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, 
      		   Boolean.FALSE,Boolean.TRUE, service,  TaskSummaryWrapper.class );
        log.info("===> callBpmsFindPotencialOwnerTaskByProcessInstanceAndStatus parametroa generados " + response);
        TaskSummaryWrapper sw=(TaskSummaryWrapper)response.get(ReRestClient.RETURN_OBJECT);
        if( sw != null && sw.getTaskSummary() != null && !sw.getTaskSummary().isEmpty() ) {
        	List<TaskItemWrapper> tmp= sw.getTaskSummary().stream().filter( 
        			t->t.getTaskProcessInstanceId()==String.valueOf(processInstanceId) )
        			.collect(Collectors.toList());
        	if( tmp != null && !tmp.isEmpty() ) {
        		log.info("===> filtro por procesos y estado " );
        		return tmp.get(0);
        	} else {
        		return null;
        	}
        } 
        return null;
    }
	
	public static TaskSummaryWrapper callBpmsFindTasksByProcessInstance(String bpmsUrlService,String authorization, Long idProcessInstance) throws RelativeException{
        log.info("===>x callBpmsFindTasksByProcessInstance con idProyecto " + idProcessInstance);
        StringBuilder param = new StringBuilder();
        param.append( "status" ).append("=").append(EstadoBpmsEnum.Ready.toString())
        .append( "&status" ).append("=").append(EstadoBpmsEnum.Reserved.toString())
        .append( "&status" ).append("=").append(EstadoBpmsEnum.InProgress.toString());
        String service = bpmsUrlService + urlTasksInProcessInstances
        		.replace("--idprocessinstace--",String.valueOf(idProcessInstance )) + "?" + param.toString();
        Map<String,Object> response= ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,RestClientWrapper.CONTENT_TYPE_JSON,authorization , 
        		empty, RestClientWrapper.METHOD_GET, 
      		   null, null, null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, 
      		   QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, 
      		   Boolean.FALSE, Boolean.TRUE,service,  TaskSummaryWrapper.class );
        log.info("===> callBpmsFindTasksByProcessInstance parametroa generados " + response);
        return (TaskSummaryWrapper)response.get(ReRestClient.RETURN_OBJECT);
        
    }
	
	
	public static TaskItemWrapper callBpmsTaskById( String bpmsUrlService,String authorization, String  idProyecto, 
			String versionProyecto, String taskId ) throws RelativeException{
        log.info("===> callBpmTaskById con idProyecto " + taskId);
        String containerId=idProyecto + "_" + versionProyecto;
        StringBuilder param = new StringBuilder();
        param.append( "withInputData" ).append("=").append(Boolean.TRUE)
        .append( "&withOutputData" ).append("=").append(Boolean.TRUE);
        
        String service = bpmsUrlService + urlTaskVariables.replace("--containerid--",containerId)
        .replace("--taskid--",taskId)+"?"+param.toString();
        Map<String,Object> response= ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,RestClientWrapper.CONTENT_TYPE_JSON,authorization , 
        		empty, RestClientWrapper.METHOD_GET, 
      		   null, null, null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, 
      		   QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, 
      		   Boolean.FALSE,Boolean.TRUE, service,  TaskItemWrapper.class );
        log.info("===> callBpmsFindTasksByProcessInstance parametroa generados " + response);
        return (TaskItemWrapper)response.get(ReRestClient.RETURN_OBJECT);
    }
	
	
	public static TokenWrapper getToken(String amUrlService,String authorization) throws RelativeException{
        log.info("===>x getToken con authorization " + authorization);
        StringBuilder param = new StringBuilder();
        param.append( "grant_type" ).append("=").append("client_credentials");
        String service = amUrlService + urlToken + "?" + param.toString();
        Map<String,Object> response= ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,
        		RestClientWrapper.CONTENT_TYPE_X_WWW_FORM,authorization , 
        		empty, RestClientWrapper.METHOD_POST, 
      		   null, null, null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, 
      		   QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, 
      		   Boolean.FALSE,Boolean.TRUE, service,  TokenWrapper.class );
        log.info("===> getToken wraooer generados " + response);
        if( response != null && response.get("message") != null && response.get("message").toString().indexOf( "Unsuccessful" ) <0 ) {
        	TokenWrapper tmp= (TokenWrapper)response.get(ReRestClient.RETURN_OBJECT);
        	if( tmp.getError() != null ) {
        		throw new RelativeException( Constantes.ERROR_CODE_CUSTOM, "ERROR EN LA OBTENCION DEL TOKEN DE SEGURIDAD, POR FAVOR CONSULTE A SU ADMINISTRADOR " + tmp.getErrorDescription() );

        	} else if( Long.valueOf(tmp.getExpiresIn() ) <=60 ) {
        		throw new RelativeException( Constantes.ERROR_CODE_CUSTOM, "POR RAZONES DE SEGURIDAD SU TOKEN ESTA A PUNTO DE CADUCAR, POR FAVOR INTENTE EN 60 SEGUNDOS"  );

        	}
        	return tmp;
        } else {
        	throw new RelativeException( Constantes.ERROR_CODE_CUSTOM, "ERROR EN LA EJECUCION DE LA LLAMADA REST " + amUrlService + " INTENTE EN 30 SEGUNDOS, SI EL ERROR PERSISTE CONSULTE A SU ADMINISTRADOR"  );
        }
        
    }
	
	/**
	 * Metodo que reclama, inicia y termina tareas en el bpms
	 * @param idProcessGen
	 * @param authorizationBpm
	 * @param envio
	 * @throws RelativeException
	 */
	public static void callFullBpmsTaskHandle( String bpmsUrlService, String bpmProject, String bpmProjectVersion, 
			String actor, String idProcessGen, String authorizationBpm,  
			EnvioWrapper envio, ProcessInputWrapper piw) throws RelativeException {
		TaskSummaryWrapper tsw= callBpmsFindTasksByProcessInstance(bpmsUrlService, authorizationBpm,
        		Long.valueOf(idProcessGen));
        if( tsw != null && tsw.getTaskSummary()!=null && !tsw.getTaskSummary().isEmpty() ) {
        	log.info("===> obtuvo tareas primera tarea " + tsw.getTaskSummary().get(0).getTaskId());
        	Long status = Long.valueOf( callBpmsStateTask(bpmsUrlService, authorizationBpm, 
        		bpmProject, bpmProjectVersion, 
        		tsw.getTaskSummary().get(0).getTaskId() , EstadoBpmsEnum.claimed.toString(), 
        		actor != null && !actor.isEmpty() ?actor:QuskiOroConstantes.BPMS_DEFAULT_USER, null,null) );
        	log.info("!!!!!!!!!!!!!!!!!!===>  tomo tarea con status: " + status);
        	if( status >= 200 && status < 300 ) {
        		status = Long.valueOf( callBpmsStateTask(bpmsUrlService, authorizationBpm, 
		        		bpmProject, bpmProjectVersion, 
		        		tsw.getTaskSummary().get(0).getTaskId() , EstadoBpmsEnum.started.toString(), 
		        		actor != null && !actor.isEmpty()?actor:QuskiOroConstantes.BPMS_DEFAULT_USER, 
		        		null,piw) );
	        	log.info("!!!!!!!!!!!!!!!!!!===>  inicio tarea  con status: " + status);
	        	if( status >= 200 && status < 300 ) {
	        		status = Long.valueOf(callBpmsStateTask(bpmsUrlService, authorizationBpm, 
			        		bpmProject, bpmProjectVersion, 
			        		tsw.getTaskSummary().get(0).getTaskId() , EstadoBpmsEnum.completed.toString(), 
			        		actor != null && !actor.isEmpty()?actor:QuskiOroConstantes.BPMS_DEFAULT_USER, 
			        		envio,null) );
	        		log.info("===>  completo tarea con estatus: " + status);
	        		if( status >= 200 && status < 300 ) {
	        			log.info("===>  FINALIZACION TOMA, INICIO, Y CIERRE  DE PROCESO " + idProcessGen + " TAREA " + tsw.getTaskSummary().get(0).getTaskId()  );
	        		}else {
		        		throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR EN COMPLETAR TAREA PARA PROCESO " +idProcessGen 
		        				+ " TAREA " + tsw.getTaskSummary().get(0).getTaskId() + " y usuario " + actor  );
		        	}
	        	} else {
	        		throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR EN INICIO DE TAREA PARA PROCESO " +idProcessGen 
	        				+ " TAREA " + tsw.getTaskSummary().get(0).getTaskId() + " y usuario " + actor );
	        	}
        	} else {
        		throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR EN LA TOMA DE TAREA PARA PROCESO " +idProcessGen 
        				+ " TAREA " + tsw.getTaskSummary().get(0).getTaskId() + " y usuario " + actor );
        	}
        }
	}
	
	/**
	 * Metodo que aborta el proceso 
	 * @param bpmsUrlService
	 * @param authorization
	 * @param idProyecto
	 * @param versionProyecto
	 * @param processId
	 * @return
	 * @throws RelativeException
	 */
	public static TaskItemWrapper callBpmsAbortProcessById( String bpmsUrlService,String authorization, 
			String  idProyecto, String versionProyecto, String processId ) throws RelativeException{
        log.info("===> callBpmsAbortProcessById con processId " + processId);
        String containerId=idProyecto + "_" + versionProyecto;
        
        String service = bpmsUrlService + urlAbortProcess.replace("--containerid--",containerId)
        .replace("--processid--",processId);
        Map<String,Object> response= ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,RestClientWrapper.CONTENT_TYPE_JSON,authorization , 
        		empty, RestClientWrapper.METHOD_DELETE, 
      		   null, null, null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, 
      		   QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, 
      		   Boolean.FALSE,Boolean.TRUE, service,  TaskItemWrapper.class );
        log.info("===> callBpmsFindTasksByProcessInstance parametroa generados " + response);
        return (TaskItemWrapper)response.get(ReRestClient.RETURN_OBJECT);
    }

	/**
	 * Metodo que ejecuta la llamada al servicio rest para iniciar la ejecucion de un processo de negocio que ejecuta solo reglas drools
	 * @param bpmsUrlService
	 * @param authorization
	 * @param containerId
	 * @param bpmProcessId
	 * @param parameters
	 * @return Id de la instancia de proceso con la que se corrio ejecuto bre
	 * @throws RelativeException
	 */
	public static String callBpmsDroolProcesss(String bpmsUrlService, String authorization,
			String containerId, String bpmProcessId,  Map<String, String> parameters)  throws RelativeException{
        log.info("===> callBpmsDroolProcesss con containerId " + containerId);
        log.info("===> callBpmsDroolProcesss con bpmProcessId " + bpmProcessId);
        Gson gson = new Gson();
        String content = gson.toJson(parameters);
        log.info("=====> PIW "+ content);
        String service = bpmsUrlService + urlInstanceProcess.replace("--containerid--",containerId).replace("--bpmprocessid--",bpmProcessId);
        log.info("===> callBpmsDroolProcesss con servcio " + service);
        Map<String,Object> response= ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,RestClientWrapper.CONTENT_TYPE_JSON,authorization , content, RestClientWrapper.METHOD_POST, 
    		   null, null, null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, 
    		   Boolean.FALSE, Boolean.FALSE,service,  String.class );
        log.info("===> REspuesta de servicio "  + response);
        return String.valueOf( response.get(ReRestClient.RETURN_OBJECT) );
        //return String.valueOf(response.get(ReRestClient.RETURN_OBJECT));    
    }
	
	/**
	 * Retorna listado de variables generadas en la ejecucion de una instancia de proceso
	 * @param bpmsUrlService
	 * @param authorization
	 * @param instanceId
	 * @return Listado de objetos con el contenido de las variables
	 * @throws RelativeException
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<HashMap<String,Object>> getBpmsProcesssVariable(String bpmsUrlService, String authorization,String instanceId)  throws RelativeException{
        log.info("===> getBpmsProcesssVariable con instanceId " + instanceId);
        log.info("===> getBpmsProcesssVariable con authorization " + authorization);
        Gson gson = new Gson();
        String content = "{}";
        log.info("=====> PIW "+ content);
        String service = bpmsUrlService + urlQueryVariablesByInstanceId.replace("--instanceid--",instanceId);
        log.info("===> getBpmsProcesssVariable con servcio " + service);
        Map<String,Object> response= ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,RestClientWrapper.CONTENT_TYPE_JSON,authorization , 
        		content, RestClientWrapper.METHOD_GET, 
    		   null, null, null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, 
    		   Boolean.FALSE, Boolean.FALSE,service,  String.class );
        log.info("===> REspuesta de servicio "  + response);
        gson = new Gson();
		Type empMapType = new TypeToken<Map<String, Object>>() {}.getType();
        Map<String, Object> variables = gson.fromJson(String.valueOf(response.get(ReRestClient.RETURN_OBJECT)), empMapType);
		 //System.out.println("===>datos has " +variables);
        log.info("===>datos has variable " +variables.get("variable-instance")); 
        log.info("===>datos has variable " +variables.get("variable-instance").getClass().getName());
		return (ArrayList<HashMap<String, Object>>)variables.get("variable-instance");    
        //return String.valueOf(response.get(ReRestClient.RETURN_OBJECT));    
    }
	
}
