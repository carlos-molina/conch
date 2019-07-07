
/*
 * Program: collectPayment.sol it is a basic solidity prog that
 *           is able to receive a payment. It includes two functions:
 *           1) submitPayment: receives a message with an uint that
 *           represents a payment. It returns a string:
 *           "Payment received" + input pay converting it into str
 *           2) getReceipt:  receives a transaction number. It
 *           It returns a string: "Your receipt for Tx " + input transactionNum
 *           after converting it into str
 * Programmer: Carlos Molina, 14 Jul 2018, Computer Lab, Cambridge
 */

pragma solidity ^0.4.4;

import "strings.sol";

contract collectPayment {
    using strings for *;
  
    
    function uint2str(uint i) internal pure returns (string){ 
    /// Takes an uint and return it convertedinto string  
    /// source: https://github.com/oraclize/ethereum-api/blob/master/oraclizeAPI_0.5.sol
        if (i == 0) return "0"; 
        uint j = i; 
        uint length; 
        while (j != 0){ length++; j /= 10; } 
        bytes memory bstr = new bytes(length); 
        uint k = length - 1; 
        while (i != 0){ bstr[k--] = byte(48 + i % 10); i /= 10; } 
        return string(bstr); 
    }
    
    
    function submitPayment(uint pay) public constant returns (string) { 
    /// func to submit payment. It returns a string:
    /// "Payment received" + input pay convertion into str
      var s= uint2str(pay);
      var new_str= s.toSlice().concat(" Received".toSlice());
      return new_str;
    }

  
    function getReceipt(uint trasactionNum) public constant returns (string) { 
    /// func to get a receipt of a given transaction. It returns a string:
    /// "Your receipt for Tx " + input transactionNum after convertion into str
      var s= uint2str(trasactionNum);
      var new_str= "Your receipt for Tx ".toSlice().concat(s.toSlice());
      return new_str;
    }
  }  
  

