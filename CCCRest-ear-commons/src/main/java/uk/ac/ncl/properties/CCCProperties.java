package uk.ac.ncl.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;
import uk.ac.ncl.logging.CCCLogger;

public class CCCProperties {

  public static final String CCC_ETHEREUM_PROPERTIES = "ccc-ethereum.properties"; //"/Users/alpac/DEV/conch/CCCRest-ear-commons/src/main/resources/ccc-ethereum.properties";
  private Properties properties;

  public CCCProperties() {
    properties = new Properties();
  }

  public void loadProperties() {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    InputStream configurePropsStream = classLoader.getResourceAsStream(CCC_ETHEREUM_PROPERTIES);
    try {
      properties.load(configurePropsStream);
      CCCLogger.logInfo("loaded ccc properties");
    } catch (IOException e1) {
      CCCLogger.logInfo("ccc properties file does not exist " + e1.getMessage());
    }
  }

  public Properties getProperties() {
    return properties;
  }

}
