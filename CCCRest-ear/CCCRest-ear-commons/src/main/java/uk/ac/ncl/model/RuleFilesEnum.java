/**
 *
 */
package uk.ac.ncl.model;

/**
 * The Enum RuleFilesEnum holds all the file path for each sample contract.
 *
 */
public enum RuleFilesEnum {

	TEST_RULE(System.getProperty("jboss.server.base.dir") + "/drools/upload/" + "TestRule.drl"),
	TESTING_CONTRACT(System.getProperty("jboss.server.base.dir") + "/drools/upload/" + "TestingContract.drl"),
	SIMPLE_CONTRACT(System.getProperty("jboss.server.base.dir") + "/drools/upload/" + "SimpleContract.drl"),
	EXTREMELY_SIMPLE_CONTRACT(System.getProperty("jboss.server.base.dir") + "/drools/upload/" + "ExtremelySimpleContract.drl"),
	EXAMPLE_CONTRACT(System.getProperty("jboss.server.base.dir") + "/drools/upload/" + "ExampleContract.drl"),
	BUYER_STORE_CONTRACT(System.getProperty("jboss.server.base.dir") + "/drools/upload/" + "BuyerStoreContractEx.drl");


	private String ruleFilePath;
	private String RULES_FOLDER_PATH = "/drools/upload";

	/**
	 * Instantiates a new rule files enum.
	 *
	 * @param ruleFilePath
	 *            the rule file path
	 */
	RuleFilesEnum(String ruleFilePath) {
		this.ruleFilePath = ruleFilePath;

	}

	/**
	 * Returns the rules folder path. Server's base directory path + rules folder
	 * path "/drools/upload"
	 *
	 * @return the rules folder path
	 */
	public String getRulesFolderPath() {
		return System.getProperty("jboss.server.base.dir") + RULES_FOLDER_PATH;

	}

	/**
	 * Returns the rule file path.
	 *
	 * @return the rule file path
	 */
	public String getRuleFilePath() {
		return ruleFilePath;
	}

}
