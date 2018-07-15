package uk.ac.ncl.ethereum;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;
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

  public BlockChainEvent() throws IOException {
    CCCLogger.logInfo("new blockchain event");

  }

  public Web3j initWeb3() {
    return web3 = Web3j.build(new HttpService());  // http://localhost:8545/
  }

  public String getWeb3ClientVerion() throws IOException {
    web3ClientVersion = web3.web3ClientVersion().send();
    String clientVersion = web3ClientVersion.getWeb3ClientVersion();
    return clientVersion;

  }

  public Credentials getCredentials() throws IOException, CipherException {
    // TODO add password and keystore path for ethereum wallet
    String keyStorePath = "path/to/keystore";
    String walletPsw = "password";

    return WalletUtils.loadCredentials(walletPsw, keyStorePath);
  }

  public TransactionReceipt sendEther(String address) throws Exception {

    return transactionReceipt = Transfer.sendFunds(
        web3, getCredentials(), address,
        BigDecimal.valueOf(0.01), Convert.Unit.ETHER)
        .send();
  }


  //custom raw transaction
  public BigInteger getNextAvailableNonce(String address) throws IOException {
    EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(
        address, DefaultBlockParameterName.LATEST).send();
    return nonce = ethGetTransactionCount.getTransactionCount();

  }


}
