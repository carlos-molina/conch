## conch
The conch repository hosts the Contract Compliance 
Checkert (CCC) tool that we have implemented for monitoring
and enforcing smart contracts at run time.

* **Off blockchain architecture:** In the implementation
  hosted in conch, the CCC is expected to be deployed as 
  a web server
  on a Trusted Third Party, thus it is a **centralised**
  smart contract enforcer.

* **Hybrid architecture:** we have also implemented a hybrid
  architecture where the CCC playing the role of the
  off blockchain component integrates with the
  Rinkeby Ethereum Network. This implementation is
  hosted in the [TECOmate repository](https://github.com/carlos-molina/TECOmate)
  and discussed in the
  [Implementation of Smart Contracts Using Hybrid Architectures with 
   On- and Off-Blockchain Components](https://arxiv.org/pdf/1808.00093.pdf 
  "implementation paper")) technical report.
     

#### Smart contracts
An executable contract (also called a smart contract) 
is (at least under my personal definition) a conventional 
contract that can be converted into executable code, 
executed and enforced programmatically at run-time.

#### Smart contracts and blockchain
The main feature of a smart contract is that its
executable and capable of monitoring and enforcing 
contractual commitments (rights, obligations and
prohibitions) at run--time. 

A smart contract can be implemented
using a centralised or a distributed approach. The
distributed approach involves the achievement of
consensus between several remote parties,
consequently, the implementation of
distributed smart contracts is significantly
simplified by the support of 
middleware services offered by blockchain 
technology.

The current implementation of conch follows a 
centralised approach and therefore, does not
rely on blockchain.


