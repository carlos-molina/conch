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
public class CollectPaymentFull extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50610408806100206000396000f30060806040526004361061004b5763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416633f11ec0a8114610050578063b63e6ac3146100dd575b600080fd5b34801561005c57600080fd5b506100686004356100f5565b6040805160208082528351818301528351919283929083019185019080838360005b838110156100a257818101518382015260200161008a565b50505050905090810190601f1680156100cf5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156100e957600080fd5b50610068600435610163565b6060806060610103846101bd565b915061015b6101466040805190810160405280600981526020017f20526563656976656400000000000000000000000000000000000000000000008152506102e4565b61014f846102e4565b9063ffffffff61030a16565b949350505050565b6060806060610171846101bd565b915061015b61017f836102e4565b61014f6040805190810160405280601481526020017f596f7572207265636569707420666f72205478200000000000000000000000008152506102e4565b606060008082818515156102065760408051808201909152600181527f3000000000000000000000000000000000000000000000000000000000000000602082015294506102db565b8593505b831561022157600190920191600a8404935061020a565b826040519080825280601f01601f19166020018201604052801561024f578160200160208202803883390190505b5091505060001982015b85156102d75781516000198201917f01000000000000000000000000000000000000000000000000000000000000006030600a8a06010291849190811061029c57fe5b9060200101907effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916908160001a905350600a86049550610259565b8194505b50505050919050565b6102ec6103c5565b50604080518082019091528151815260209182019181019190915290565b606080600083600001518560000151016040519080825280601f01601f191660200182016040528015610347578160200160208202803883390190505b5091506020820190506103638186602001518760000151610381565b8451602085015185516103799284019190610381565b509392505050565b60005b602082106103a6578251845260209384019390920191601f1990910190610384565b50905182516020929092036101000a6000190180199091169116179052565b6040805180820190915260008082526020820152905600a165627a7a7230582080df43e40917b25b1823a763b2c4a2e4567c4f54a23ef28ee8637d8f272ec0730029";

    public static final String FUNC_SUBMITPAYMENT = "submitPayment";

    public static final String FUNC_GETRECEIPT = "getReceipt";

    protected CollectPaymentFull(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected CollectPaymentFull(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<String> submitPayment(BigInteger pay) {
        final Function function = new Function(FUNC_SUBMITPAYMENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(pay)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getReceipt(BigInteger trasactionNum) {
        final Function function = new Function(FUNC_GETRECEIPT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(trasactionNum)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static RemoteCall<CollectPaymentFull> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(CollectPaymentFull.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<CollectPaymentFull> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(CollectPaymentFull.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static CollectPaymentFull load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new CollectPaymentFull(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static CollectPaymentFull load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new CollectPaymentFull(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
