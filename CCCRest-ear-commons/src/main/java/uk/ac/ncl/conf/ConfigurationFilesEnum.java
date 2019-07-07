/** *
 */
package uk.ac.ncl.conf;

/**
 * The Enum ConfigurationFilesEnum holds all the file paths and configuration files for CCC
 */
public enum ConfigurationFilesEnum {

  CONTRACTS_PATH(System.getProperty("jboss.server.base.dir"));

  private static String RULES_FOLDER_PATH = "/drools/upload";
  private String ruleFilePath;

  /**
   * Instantiates a new rule files enum.
   *
   * @param ruleFilePath the rule file path
   */
  ConfigurationFilesEnum(String ruleFilePath) {
    this.ruleFilePath = ruleFilePath;

  }

  /**
   * Returns the rules folder path. Server's base directory path + rules folder path
   * "/drools/upload"
   *
   * @return the rules folder path
   */
  public String getRulesFolderPath() {
    return CONTRACTS_PATH + RULES_FOLDER_PATH;

  }

  /**
   * Returns the rule file path.
   *
   * @return the rule file path
   */
  public String getRuleFilePath() {
    return ruleFilePath;
  }

  /**
   * Gets the configuration file path.
   *
   * @return the configuration file path
   */
  public String getConfigurationFilePath() {
    return ruleFilePath;
  }

}
