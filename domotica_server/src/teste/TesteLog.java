package teste;

import org.apache.log4j.Logger;

public class TesteLog {
	
	final static Logger logger = Logger.getLogger(TesteLog.class);
	
	
	public static void main(String[] args) {
		
		TesteLog obj = new TesteLog();
		obj.runMe("mkyong");
		
	}
	
	private void runMe(String parameter){
		
		if(logger.isDebugEnabled()){
			logger.debug("This is debug : ");
		}
		
		if(logger.isInfoEnabled()){
			logger.info("This is info : " + parameter);
		}
		
		logger.warn("This is warn : " + parameter);
		logger.error("This is error : " + parameter);
		logger.fatal("This is fatal : " + parameter);
		
	}

}
