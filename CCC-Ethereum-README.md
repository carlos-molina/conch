# 1. Installation and creating new account for ethereum v1.8.13-stable

1. follow instructions to install geth
	- [github.com/ethereum/go-ethereum/wiki/Building-Ethereum](https://github.com/ethereum/go-ethereum/wiki/Building-Ethereum)
2. follow instructions to create a new account in rinkeby testnet using:
	- geth —rinkeby account new
	- add account password to accountPassword field in ccc-ethereum.properties file (conch/CCCRest-ear-commons/src/main/resources/ccc-ethereum.properties)
3. show the list of current accounts in rinkeby testnet to verify the account is created and where the keystore file is stored:
	- geth —rinkeby acount list
		- copy address of account created e.g. 0x9a6944f1b5f3368c53966415dc6ef66f3a7338d3
		- add keystore file path to keyStoreFilePath field in ccc-ethereum.properties file  (conch/CCCRest-ear-commons/src/main/resources/ccc-ethereum.properties)




---

# 2. Install metamask

1. install metamask browser add-on from [metamask.io](https://metamask.io/)
2. create account for metamask
3. choose rinkeby testnet
4. import in metamask the account created previously using geth
	- use geth —rinkeby acount list  to find where the keystore file is stored
5. buy ether using rinkeby faucet [www.rinkeby.io](https://www.rinkeby.io/#faucet)




---

# 4. Compile contract and create wrapper for deployed contract

1. install solc compiler: ([solidity.readthedocs.io/en/v0.4.24/installing-solidity.html](http://solidity.readthedocs.io/en/v0.4.24/installing-solidity.html))
	- npm install -g solc
2. install web3j command-line tool v3.5.0
	- [docs.web3j.io/command_line.html](https://docs.web3j.io/command_line.html)
3. go to solidity-contracts folder
4. compile collectPayment.sol using the solc compiler:
	- solc collectPayment.sol --bin --abi --optimize -o .
5. execute web3j command-line tool to create java wrappers for collectPayment solidity contract
	- web3j solidity generate collectPayment.bin collectPayment.abi -p uk.ac.ncl.ethereum.contract -o .
6. copy generated contract folder to conch/CCCRest-ear-commons/src/main/java/uk/ac/ncl/ethereum




---

# 3. Deploy collectPayment.sol contract in Rinkeby testnet

1. use remix ide to compile and deploy the collectPayment.sol smart contract
	- [remix.ethereum.org](http://remix.ethereum.org)
2. if there are no errors during compilation go to Run tab
3. choose Injected web3 for “Environment” (it will use metamask to deploy the smart contract)
4. make sure the correct account is selected in “Account”
5. Click deploy button
6. click submit in metamask to deploy the collectPayment.sol smart contract to Rinkeby testnet
7. add contract address in ccc-ethereum.properties file contractAddress field




---

# 5. Execute smart contracts in CCC and Ethereum

1. start jboss EAP server:
	- ./run.sh
2. deploy a new version of the CCC with the included java wrapper for the collectPayment.sol smart contract
	- mvn clean package jboss-as:deploy
3. start geth using rinkeby testnet and enabling json-rpc api:
	- geth --rinkeby   --syncmode "fast" --cache=1024 --rpc console
4. go to  CCCRestClient folder to start the client that sends the sequences to the CCC
	- ./runClient.sh


