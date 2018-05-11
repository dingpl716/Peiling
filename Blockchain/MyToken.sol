pragma solidity 0.4.21;

contract owned {

    address private owner;

    function getOwner() public view returns (address currentOwner) {
        return owner;
    }

    function owned() public {
        owner = msg.sender;
    }

    modifier onlyOwner() {
        require(msg.sender == owner);
        _;
    }

    function changeOwner(address _to) public onlyOwner {
        owner = _to;
    }
}

contract MyToken is owned {
    string private name;
    string private symbol;
    uint128 private decimals;
    mapping (address => uint256) private balanceOf;
    uint256 private totalSupply;
    uint256 private buyPrice;
    uint256 private sellPrice;

    event TransferDone(address from, address to, uint256 amount);

    function getName() public view returns (string TokenName) {
        TokenName = name;
        return TokenName;
    }

    function getSymbol() public view returns (string TokenSymbol) {
        return symbol;
    }

    function getDecimals() public view returns (uint256 TokenDecimals) {
        return decimals;
    }

    function getBalance(address target) public view returns (uint256 CurrentBalance) {
        return balanceOf[target];
    }

    function getTotalSupply() public view returns (uint256 TotalSupply) {
        return totalSupply;
    }

    function getBuyPrice() public view returns (uint256 CurrentBuyPrice) {
        return buyPrice;
    }

    function getSellPrice() public view returns (uint256 CurrentSellPrice) {
        return sellPrice;
    }

    function setBuyPrice(uint256 _value) public onlyOwner {
        buyPrice = _value * 1 finney;
    }

    function setSellPrice(uint256 _value) public onlyOwner {
        sellPrice = _value * 1 finney;
    }

    function MyToken(string tokenName, string tokenSymbol, uint128 tokenDecimals, uint256 initialSupply) public {
        name = tokenName;
        symbol = tokenSymbol;
        decimals = tokenDecimals;
        totalSupply = initialSupply;
        buyPrice = 1000 finney;
        sellPrice = 995 finney;
    }

    function mint(uint256 amount) public onlyOwner {
        balanceOf[this] += amount;
    }

    function transfer(address _from, address _to, uint256 amount) public {
        require(amount > 0);
        require(_from != _to);
        require(balanceOf[_from] > amount);
        require(balanceOf[_to] + amount > balanceOf[_to]); // check overflow;

        balanceOf[_from] -= amount;
        balanceOf[_to] += amount;
        emit TransferDone(_from, _to, amount);
    }

    function buy() payable public returns (uint256 amount) {
        amount = msg.value / buyPrice;
        transfer(this, msg.sender, amount);

        return amount;
    }

    function sell(uint amount) public returns(uint256 payback) {
        transfer(msg.sender, this, amount);

        payback = amount * sellPrice;
        if (!msg.sender.send(payback)) {
            revert();
        } else {
            return payback;
        }
    }
}
