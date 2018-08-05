package uk.ac.ncl.ethereum;

import java.io.IOException;
import java.math.BigInteger;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import uk.ac.ncl.logging.CCCLogger;

/**
 * Experimental wrapper code for Ethereum using Web3j
 */
public class BlockChainEvent {

  private Web3ClientVersion web3ClientVersion;
  private Web3j web3;
  private TransactionReceipt transactionReceipt;
  private BigInteger nonce;
  private RawTransaction rawTransaction;
  private String keyStorePath;
  private String walletPSW;

  public BlockChainEvent() throws IOException {
    CCCLogger.logInfo("new blockchain event");
  }

  public Web3j initWeb3(String keyStorePath, String walletPSW) {
    this.keyStorePath = keyStorePath;
    this.walletPSW = walletPSW;
    return web3 = Web3j.build(new HttpService());  // http://localhost:8545/
  }

  public String getWeb3ClientVerion() throws IOException {
    web3ClientVersion = web3.web3ClientVersion().send();
    String clientVersion = web3ClientVersion.getWeb3ClientVersion();
    return clientVersion;
  }

  public Credentials getCredentials() throws IOException, CipherException {
    return WalletUtils.loadCredentials(walletPSW, keyStorePath);
  }

}
