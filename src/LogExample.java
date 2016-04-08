import java.util.logging.*;

 public class LogExample {
	private final static Logger LOGGER = Logger.getLogger(LogExample.class.getName());
	
	public static void main(String[] args){
		
//		LOGGER.setLevel(Level.INFO); 

		
		System.out.println("hello world!");
		
		 // set the LogLevel to Severe, only severe Messages will be written
	    LOGGER.setLevel(Level.SEVERE);
	    LOGGER.severe("Info Log");
	    LOGGER.warning("Info Log");
	    LOGGER.info("Info Log");
	    LOGGER.finest("Really not important");

	    // set the LogLevel to Info, severe, warning and info will be written
	    // finest is still not written
	    LOGGER.setLevel(Level.INFO);
	    LOGGER.severe("Info Log");
	    LOGGER.warning("Info Log");
	    LOGGER.info("Info Log");
	    LOGGER.finest("Really not important");
	}

}
