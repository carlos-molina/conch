package uk.ac.ncl.ethereum.contract;

import java.math.BigInteger;
import java.util.Arrays;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.4.0.
 */
public class CollectPayment extends Contract {
    private static final String BINARY = "6080604052600560005534801561001557600080fd5b5061018d806100256000396000f30060806040526004361061004b5763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416633f11ec0a811461005057806357d945c9146100dd575b600080fd5b34801561005c57600080fd5b506100686004356100f2565b6040805160208082528351818301528351919283929083019185019080838360005b838110156100a257818101518382015260200161008a565b50505050905090810190601f1680156100cf5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156100e957600080fd5b5061006861012a565b5060408051808201909152601d81527f5061796d656e74207375636365737366756c6c79207265636569766564000000602082015290565b60408051808201909152601481527f4865726520697320796f757220526563656970740000000000000000000000006020820152905600a165627a7a7230582016e93a9b3a9091c0693d4a0d72b97c4606ef019b73adb3775fa9583ef1be9a3a0029";

    public static final String FUNC_SUBMITPAYMENT = "submitPayment";

    public static final String FUNC_GETRECEIPT = "getReceipt";

    public CollectPayment(String contractAddress, Web3j web3j, Credentials credentials,
        BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected CollectPayment(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<String> submitPayment(BigInteger pay) {
        final Function function = new Function(FUNC_SUBMITPAYMENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(pay)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getReceipt() {
        final Function function = new Function(FUNC_GETRECEIPT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static RemoteCall<CollectPayment> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(CollectPayment.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<CollectPayment> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(CollectPayment.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static CollectPayment load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new CollectPayment(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static CollectPayment load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new CollectPayment(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
