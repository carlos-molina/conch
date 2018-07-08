
/*
 *
 * Programmer: carlos molina-jimenez Carlos.Molina@cl.cam.ac.uk
 * date:       29 Jun 2018, Computer Lab, University of Cambridge
 *
 * Program: storeData.sol shows how a smart contract in solidity can
 *          be requested to store a piece an unsigned integer
 *          and return after doubling it. 
 *          For ex if input to the function set is 25, them
 *          the getDouble function returns 50
 *
 * Source:
 * http://solidity.readthedocs.io
 * /en/v0.4.21/introduction-to-smart-contracts.html#
 *
 * Compilation and execution:
 * I compiled and run successfully online in
 * remix.ethereum.org
 *
 */

/* 
 * Program: greeter.sol is a helloworld in solidity
 * Programmer: carlos Molina, 29 Jun 2018, Computer Lab, Cambridge
 */
pragma solidity ^0.4.0;
contract SimpleStorage {
    uint storedData;   //declares a state variable of type
                       //uint (unsigned integer of 256 bits)

    function set(uint x) public { // function to alter the data
        storedData = x;
    }

    function getDouble() public constant returns (uint) { /// func to retrive the data
        return storedData*2;
    }
}


