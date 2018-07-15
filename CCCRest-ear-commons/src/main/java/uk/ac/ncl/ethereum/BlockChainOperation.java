package uk.ac.ncl.ethereum;

import java.io.IOException;
import java.math.BigInteger;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import uk.ac.ncl.erop.BusinessOperation;
import uk.ac.ncl.ethereum.contract.CollectPayment;
import uk.ac.ncl.ethereum.contract.CollectPaymentFull;
import uk.ac.ncl.logging.CCCLogger;

/**
 * Experimental code for CCC-blockchain integration
 */
public class BlockChainOperation extends BusinessOperation {
  /**
   * Instantiates a new blockchain operation.
   *
   * @param name the name
   */
  private BlockChainEvent bcEvent;
  private CollectPayment collectPayment;
  private Web3j web3j;
  private Credentials credentials;
  private CollectPaymentFull contract;

  /**
   * Instantiates a new Block chain operation.
   *
   * @param name the name
   */
  public BlockChainOperation(String name) {
    super(name);
  }

  /**
   * Initialize web3 library and ethereum credentials.
   *
   * @throws IOException the io exception
   * @throws CipherException the cipher exception
   */
  public void init() throws IOException, CipherException {
    CCCLogger.logInfo("-- initialize BlockChain Event --");
    bcEvent = new BlockChainEvent();
    CCCLogger.logInfo("-- initialize Web3 client --");
    web3j = bcEvent.initWeb3();
    CCCLogger.logInfo("-- getWeb3ClientVersion --" + bcEvent.getWeb3ClientVerion());
    CCCLogger.logInfo("-- getCredentials --");
    credentials = bcEvent.getCredentials();
    /** TODO add contract address */
    String contractAddress = "0xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";

    /** TODO add gas price and gasLimit */
    BigInteger gasPrice = new BigInteger("1");
    BigInteger gasLimit = new BigInteger("321635");

    contract = CollectPaymentFull
        .load(contractAddress, web3j, credentials, gasPrice, gasLimit);
  }

  /**
   * Send sample blockchain events.
   *
   * @param name of the event
   * @throws Exception the exception
   */
  public void sendBCEvent(String name) throws Exception {
    // TODO add address to send ether to
    TransactionReceipt transactionReceipt = bcEvent.sendEther("0xaddress");
    CCCLogger.logInfo("transaction: " + transactionReceipt);
  }

  /**
   * Submits a payment for the CollectPayment smart contract deployed on Ethereum.
   *
   * @throws Exception the exception
   */
  public void submitPayment() throws Exception {
    CCCLogger.logInfo("submit payment for contract: ");
    BigInteger amountToPay = BigInteger.TEN;
    String payment = contract.submitPayment(amountToPay).send();
    CCCLogger.logInfo("payment: " + payment);
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
