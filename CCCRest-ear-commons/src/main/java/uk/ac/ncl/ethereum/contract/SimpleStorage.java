package uk.ac.ncl.ethereum.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line
 * tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.4.0.
 */
public class SimpleStorage extends Contract {

  private static final String BINARY = "608060405234801561001057600080fd5b5060c28061001f6000396000f30060806040526004361060485763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166360fe47b18114604d578063a3f1bd35146064575b600080fd5b348015605857600080fd5b5060626004356088565b005b348015606f57600080fd5b506076608d565b60408051918252519081900360200190f35b600055565b600054600202905600a165627a7a72305820592f5f133325f51ec535764a4aef01e1b2460c08c3a33ed091f70c55cc3d0ad50029";

  public static final String FUNC_SET = "set";

  public static final String FUNC_GETDOUBLE = "getDouble";

  public SimpleStorage(String contractAddress, Web3j web3j, Credentials credentials,
      BigInteger gasPrice, BigInteger gasLimit) {
    super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
  }

  protected SimpleStorage(String contractAddress, Web3j web3j,
      TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
    super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
  }

  public RemoteCall<TransactionReceipt> set(BigInteger x) {
    final Function function = new Function(
        FUNC_SET,
        Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(x)),
        Collections.<TypeReference<?>>emptyList());
    return executeRemoteCallTransaction(function);
  }

  public RemoteCall<BigInteger> getDouble() {
    final Function function = new Function(FUNC_GETDOUBLE,
        Arrays.<Type>asList(),
        Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
        }));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }

  public static RemoteCall<SimpleStorage> deploy(Web3j web3j, Credentials credentials,
      BigInteger gasPrice, BigInteger gasLimit) {
    return deployRemoteCall(SimpleStorage.class, web3j, credentials, gasPrice, gasLimit, BINARY,
        "");
  }

  public static RemoteCall<SimpleStorage> deploy(Web3j web3j, TransactionManager transactionManager,
      BigInteger gasPrice, BigInteger gasLimit) {
    return deployRemoteCall(SimpleStorage.class, web3j, transactionManager, gasPrice, gasLimit,
        BINARY, "");
  }

  public static SimpleStorage load(String contractAddress, Web3j web3j, Credentials credentials,
      BigInteger gasPrice, BigInteger gasLimit) {
    return new SimpleStorage(contractAddress, web3j, credentials, gasPrice, gasLimit);
  }

  public static SimpleStorage load(String contractAddress, Web3j web3j,
      TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
    return new SimpleStorage(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
  }
}
