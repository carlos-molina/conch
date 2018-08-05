package uk.ac.ncl.ethereum;

import static org.web3j.tx.gas.DefaultGasProvider.GAS_LIMIT;
import static org.web3j.tx.gas.DefaultGasProvider.GAS_PRICE;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Properties;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import uk.ac.ncl.erop.BusinessOperation;
import uk.ac.ncl.ethereum.contract.CollectPayment;
import uk.ac.ncl.logging.CCCLogger;
import uk.ac.ncl.properties.CCCProperties;

/**
 * Experimental code for CCC and Ethereum integration
 */
public class BlockChainOperation extends BusinessOperation {

  private String paymentResult;
  private String name;
  /**
   * Instantiates a new blockchain operation.
   *
   * @param name the name
   */
  private BlockChainEvent bcEvent;
  private Web3j web3j;
  private Credentials credentials;
  private static CollectPayment contract;
  private Properties properties;
  private String contractAddress;

  /**
   * Instantiates a new Block chain operation.
   *
   * @param name the name
   */
  public BlockChainOperation(String name) {
    super(name);
    this.name = name;
  }

  /**
   * Initialize web3 library and ethereum credentials.
   *
   * @throws IOException the io exception
   * @throws CipherException the cipher exception
   */
  public void init() throws IOException, CipherException {
    loadConfiguration();
    contractAddress = properties.getProperty("contractAddress");
    String keyStorePath = properties.getProperty("keyStoreFilePath");
    CCCLogger.logInfo("keyStorePath: " + keyStorePath);
    String walletPSW = properties.getProperty("accountPassword");

    CCCLogger.logInfo("-- initialize BlockChain Event --");
    bcEvent = new BlockChainEvent();

    CCCLogger.logInfo("-- initialize Web3 client --");
    web3j = bcEvent.initWeb3(keyStorePath, walletPSW);

    CCCLogger.logInfo("-- getWeb3ClientVersion --" + bcEvent.getWeb3ClientVerion());

    CCCLogger.logInfo("-- getCredentials --");
    credentials = bcEvent.getCredentials();

    // load contract
    contract = CollectPayment.load(contractAddress, web3j, credentials, GAS_PRICE, GAS_LIMIT);
  }

  private void loadConfiguration() {
    CCCLogger.logInfo("load ethereum properties");
    CCCProperties cccProperties = new CCCProperties();
    cccProperties.loadProperties();
    properties = cccProperties.getProperties();
  }


  /**
   * Submits a payment for the CollectPayment smart contract deployed on Ethereum.
   *
   * @throws Exception the exception
   */
  public void submitPayment() {
    CCCLogger.logInfo("submit payment for contract: ");
    BigInteger amountToPay = BigInteger.TEN;
    try {
      paymentResult = contract.submitPayment(amountToPay).send();
    } catch (Exception e) {
      CCCLogger.logError("payment error Result: " + e.getMessage());
    }
  }

  /**
   * Returns a voucher for the CollectPayment smart contract deployed on Ethereum.
   *
   * @throws Exception the exception
   */
  public void getVoucher() throws Exception {
    CCCLogger.logInfo("get voucher for contract: ");
    BigInteger transactionNo = BigInteger.ONE;
    String receipt = contract.getReceipt(transactionNo).send();
    CCCLogger.logInfo("receipt: " + receipt);
  }

}
